package kr.hs.entrydsm.application.presenter.web;

import kr.hs.entrydsm.application.entity.School;
import kr.hs.entrydsm.application.entity.SchoolRepository;
import kr.hs.entrydsm.common.context.auth.time.ScheduleInRequired;
import kr.hs.entrydsm.common.context.auth.token.JWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Published
@RestController
@RequiredArgsConstructor
@JWTRequired
@ScheduleInRequired
@RequestMapping("/application")
public class SchoolController {

    private final SchoolRepository schoolRepository;

    @GetMapping("/schools")
    public Page<School> searchSchools(@RequestParam @NotBlank String name, Pageable pageable) {
        return schoolRepository.findByInformationContains(name, pageable);
    }
}
