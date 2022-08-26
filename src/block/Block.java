package block;

import java.awt.Color;

import field.Direction;
import field.MovePattern;

/**
 * Immutable class representing a Block on a 2D-Plane.
 */
public final class Block implements Comparable<Block> {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /** The name of this Block. */
    private final String name;

    /** The Color of this Block. */
    private final Color color;

    /** Indicates if this Block is a MainBlock. */
    private final boolean isMainBlock;

    /** Defines how this Block can move in a 2D-plane. */
    private final MovePattern movePattern;

    /** A List of all Positions this Block has. */
    private final PositionList positions;

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /**
     * Class constructor from a BlockInfo record.
     *
     * @param blockInfo     The BlockInfo
     */
    public Block(final BlockInfo blockInfo) {
        this.name           = blockInfo.name();
        this.color          = blockInfo.color();
        this.isMainBlock    = blockInfo.isMainBlock();
        this.movePattern    = blockInfo.movePattern();
        this.positions      = new PositionList(blockInfo.positionsInfo());
    }

    /**
     * Copy constructor.
     *
     * @param block        the Block
     */
    public Block(final Block block) {
        this.name           = block.name;
        this.color          = block.color;
        this.isMainBlock    = block.isMainBlock;
        this.movePattern    = block.movePattern;
        this.positions      = new PositionList(block.positions);
    }

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    /**
     * Returns the boolean isMainBlock field of this Block.
     *
     * @return  {@code true} if this Block is a MainBlock, {@code false}
     *          otherwise.
     */
    public boolean isMainBlock() {
        return isMainBlock;
    }

    /**
     * Returns a String with the name of this Block.
     *
     * @return  A String with this BlockName
     */
    public String name() {
        return name;
    }

    /**
     * Returns the Color of this Block.
     *
     * @return  The Color
     */
    public Color color() {
        return color;
    }

    /**
     * Returns a new PositionList equal to the PositionList of this Block.
     *
     * @return  The PositionList of this Block
     */
    public PositionList positions() {
        return new PositionList(positions);
    }

    /**
     * Returns the MovePattern of this Block.
     *
     * @return  The MovePattern
     */
    public MovePattern movePattern() {
        return movePattern;
    }

    // -------------------------------------------------------------------------
    // FORWARDING - METHODS
    // -------------------------------------------------------------------------

    /**
     * Checks if this Block occupies the Position.
     *
     * @param position  The Position
     * @return  {@code true} if the PositionList of this Block contains the
     *          Position, {@code false} otehrwise.
     */
    public boolean containsPosition(final Position position) {
        return positions.contains(position);
    }

    /**
     * Changes every Position in the PositionList of this
     * Block.
     *
     * @param directions    One or more Directions this Block moves towards.
     */
    public void moveTowards(final Direction... directions) {
        if (!movePattern.containsAll(directions)){
            return;
        }

        positions.moveTowards(directions);

        return;
    }

    // -------------------------------------------------------------------------
    // EQUALS AND HASH-CODE
    // -------------------------------------------------------------------------

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     *
     * This method ignores the String BlockName and the Color.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        // Object must be Block at this point
        final Block other = (Block) obj;

        return  (isMainBlock == other.isMainBlock)
                && ((positions == other.positions)
                    || ((positions != null)
                        && positions.equals(other.positions)))
                && ((movePattern == other.movePattern)
                    || ((movePattern != null)
                        && movePattern.equals(other.movePattern)));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     *
     * This method ignores the String BlockName and the Color.
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + Boolean.hashCode(isMainBlock);
        hash = PRIME * hash + ((positions == null) ? 0 : positions.hashCode());
        hash = PRIME * hash + ((movePattern == null) ? 0 : movePattern.hashCode());

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
                Block [name=%s, \
                color=%s, \
                isMainBlock=%s, \
                movePattern=%s, \
                positions=%s]\
                """
                .formatted(name, color, isMainBlock, movePattern, positions);
    }

    // =========================================================================
    // INTERFACE - METHODS
    // =========================================================================

    // -------------------------------------------------------------------------
    // COMPARABLE
    // -------------------------------------------------------------------------

    /*
     * Blocks with 'isMainBlock' will come first, after that Blocks with a
     * bigger PositionsList.
     */
    @Override
    public int compareTo(final Block other) {
        return (isMainBlock != other.isMainBlock)
                ? -Boolean.compare(isMainBlock, other.isMainBlock)
                : positions.compareTo(other.positions);
    }

}    // Block class
