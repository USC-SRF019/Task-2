package Fallin.engine;

public class MedicalCell extends Cell {
    private int healingValue;

    public MedicalCell(int x, int y, int healingValue) {
        super(x, y, "Medical");
        this.healingValue = healingValue;
    }

    public int getHealingValue() {
        return healingValue;
    }
}
