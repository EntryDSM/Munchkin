package kr.hs.entrydsm.main;

import kr.hs.entrydsm.main.configuration.MunchkinApplicationBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
public class MunchkinApplication {

    public static final String PROPS_CONFIG_NAME = "spring.config.name: main, user, admin, application, score";

    public static void main(String[] args) {
        SpringApplication application = new MunchkinApplicationBuilder(MunchkinApplication.class)
                .properties(PROPS_CONFIG_NAME)
                .build(args);

        application.run(args);
    }

}
