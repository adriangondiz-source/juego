package application;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.Scene;



public class Game {

	private Pelota pelota ;
	private Canvas canvas;
	private GraphicsContext gc;
	private Jugador jugador1;
	private Jugador jugador2;
	private boolean wPresionado = false;
	private boolean sPresionado = false;
	private boolean upPresionado = false;
	private boolean downPresionado = false;
	private int puntuacionJugador1 = 0;
	private int puntuacionJugador2 = 0;
	private final int ANCHO_CAMPO = 800;


	public Game() {
		canvas = new Canvas(800, 500);
		gc = canvas.getGraphicsContext2D();
		pelota = new Pelota(400, 250); // posición inicial en el centro
		jugador1 = new Jugador(50, 200); // posición inicial del jugador 1
		jugador2 = new Jugador(730, 200); // posición inicial del jugador 2

		startGameLoop(); // Inicia el bucle del juego
	}

	

	public Canvas getCanvas() { // Método para obtener el canvas y agregarlo a la escena
		
		return canvas;
	}

	private void startGameLoop() { // Bucle del juego usando AnimationTimer
		new AnimationTimer() { // Este método se llama en cada frame
			@Override
			public void handle(long now) { // Aquí va la lógica del juego que se ejecuta en cada frame
				
				clear(); // Limpia el canvas antes de redibujar
				draw(); // Dibuja el estado actual del juego en el canvas
				drawCenterLine(); // Dibuja la línea central del campo de juego
				
				
				
				
				pelota.mover();
				pelota.comprobarRebotes(800, 500); // Verifica si la pelota rebota en los bordes del área de juego
				pelota.dibujar(gc);

				update(); // mover jugadores y pelota

				jugador1.dibujar(gc);
				jugador2.dibujar(gc);

				pelota.mover();
				pelota.comprobarRebotes(800, 500); // Verifica si la pelota rebota en los bordes del área de juego

				comprobarColisionPelotaJugador(); // Verifica si la pelota colisiona con alguno de los jugadores
				comprobarColisionPelotaBordes(); // Verifica si la pelota colisiona con los bordes del área de juego
				comprobarColisionJugadorBordes(); // Verifica si los jugadores colisionan con los bordes del área de juego

			/*	if (pelota.getX() < 0) {
				    puntuacionJugador2++;
				    reiniciarPelota();
				    actualizarMarcador();
				}

				if (pelota.getX() > ANCHO_CAMPO) {
				    puntuacionJugador1++;
				    reiniciarPelota();
				    actualizarMarcador();
				}*/
				
				pelota.dibujar(gc);
			}
		}.start();
	}

	private void draw() { // Método para dibujar el fondo y los jugadores en el canvas
		gc.setFill(Color.BLACK ); // Fondo
		gc.setStroke(Color.WHITE); // Color de los bordes
		gc.setLineWidth(2); // Ancho de los bordes
		gc.strokeRect(0, 0, 800, 800); // Dibuja el borde del área de juego
		gc.fillRect(0, 0, 800, 800); 

		jugador1.dibujar(gc);
		jugador2.dibujar(gc);

		pelota.dibujar(gc);

	}

	private void drawCenterLine() { // Método para dibujar la linea discuntinua en el centro del campo de juego
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(2);
		for (int i = 0; i < 500; i += 30) {
			gc.strokeLine(400, i, 400, i + 15); // Dibuja segmentos de línea para crear una línea discontinua
		}
	}

	
	private void clear() { // Método para limpiar el canvas antes de redibujar
		gc.clearRect(0, 0, 800, 500);
	}

	
	
	public void setControles(Scene scene) {

		scene.setOnKeyPressed(event -> {
			switch (event.getCode()) {
			case W -> wPresionado = true;
			case S -> sPresionado = true;
			case UP -> upPresionado = true;
			case DOWN -> downPresionado = true;
			}
		});

		scene.setOnKeyReleased(event -> {
			switch (event.getCode()) {
			case W -> wPresionado = false;
			case S -> sPresionado = false;
			case UP -> upPresionado = false;
			case DOWN -> downPresionado = false;
			}
		});
	}

	private void update() {

		if (wPresionado) jugador1.moverArriba();
		if (sPresionado) jugador1.moverAbajo();

		if (upPresionado) jugador2.moverArriba();
		if (downPresionado) jugador2.moverAbajo();

		pelota.mover();
	}

	private void comprobarColisionPelotaJugador() {

		// Colisión con jugador 1 (Verifica si la pelota colisiona con el jugador 1 utilizando las coordenadas y dimensiones de ambos objetos)
		if (pelota.getX() < jugador1.getX() + jugador1.getAncho() &&
				pelota.getX() + pelota.getTamaño() > jugador1.getX() &&
				pelota.getY() < jugador1.getY() + jugador1.getAlto() &&
				pelota.getY() + pelota.getTamaño() > jugador1.getY()) {

			pelota.rebotarHorizontal(); // Si hay colisión, la pelota rebota horizontalmente
		}

		// Colisión con jugador 2 (Verifica si la pelota colisiona con el jugador 2 utilizando las coordenadas y dimensiones de ambos objetos)
		if (pelota.getX() < jugador2.getX() + jugador2.getAncho() && 
				pelota.getX() + pelota.getTamaño() > jugador2.getX() &&
				pelota.getY() < jugador2.getY() + jugador2.getAlto() &&
				pelota.getY() + pelota.getTamaño() > jugador2.getY()) {

			pelota.rebotarHorizontal(); // Si hay colisión, la pelota rebota horizontalmente
		}
	}

	private void comprobarColisionPelotaBordes() {
		if (pelota.getY() <= 0 || pelota.getY() + pelota.getTamaño() >= 500) {
			pelota.rebotarVertical();
		}

		if (pelota.getX() <= 0 || pelota.getX() + pelota.getTamaño() >= 800) {
			pelota.rebotarHorizontal();
		}
	}

	private void comprobarColisionJugadorBordes() {
		// Limitar movimiento del jugador 1
		if (jugador1.getY() < 0) {
			jugador1.moverAbajo(); // Evita que el jugador se mueva fuera del área superior
		} else if (jugador1.getY() + jugador1.getAlto() > 500) {
			jugador1.moverArriba(); // Evita que el jugador se mueva fuera del área inferior
		}

		// Limitar movimiento del jugador 2
		if (jugador2.getY() < 0) {
			jugador2.moverAbajo(); // Evita que el jugador se mueva fuera del área superior
		} else if (jugador2.getY() + jugador2.getAlto() > 500) {
			jugador2.moverArriba(); // Evita que el jugador se mueva fuera del área inferior
		}
	}


}

