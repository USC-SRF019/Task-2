package Fallin.engine;

public class TrapCell extends Cell {
    private int damageValue;

    public TrapCell(int x, int y, int damageValue) {
        super(x, y, "Trap");
        this.damageValue = damageValue;
    }

    public int getDamageValue() {
        return damageValue;
    }
}
