package block;

import field.Direction;

public record Position(int x, int y) implements Comparable<Position> {

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Copy constructor.
     *
     * @param position
     */
    public Position (Position position) {
        this(position.x, position.y);
    }

    // -------------------------------------------------------------------------
    // MOVE TOWARDS
    // -------------------------------------------------------------------------

    /**
     * Returns a new {@code Position} with changed x-, and y-values according to the
     * {@code Directions} given as arguments.
     *
     * @param directions    one or more {@code Directions}.
     * @return              a new Position
     */
    public Position moveTowards(final Direction... directions) {
        int newX = this.x;
        int newY = this.y;

        for (final Direction direction : directions) {
            switch (direction) {
                case R: newX++; break;
                case D: newY--; break;
                case L: newX--; break;
                case U: newY++; break;
            }
        }

        return new Position(newX, newY);
    }

    // =========================================================================
    // INTERFACE - METHODS
    // =========================================================================

    // -------------------------------------------------------------------------
    // COMPARABLE
    // -------------------------------------------------------------------------

    /**
     * Overrides the compareTo() method of the interface {@code comparable}.
     * First the y-values are compared, then the x-values.
     */
    @Override
    public int compareTo(Position other) {
        return (this.y == other.y)
            ? Integer.compare(this.x, other.x)
            : Integer.compare(this.y, other.y);
    }

    // =========================================================================

}   // Position record
