public class Main {

	public static void main(String[] args) {
		String operator = "%";
		double wert1 = 2.5;
		double wert2 = 3;
		
		System.out.println(Calc.getEnumValue(operator).getResult(wert1, wert2));
	}
}
