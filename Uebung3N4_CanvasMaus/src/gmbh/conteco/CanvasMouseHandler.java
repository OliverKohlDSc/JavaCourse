package gmbh.conteco;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CanvasMouseHandler implements EventHandler<MouseEvent> {
    Canvas canvas;
    public CanvasMouseHandler(Canvas canvas) { this.canvas = canvas; }
    public void handle(MouseEvent ev){
        double x = ev.getX();
        double y = ev.getY();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillOval(x-20,y-20,40,40);
    }
}