package es.uca.dss.knights.spi.genericidad;

public interface Knight<T> {
    T embarkOnQuest() throws QuestFailedException;
    void setQuest(Quest<T> quest);
}
