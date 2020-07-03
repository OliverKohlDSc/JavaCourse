import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Game(name = "Football", day = "Sunday")
@Game(name = "Tennis", day = "Monday")
@Game(name = "Hockey", day = "Friday")
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
	    // Type Annotations
        // Repeating Annotations
        // Pluggable Type System?

        for (Method method : Class.forName("Main").getMethods()) {
            if (method.isAnnotationPresent((RequestForEnhancement.class))) {
                try {
                    method.invoke(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        Game[] games = Main.class.getAnnotationsByType(Game.class);
        for (Game game : games) {
            System.out.println(game.name() + " on " + game.day());
        }
    }

    @RequestForEnhancement(
            id  = 13241234,
            synpsis = "Invent the Wheel",
            engineer = "Mr. Doe",
            date = "1/1/2021"
    )
    public static void inventWheel() {
        System.out.println("TEST");
    }
}
