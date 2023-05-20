package es.uca.dss.knights.spi;

public class HolyGrailQuest implements Quest<HolyGrail> {
    public HolyGrailQuest() {
    }

    public HolyGrail embark() throws QuestFailedException {
        // Hacer lo que sea necesario para embarcarse en la búsqueda
        return new HolyGrail();
    }
}
