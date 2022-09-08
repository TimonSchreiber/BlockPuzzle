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
    private final List<Position> positionList;

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /**
     * Class constrcutor form a List of Positions.
     *
     * @param positionList  A List of Positions
     */
    public PositionList(List<Position> positionList) {
        this.positionList = new ArrayList<>(positionList);

        // make sure this List is sorted.
        Collections.sort(this.positionList);
    }

    /**
     * Class constructor from a PosittionsInfo object. Delegates
     * the construction to one of two private factory methods.
     *
     * @param positionListInfo  The PositionListInfo
     */
    public PositionList(PositionListInfo positionListInfo) {
        this(
            positionListInfo.isElbow()
                ? newElbow(    positionListInfo)
                : newRectangle(positionListInfo)
        );
    }

    /** TODO: can the 'canonical' constructor be used here?
     * Copy constructor.
     *
     * @param positionList  A PositionList
     */
    public PositionList(PositionList positionList) {
        this.positionList = new ArrayList<>();

        for (Position position : positionList) {
            this.positionList.add(new Position(position));
        }
    }

    // -------------------------------------------------------------------------
    // FACTORYS
    // -------------------------------------------------------------------------

    /**
     * Returns a new List of Positions where all Positions form a straight line.
     */
    private static List<Position> newRectangle(PositionListInfo positionListInfo) {
        final List<Position> newList = new ArrayList<>();
        Position newPosition = positionListInfo.position();

        // add the current (first) Position to the List.
        newList.add(newPosition);
        
        // if the size is > 1: move the newPosition towards the Direction and
        // add it to the List.
        for (int i = 1; i < positionListInfo.size(); ++i) {
            newPosition = newPosition.moveTowards(positionListInfo.direction());
            newList.add(newPosition);
        }

        return newList;
    }

    /**
     * Returns a new List of Positions where the Positions form a shape that
     * bends like an L (size 3) or a big square (size 4).
     *               X _             X X
     *               X X             X X
     * Note: This method should only get a PositionListInfo with size 3 or 4.
     */
    private static List<Position> newElbow(PositionListInfo positionListInfo) {
        final List<Position> newList = new ArrayList<>();
        final Position newPosition = positionListInfo.position();

        switch (positionListInfo.size()) {
            // add the 'diagonal Direction' Position to the List.
            case 4:
                newList.add(
                    newPosition.moveTowards(
                        positionListInfo.direction(),
                        positionListInfo.direction().next()
                    )
                );
                // falls through

            // add the 'next Direction' Position to the List.
            case 3:
                newList.add(
                    newPosition.moveTowards(
                        positionListInfo.direction().next()
                    )
                );
                // falls through

            // add the 'Direction' and 'current' Position to the List.
            default:
                newList.add(
                    newPosition.moveTowards(
                        positionListInfo.direction()
                    )
                );
                newList.add(
                    newPosition
                );
                break;
        }

        return newList;
    }

    // -------------------------------------------------------------------------
    // MOVE TOWARDS
    // -------------------------------------------------------------------------

    /**
     * Replaces every Position in this List with a new Position resulting from
     * moving it towards the specified Direction.
     *
     * @param direction     One or more Direction.
     */
    public void moveTowards(Direction direction) {
        positionList.replaceAll(
            position -> position.moveTowards(direction)
        );
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
    public boolean contains(Position position) {
        return positionList.contains(position);
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

        return (positionList == other.positionList)
                || ((positionList != null)
                    && positionList.equals(other.positionList));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + ((positionList == null) ? 0 : positionList.hashCode());

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
                PositionList [positionList=%s]\
                """.formatted(positionList);
    }

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
        return positionList.iterator();
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
        return (positionList.size() != other.positionList.size())
                ? -Integer.compare(positionList.size(), other.positionList.size())
                : positionList.get(0).compareTo(other.positionList.get(0));
    }

}
