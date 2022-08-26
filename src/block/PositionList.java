package block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import field.Direction;

/**
 * Wrapper around an ArrayList of Positions.
 */
public final class PositionList implements Iterable<Position>, Comparable<PositionList> {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /** ArrayList of Positions */
    private final List<Position> positions;

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /**
     * Class constructor from a PosittionsInfo object. Delegates
     * the construction to one of two private factory methods.
     *
     * @param positionsInfo     The PositionsInfo
     */
    public PositionList(final PositionListInfo positionsInfo) {
        if (positionsInfo.isElbow()) {
            positions = newElbow(positionsInfo);
        } else {
            positions = newRectangle(positionsInfo);
        }
    }

    /**
     * Class constrcutor form a List of Positions.
     *
     * @param positions     A List of Positions
     */
    public PositionList(final List<Position> positions) {
        this.positions = new ArrayList<>(positions);

        // make sure this List is sorted.
        Collections.sort(this.positions);
    }

    /**
     * Copy constructor.
     *
     * @param positions     A PositionList
     */
    public PositionList(final PositionList positions) {
        this.positions = new ArrayList<>();

        for (final Position position : positions) {
            this.positions.add(new Position(position));
        }
    }

    // -------------------------------------------------------------------------
    // FACTORYS
    // -------------------------------------------------------------------------

    /**
     * Returns a new List of Positions where all Positions form a straight line.
     */
    private List<Position> newRectangle(PositionListInfo positionsInfo) {
        final List<Position> newList = new ArrayList<>();
        Position newPosition = positionsInfo.position();

        for (int i = 0; i < positionsInfo.size(); ++i) {
            newList.add(newPosition);
            newPosition = newPosition.moveTowards(positionsInfo.direction());
        }

        // make sure the List is sorted.
        Collections.sort(newList);

        return newList;
    }

    /**
     * Returns a new List of Positions where the Positions form a shape that
     * bends like a L or a big square.
     */
    private List<Position> newElbow(final PositionListInfo positionsInfo) {
        final List<Position> newList = new ArrayList<>();

        switch (positionsInfo.size()) {
            case 4:     // add the 'diagonal' Block
                newList.add(
                    positionsInfo.position().moveTowards(
                        positionsInfo.direction(),
                        positionsInfo.direction().next()));
                // falls through
            case 3:     // add the 'next' Block
                newList.add(
                    positionsInfo.position().moveTowards(
                        positionsInfo.direction().next()));
                // falls through
            default:    // add the 'direction' and 'this position' Block
                newList.add(
                    positionsInfo.position().moveTowards(
                        positionsInfo.direction()));
                newList.add(
                    positionsInfo.position());
                break;
        }

        // make sure the List is sorted.
        Collections.sort(newList);

        return newList;
    }

    // -------------------------------------------------------------------------
    // MOVE TOWARDS
    // -------------------------------------------------------------------------

    /**
     * Replaces every Position in this List with a new Position resulting from
     * moving it towards the specified Directions.
     *
     * @param directions    One or more Directions.
     */
    public void moveTowards(final Direction... directions) {
        positions.replaceAll(pos -> pos.moveTowards(directions));
    }

    // -------------------------------------------------------------------------
    // FORWARDING - METHODS
    // -------------------------------------------------------------------------

    /**
     * Forwards to the {@link List#contains(Position)} method.
     *
     * @param position  Positions whose presence is to be tested.
     * @return          {@code true} if this PositionList contains the Position,
     *                  {@code false} otherwise.
     * @see java.util.List#contains(Object)
     */
    public boolean contains(final Position position) {
        return positions.contains(position);
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

        // Object must be PositionList at this point
        final PositionList other = (PositionList) obj;

        return  ((positions == other.positions)
                || ((positions != null)
                    && positions.equals(other.positions)));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + ((positions == null) ? 0 : positions.hashCode());

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
                PositionList [positions=%s]\
                """
                .formatted(positions);
    }

    // =========================================================================
    // INTERFACE - METHODS
    // =========================================================================

    // -------------------------------------------------------------------------
    // ITERABLE
    // -------------------------------------------------------------------------

    /**
     * Returns an Iterator over all Positions.
     * 
     * @see java.util.List#iterator()
     */
    @Override
    public Iterator<Position> iterator() {
        return positions.iterator();
    }

    // -------------------------------------------------------------------------
    // COMPARABLE
    // -------------------------------------------------------------------------

    /**
     * Compares two PositionLists. First compares the size (larger Lists before
     * smaller ones), and then compares the first Position in each List.
     */
    @Override
    public int compareTo(PositionList other) {
        return (positions.size() != other.positions.size())
                ? -Integer.compare(positions.size(), other.positions.size())
                : positions.get(0).compareTo(other.positions.get(0));
    }

}   // PositionList class
