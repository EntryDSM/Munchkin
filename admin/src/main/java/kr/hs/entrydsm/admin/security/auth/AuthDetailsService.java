package kr.hs.entrydsm.admin.security.auth;

import kr.hs.entrydsm.admin.domain.repository.AdminRepository;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public AuthDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return adminRepository.findById(Integer.parseInt(id))
                .map(AuthDetails::new)
                .orElseThrow(AdminNotFoundException::new);
    }

}
