package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pelota {

    private double x;
    private double y;
    private double velX = 1; // Velocidad inicial en X
    private double velY = 1; // Velocidad inicial en Y
    private double tamaño = 20; // Tamaño de la pelota

    public Pelota(double x, double y) { // Constructor para inicializar la posición de la pelota
        this.x = x;
        this.y = y;
    }

    public void mover() { // Actualiza la posición de la pelota según su velocidad
        x += velX;
        y += velY;
    }

    public void rebotarVertical() { // Invierte la velocidad vertical para simular un rebote
        velY = -velY;
    }

    public void rebotarHorizontal() { // Invierte la velocidad horizontal para simular un rebote
        velX = -velX;
    }

    public void dibujar(GraphicsContext gc) { // Dibuja la pelota en el canvas
        gc.setFill(Color.WHITE);
        gc.fillOval(x, y, tamaño, tamaño);
    }

    // Getters para colisiones
    public double getX() { return x; }
    public double getY() { return y; }
    public double getTamaño() { return tamaño; }
    
    public void comprobarRebotes(double ancho, double alto) { // Verifica si la pelota rebota en los bordes del área de juego
        if (y <= 0 || y + tamaño >= alto) {
            velY = -velY;
        }

        if (x <= 0 || x + tamaño >= ancho) {
            velX = -velX;
        }
    }
}
