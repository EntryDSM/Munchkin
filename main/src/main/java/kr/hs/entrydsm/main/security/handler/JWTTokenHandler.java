package kr.hs.entrydsm.main.security.handler;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.infrastructure.database.AdminRepositoryManager;
import kr.hs.entrydsm.common.context.auth.token.AdminJWTRequired;
import kr.hs.entrydsm.common.context.auth.token.JWTRequired;
import kr.hs.entrydsm.common.context.auth.token.JWTTokenProvider;
import kr.hs.entrydsm.common.context.auth.token.RefreshRequired;
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

@Component
@RequiredArgsConstructor
public class JWTTokenHandler implements HandlerInterceptor {

    private final JWTTokenProvider tokenProvider;
    private final UserRepositoryManager userRepositoryManager;
    private final AdminRepositoryManager adminRepositoryManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (response.getStatus() == 404) {
            throw new MunchkinException(ErrorCode.NOT_FOUND);
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> handlerClass = handlerMethod.getBeanType();

        boolean jwtRequired = false;
        if (handlerMethod.hasMethodAnnotation(JWTRequired.class) || handlerClass.getDeclaredAnnotation(JWTRequired.class) != null) {
            jwtRequired = true;
        }
        if (jwtRequired) {
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

        boolean adminJwtRequired = false;
        if (handlerMethod.hasMethodAnnotation(AdminJWTRequired.class) || handlerClass.getDeclaredAnnotation(AdminJWTRequired.class) != null) {
            adminJwtRequired = true;
        }
        if (adminJwtRequired) {
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

        boolean refreshRequired = false;
        if (handlerMethod.hasMethodAnnotation(RefreshRequired.class) || handlerClass.getDeclaredAnnotation(RefreshRequired.class) != null) {
            refreshRequired = true;
        }
        if (refreshRequired) {
            String token = tokenProvider.resolveRefreshToken(request);
            if (tokenProvider.validateToken(token) && tokenProvider.isRefreshToken(token)) {
                return true;
            }
            throw new MunchkinException(ErrorCode.INVALID_TOKEN);
        }
        return true;
    }

}
