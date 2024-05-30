package Fallin.engine;

public class Player {
    private int x;
    private int y;
    private int life;
    private int treasuresCollected;
    private int stepsTaken;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.life = 100; // Assuming starting life is 100
        this.treasuresCollected = 0;
        this.stepsTaken = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLife() {
        return life;
    }

    public int getTreasuresCollected() {
        return treasuresCollected;
    }

    public int getStepsTaken() {
        return stepsTaken;
    }

    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        this.stepsTaken++;
    }

    public void collectTreasure() {
        this.treasuresCollected++;
    }

    public void takeDamage(int amount) {
        this.life -= amount;
    }

    public void heal(int amount) {
        this.life += amount;
    }
}
