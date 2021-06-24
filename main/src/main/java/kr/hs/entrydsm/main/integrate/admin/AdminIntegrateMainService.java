package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.integrate.main.MainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminIntegrateMainService implements MainRepository {

    @Override
    public void deleteAll() {

    }

}
