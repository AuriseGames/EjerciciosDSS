package es.uca.dss.knights.spi.inyeccion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KnightOfTheRoundTableTest<T> {

    @Autowired
    Knight<T> knight;

    @Autowired
    Quest<T> quest;

    @Test
    void testEmbarkOnQuest() {
        try {
            knight.setQuest(quest);
            knight.embarkOnQuest();
        } catch (QuestFailedException e) {
            e.printStackTrace();
        }
    }

}