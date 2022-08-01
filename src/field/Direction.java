package field;

public enum Direction {

    // -------------------------------------------------------------------------
    // CONSTANTS
    // -------------------------------------------------------------------------

    R,    // x + 1
    D,    // y - 1
    L,    // x - 1
    U;    // y + 1

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /**
     * Array of <code>Direction</code>s with the value of every
     * <code>Direction</code> constant.
     */
    private static Direction[] VALUES = Direction.values();

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    /**
     * Gets the number of <code>Direction</code> constants.
     *
     * @return    the number of <code>Direction</code> constants
     */
    public static int getSize() {
        return Direction.VALUES.length;
    }

    // -------------------------------------------------------------------------
    // NEXT
    // -------------------------------------------------------------------------

    /**
     * Returns the next <code>Direction</code> constant.
     *
     * @return    the next <code>Direction</code> constant
     */
    public Direction next() {
        return Direction.VALUES[(this.ordinal() + 1) % Direction.VALUES.length];
    }

    // -------------------------------------------------------------------------
    // REVERSE
    // -------------------------------------------------------------------------

    /**
     * Returns the opposite <code>Direction</code> constant.
     *
     * @return    the opposite <code>Direction</code> constant
     */
    public Direction reverse() {
        return Direction.VALUES[(this.ordinal() + 2) % Direction.VALUES.length];
    }

    // =========================================================================

}   // Directions enum
