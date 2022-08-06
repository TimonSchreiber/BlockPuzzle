package field;

// import java.util.Iterator;   // TODO: needed? check #copyList() method
import java.util.LinkedList;
import java.util.List;

public final class GameState {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------
    private final BlockSet blockSet;
    private final List<Move> moves;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor with empty {@code moves}.
     * @param blockSet
     */
    public GameState(final BlockSet blockSet) {
        this.blockSet = new BlockSet(blockSet);
        this.moves = new LinkedList<>();
    }

    /**
     * Class constructor with {@code moves}.
     * @param blockSet
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

    public BlockSet blockSet() {
        return new BlockSet(this.blockSet);
    }

    public List<Move> moves() {
        final List<Move> tmpList = new LinkedList<>();
        for (final Move move : this.moves) {
            tmpList.add(new Move(move));
        }
        return tmpList;
    }

    // -------------------------------------------------------------------------
    // STATIC DEEP COPY LIST
    // -------------------------------------------------------------------------

    /**
     * TODO: for-each loop or iterator with while?
     *
     * @param moves
     * @return
     */
    public static List<Move> copyList(final List<Move> moves) {
        final List<Move> newMovesList = new LinkedList<>();
        // final Iterator<Move> iterator = list.iterator();

        for (final Move move : moves) {
            newMovesList.add(new Move(move));
        }

        // while (iterator.hasNext()) {
        //     final Move tmp = iterator.next();
        //     newList.add(new Move(tmp.name(), tmp.direction()));
        // }

        return newMovesList;
    }

    // -------------------------------------------------------------------------
    // EQUALS AND HASH-CODE
    // -------------------------------------------------------------------------

    /**
     * Overrides the equals method
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
        final GameState other = (GameState) obj;

        return  ((this.blockSet == other.blockSet)
                    || ((this.blockSet != null)
                        && this.blockSet.equals(other.blockSet)))
                && ((this.moves == other.moves)
                    || ((this.moves != null)
                        && this.moves.equals(other.moves)));
    }

    /** TODO
     * Overrides the hashCode method to match the equals method.
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + ((this.blockSet == null)
                                    ? 0
                                    : this.blockSet.hashCode());
        hash = PRIME * hash + ((this.moves == null)
                                    ? 0
                                    : this.moves.hashCode());

        return hash;
    }

    // =========================================================================

}   // GameState class