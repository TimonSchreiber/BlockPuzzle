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
     * @param blockSet    the {@code BlockSet}
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

    /** TODO
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

    /** TODO when this method is called a null check must be implemented?
     *
     * @param position
     * @return
     */
    public String getBlockName(final Position position) {
        // return this.blocks.stream()
        //         .filter(block -> block.positionList().contains(position))
        //         .findAny().get().blockName();
        // TODO: what happens if the Block is not found?

        for (final Block block : blockSet) {
            if (block.containsPosition(position)) {
                return block.blockName();
            }
        }
        // TODO: make this an Optional (or does the Optional belong on the other side (where this method is called?))
        return null;
    }

    /** TODO: try to avoid code duplication with the previous methods!
     * TODO: When this method is called a null check must be implemented?
     *
     * @param blockName
     * @return
     */
    public Block getBlock(final String name/* TODO: used to be Move move */) {
        // return this.blocks.stream()
        //         .filter(block -> block.blockName().equals(name))
        //         .findAny().get();    // TODO: same as obove

        for (final Block block : blockSet) {
            if (block.blockName().equals(name)) {
                return block;
            }
        }
        // TODO: make this an Optional (or does the Optional belong on the other side (where this method is called?))
        return null;
    }

    /** TODO: can this get more efficient? this method needs to be called every time (a lot...)
     *  TODO: check if this method is used.
     * @return
     */
    public BlockSet getMainBlocks() {
        final BlockSet mainBlocks = new BlockSet();
        for (Block block : blockSet) {
            if (block.isMainBlock()) {
                // TODO: Maybe use 'new Block(block)'
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

    /** FIXME: check if the move is allowed (aka block.movePattern.contains(move.direction()))
     *
     * @param move
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

    // TODO: maybe check if no Blocks overlapp? is 'new Block()' neccessary?
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
     */
    @Override
    public Iterator<Block> iterator() {
        return blockSet.iterator();
    }

    // =========================================================================

}   // BlockSet class
