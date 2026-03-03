package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private double jugador1X = 100;
    private double jugador1Y = 300;

    private double jugador2X = 600;
    private double jugador2Y = 300;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 400); // tamaño del área de juego
        GraphicsContext gc = canvas.getGraphicsContext2D(); // para dibujar en el canvas

        Scene scene = new Scene(new StackPane(canvas)); // contenedor para el canvas
        stage.setScene(scene);
        stage.setTitle("Juego de Lucha - Prueba");
        stage.show();

        new AnimationTimer() { // bucle de juego
            @Override
            public void handle(long now) {
                actualizar(); 
                dibujar(gc);// renderizar el juego
            }
        }.start();
    }

    private void actualizar() {
        // lógica mínima: ejemplo de movimiento automático para comprobar render
        jugador1X += 0.5; // mueve el jugador 1 hacia la derecha
        if (jugador1X > 800) jugador1X = -5;// reinicia la posición del jugador 1 al salir de la pantalla0; 
        jugador2X -= 0.5; // mueve el jugador 2 hacia la izquierda
        if (jugador2X < -800) jugador2X = +5; // reinicia la posición del jugador 2 al salir de la pantalla
    }
    
    private void colicion() {
		// Aquí se implementaría la lógica de colisión entre los jugadores
    	
    	// Ejemplo simple de detección de colisión (rectángulos)
		if (jugador1X < jugador2X + 50 && jugador1X + 50 > jugador2X &&
			jugador1Y < jugador2Y + 50 && jugador1Y + 50 > jugador2Y) {
			
			
			System.out.println("¡Colisión detectada! Jugadores reiniciados.");
		}
		
		
	}

    private void dibujar(GraphicsContext gc) {
        gc.setFill(Color.DARKSLATEGRAY); // fondo del juego
        gc.fillRect(0, 0, 800, 400);

        gc.setFill(Color.RED);// dibuja el jugador 1
        gc.fillRect(jugador1X, jugador1Y, 50, 50);

        gc.setFill(Color.BLUE); // dibuja el jugador 2
        gc.fillRect(jugador2X, jugador2Y, 50, 50);
    }

    public static void main(String[] args) {
        launch();
    }
}