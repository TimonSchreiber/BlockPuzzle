package block;

import java.awt.Color;

import field.Direction;
import field.MovePattern;

/**
 * Record class representing a Block on a 2D-Plane.
 * <p>Each Block has a name, a color, a 'MainBlock'-flag, a MovePattern and a
 * PositionList.
 */
public record Block(
    String name,
    Color color,
    boolean isMainBlock,
    MovePattern movePattern,
    PositionList positions
) implements Comparable<Block> {

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /**
     * Class constructor from a BlockInfo record.
     *
     * @param blockInfo     The BlockInfo
     */
    public Block(BlockInfo blockInfo) {
        this(
            blockInfo.name(),
            blockInfo.color(),
            blockInfo.isMainBlock(),
            blockInfo.movePattern(),
            new PositionList(blockInfo.positionListInfo())
        );
    }

    /**
     * Copy constructor deep copying the PositionList.
     *
     * @param block        the Block to be copied.
     */
    public Block(Block block) {
        this(
            block.name,
            block.color,
            block.isMainBlock,
            block.movePattern,
            new PositionList(block.positions)
        );
    }

    // -------------------------------------------------------------------------
    // FORWARDING - METHODS
    // -------------------------------------------------------------------------

    /**
     * Checks if this Block occupies the Position.
     *
     * @param position  The Position
     * @return  {@code true} if the PositionList of this Block contains the
     *          Position, {@code false} otherwise.
     */
    public boolean contains(Position position) {
        return positions.contains(position);
    }

    /**
     * Checks if the specified Direction is part of this Blocks MovePattern. If
     * yes, move this Blocks PositionList towards the Direction.
     *
     * @param direction     The Direction to move towards.
     */
    public void moveTowards(Direction direction) {
        if (!movePattern.contains(direction)){
            return;
        }

        positions.moveTowards(direction);

        return;
    }

    // -------------------------------------------------------------------------
    // EQUALS AND HASH-CODE
    // -------------------------------------------------------------------------

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     *
     * This method ignores the String name and the Color.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        // Object must be Block at this point
        final Block other = (Block) obj;

        return (isMainBlock == other.isMainBlock)
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
     * This method ignores the String name and the Color.
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + Boolean.hashCode(isMainBlock);
        hash = PRIME * hash + ((  positions == null) ? 0 :   positions.hashCode());
        hash = PRIME * hash + ((movePattern == null) ? 0 : movePattern.hashCode());

        return hash;
    }

    // -------------------------------------------------------------------------
    // COMPARABLE
    // -------------------------------------------------------------------------

    /*
     * Blocks with 'isMainBlock' will come first, after that Blocks with a
     * bigger PositionsList.
     */
    @Override
    public int compareTo(Block other) {
        return (isMainBlock != other.isMainBlock)
                ? -Boolean.compare(isMainBlock, other.isMainBlock)
                : positions.compareTo(other.positions);
    }

}
