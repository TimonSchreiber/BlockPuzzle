package block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import field.Directions;

public final class PositionList implements Iterable<Position>, Comparable<PositionList> {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    private final List<Position> positions;

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /**
     * TODO: Maybe put this into a static factory method? (single responsibility)
     * Class constructor form a {@code BlockInfo}.
     *
     * @param blockInfo
     */
    public PositionList(final BlockInfo blockInfo) {
        this.positions = new ArrayList<>();

        switch (blockInfo.size()) {
            case 4:
                this.positions.add(
                    blockInfo.position().moveTowards(
                        blockInfo.direction(),
                        blockInfo.direction().next()));
                // falls through
            case 3:
                this.positions.add(
                    blockInfo.position().moveTowards(
                        blockInfo.direction().next()));
                // falls through
            case 2:
                this.positions.add(
                    blockInfo.position().moveTowards(
                        blockInfo.direction()));
                // falls through
            default:
                this.positions.add(
                    blockInfo.position());
                break;
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
    // MOVE TOWARDS
    // -------------------------------------------------------------------------

    /**
     * TODO
     *
     * @param directions
     */
    public void moveTowards(final Directions... directions) {
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

        return    ((this.positions == other.positions)
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

        hash = PRIME * hash + this.positions.size();
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
     * Returns an {@code Iterator} over all {@code Position}s.
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
