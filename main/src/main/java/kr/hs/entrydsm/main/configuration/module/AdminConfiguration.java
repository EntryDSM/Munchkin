package kr.hs.entrydsm.main.configuration.module;

import kr.hs.entrydsm.admin.EnableAdminModule;
import kr.hs.entrydsm.main.configuration.DependentModule;

@EnableAdminModule
@DependentModule(ApplicationConfiguration.class)
public class AdminConfiguration {
}
