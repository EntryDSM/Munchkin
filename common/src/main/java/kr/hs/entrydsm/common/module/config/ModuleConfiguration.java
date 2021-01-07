package kr.hs.entrydsm.common.module.config;

import kr.hs.entrydsm.common.context.beans.PublishedComponentRegisteringPostProcessor;
import kr.hs.entrydsm.common.module.info.ModuleNameInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

@Slf4j
public abstract class ModuleConfiguration implements ApplicationRunner {

    public abstract ModuleNameInfo moduleInfoProperties();

    @Bean
    public PublishedComponentRegisteringPostProcessor orderPublishedComponentRegisteringPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        return new PublishedComponentRegisteringPostProcessor(beanFactory);
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("Started {}", moduleInfoProperties());
    }

}
