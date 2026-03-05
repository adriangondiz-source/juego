package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Jugador jugador1;
	private Jugador jugador2;

	@Override
	public void start(Stage stage) {

		Pelota pelota = new Pelota(640, 360);

		Game game = new Game();

		Scene scene = new Scene(new StackPane(game.getCanvas())); // Agrega el canvas del juego a la escena

		stage.setScene(scene); // Configura la escena en la ventana
		stage.setTitle("Pong - JavaFX"); // Título de la ventana
		stage.show(); // Muestra la ventana

		game.setControles(scene); // Configura los controles para mover los jugadores



	}

	public void setControles(Scene scene) { // Configura los controles para mover los jugadores
		scene.setOnKeyPressed(event -> {
			switch (event.getCode()) {
			case W -> jugador1.moverArriba();
			case S -> jugador1.moverAbajo();
			case UP -> jugador2.moverArriba();
			case DOWN -> jugador2.moverAbajo();
			}
		});
	}



	public static void main(String[] args) { // Método principal para lanzar la aplicación
		launch();
	}
}
