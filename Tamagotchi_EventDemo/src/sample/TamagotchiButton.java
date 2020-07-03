package sample;

import javafx.scene.control.Button;

public class TamagotchiButton extends Button {
    private boolean isHungry = false;

    public boolean isHungry() {
        return this.isHungry;
    }

    public void setHungry(boolean hungry) {
        this.isHungry = hungry;
        HungerEvent actionEvent = new HungerEvent(HungerEvent.HUNGRY);
        this.fireEvent(actionEvent);
    }
}
