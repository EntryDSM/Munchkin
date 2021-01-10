package kr.hs.entrydsm.admin;

import kr.hs.entrydsm.common.context.beans.EnableModuleConfiguration;
import kr.hs.entrydsm.common.module.config.ModuleConfiguration;
import kr.hs.entrydsm.common.module.info.ModuleNameInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableModuleConfiguration
public class AdminModuleConfiguration extends ModuleConfiguration {

    @Override
    @Bean
    @ConfigurationProperties("munchkin.admin-module")
    public ModuleNameInfo moduleInfoProperties() {
       return new ModuleNameInfo();
    }

}
