package kr.hs.entrydsm.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(basePackageClasses = ApplicationModuleConfiguration.class)
@Import(ApplicationModuleConfiguration.class)
@EnableJpaAuditing
public @interface EnableApplicationModule {
}
