
public class DeprecatedDemo {
	/**
	 * Multiplicates two numbers
	 * @deprecated
	 * This method should no longer be used for multiplication.
	 * <p> Use {@link Utils#...} instead
	 * @param operand one
	 * @param operand two
	 * @return the multiplied result
	 */
	@Deprecated (since = "1.5", forRemoval = true)
	public int calculate(int wert1, int wert2) {
		return wert1 * wert2;
	}
}
