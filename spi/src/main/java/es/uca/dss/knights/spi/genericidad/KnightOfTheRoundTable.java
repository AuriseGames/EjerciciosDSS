package es.uca.dss.knights.spi.genericidad;

public class KnightOfTheRoundTable<T> implements Knight<T> {
    private String name;
    private Quest<T> quest;

    public KnightOfTheRoundTable(String name) {
        this.name = name;
    }

    public T embarkOnQuest() throws QuestFailedException {
        return quest.embark();
    }

    public void setQuest(Quest<T> quest) {
        this.quest = quest;
    }
}
