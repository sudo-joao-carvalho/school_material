package ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainJFX extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        RootPane root = new RootPane();
        Scene scene = new Scene(root, 600, 400, Color.INDIGO);
        stage.setScene(scene);
        stage.setMinWidth(500);
        stage.setMinHeight(200);
        stage.setTitle("JavaFX");
        stage.show();
    }
}
