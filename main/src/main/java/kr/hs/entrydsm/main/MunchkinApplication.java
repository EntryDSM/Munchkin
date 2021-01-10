package kr.hs.entrydsm.main;

import kr.hs.entrydsm.main.configuration.MunchkinApplicationBuilder;
import org.springframework.boot.SpringApplication;

public class MunchkinApplication {

    public static final String PROPS_CONFIG_NAME = "spring.config.name: main, user, admin, notification, application, score";

    public static void main(String[] args) {
        SpringApplication application = new MunchkinApplicationBuilder(MunchkinApplication.class)
                .properties(PROPS_CONFIG_NAME)
                .build(args);

        application.run(args);
    }

}
