package Fallin.engine;

public class TreasureCell extends Cell {
    private int treasureValue;

    public TreasureCell(int x, int y, int treasureValue) {
        super(x, y, "Treasure");
        this.treasureValue = treasureValue;
    }

    public int getTreasureValue() {
        return treasureValue;
    }
}

