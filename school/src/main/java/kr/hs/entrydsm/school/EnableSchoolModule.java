package kr.hs.entrydsm.school;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(basePackageClasses = SchoolModuleConfiguration.class)
@Import(SchoolModuleConfiguration.class)
public @interface EnableSchoolModule {
}
