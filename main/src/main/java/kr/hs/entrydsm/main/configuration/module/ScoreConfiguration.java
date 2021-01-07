package kr.hs.entrydsm.main.configuration.module;

import kr.hs.entrydsm.main.configuration.DependentModule;
import kr.hs.entrydsm.score.EnableScoreModule;

@EnableScoreModule
@DependentModule(ApplicationConfiguration.class)
public class ScoreConfiguration {
}
