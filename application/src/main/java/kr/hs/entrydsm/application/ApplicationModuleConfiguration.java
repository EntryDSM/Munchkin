package kr.hs.entrydsm.application;

import kr.hs.entrydsm.common.module.config.ModuleConfiguration;
import kr.hs.entrydsm.common.module.info.ModuleNameInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ApplicationModuleConfiguration extends ModuleConfiguration {

    @Override
    @Bean
    @ConfigurationProperties("munchkin.application-module")
    public ModuleNameInfo moduleInfoProperties() {
        return new ModuleNameInfo();
    }

}
