package kr.hs.entrydsm.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(basePackageClasses = ApplicationModuleConfiguration.class)
@Import(ApplicationModuleConfiguration.class)
public @interface EnableApplicationModule {
}
