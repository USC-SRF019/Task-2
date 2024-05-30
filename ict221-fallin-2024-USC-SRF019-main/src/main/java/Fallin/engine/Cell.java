package Fallin.engine;

import javafx.scene.layout.StackPane;

public class Cell extends StackPane {
    private int x;
    private int y;
    private String type;

    public Cell(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

