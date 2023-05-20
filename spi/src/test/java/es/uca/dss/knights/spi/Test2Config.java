package es.uca.dss.knights.spi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Test2Config {
    
    @Bean
    public KnightOfTheRoundTableTest2<HolyGrail> knightOfTheRoundTableTest2() {
        return new KnightOfTheRoundTableTest2<HolyGrail>();
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
