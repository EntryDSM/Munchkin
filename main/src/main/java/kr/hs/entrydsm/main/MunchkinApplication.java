package kr.hs.entrydsm.main;

import kr.hs.entrydsm.main.configuration.MunchkinApplicationBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "kr.hs.entrydsm.main.integrate", lazyInit = true)
public class MunchkinApplication {

    public static final String PROPS_CONFIG_NAME = "spring.config.name: main, user, admin, notification, application, score, school";

    public static void main(String[] args) {
        SpringApplication application = new MunchkinApplicationBuilder(MunchkinApplication.class)
                .properties(PROPS_CONFIG_NAME)
                .build(args);

        application.run(args);
    }

}
