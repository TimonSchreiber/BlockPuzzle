package field;

import java.util.Iterator;
import java.util.List;

/**
 * Stores the Directions a Block can Move.
 */
public final class MovePattern implements Iterable<Direction> {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /** List of Directions. */
    private final List<Direction> movePattern;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Private constructor.
     */
    private MovePattern(List<Direction> directions) {
        this.movePattern = directions;
    }


    // -------------------------------------------------------------------------
    // INSTANCES
    // -------------------------------------------------------------------------

    /** Empty Direction List */
    public static final
    MovePattern NO_DIRECTIONS =
        new MovePattern(
            List.of()
        );

    /** Only Down and Up Directions*/
    public static final
    MovePattern DOWN_UP_DIRECTIONS =
        new MovePattern(
            List.of(
                Direction.D,
                Direction.U
            )
        );

    /** Only Right and Left Directions */
    public static final
    MovePattern RIGHT_LEFT_DIRECTIONS =
        new MovePattern(
            List.of(
                Direction.R,
                Direction.L
            )
        );

    /** Right, Down, Left, and Up */
    public static final
    MovePattern ALL_DIRECTIONS =
        new MovePattern(
            List.of(
                Direction.R,
                Direction.D,
                Direction.L,
                Direction.U
            )
        );



    // -------------------------------------------------------------------------
    // Two Steps in every possible (and useful) combination of Directions.
    // TODO: Make this into a MovePattern.
    // not working because MovePattern is a 'List of Directions' and not a 'List of Direction Arrays'.

    // public static final
    // List<List<Direction>> ALL_DIRECTIONS_PLUS =
    //     List.of(
    //         new Direction[]{Direction.R},
    //         new Direction[]{Direction.D},
    //         new Direction[]{Direction.L},
    //         new Direction[]{Direction.U},
    //         new Direction[]{Direction.R, Direction.R},
    //         new Direction[]{Direction.R, Direction.D},
    //         new Direction[]{Direction.R, Direction.U},
    //         new Direction[]{Direction.D, Direction.R},
    //         new Direction[]{Direction.D, Direction.D},
    //         new Direction[]{Direction.D, Direction.L},
    //         new Direction[]{Direction.L, Direction.D},
    //         new Direction[]{Direction.L, Direction.L},
    //         new Direction[]{Direction.L, Direction.U},
    //         new Direction[]{Direction.U, Direction.R},
    //         new Direction[]{Direction.U, Direction.L},
    //         new Direction[]{Direction.U, Direction.U}
    //     );


    // -------------------------------------------------------------------------
    // FORWARDING METHODS
    // -------------------------------------------------------------------------

    /**
     * Checks if this MovePattern conatins all the specified Directions.
     *
     * @param direction    The Directions whose presence is to be tested.
     * @return              {@code true} if this MovePattern conatins all the
     *                      specified Directions, {@code false} otherwise.
     * @see java.util.List#contains(Object)
     */
    public boolean contains(Direction direction) {
        return this.movePattern.contains(direction);
    }

    // -------------------------------------------------------------------------
    // EQUALS AND HASH-CODE
    // -------------------------------------------------------------------------

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        // Object must be MovePattern at this point
        final MovePattern other = (MovePattern) obj;

        return (movePattern == other.movePattern)
                || ((movePattern != null)
                    && movePattern.equals(other.movePattern));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + ((movePattern == null) ? 0 : movePattern.hashCode());

        return hash;
    }

    // -------------------------------------------------------------------------
    // TO STRING
    // -------------------------------------------------------------------------

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return """
                MovePattern [movePattern=%s]\
                """.formatted(movePattern);
    }

    // -------------------------------------------------------------------------
    // ITERABLE
    // -------------------------------------------------------------------------

    /**
     * Returns an Iterator over all Directions.
     *
     * @see java.util.List#iterator()
     */
    @Override
    public Iterator<Direction> iterator() {
        return movePattern.iterator();
    }

}
