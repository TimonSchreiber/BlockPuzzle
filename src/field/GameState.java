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
    private final List<Move> moves;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor copying a BlockSet and creating an empty List of Moves.
     *
     * @param blockSet  The BlockSet.
     */
    public GameState(final BlockSet blockSet) {
        this.blockSet = new BlockSet(blockSet);
        this.moves = new LinkedList<>();
    }

    /**
     * Class constructor copying a BlockSet and a List of Moves.
     *
     * @param blockSet  The BlockSet
     * @param moves     The List of Moves
     */
    public GameState(final BlockSet blockSet, final List<Move> moves) {
        this.blockSet = new BlockSet(blockSet);
        this.moves = new LinkedList<>();
        for (final Move move : moves) {
            this.moves.add(new Move(move));
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
    public List<Move> moves() {
        final List<Move> tmpList = new LinkedList<>();
        for (final Move move : moves) {
            tmpList.add(new Move(move));
        }
        return tmpList;
    }

    // -------------------------------------------------------------------------
    // STATIC ADD BLOCK TO NEW LIST
    // -------------------------------------------------------------------------

    /**
     * Creates a copy of this
     *
     * @param moves
     * @return
     */
    public static List<Move> addMoveToNewList(final List<Move> moves, final Move newMove) {
        final List<Move> tmpList = new LinkedList<>();

        for (final Move move : moves) {
            tmpList.add(new Move(move));
        }

        tmpList.add(new Move(newMove));

        return tmpList;
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

        return  ((blockSet == other.blockSet)
                    || ((blockSet != null)
                        && blockSet.equals(other.blockSet)))
                && ((moves == other.moves)
                    || ((moves != null)
                        && moves.equals(other.moves)));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + ((blockSet == null) ? 0 : blockSet.hashCode());
        hash = PRIME * hash + ((moves == null) ? 0 : moves.hashCode());

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
        return "GameState [blockSet=" + blockSet + ", moves=" + moves + "]";
    }

    // =========================================================================

}   // GameState class