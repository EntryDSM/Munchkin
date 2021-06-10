package kr.hs.entrydsm.main.configuration.module;

import kr.hs.entrydsm.application.EnableApplicationModule;
import kr.hs.entrydsm.main.configuration.DependentModule;

@EnableApplicationModule
@DependentModule(ScoreConfiguration.class)
public class ApplicationConfiguration {
}
