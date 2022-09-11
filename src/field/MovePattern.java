package field;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Record class with Lists of Directions a Block can Move towards.
 */
public record MovePattern(List<Direction> movePattern)
    implements Iterable<Direction> {

    // -------------------------------------------------------------------------
    // INSTANCES
    // -------------------------------------------------------------------------

    /** Empty Direction List */
    public static final
    MovePattern NO_DIRECTIONS =
        new MovePattern(
            Collections.emptyList()
        );

    /** Only Down, and Up Directions*/
    public static final
    MovePattern DOWN_UP_DIRECTIONS =
        new MovePattern(
            List.of(
                Direction.D,
                Direction.U
            )
        );

    /** Only Right, and Left Directions */
    public static final
    MovePattern RIGHT_LEFT_DIRECTIONS =
        new MovePattern(
            List.of(
                Direction.R,
                Direction.L
            )
        );

    /** Right, Down, Left, and Up Directions */
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
