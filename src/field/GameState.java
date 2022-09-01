package field;

import java.util.LinkedList;
import java.util.List;

/**
 * Holds a BlockSet and a List of Moves which led to this BlockSet
 * configuration.
 */
public final class GameState {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /** The BlockSet */
    private final BlockSet blockSet;

    /** A LinkedList of Moves */
    private final List<Move> moveList;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor copying a BlockSet and creating an empty List of Moves.
     *
     * @param blockSet  The BlockSet.
     */
    public GameState(final BlockSet blockSet) {
        this.blockSet   = new BlockSet(blockSet);
        this.moveList   = new LinkedList<>();
    }

    /**
     * Class constructor copying a BlockSet and a List of Moves.
     *
     * @param blockSet  The BlockSet
     * @param moveList  The List of Moves
     */
    public GameState(final BlockSet blockSet, final List<Move> moveList) {
        this(blockSet);
        
        for (final Move move : moveList) {
            this.moveList.add(new Move(move));
        }
    }

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    /**
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
    public static List<Move> addMoveToNewList(final List<Move> moveList, final Move newMove) {
        final List<Move> newMoveList = new LinkedList<>();

        for (final Move move : moveList) {
            newMoveList.add(new Move(move));
        }

        newMoveList.add(new Move(newMove));

        return newMoveList;
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
        final GameState other = (GameState) obj;

        return ((blockSet == other.blockSet)
                || ((blockSet != null)
                    && blockSet.equals(other.blockSet)))
            && ((moveList == other.moveList)
                || ((moveList != null)
                    && moveList.equals(other.moveList)));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + ((blockSet == null) ? 0 : blockSet.hashCode());
        hash = PRIME * hash + ((moveList == null) ? 0 : moveList.hashCode());

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
                GameState [\
                blockSet=%s, \
                moveList=%s\
                ]\
                """.formatted(
                    blockSet,
                    moveList
                );
    }

}   // Game State class