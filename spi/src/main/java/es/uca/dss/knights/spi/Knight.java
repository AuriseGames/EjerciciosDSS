package es.uca.dss.knights.spi;

public interface Knight<T> {
    T embarkOnQuest() throws QuestFailedException;
    void setQuest(Quest<T> quest);
}
