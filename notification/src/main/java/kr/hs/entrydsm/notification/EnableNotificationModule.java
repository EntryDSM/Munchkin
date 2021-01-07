package kr.hs.entrydsm.notification;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(basePackageClasses = NotificationModuleConfiguration.class)
@Import(NotificationModuleConfiguration.class)
public @interface EnableNotificationModule {
}
