package kr.hs.entrydsm.main.configuration.module;

import kr.hs.entrydsm.main.configuration.DependentModule;
import kr.hs.entrydsm.notification.EnableNotificationModule;

@EnableNotificationModule
@DependentModule(AdminConfiguration.class)
public class NotificationConfiguration {
}
