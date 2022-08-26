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
    private final BlockSet blocks;

    /** A LinkedList of Moves */
    private final List<Move> moves;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor copying a BlockSet and creating an empty List of Moves.
     *
     * @param blocks  The BlockSet.
     */
    public GameState(final BlockSet blocks) {
        this.blocks = new BlockSet(blocks);
        this.moves  = new LinkedList<>();
    }

    /**
     * Class constructor copying a BlockSet and a List of Moves.
     *
     * @param blocks    The BlockSet
     * @param moves     The List of Moves
     */
    public GameState(final BlockSet blocks, final List<Move> moves) {
        this.blocks = new BlockSet(blocks);
        this.moves  = new LinkedList<>();
        
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
    public BlockSet blocks() {
        return new BlockSet(blocks);
    }

    /**
     * Returns a new List of Moves.
     *
     * @return  A new List with a copy of every Move.
     */
    public List<Move> moves() {
        final List<Move> neMoveList = new LinkedList<>();

        for (final Move move : moves) {
            neMoveList.add(new Move(move));
        }

        return neMoveList;
    }

    // -------------------------------------------------------------------------
    // ADD BLOCK TO NEW LIST
    // -------------------------------------------------------------------------

    /**
     * Creates a copy of this
     *
     * @param moves
     * @return
     */
    public static List<Move> addMoveToNewList(final List<Move> moves, final Move newMove) {
        final List<Move> newMoveList = new LinkedList<>();

        for (final Move move : moves) {
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

        return  ((blocks == other.blocks)
                    || ((blocks != null)
                        && blocks.equals(other.blocks)))
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

        hash = PRIME * hash + ((blocks == null) ? 0 : blocks.hashCode());
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
        return """
                GameState [blocks=%s, \
                moves=%s]\
                """
                .formatted(blocks, moves);
    }

}   // GameState class