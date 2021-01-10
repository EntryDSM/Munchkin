package kr.hs.entrydsm.application;

import kr.hs.entrydsm.common.context.beans.EnableModuleConfiguration;
import kr.hs.entrydsm.common.context.ModuleConfiguration;
import kr.hs.entrydsm.common.context.Module;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableModuleConfiguration
public class ApplicationModuleConfiguration extends ModuleConfiguration {

    @Override
    @Bean
    @ConfigurationProperties("munchkin.application-module")
    public Module moduleInfoProperties() {
        return new Module();
    }

}
