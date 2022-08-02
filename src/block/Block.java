package block;

import java.awt.Color;

import field.Direction;
import field.MovePattern;

/**
 * TODO: maybe change the color creation, so that we get a name and create the color, and not a color and create the name
 */
public final class Block implements Comparable<Block> {

    // -------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -------------------------------------------------------------------------

    // TODO: change the way a Block is created; take a name and or color infer the size
    // and not a size and infer the name/color
    /** static counter for different types of {@code Block}s */
    // private static final int[] BLOCK_COUNTER = new int[BlockType.getSize()];

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    private final boolean isMainBlock;  // TODO: new field form BlockInfo; add to #equals()
    private final String blockName;
    private final Color color;
    private final PositionList positionList;
    private final MovePattern movePattern;  // TODO: new field form BlockInfo; FIXME: Maybe add to #equals() und #hashCode()???

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
    // CREATE COLOR
    // -------------------------------------------------------------------------

    /**
     * TODO: deprecated?
     * Creates the {@code Color} for the {@code Block} by repeatedly using
     * {@link Color#darker()} for each {@code Block} of the same
     * {@code BlockType} that was already created.
     *
     * @param size    the size
     * @return        a darker {@code Color}
     */
    // private static Color createColor(final int size) {
    //     Color tmpClr = BlockType.getColor(size);

    //     for (int i = 1; i < Block.BLOCK_COUNTER[size - 1]; i++) {
    //         tmpClr = tmpClr.darker();
    //     }

    //     return tmpClr;
    // }

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
    // MOVE TOWARDS
    // -------------------------------------------------------------------------

    /** TODO is void here OK? or do i need to return a new Block?
     *
     * Moves this {@code Block} by changing every {@code Position}
     * to an adjacent coordinate.
     *
     * @param directions        the {@code Direction}s
     */
    public void moveTowards(final Direction... directions) {
        this.positionList.moveTowards(directions);
        return;
    }

    // -------------------------------------------------------------------------
    // FORWARDING - METHODS
    // -------------------------------------------------------------------------

    public boolean containsPosition(final Position position) { return this.positionList.contains(position); }

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
        Block other = (Block) obj;

        return  (this.isMainBlock == other.isMainBlock)     // TODO: added with new BlockInfo fields
                && (this.positionList.size() == other.positionList.size())
                && ((this.positionList == other.positionList)
                    || ((this.positionList != null)
                        && this.positionList.equals(other.positionList)));
    }

    /**
     * Overrides the hashCode method to match the equals method.
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + Boolean.hashCode(this.isMainBlock);
        hash = PRIME * hash + Integer.hashCode(this.positionList.size());
        hash = PRIME * hash + ((this.positionList == null)
                                    ? 0
                                    : this.positionList.hashCode());

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
