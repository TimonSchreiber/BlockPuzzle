package field;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public record GameState(BlockSet blockSet, List<Move> moveList) {

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    public GameState(final BlockSet blockSet) {
        this(blockSet, new LinkedList<>());
    }

    // -------------------------------------------------------------------------
    // DEEP COPY LIST
    // -------------------------------------------------------------------------

    /**
     * TODO: maybe make this a member method of GameState?
     * @param list
     * @return
     */
    public static List<Move> copyList(final List<Move> list) {
        final List<Move> newList = new LinkedList<>();
        final Iterator<Move> iterator = list.iterator();

        while (iterator.hasNext()) {
            final Move tmp = iterator.next();
            newList.add(new Move(tmp.name(), tmp.direction()));
        }

        return newList;
    }

    // =========================================================================

}   // GameState class