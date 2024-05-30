package Fallin.gui;

import Fallin.engine.GameEngine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * GUI for the Maze Runner Game.
 *
 * NOTE: Do NOT run this class directly in IntelliJ - run 'RunGame' instead.
 */
public class GameGUI extends Application {
    private GameEngine gameEngine;
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fallin/gui/game_gui.fxml"));
        Parent root = loader.load();
        controller = loader.getController();

        gameEngine = new GameEngine(10, 5); // 10x10 grid with difficulty 5
        controller.setGameEngine(gameEngine);

        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.setTitle("Fallin Game");
        primaryStage.show();

        // Add key event handler to the scene
        primaryStage.getScene().setOnKeyPressed(controller::handleKeyInput);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
