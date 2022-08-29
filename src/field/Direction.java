package field;

/**
 * An Enum holding and ordering all the Directions in a 2D-Plane.
 */
public enum Direction {

    // -------------------------------------------------------------------------
    // CONSTANTS
    // -------------------------------------------------------------------------

    /** x + 1 */
    R,
    /** y - 1 */
    D,
    /** x - 1 */
    L,
    /** y + 1 */
    U;

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /**
     * Array of Directions with the value of every Direction constant.
     */
    private final static Direction[] VALUES = Direction.values();

    // -------------------------------------------------------------------------
    // NEXT
    // -------------------------------------------------------------------------

    /**
     * Returns the next Direction constant.
     *
     * @return    the next Direction constant
     */
    public Direction next() {
        return Direction.VALUES[
            (ordinal() + 1) % Direction.VALUES.length
        ];
    }

    // -------------------------------------------------------------------------
    // REVERSE
    // -------------------------------------------------------------------------

    /**
     * Returns the opposite Direction constant.
     *
     * @return    the opposite Direction constant
     */
    public Direction reverse() {
        return Direction.VALUES[
            (ordinal() + 2) % Direction.VALUES.length
        ];
    }

}   // Direction enum
