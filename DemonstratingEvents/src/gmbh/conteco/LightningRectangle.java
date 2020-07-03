package gmbh.conteco;

import javafx.animation.FillTransition;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class LightningRectangle extends Rectangle {
    private static final int SIZE = 20;
    private final int i;
    private final int j;

    private FillTransition fillTransition = new FillTransition(Duration.seconds(4));

    public LightningRectangle(int i, int j, EventHandler<? super LightningEvent> eventHandler) {
        super(SIZE, SIZE);

        this.i = i;
        this.j = j;

        Color baseColor = (i+j)%2==0 ? Color.RED : Color.WHITE;

        setFill(baseColor);

        fillTransition.setFromValue(Color.YELLOW);
        fillTransition.setToValue(baseColor);
        fillTransition.setShape(this);

        addEventHandler(LightningEvent.PLASMA_STRIKE, eventHandler);
    }

    public void strike() {
        fillTransition.playFromStart();
    }

    public int getI() {
        return this.i;
    }

    public int getJ() {
        return this.j;
    }
}
