package sample;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;

public class MainWindowScene extends Scene {

    public MainWindowScene(Parent parent) {
        super(parent);
        setContent(parent);
    }

    public void setContent(Parent parent) {

    }

    public MainWindowScene(Parent parent, double v, double v1) {
        super(parent, v, v1);
    }

    public MainWindowScene(Parent parent, Paint paint) {
        super(parent, paint);
    }

    public MainWindowScene(Parent parent, double v, double v1, Paint paint) {
        super(parent, v, v1, paint);
    }

    public MainWindowScene(Parent parent, double v, double v1, boolean b) {
        super(parent, v, v1, b);
    }

    public MainWindowScene(Parent parent, double v, double v1, boolean b, SceneAntialiasing sceneAntialiasing) {
        super(parent, v, v1, b, sceneAntialiasing);
    }
}
