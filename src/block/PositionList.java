package block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import field.Direction;

public final class PositionList implements Iterable<Position>, Comparable<PositionList> {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    private final List<Position> positions;

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /** Old constructor: when not needed -> delete
     * TODO: Maybe put this into a static factory method? (single responsibility)
     * Class constructor form a {@code BlockInfo}.
     *
     * @param positionsInfo
     */
    // public PositionList(final PositionsInfo positionsInfo) {
    //     this.positions = new ArrayList<>();

    //     switch (positionsInfo.size()) {
    //         case 4:
    //             this.positions.add(
    //                 positionsInfo.position().moveTowards(
    //                     positionsInfo.direction(),
    //                     positionsInfo.direction().next()));
    //             // falls through
    //         case 3:
    //             this.positions.add(
    //                 positionsInfo.position().moveTowards(
    //                     positionsInfo.direction().next()));
    //             // falls through
    //         case 2:
    //             this.positions.add(
    //                 positionsInfo.position().moveTowards(
    //                     positionsInfo.direction()));
    //             // falls through
    //         default:
    //             this.positions.add(
    //                 positionsInfo.position());
    //             break;
    //     }

    //     Collections.sort(this.positions);
    // }

    /** TODO:
     * 
     */
    public PositionList(final PositionsInfo positionsInfo) {
        if (positionsInfo.isElbow()) {
            this.positions = this.newElbow(positionsInfo);
        } else {
            this.positions = this.newLine(positionsInfo);
        }
    }

    /**
     * TODO: only added to create the PositionList form teh winning conditions.
     * Class constrcutor form a {@code List} of {@code Positions}.
     * 
     * @param positions
     */
    public PositionList(final List<Position> positions) {
        this.positions = new ArrayList<>();

        for (final Position position : positions) {
            this.positions.add(new Position(position));
        }

        Collections.sort(this.positions);
    }

    /**
     * Copy constructor.
     *
     * @param positionList
     */
    public PositionList(final PositionList positionList) {
        this.positions = new ArrayList<>();
        
        for (final Position position : positionList) {
            this.positions.add(new Position(position));
        }
    }

    // -------------------------------------------------------------------------
    // FACTORYS
    // -------------------------------------------------------------------------

    /** TODO:
     * 
     */
    private List<Position> newLine(PositionsInfo positionsInfo) {
        List<Position> tmpList = new ArrayList<>();
        Position tmpPosition = positionsInfo.position();

        for (int i = 0; i < positionsInfo.size(); ++i) {
            tmpList.add(tmpPosition);
            tmpPosition = tmpPosition.moveTowards(positionsInfo.direction());
        }

        Collections.sort(tmpList);
        return tmpList;
    }

    /** TODO:
     * 
     */
    private List<Position> newElbow(final PositionsInfo positionsInfo) {
        List<Position> tmpList = new ArrayList<>();

        switch (positionsInfo.size()) {
            case 4:
                tmpList.add(
                    positionsInfo.position().moveTowards(
                        positionsInfo.direction(),
                        positionsInfo.direction().next()));
                // falls through
            case 3:
                tmpList.add(
                    positionsInfo.position().moveTowards(
                        positionsInfo.direction().next()));
                // falls through
            default:
                tmpList.add(
                    positionsInfo.position().moveTowards(
                        positionsInfo.direction()));
                tmpList.add(
                    positionsInfo.position());
                break;
        }

        Collections.sort(tmpList);
        return tmpList;
    }

    // -------------------------------------------------------------------------
    // MOVE TOWARDS
    // -------------------------------------------------------------------------

    /**
     * TODO
     *
     * @param directions
     */
    public void moveTowards(final Direction... directions) {
        this.positions.replaceAll(pos -> pos.moveTowards(directions));
    }

    // -------------------------------------------------------------------------
    // FORWARDING - METHODS
    // -------------------------------------------------------------------------

    public boolean contains(final Position position) { return this.positions.contains(position); }
    public boolean addAll(final PositionList positionList) { return this.positions.addAll(positionList.positions); }
    public int size() { return this.positions.size(); }

    // -------------------------------------------------------------------------
    // EQUALS AND HASH-CODE
    // -------------------------------------------------------------------------

    /** TODO
     * Overrides the equals method
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        // Object must be PositionList at this point
        final PositionList other = (PositionList) obj;

        return  ((this.positions == other.positions)
                || ((this.positions != null)
                    && this.positions.equals(other.positions)));
    }

    /** TODO
     * Overrides the hashCode method to match the equals method.
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + Integer.hashCode(this.positions.size());
        hash = PRIME * hash + ((this.positions == null)
                                    ? 0
                                    : this.positions.hashCode());

        return hash;
    }

    // =========================================================================
    // INTERFACE - METHODS
    // =========================================================================

    // -------------------------------------------------------------------------
    // ITERABLE
    // -------------------------------------------------------------------------

    /**
     * Returns an {@code Iterator} over all {@code Positions}.
     */
    @Override
    public Iterator<Position> iterator() {
        return this.positions.iterator();
    }

    // -------------------------------------------------------------------------
    // COMPARABLE
    // -------------------------------------------------------------------------

    /**
     *
     */
    @Override
    public int compareTo(PositionList other) {
        return (this.positions.size() != other.positions.size())
                ? -Integer.compare(this.positions.size(), other.positions.size()) // larger Blocks before smaller Blocks
                : this.positions.get(0).compareTo(other.positions.get(0));
    }

    // =========================================================================

}   // PositionList class
