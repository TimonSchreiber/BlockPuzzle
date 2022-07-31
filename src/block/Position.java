package block;

import field.Directions;

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
     * TODO: add description
     *
     * @param directions
     * @return
     */
    public Position moveTowards(final Directions... directions) {
        int newX = this.x;
        int newY = this.y;

        for (final Directions direction : directions) {
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

    /** TODO
     *
     */
    @Override
    public int compareTo(Position other) {
        return (this.y == other.y)
            ? Integer.compare(this.x, other.x)
            : Integer.compare(this.y, other.y);
    }

    // =========================================================================

}   // Position record
