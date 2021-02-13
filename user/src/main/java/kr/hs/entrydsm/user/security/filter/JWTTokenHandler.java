package kr.hs.entrydsm.user.security.filter;

import kr.hs.entrydsm.common.context.auth.token.JWTRequired;
import kr.hs.entrydsm.common.context.auth.token.JWTTokenProvider;
import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JWTTokenHandler implements HandlerInterceptor {

    private final JWTTokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (response.getStatus() == 404) {
            throw new MunchkinException(ErrorCode.NOT_FOUND);
        }

        boolean jwtRequired = false;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> handlerClass = handlerMethod.getBeanType();
        if (handlerClass.getDeclaredAnnotation(JWTRequired.class) != null) {
            jwtRequired = true;
        }
        if (handlerMethod.hasMethodAnnotation(JWTRequired.class)) {
            jwtRequired = true;
        }
        if (jwtRequired) {
            String token = tokenProvider.resolveToken(request);
            if (tokenProvider.validateToken(token)) {
                return true;
            } else {
                throw new MunchkinException(ErrorCode.UNAUTHENTICATED);
            }
        }
        return true;
    }

}
