package field;

public record Move(String name, Directions direction) {

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
     * Gets a new {@code Move} with the reversed {@code Direction} of
     * this {@code Move}.
     * 
     * @return    the new {@code Move} with reversed {@code Direction}
     */
    public Move reverse() {
        return new Move(this.name, this.direction.reverse());
    }

    // =========================================================================

}   // Move record
