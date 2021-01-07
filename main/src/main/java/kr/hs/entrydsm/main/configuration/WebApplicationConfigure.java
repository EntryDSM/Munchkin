package kr.hs.entrydsm.main.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Objects;

@RequiredArgsConstructor
@EnableAutoConfiguration
public class WebApplicationConfigure {

    private final ConfigurableListableBeanFactory beanFactory;
    private final ApplicationContext context;

    @PostConstruct
    public void registerBeans() {
        ApplicationContext parentContext = context.getParent();
        Objects.requireNonNull(parentContext).getBeansWithAnnotation(RestController.class)
                .forEach(beanFactory::registerSingleton);
    }

}
