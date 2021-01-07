package kr.hs.entrydsm.main.configuration.module;

import kr.hs.entrydsm.main.configuration.DependentModule;
import kr.hs.entrydsm.school.EnableSchoolModule;

@EnableSchoolModule
@DependentModule(ApplicationConfiguration.class)
public class SchoolConfiguration {
}
