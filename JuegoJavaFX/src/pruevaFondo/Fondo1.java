package pruevaFondo;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Fondo1 {

    private Canvas canvas;

    public Fondo1() {
        canvas = new Canvas(800, 500);

        Image img = new Image(getClass().getResource("/images/Copilot_20260303_201729.png").toString());

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(img, 0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
