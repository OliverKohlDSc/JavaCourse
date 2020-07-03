import java.util.function.BiFunction;

public class Main {

    public void saySomething() {
        Main.sayMyName();
    }

    public float multiply(float number1, float number2) {
        return number1*number2;
    }

    public static void sayMyName() {
        System.out.println("Oliver is speaking ...");
    }

    public static void main(String[] args) {
        // STATIC
	    Sayable sayable = Main::sayMyName;
	    sayable.say();

	    // INSTANCE
	    Main main = new Main();
	    Sayable sayableInstance = main::saySomething;
	    sayableInstance.say();

	    //BiFunction<Float, Float, Float> multiplier = main::multiply;
        BiFunction<Float, Float, Float> multiplier = new Main()::multiply;
        Float result = multiplier.apply(10f, 3.1f);
        System.out.println(result);

        Logable helloWorld = Log::new;
        helloWorld.getLogEntry("Hello World");
    }
}
