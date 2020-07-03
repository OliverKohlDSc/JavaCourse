package gmbh.conteco;

import javafx.event.EventHandler;

public class StrikeEventHandler implements EventHandler<LightningEvent> {
    @Override
    public void handle(LightningEvent event) {
        LightningRectangle rect = (LightningRectangle) event.getTarget();
        rect.strike();

        System.out.println("Received strike: " + rect.getI() + ", " + rect.getJ());
    }
}
