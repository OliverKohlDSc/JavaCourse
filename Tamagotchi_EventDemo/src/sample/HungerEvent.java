package sample;

import javafx.event.Event;
import javafx.event.EventType;

public class HungerEvent extends Event {
    public static final EventType<HungerEvent> HUNGRY = new EventType<>("HUNGRY");
    public static final EventType<HungerEvent> PEE = new EventType<>("PEE");

    public HungerEvent(EventType<? extends HungerEvent> eventType) {
        super(eventType);
    }
}
