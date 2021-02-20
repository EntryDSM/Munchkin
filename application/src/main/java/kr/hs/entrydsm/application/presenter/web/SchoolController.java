package kr.hs.entrydsm.application.presenter.web;

import kr.hs.entrydsm.application.domain.entity.School;
import kr.hs.entrydsm.application.domain.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class SchoolController {

    private final SchoolRepository schoolRepository;

    @GetMapping("/schools")
    public Page<School> searchSchools(@RequestParam @NotBlank String name, Pageable pageable) {
        return schoolRepository.findByInformationContains(name, pageable);
    }
}