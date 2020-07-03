package gmbh.conteco;

import javafx.scene.control.TextField;

public class MyOwnTextField extends TextField {

    public MyOwnTextField() {
        this.setPrefWidth(70);
    }

    @Override
    public void replaceText(int start, int end, String text) {
        // Überprüfe ob die Eingabe eine Zahl ist
        if (text.matches("[0-9]+")) {
            // wenn ja, dann darf das eingegeben werden
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        // Überprüfe ob die Eingabe eine Zahl ist
        if (text.matches("[0-9]+")) {
            // wenn ja, dann darf das eingegeben werden
            super.replaceSelection(text);
        }
    }
}
