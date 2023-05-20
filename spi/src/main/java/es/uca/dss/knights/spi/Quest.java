package es.uca.dss.knights.spi;

public interface Quest<T> {
    T embark() throws QuestFailedException;
}
