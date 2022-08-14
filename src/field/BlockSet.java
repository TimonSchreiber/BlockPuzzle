package field;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import block.Block;
import block.Position;

public final class BlockSet implements Iterable<Block> {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

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
     * @param blockSet    the {@code BlockSet}
     */
    public BlockSet(final BlockSet blockSet) {
        this.blocks = new TreeSet<>();

        for (final Block block : blockSet) {
            this.blocks.add(new Block(block));
        }
    }

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    /** TODO
     * Checks if there is a Block in this {@code BlockSet} with the same
     * x- and y-coordinates as one of its {@code Block}s.
     *
     * @param position    the {@code Position}
     * @return            {@code true} if there is one Block which has these
     *                     x- and y-coordinates; {@code false} otherwise
     */
    public boolean isBlock(final Position position) {
        // return this.blocks.stream()
        //         .anyMatch(block -> block.containsPosition(position));

        for (final Block block : this.blocks) {
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
        //         .findAny().get().blockName();    // TODO: what happens if the Block is not found?

        for (final Block block : this.blocks) {
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

        for (final Block block : this.blocks) {
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
        for (Block block : this.blocks) {
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
        for (final Block block : this.blocks) {
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
        return this.blocks.add(new Block(block));
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

        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        // Object must be PositionList at this point
        final BlockSet other = (BlockSet) obj;

        return  ((this.blocks == other.blocks)
                || ((this.blocks != null)
                    && this.blocks.equals(other.blocks)));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + ((this.blocks == null) ? 0 : this.blocks.hashCode());

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
        return "BlockSet [blocks=" + blocks + "]";
    }

    // =========================================================================
    // INTERFACE - METHODS
    // =========================================================================

    // -------------------------------------------------------------------------
    // ITERABLE
    // -------------------------------------------------------------------------

    /**
     * Returns an {@code Iterator} over all {@code Block}s.
     */
    @Override
    public Iterator<Block> iterator() {
        return this.blocks.iterator();
    }

    // =========================================================================

}   // BlockSet class
