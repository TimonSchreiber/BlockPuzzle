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
    private final Set<Block> blocks;

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /**
     * Class constructor.
     */
    public BlockSet() {
        this.blocks = new TreeSet<>();
    }

    /**
     * Copy constructor.
     *
     * @param blocks    the BlockSet
     */
    public BlockSet(final BlockSet blocks) {
        this.blocks = new TreeSet<>();

        for (final Block block : blocks) {
            this.blocks.add(new Block(block));
        }
    }

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    /** TODO: maybe return an Optional<String> here
     * Returns the Name of the Block with this Position.
     *
     * @param position  The Position
     * @return          The name of the Block if there is one at this Position,
     *                  {@code null} otherwise.
     */
    public String getNameByPosition(final Position position) {
        // return this.blocks.stream()
        //         .filter(block -> block.positionList().contains(position))
        //         .findAny().get().blockName();

        for (final Block block : blocks) {
            if (block.containsPosition(position)) {
                return block.name();
            }
        }

        return null;
    }

    /** TODO: maybe return an Optional<Block> here
     * Returns the Block with the specified name.
     *
     * @param name  Name of the Block.
     * @return      The Block with the specified name, or {@code null} if the
     *              Block does not exist.
     */
    public Block getBlockByName(final String name) {
        // return this.blocks.stream()
        //         .filter(block -> block.blockName().equals(name))
        //         .findAny().get();

        for (final Block block : blocks) {
            if (block.name().equals(name)) {
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

        for (final Block block : blocks) {

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

    /** TODO: make it possible for a Block to move more than once
     * Moves the Block specified by the Move object in this BlockSet.
     *
     * @param move  The Move to make
     */
    public void makeMove(final Move move) {
        for (final Block block : blocks) {
            if (block.name().equals(move.name())) {
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
        return blocks.add(new Block(block));
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

        return (blocks == other.blocks)
                || ((blocks != null)
                    && blocks.equals(other.blocks));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + ((blocks == null) ? 0 : blocks.hashCode());

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
                BlockSet [blocks=%s]\
                """
                .formatted(blocks);
    }

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
        return blocks.iterator();
    }

}   // BlockSet class
