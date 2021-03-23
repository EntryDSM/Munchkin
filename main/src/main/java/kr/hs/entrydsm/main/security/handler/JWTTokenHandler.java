package kr.hs.entrydsm.main.security.handler;

import kr.hs.entrydsm.admin.entity.Admin;
import kr.hs.entrydsm.admin.infrastructure.database.AdminRepositoryManager;
import kr.hs.entrydsm.common.context.auth.token.AdminJWTRequired;
import kr.hs.entrydsm.common.context.auth.token.JWTRequired;
import kr.hs.entrydsm.common.context.auth.token.JWTTokenProvider;
import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;
import kr.hs.entrydsm.main.security.auth.AdminAuthentication;
import kr.hs.entrydsm.main.security.auth.UserAuthentication;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.infrastructure.database.UserRepositoryManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

@Component
@RequiredArgsConstructor
public class JWTTokenHandler implements HandlerInterceptor {

    private final JWTTokenProvider tokenProvider;
    private final UserRepositoryManager userRepositoryManager;
    private final AdminRepositoryManager adminRepositoryManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod))
            return true;

        if (response.getStatus() == 404)
            throw new MunchkinException(ErrorCode.NOT_FOUND);

        if (hasJWTRequired((HandlerMethod) handler))
            return checkJWTRequired(request);
        if (hasAdminJWTRequired((HandlerMethod) handler))
            return checkAdminJWTRequired(request);

        return true;
    }

    private boolean checkJWTRequired(HttpServletRequest request) {
        String token = tokenProvider.resolveAccessToken(request);
        if (tokenProvider.validateToken(token)) {
            long receiptCode = tokenProvider.parseAccessToken(token);
            User user = userRepositoryManager.findByReceiptCode(receiptCode)
                    .orElseThrow(() -> new MunchkinException(ErrorCode.NOT_FOUND));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = new UserAuthentication(user);
            securityContext.setAuthentication(authentication);
            return true;
        }
        throw new MunchkinException(ErrorCode.UNAUTHENTICATED);
    }

    private boolean checkAdminJWTRequired(HttpServletRequest request) {
        String token = tokenProvider.resolveAccessToken(request);
        if (tokenProvider.validateToken(token)) {
            String adminId = tokenProvider.parseAdminToken(token);
            Admin admin = adminRepositoryManager.findById(adminId)
                    .orElseThrow(() -> new MunchkinException(ErrorCode.NOT_FOUND));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = new AdminAuthentication(admin);
            securityContext.setAuthentication(authentication);
            return true;
        }
        throw new MunchkinException(ErrorCode.UNAUTHENTICATED);
    }

    private boolean hasJWTRequired(HandlerMethod handlerMethod) {
        return hasAnnotationRequired(handlerMethod, JWTRequired.class);
    }

    private boolean hasAdminJWTRequired(HandlerMethod handlerMethod) {
        return hasAnnotationRequired(handlerMethod, AdminJWTRequired.class);
    }

    private boolean hasAnnotationRequired(HandlerMethod handlerMethod, Class<? extends Annotation> annotationClass) {
        Class<?> handlerClass = handlerMethod.getBeanType();
        return handlerMethod.hasMethodAnnotation(annotationClass) ||
                handlerClass.getDeclaredAnnotation(annotationClass) != null;
    }

}
