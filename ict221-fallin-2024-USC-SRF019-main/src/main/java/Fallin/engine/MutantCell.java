package Fallin.engine;

public class MutantCell extends Cell {
    private int mutantStrength;

    public MutantCell(int x, int y, int mutantStrength) {
        super(x, y, "Mutant");
        this.mutantStrength = mutantStrength;
    }

    public int getMutantStrength() {
        return mutantStrength;
    }

    public void setY(int y) {
    }

    public void setX(int x) {
    }
}

