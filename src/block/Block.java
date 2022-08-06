package block;

import java.awt.Color;

import field.Direction;
import field.MovePattern;

/**
 * TODO: maybe change the color creation, so that we get a name and create the color, and not a color and create the name
 */
public final class Block implements Comparable<Block> {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    private final boolean isMainBlock;  // TODO: new field form BlockInfo; add to #equals()
    private final String blockName;
    private final Color color;
    private final PositionList positionList;
    private final MovePattern movePattern;  // TODO: new field form BlockInfo // FIXME: Maybe add to #equals() und #hashCode()???

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /**
     * Class constructor with size and {@code BlockName}.
     *
     * @param blockInfo
     */
    // public Block(final BlockInfo blockInfo) {
    //     this.blockName = BlockType.getName(blockInfo.size())
    //                     + ++Block.BLOCK_COUNTER[blockInfo.size() - 1];
    //     this.color = Block.createColor(blockInfo.size());
    //     this.positionList = new PositionList(blockInfo);
    // }

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
     * @param block        the {@code Block}
     */
    public Block(final Block block) {
        this.isMainBlock = block.isMainBlock;
        this.blockName = block.blockName;
        this.color = block.color;
        this.positionList = new PositionList(block.positionList);
        this.movePattern = block.movePattern;
    }

    // -------------------------------------------------------------------------
    // GETTERS TODO: Can these methods be substituded with a frowarding method?
    // -------------------------------------------------------------------------

    /** TODO:
     * @return
     */
    public String blockName() {
        return this.blockName;
    }

    /** TODO:
     * @return
     */
    public Color color() {
        return new Color(this.color.getRGB());
    }

    /** TODO
     * @return
     */
    public PositionList positionList() {
        return new PositionList(this.positionList);
    }

    public MovePattern movePattern() {
        return this.movePattern;
    }
    
    // -------------------------------------------------------------------------
    // FORWARDING - METHODS
    // -------------------------------------------------------------------------
    
    public boolean containsPosition(final Position position) { return this.positionList.contains(position); }
    public void moveTowards(final Direction... directions) { this.positionList.moveTowards(directions); }

    // -------------------------------------------------------------------------
    // EQUALS AND HASH-CODE
    // -------------------------------------------------------------------------

    /**
     * Overrides the equals method, so that neither the name, nor the color will
     * be used in the equals method.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        // Object must be Block at this point
        final Block other = (Block) obj;

        return  (this.isMainBlock == other.isMainBlock)     // TODO: added with new BlockInfo fields: check if it works
                && ((this.positionList == other.positionList)
                    || ((this.positionList != null)
                        && this.positionList.equals(other.positionList)))
                && ((this.movePattern == other.movePattern) // TODO: added with new BlockInfo fields: check if it works
                    || ((this.movePattern != null)
                        && this.movePattern.equals(other.movePattern)));
    }

    /**
     * Overrides the hashCode method to match the equals method.
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + Boolean.hashCode(this.isMainBlock);
        hash = PRIME * hash + ((this.positionList == null)
                                    ? 0
                                    : this.positionList.hashCode());
        hash = PRIME * hash + ((this.movePattern == null)   // TODO: added with new BlockInfo fields: check if it works
                                    ? 0
                                    : this.movePattern.hashCode());

        return hash;
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
    public int compareTo(Block other) {
        // TODO: used to be "return this.positionList.compareTo(other.positionList)"
        return (this.isMainBlock != other.isMainBlock)
                ? -Boolean.compare(this.isMainBlock, other.isMainBlock) // isMainBlock "first" (small compare value)
                : this.positionList.compareTo(other.positionList);
    }

    // =========================================================================

}    // Block class
