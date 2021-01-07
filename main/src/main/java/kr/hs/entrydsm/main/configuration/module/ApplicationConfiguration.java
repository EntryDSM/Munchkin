package kr.hs.entrydsm.main.configuration.module;

import kr.hs.entrydsm.application.EnableApplicationModule;
import kr.hs.entrydsm.main.configuration.DependentModule;

@EnableApplicationModule
@DependentModule(UserConfiguration.class)
public class ApplicationConfiguration {
}
