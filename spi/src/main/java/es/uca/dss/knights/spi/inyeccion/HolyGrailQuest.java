package es.uca.dss.knights.spi.inyeccion;

public class HolyGrailQuest implements Quest<HolyGrail> {
    public HolyGrailQuest() {
    }

    public HolyGrail embark() throws QuestFailedException {
        // Hacer lo que sea necesario para embarcarse en la búsqueda
        return new HolyGrail();
    }
}
