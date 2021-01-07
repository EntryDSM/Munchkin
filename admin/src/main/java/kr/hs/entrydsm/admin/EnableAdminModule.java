package kr.hs.entrydsm.admin;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(basePackageClasses = AdminModuleConfiguration.class)
@Import(AdminModuleConfiguration.class)
public @interface EnableAdminModule {
}
