package field;

import java.util.Iterator;
import java.util.List;

public final class MovePattern implements Iterable<Direction> {

    // -------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -------------------------------------------------------------------------

    /** {@code List} of {@code Directions} */
    private final List<Direction> directions;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /** TODO
     * Private constructor.
     */
    private MovePattern(List<Direction> directions) {
        this.directions = directions;
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
    // Two Steps in every possible (and useful) combination of Directions
    // TODO: Make this into a MovePattern.
    // not working because this is a'List of List of Directions' instead of a 'List of Directions'

    // public static final
    // List<List<Direction>> ALL_DIRECTIONS_PLUS =
    //     List.of(
    //         List.of(Direction.R),
    //         List.of(Direction.D),
    //         List.of(Direction.L),
    //         List.of(Direction.U),
    //         List.of(Direction.R, Direction.R),
    //         List.of(Direction.R, Direction.D),
    //         List.of(Direction.R, Direction.U),
    //         List.of(Direction.D, Direction.R),
    //         List.of(Direction.D, Direction.D),
    //         List.of(Direction.D, Direction.L),
    //         List.of(Direction.L, Direction.D),
    //         List.of(Direction.L, Direction.L),
    //         List.of(Direction.L, Direction.U),
    //         List.of(Direction.U, Direction.R),
    //         List.of(Direction.U, Direction.L),
    //         List.of(Direction.U, Direction.U)
    //     );


    // -------------------------------------------------------------------------
    // EQUALS AND HASH-CODE
    // -------------------------------------------------------------------------

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        // Object must be Block at this point
        MovePattern other = (MovePattern) obj;

        return  ((this.directions == other.directions)
                || ((this.directions != null)
                    && this.directions.equals(other.directions)));
    }

    /**
     * Overrides the hashCode method to match the equals method.
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + ((this.directions == null)
                                    ? 0
                                    : this.directions.hashCode());

        return hash;
    }

    
    // =========================================================================
    // INTERFACE - METHODS
    // =========================================================================

    // -------------------------------------------------------------------------
    // ITERABLE
    // -------------------------------------------------------------------------

    /**
     * Returns an {@code Iterator} over all {@code Directions}.
     */
    @Override
    public Iterator<Direction> iterator() {
        return this.directions.iterator();
    }

    // =========================================================================

}   // Move Pattern class
