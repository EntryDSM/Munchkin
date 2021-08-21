package kr.hs.entrydsm.common.context.auth.manager;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationManager {

    public static long getUserReceiptCode() {
        return (long) getAuthentication().getPrincipal();
    }

    public static String getAdminId() {
        return (String) getAuthentication().getPrincipal();
    }

    private static Authentication getAuthentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication();
    }

}
