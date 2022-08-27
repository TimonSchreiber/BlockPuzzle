package block;

import field.Direction;

/**
 * Represents a pair of x- and y-values in a 2D-plane.
 */
public record Position(int x, int y) implements Comparable<Position> {

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Copy constructor.
     *
     * @param position
     */
    public Position(Position position) {
        this(position.x, position.y);
    }

    // -------------------------------------------------------------------------
    // MOVE TOWARDS
    // -------------------------------------------------------------------------

    /**
     * Returns a new Position with changed x-, and y-values according to the
     * Directions given as arguments.
     *
     * @param directions    one or more Directions
     * @return              a new Position
     */
    public Position moveTowards(final Direction... directions) {
        int x = this.x;
        int y = this.y;

        for (final Direction direction : directions) {
            switch (direction) {
                case R -> ++x;
                case D -> --y;
                case L -> --x;
                case U -> ++y;
            }
        }

        return new Position(x, y);
    }

    // -------------------------------------------------------------------------
    // COMPARABLE
    // -------------------------------------------------------------------------

    /**
     * Overrides the compareTo() method of the interface {@code comparable}.
     * First the y-values are compared, then the x-values.
     */
    @Override
    public int compareTo(Position other) {
        return (y != other.y)
            ? Integer.compare(y, other.y)
            : Integer.compare(x, other.x);
    }

}   // Position record
