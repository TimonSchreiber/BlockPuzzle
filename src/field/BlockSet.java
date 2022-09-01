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

    // TODO: Maybe make this a map with Blockname (key)  to Block (value)
    // and only look at the value-Set for equals and HashCode
    // So all the complex 'for (Block block : blockSet)'s are no longer needed
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
     * @param blockSet  the BlockSet
     */
    public BlockSet(final BlockSet blockSet) {
        this();

        for (final Block block : blockSet) {
            this.blockSet.add(new Block(block));
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
        for (final Block block : blockSet) {

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
        for (final Block block : blockSet) {

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
        final BlockSet mainBlockSet = new BlockSet();

        for (final Block block : blockSet) {

            if (block.isMainBlock()) {
                mainBlockSet.blockSet.add(block);
            } else {
                break;
            }
        }

        return mainBlockSet;
    }

    // -------------------------------------------------------------------------
    //  MOVE
    // -------------------------------------------------------------------------

    /** TODO: make it possible for a Block to move more than once
     * Moves the Block specified by the Move object in this BlockSet.
     *
     * @param move  The Move to make
     */
    public void makeMove(final Move move /* maybe add a number how often this move should be repeated {, int number} */) {
        for (final Block block : blockSet) {

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

    /**
     * Adds a Block to this BlockSet. Checks if the new Block does not overlapp
     * with an existing Block.
     *
     * @param block     The new Block to add.
     * @return          {@code true} if the Block was added, {@code false}
     *                  otherwise.
     */
    public boolean add(final Block block) {
        // check if any Position of the new Block is already occupied
        for (final Position position : block.positions()) {

            if (getNameByPosition(position) != null) {
                return false;
            }
        }
        // the new Block can be added to this BlockSet
        return blockSet.add(block);
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

        return (blockSet == other.blockSet)
                || ((blockSet != null)
                    && blockSet.equals(other.blockSet));
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
        return
            """
            BlockSet [blockSet=%s]\
            """.formatted(blockSet);
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
        return blockSet.iterator();
    }

}   // BlockSet class
