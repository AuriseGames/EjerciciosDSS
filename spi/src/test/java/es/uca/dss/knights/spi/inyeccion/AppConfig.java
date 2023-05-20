package es.uca.dss.knights.spi.inyeccion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public KnightOfTheRoundTableTest<HolyGrail> knightOfTheRoundTableTest() {
        return new KnightOfTheRoundTableTest<HolyGrail>();
    }

    @Bean
    public Knight<HolyGrail> knight() {
        return new KnightOfTheRoundTable<HolyGrail>("Lancelot");
    }

    @Bean
    public Quest<HolyGrail> quest() {
        return new HolyGrailQuest();
    }
    
}
