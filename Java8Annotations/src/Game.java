import java.lang.annotation.Repeatable;

@Repeatable(Games.class)
public @interface Game {
    String name();
    String day();
}
