package application;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Puntuacion {
	
	private static final int ANCHO_CAMPO = 0;
	private static final int ALTO_CAMPO = 0;
	private int puntuacionJugador1 = 0;
	private int puntuacionJugador2 = 0;
	private double pelota;

	Text marcador = new Text("0 : 0");
	
	
	private  void setX(double x) {
		// TODO Auto-generated method stub
		
	}
	
	private void setY(double y) {
		// TODO Auto-generated method stub
		
	}
	private void actualizarPuntuacion() {
		// Aquí puedes implementar la lógica para actualizar la puntuación
		// por ejemplo, si la pelota pasa por el lado del jugador 1, incrementa la puntuación del jugador 2
		// y viceversa.
		
		
		marcador.setFont(Font.font(30));
		marcador.setX(250);
		marcador.setY(50);

	}
	private void actualizarMarcador() {
	    marcador.setText(puntuacionJugador1 + " : " + puntuacionJugador2);
	}
	
	private void reiniciarPelota() {
	    pelota.setX(ANCHO_CAMPO / 2);
	    pelota.setY(ALTO_CAMPO / 2);
	    pelota.reiniciarVelocidad(); // si tienes este método
	}
	
}
