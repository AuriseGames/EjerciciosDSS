package es.uca.dss.knights.spi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KnightOfTheRoundTableTest {

    Knight<HolyGrail> knight;
    Quest<HolyGrail> quest;

    @BeforeEach
    void setUp() {
        knight = new KnightOfTheRoundTable<HolyGrail>("Lancelot");
        Quest<HolyGrail> quest = new HolyGrailQuest();
        knight.setQuest(quest);
    }

    @Test
    void testEmbarkOnQuest() {
        try {
            knight.embarkOnQuest();
        } catch (QuestFailedException e) {
            e.printStackTrace();
        }
    }

}