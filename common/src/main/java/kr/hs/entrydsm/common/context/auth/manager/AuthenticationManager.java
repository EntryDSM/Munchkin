package kr.hs.entrydsm.common.context.auth.manager;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager {

    public long getUserReceiptCode() {
        return Long.parseLong((String) getAuthentication().getPrincipal());
    }

    public String getAdminId() {
        return (String) getAuthentication().getPrincipal();
    }

    private Authentication getAuthentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication();
    }

}
