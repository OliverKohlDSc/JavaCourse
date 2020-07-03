import java.util.Arrays;
import java.util.Optional;
import java.util.function.*;

public enum Calc {
	ADDITION ("+" , (wert1, wert2) -> wert1+wert2),
	SUBTRACTION ("-", (wert1, wert2) -> wert1-wert2),
	DIVISION ("/", (wert1, wert2) -> wert1/wert2),
	MULTIPLICATION ("*", (wert1, wert2) -> wert1*wert2),
	//EXPONENT ("^", (wert1, wert2) -> Math.pow(wert1, wert2));
	EXPONENT ("^", Math::pow),
	MODULO ("%", (wert1, wert2) -> wert1%wert2);
	
	private String operator;
	private BiFunction<Double, Double, Double> function;
	
	private Calc(String operator, BiFunction<Double, Double, Double> function) {
		this.function = function;
		this.operator = operator;
	}
	
	public Double getResult(Double wert1, Double wert2) {
		return function.apply(wert1, wert2);
	}
	
	public String getOperator() {
		return this.operator;
	}
	
	public static Calc getEnumValue(String operator) {
		// JAVA 8 Streams
		Optional<Calc> result =
		Arrays.asList(Calc.values()).stream()
			.filter(element -> element.operator.equals(operator))
			.findFirst();
		
		if (result.isPresent())
			return result.get();
		
		return null;
	}
}