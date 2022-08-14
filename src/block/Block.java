package block;

import java.awt.Color;

import field.Direction;
import field.MovePattern;

public final class Block implements Comparable<Block> {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /** Indicates if this Block is a MainBlock. */
    private final boolean isMainBlock;

    /** The name of this Block. */
    private final String blockName;

    /** The Color of this Block. */
    private final Color color;

    /** A List of all Positions this Block has. */
    private final PositionList positionList;

    /** Defines how this Block can move in a 2D-plane. */
    private final MovePattern movePattern;

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /**
     * Class constructor from a BlockInfo record.
     * 
     * @param blockInfo     The BlockInfo
     */
    public Block(final BlockInfo blockInfo) {
        this.isMainBlock = blockInfo.isMainBlock();
        this.blockName = blockInfo.name();
        this.color = blockInfo.color();
        this.positionList = new PositionList(blockInfo.positionsInfo());
        this.movePattern = blockInfo.movePattern();
    }

    /**
     * Copy constructor.
     *
     * @param block        the Block
     */
    public Block(final Block block) {
        this.isMainBlock = block.isMainBlock;
        this.blockName = block.blockName;
        this.color = block.color;
        this.positionList = new PositionList(block.positionList);
        this.movePattern = block.movePattern;
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
        return this.isMainBlock;
    }

    /**
     * Returns a String with the name of this Block.
     * 
     * @return  A String with this BlockName
     */
    public String blockName() {
        return this.blockName;
    }

    /**
     * Returns the Color of this Block.
     * 
     * @return  The Color
     */
    public Color color() {
        return this.color;
    }

    /**
     * Returns a new PositionList equals to the PositionList of this Block.
     * 
     * @return  The PositionList of this Block
     */
    public PositionList positionList() {
        return new PositionList(this.positionList);
    }

    /**
     * Returns the MovePattern of this Block.
     * 
     * @return  The MovePattern
     */
    public MovePattern movePattern() {
        return this.movePattern;
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
        return this.positionList.contains(position);
    }

    /**
     * Changes every Position in the PositionList of this
     * Block.
     * 
     * @param directions    One or more Directions this Block moves towards.
     */
    public void moveTowards(final Direction... directions) {
        this.positionList.moveTowards(directions);
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

        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        // Object must be Block at this point
        final Block other = (Block) obj;

        return  (this.isMainBlock == other.isMainBlock)
                && ((this.positionList == other.positionList)
                    || ((this.positionList != null)
                        && this.positionList.equals(other.positionList)))
                && ((this.movePattern == other.movePattern)
                    || ((this.movePattern != null)
                        && this.movePattern.equals(other.movePattern)));
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

        hash = PRIME * hash + Boolean.hashCode(this.isMainBlock);
        hash = PRIME * hash + ((this.positionList == null) ? 0 : this.positionList.hashCode());
        hash = PRIME * hash + ((this.movePattern == null) ? 0 : this.movePattern.hashCode());

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
        return "Block [blockName=" + blockName + ", color=" + color + ", isMainBlock=" + isMainBlock + ", movePattern="
                + movePattern + ", positionList=" + positionList + "]";
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
        return (this.isMainBlock != other.isMainBlock)
                ? -Boolean.compare(this.isMainBlock, other.isMainBlock)
                : this.positionList.compareTo(other.positionList);
    }

    // =========================================================================

}    // Block class
