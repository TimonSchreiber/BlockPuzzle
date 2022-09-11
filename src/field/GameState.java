package field;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Record class with a BlockSet and a List of Moves which led to this BlockSet.
 */
public record GameState(BlockSet blockSet, List<Move> moveList) {

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Canonical constructor creating a deep copy of each field.
     *
     * @param blockSet  The BlockSet
     * @param moveList  The List of Moves
     */
    public GameState(BlockSet blockSet, List<Move> moveList) {
        this.blockSet = new BlockSet(blockSet);
        this.moveList = new LinkedList<>();
        
        for (final Move move : moveList) {
            this.moveList.add(new Move(move));
        }
    }

    /**
     * Class constructor copying a BlockSet and creating an empty List of Moves.
     *
     * @param blockSet  The BlockSet.
     */
    public GameState(BlockSet blockSet) {
        this(
            blockSet,
            Collections.emptyList()
        );
    }

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    /** TODO: are these needed? Maybe use a 'for each Block'-method when other
     * classes want to itterate over all blocks.
     * Returns the BlockSet.
     *
     * @return  The BlockSet
     */
    public BlockSet blockSet() {
        return new BlockSet(blockSet);
    }

    /**
     * Returns a new List of Moves.
     *
     * @return  A new List with a copy of every Move.
     */
    public List<Move> moveList() {
        final List<Move> neMoveList = new LinkedList<>();

        for (final Move move : moveList) {
            neMoveList.add(new Move(move));
        }

        return neMoveList;
    }

    // -------------------------------------------------------------------------
    // ADD BLOCK TO NEW LIST
    // -------------------------------------------------------------------------

    /**
     * Creates a new List with an additional Move added to the end.
     *
     * @param moveList  The move List to copy
     * @param newMove   The new Move
     * @return          A new List
     */
    public static List<Move> addMoveToNewList(List<Move> moveList, Move newMove) {
        final List<Move> newMoveList = new LinkedList<>();

        for (final Move move : moveList) {
            newMoveList.add(new Move(move));
        }

        newMoveList.add(new Move(newMove));

        return newMoveList;
    }

}