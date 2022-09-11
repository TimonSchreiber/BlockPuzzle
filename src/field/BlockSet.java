package field;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import block.Block;
import block.Position;

/**
 * Record class with a {@code TreeSet} of Blocks.
 */
public record BlockSet(Set<Block> blockSet) implements Iterable<Block> {

    /* TODO: Maybe make this a map with Blockname (key) to Block (value) and
     * only look at the value-Set for equals and HashCode, yo all the complex
     * 'for (final Block block : blockSet)'s are no longer needed.
     */

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /**
     * Canonical constructor creating a deep copy of the field.
     *
     * @param blockSet  The {@code Set} of Blocks.
     */
    public BlockSet(Set<Block> blockSet) {
        this.blockSet = new TreeSet<>();

        for (final Block block : blockSet) {
            this.blockSet.add(new Block(block));
        }
    }

    /**
     * Class constructor with an empty {@code Set}.
     */
    public BlockSet() {
        this(Collections.emptySet());
    }

    /**
     * Copy constructor.
     *
     * @param blockSet  the BlockSet
     */
    public BlockSet(BlockSet blockSet) {
        this(blockSet.blockSet);
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
    public String getNameBy(Position position) {
        for (final Block block : blockSet) {

            if (block.contains(position)) {
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
    public Block getBlockBy(String name) {
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

            // all the MainBlocks are at the beginning of the BlockSet.
            // if the current Block is not a MainBlock: leave the for loop
            if (!block.isMainBlock()) {
                break;
            }
            mainBlockSet.blockSet.add(block);
        }

        return mainBlockSet;
    }

    // -------------------------------------------------------------------------
    //  MOVE
    // -------------------------------------------------------------------------

    /**
     * Moves the Block specified by the Move object in this BlockSet.
     *
     * @param move  The Move to make
     */
    public void makeMove(Move move) {
        for (final Block block : blockSet) {

            if (block.name().equals(move.name())) {
                block.moveTowards(move.direction());
                return;
            }
        }

        return;
    }

    /** FIXME: not used so far <---> maybe make this a (Move... moves) method?
     * Moves the Block specified by the Move object in this BlockSet a certain
     * amount of times.
     *
     * @param move          The Move to make
     * @param repetitions   How often to repeat the Move.
     */
    public void makeMove(Move move, int repetitions) {
        for (final Block block : blockSet) {

            if (block.name().equals(move.name())) {
                for (int i = 0; i < repetitions; ++i) {
                    block.moveTowards(move.direction());
                }
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
    public boolean add(Block block) {
        // check if any Position of the new Block is already occupied
        for (final Position position : block.positions()) {

            if (getNameBy(position) != null) {
                return false;
            }
        }
        // the new Block can be added to this BlockSet
        return blockSet.add(block);
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

}
