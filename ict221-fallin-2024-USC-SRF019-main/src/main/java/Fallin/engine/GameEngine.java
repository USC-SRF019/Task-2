package Fallin.engine;

import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameEngine {
    private Cell[][] map;
    private Player player;
    private List<MutantCell> mutants;
    private int difficulty;
    private int stepLimit = 100;
    private int currentSteps = 0;
    private int score = 0;

    public GameEngine(int size, int difficulty) {
        this.difficulty = difficulty;
        this.map = new Cell[size][size];
        this.player = new Player(9, 0); // Entrance at bottom left
        this.mutants = new ArrayList<>();
        initializeGrid(size);
    }

    private void initializeGrid(int size) {
        Random rand = new Random();

        // Place player at entrance
        map[9][0] = new Cell(9, 0, "Entrance");

        // Place exit at top right
        map[0][9] = new Cell(0, 9, "Exit");

        // Place treasures
        for (int i = 0; i < 8; i++) {
            int x, y;
            do {
                x = rand.nextInt(size);
                y = rand.nextInt(size);
            } while (map[x][y] != null);
            map[x][y] = new TreasureCell(x, y, 10); // example treasure value
        }

        // Place medical units
        for (int i = 0; i < 2; i++) {
            int x, y;
            do {
                x = rand.nextInt(size);
                y = rand.nextInt(size);
            } while (map[x][y] != null);
            map[x][y] = new MedicalCell(x, y, 10); // example healing value
        }

        // Place traps
        for (int i = 0; i < 5; i++) {
            int x, y;
            do {
                x = rand.nextInt(size);
                y = rand.nextInt(size);
            } while (map[x][y] != null);
            map[x][y] = new TrapCell(x, y, 10); // example damage value
        }

        // Place mutants
        for (int i = 0; i < difficulty; i++) {
            int x, y;
            do {
                x = rand.nextInt(size);
                y = rand.nextInt(size);
            } while (map[x][y] != null);
            MutantCell mutant = new MutantCell(x, y, 10); // example mutant strength
            map[x][y] = mutant;
            mutants.add(mutant);
        }

        // Fill the rest of the grid with empty cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == null) {
                    map[i][j] = new Cell(i, j, "Empty");
                }
            }
        }
    }

    public boolean movePlayer(String direction) {
        int x = player.getX();
        int y = player.getY();

        switch (direction.toLowerCase()) {
            case "up":
                if (x > 0) x--;
                break;
            case "down":
                if (x < map.length - 1) x++;
                break;
            case "left":
                if (y > 0) y--;
                break;
            case "right":
                if (y < map[0].length - 1) y++;
                break;
            default:
                return false;
        }

        // Check the cell the player is moving to
        Cell targetCell = map[x][y];
        switch (targetCell.getType()) {
            case "Treasure":
                player.collectTreasure();
                map[x][y] = new Cell(x, y, "Empty");
                break;
            case "Medical":
                player.heal(3); // example healing value
                map[x][y] = new Cell(x, y, "Empty");
                break;
            case "Trap":
                player.takeDamage(2); // example damage value
                map[x][y] = new Cell(x, y, "Empty");
                break;
            case "Mutant":
                player.takeDamage(4); // example damage value
                map[x][y] = new Cell(x, y, "Empty");
                break;
            case "Exit":
                // Check if all treasures are collected
                if (player.getTreasuresCollected() >= 8) {
                    score = 20 * player.getTreasuresCollected() + (stepLimit - currentSteps);
                    return true; // Game won
                }
                break;
        }

        // Move player and update position
        player.move(x, y);
        currentSteps++;

        // Check for game end conditions
        if (currentSteps >= stepLimit || player.getLife() <= 0) {
            score = -1;
            return true; // Game lost
        }

        return false;
    }

    public void moveMutants() {
        Random rand = new Random();

        for (MutantCell mutant : mutants) {
            int x = mutant.getX();
            int y = mutant.getY();

            int direction = rand.nextInt(5);
            switch (direction) {
                case 0: // up
                    if (x > 0) x--;
                    break;
                case 1: // down
                    if (x < map.length - 1) x++;
                    break;
                case 2: // left
                    if (y > 0) y--;
                    break;
                case 3: // right
                    if (y < map[0].length - 1) y++;
                    break;
                default: // stay
                    break;
            }

            // Check if the target cell is occupied by a special item
            if (!map[x][y].getType().equals("Empty")) {
                continue;
            }

            // Move mutant
            map[mutant.getX()][mutant.getY()] = new Cell(mutant.getX(), mutant.getY(), "Empty");
            mutant.setX(x);
            mutant.setY(y);
            map[x][y] = mutant;
        }
    }

    public boolean checkGameEnd() {
        return currentSteps >= stepLimit || player.getLife() <= 0 || (player.getX() == 0 && player.getY() == 9 && player.getTreasuresCollected() >= 8);
    }

    public void resetGame() {
        this.currentSteps = 0;
        this.player = new Player(9, 0);
        this.mutants.clear();
        initializeGrid(map.length);
    }

    public Cell[][] getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public List<MutantCell> getMutants() {
        return mutants;
    }

    public int getCurrentSteps() {
        return currentSteps;
    }

    public int getStepLimit() {
        return stepLimit;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getSize() {
        return map.length;
    }

    public int getScore() {
        return score;
    }
}
