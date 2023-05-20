package es.uca.dss.knights.spi.genericidad;

public interface Quest<T> {
    T embark() throws QuestFailedException;
}
