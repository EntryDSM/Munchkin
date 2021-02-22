package kr.hs.entrydsm.application.domain.repository;

import kr.hs.entrydsm.application.domain.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SchoolRepository {

    Page<School> findByInformationContains(String information, Pageable pageable);

    Optional<School> findByCode(String code);
}
