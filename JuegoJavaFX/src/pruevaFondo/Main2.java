package pruevaFondo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main2 extends Application {

    @Override
    public void start(Stage stage) {
        Fondo1 fondo = new Fondo1();

        StackPane root = new StackPane();
        root.getChildren().add(fondo.getCanvas());

        Scene scene = new Scene(root, 800, 500);

        stage.setTitle("Prueba Fondo Canvas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}