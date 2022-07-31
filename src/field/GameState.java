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

    // =========================================================================

}   // GameState class