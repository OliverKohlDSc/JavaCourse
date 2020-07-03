package gmbh.conteco;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;

    private double w = 250, h = 250;

    public ClockPane() {
        setCurrentTime();
    }

    public ClockPane(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        paintClock();
    }

    public int getHour() { return hour; }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setHour(int hour) {
        this.hour = hour;
        paintClock();
    }

    public void setMinute(int minute) {
        this.minute = minute;
        paintClock();
    }

    public void setSecond(int second) {
        this.second = second;
        paintClock();
    }

    public void setCurrentTime() {
        Calendar calendar = new GregorianCalendar();

        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);

        paintClock();
    }

    protected void paintClock() {
        double clockRadius = Math.min(w, h * 0.8 * 0.5);
        double centerX = w / 2;
        double centerY = h / 2;

        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        Text text12 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
        Text text9 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
        Text text3 = new Text(centerX + clockRadius - 10, centerY +3, "3");
        Text text6 = new Text(centerX - 3, centerY + clockRadius - 3, "12");

        double secondLength = clockRadius * 0.8;
        double secondX = centerX + secondLength * Math.sin(second * (2 * Math.PI / 60));
        double secondY = centerY - secondLength * Math.cos(second * (2 * Math.PI / 60));
        Line secondLine = new Line(centerX, centerY, secondX, secondY);
        secondLine.setStroke(Color.RED);

        double minuteLength = clockRadius * 0.65;
        double minuteX = centerX + minuteLength * Math.sin(minute * (2 * Math.PI / 60));
        double minuteY = centerY - minuteLength * Math.cos(minute * (2 * Math.PI / 60));
        Line minuteLine = new Line(centerX, centerY, minuteX, minuteY);
        minuteLine.setStroke(Color.BLUE);

        double hourLength = clockRadius * 0.5;
        double hourX = centerX + hourLength * Math.sin(hour * (2 * Math.PI / 60) * (2 * Math.PI / 12));
        double hourY = centerY - hourLength * Math.cos(hour * (2 * Math.PI / 60) * (2 * Math.PI / 12));
        Line hourLine = new Line(centerX, centerY, hourX, hourY);
        hourLine.setStroke(Color.GREEN);

        getChildren().clear();
        getChildren().addAll(circle, text12, text9, text3, text6, secondLine, minuteLine, hourLine);
    }
}