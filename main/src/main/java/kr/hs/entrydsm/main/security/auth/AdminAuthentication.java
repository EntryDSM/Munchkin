package kr.hs.entrydsm.main.security.auth;

import kr.hs.entrydsm.admin.domain.entity.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class AdminAuthentication implements Authentication {

    private final Admin admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Object getCredentials() {
        return admin.getName();
    }

    @Override
    public Object getDetails() {
        return admin;
    }

    @Override
    public Object getPrincipal() {
        return admin.getId();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return admin.getName();
    }
}
