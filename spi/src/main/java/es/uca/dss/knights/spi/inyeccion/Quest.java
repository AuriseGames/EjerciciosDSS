package es.uca.dss.knights.spi.inyeccion;

public interface Quest<T> {
    T embark() throws QuestFailedException;
}
