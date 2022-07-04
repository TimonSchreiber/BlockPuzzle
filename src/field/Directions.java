package field;

public enum Directions {

	// =========================================================================
	// CONSTANTS
	// =========================================================================

	R,	// x + 1
	D,	// y - 1
	L,	// x - 1
	U;	// y + 1

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	/**
	 * Array of <code>Direction</code>s with the value of every
	 * <code>Direction</code> constant.
	 */
	private static Directions[] VALUES = Directions.values();

	// =========================================================================
	// GETTER - METHOD
	// =========================================================================

	/**
	 * Gets the number of <code>Direction</code> constants.
	 * 
	 * @return	the number of <code>Direction</code> constants
	 */
	public static int getSize() {
		return Directions.VALUES.length;
	}

	// =========================================================================
	// NEXT - METHOD
	// =========================================================================

	/**
	 * Returns the next <code>Direction</code> constant.
	 * 
	 * @return	the next <code>Direction</code> constant
	 */
	public Directions next() {
		return Directions.VALUES[(this.ordinal() + 1) % Directions.VALUES.length];
	}

	// =========================================================================
	// REVERSE - METHOD
	// =========================================================================

	/**
	 * Returns the opposite <code>Direction</code> constant.
	 * 
	 * @return	the opposite <code>Direction</code> constant
	 */
	public Directions reverse() {
		return Directions.VALUES[(this.ordinal() + 2) % Directions.VALUES.length];
	}

	// =========================================================================

}   // Directions enum
