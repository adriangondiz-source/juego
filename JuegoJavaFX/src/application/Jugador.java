package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Jugador {

    private double x;
    private double y;
    private double ancho = 20;
    private double alto = 100;
    private double velocidad = 10;

    public Jugador(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void moverArriba() {
        y -= velocidad;
    }

    public void moverAbajo() {
        y += velocidad;
    }

    public void dibujar(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(x, y, ancho, alto);
    }

    // Getters para colisiones
    public double getX() { return x; }
    public double getY() { return y; }
    public double getAncho() { return ancho; }
    public double getAlto() { return alto; }
}