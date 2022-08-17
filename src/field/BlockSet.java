package field;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import block.Block;
import block.Position;

/**
 * Wrapper around a Set of Blocks.
 */
public final class BlockSet implements Iterable<Block> {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /** TreeSet of Blocks. */
    private final Set<Block> blockSet;

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /**
     * Class constructor.
     */
    public BlockSet() {
        this.blockSet = new TreeSet<>();
    }

    /**
     * Copy constructor.
     *
     * @param blockSet    the BlockSet
     */
    public BlockSet(final BlockSet blockSet) {
        this.blockSet = new TreeSet<>();

        for (final Block block : blockSet) {
            this.blockSet.add(new Block(block));
        }
    }

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    /**
     * Checks if there is a Block in this BlockSet with the same x- and
     * y-coordinates as one of its Blocks.
     *
     * @param position  the Position
     * @return          {@code true} if there is one Block which has these
     *                  x- and y-coordinates, {@code false} otherwise
     */
    public boolean isBlock(final Position position) {
        // return this.blocks.stream()
        //         .anyMatch(block -> block.containsPosition(position));

        for (final Block block : blockSet) {
            if (block.containsPosition(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the Name of the Block with this Position.
     *
     * @param position  The Position
     * @return          The name of the Block if there is one at this Position,
     *                  {@code null} otherwise.
     */
    public String getBlockName(final Position position) {
        // return this.blocks.stream()
        //         .filter(block -> block.positionList().contains(position))
        //         .findAny().get().blockName();

        for (final Block block : blockSet) {
            if (block.containsPosition(position)) {
                return block.blockName();
            }
        }

        return null;
    }

    /**
     * Returns the Block with the specified name.
     *
     * @param blockName     Name of the Block.
     * @return              The Block with the specified name, or {@code null}
     *                      if the Block does not exist.
     */
    public Block getBlock(final String name) {
        // return this.blocks.stream()
        //         .filter(block -> block.blockName().equals(name))
        //         .findAny().get();

        for (final Block block : blockSet) {
            if (block.blockName().equals(name)) {
                return new Block(block);
            }
        }

        return null;
    }

    /**
     * Returns a BlockSet containing all the MainBlocks.
     *
     * @return  A BlockSet with only MainBlocks
     */
    public BlockSet getMainBlocks() {
        final BlockSet mainBlocks = new BlockSet();

        for (Block block : blockSet) {

            if (block.isMainBlock()) {
                mainBlocks.add(block);
            } else {
                break;
            }
        }

        return mainBlocks;
    }

    // -------------------------------------------------------------------------
    //  MOVE
    // -------------------------------------------------------------------------

    /**
     * Moves the Block specified by the Move object in this BlockSet.
     *
     * @param move  The Move to make
     */
    public void makeMove(final Move move) {
        for (final Block block : blockSet) {
            if (block.blockName().equals(move.name())) {
                block.moveTowards(move.direction());
                return;
            }
        }
        return;
    }

    // -------------------------------------------------------------------------
    // FORWARDING - METHODS
    // -------------------------------------------------------------------------

    // TODO: maybe check if no Blocks overlapp?
    public boolean add(final Block block) {
        return blockSet.add(new Block(block));
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
        final BlockSet other = (BlockSet) obj;

        return  ((blockSet == other.blockSet)
                || ((blockSet != null)
                    && blockSet.equals(other.blockSet)));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + ((blockSet == null) ? 0 : blockSet.hashCode());

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
        return "BlockSet [blocks=" + blockSet + "]";
    }

    // =========================================================================
    // INTERFACE - METHODS
    // =========================================================================

    // -------------------------------------------------------------------------
    // ITERABLE
    // -------------------------------------------------------------------------

    /**
     * Returns an Iterator over all Blocks.
     *
     * @see java.util.Set#iterator()
     */
    @Override
    public Iterator<Block> iterator() {
        return blockSet.iterator();
    }

}   // BlockSet class
