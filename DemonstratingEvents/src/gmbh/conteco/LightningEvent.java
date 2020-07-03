package gmbh.conteco;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class LightningEvent extends Event {

    private int i, j;

    public int getI() {
        return this.i;
    }

    public int getJ() {
        return this.j;
    }

    // The onlny valid EventType for our lightning event.
    public static final EventType<LightningEvent> PLASMA_STRIKE = new EventType<>(Event.ANY, "PLASMA_STRIKE");

    public LightningEvent() {
        super(PLASMA_STRIKE);
    }

    public LightningEvent(EventType<? extends Event> eventType) {
        this();
    }

    public LightningEvent(Object o, EventTarget eventTarget, EventType<? extends Event> eventType) {
        super(o, eventTarget, PLASMA_STRIKE);

        this.i = ((LightningRectangle)eventTarget).getI();
        this.j = ((LightningRectangle)eventTarget).getJ();
    }

    @Override
    public EventType<? extends LightningEvent> getEventType() {
        return (EventType<? extends LightningEvent>)super.getEventType();
    }

    @Override
    public LightningEvent copyFor(Object o, EventTarget eventTarget) {
        return (LightningEvent)super.copyFor(o, eventTarget);
    }
}