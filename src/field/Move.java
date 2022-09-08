package field;

/**
 * Holds a name (of a Block) and a Direction.
 */
public record Move(
    String name,
    Direction direction
) {

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Copy constructor.
     *
     * @param move
     */
    public Move(Move move) {
        this(move.name, move.direction);
    }

    // -------------------------------------------------------------------------
    // REVERSE
    // -------------------------------------------------------------------------

    /**
     * Gets a new Move with the reversed Direction of this Move.
     *
     * @return    The new Move with reversed Directions.
     */
    public Move reverse() {
        return new Move(name, direction.reverse());
    }

}
