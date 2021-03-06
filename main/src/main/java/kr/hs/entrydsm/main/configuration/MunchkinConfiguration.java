package kr.hs.entrydsm.main.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("kr.hs.entrydsm.*.entity")
@EnableJpaRepositories("kr.hs.entrydsm.*.infrastructure.database")
@ComponentScan("kr.hs.entrydsm.common.context")
@ComponentScan("kr.hs.entrydsm.main.security")
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "kr.hs.entrydsm.main.integrate", lazyInit = true)
public class MunchkinConfiguration {
}
