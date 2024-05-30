package Fallin.gui;

import Fallin.engine.Cell;
import Fallin.engine.GameEngine;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class Controller {
    @FXML
    private GridPane gridPane;

    private GameEngine engine;

    @FXML
    public void initialize() {
        engine = new GameEngine(10, 5); // Assuming 10x10 grid with difficulty 5
        updateGui();
    }

    private void updateGui() {
        // Clear old GUI grid pane
        gridPane.getChildren().clear();

        // Loop through map board and add each cell into grid pane
        for (int i = 0; i < engine.getSize(); i++) {
            for (int j = 0; j < engine.getSize(); j++) {
                Cell cell = engine.getMap()[i][j];
                ImageView imageView = createImageViewForCell(cell);
                gridPane.add(imageView, j, i);
            }
        }
        gridPane.setGridLinesVisible(true);
    }

    private ImageView createImageViewForCell(Cell cell) {
        String type = cell.getType().toLowerCase();
        String imagePath = "/Fallin/resources/" + type + ".png"; // Adjust this path
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        if (image == null) {
            throw new NullPointerException("Image not found: " + imagePath);
        }
        return new ImageView(image);
    }

    @FXML
    void handleKeyInput(KeyEvent event) {
        String direction = null;
        switch (event.getCode()) {
            case UP: direction = "up"; break;
            case DOWN: direction = "down"; break;
            case LEFT: direction = "left"; break;
            case RIGHT: direction = "right"; break;
        }

        if (direction != null) {
            boolean gameEnd = engine.movePlayer(direction);
            engine.moveMutants();
            updateGui();

            if (gameEnd) {
                if (engine.getPlayer().getLife() <= 0 || engine.getCurrentSteps() >= engine.getStepLimit()) {
                    System.out.println("Game Over! You lost.");
                } else {
                    System.out.println("Congratulations! You won.");
                }
            }
        }
    }

    @FXML
    void handleHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Instructions");
        alert.setHeaderText(null);
        alert.setContentText("Instructions on how to play the game.");
        alert.showAndWait();
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.engine = gameEngine;
    }
}
