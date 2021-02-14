package kr.hs.entrydsm.common.context.auth.manager;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager {

    public long getUserReceiptCode() {
        SecurityContext securityContext = getSecurityContext();
        Authentication authentication = securityContext.getAuthentication();
        return (long) authentication.getPrincipal();
    }

    private SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }

}
