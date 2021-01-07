package kr.hs.entrydsm.score;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(basePackageClasses = ScoreModuleConfiguration.class)
@Import(ScoreModuleConfiguration.class)
public @interface EnableScoreModule {
}
