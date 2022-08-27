package solver;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import block.Block;
import field.BlockSet;
import field.Direction;
import field.Move;
import game.Game;

public class DepthFirstSearch {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /** If the GameField should show the Moves while solving */
    private final boolean show;

    /** Time delay between two Moves when showing the Solution */
    private final int delay;

    /** LinkedList to save every Move. */
    private final List<Move> moveList;

    /** HashSet of BlockSets to save every unique state. */
    private final Set<BlockSet> savedBlockSets;

    /** the Game */
    private final Game game;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor.
     *
     * @param game      The Game
     * @param show      show the moves while they are played
     */
    public DepthFirstSearch(final Game game, final int delay, final boolean show) {
        this.moveList       = new LinkedList<>();
        this.savedBlockSets = new HashSet<>();
        this.game           = game;
        this.delay          = delay;
        this.show           = show;
    }

    // -------------------------------------------------------------------------
    // SOLVE
    // -------------------------------------------------------------------------

    /**
     * Tries to solve the BlockPuzzle.
     */
    public void solve() {
        System.out.println("START\n");

        // add current BlockSet to the HashSet
        this.savedBlockSets.add(game.blocks());

        // Start timer
        final Instant t = Instant.now();

        // Check for game.checkWinCondition() to become true
        while (!game.checkWinCondition()) {

            // If isNewMove() is false, the last Move will be reversed
            while (!findNewMove()) {

                if (moveList.isEmpty()) {
                    System.out.println("Can't find a move.");
                    return;

                } else if (game.isValidMove(((LinkedList<Move>) moveList).getLast().reverse())) {
                    ((LinkedList<Move>) moveList).pollLast();

                } else {
                    System.out.println("Can't reverse last move.");
                    return;
                }
            }   // end inner while loop

        }   // end outer while loop

        // Stop timer
        final Duration d = Duration.between(t, Instant.now());

        System.out.println("END");

        System.out.println("\nNumber of states saved:\n" + savedBlockSets.size());

        System.out.println("\nNumber of moves made:\n" + moveList.size());

        System.out.println("\nTime to solve:\n"
            + d.toSecondsPart() + " seconds, "
            + d.toMillisPart() + " milliseconds");

        game.draw(2000);

        // Show solution
        System.out.println("\nshow solution");
        reverseGame();
        game.showSolution(moveList, delay);

        return;
    }

    // -------------------------------------------------------------------------
    // FIND NEW MOVE
    // -------------------------------------------------------------------------

    /**
     * Makes a Move by going through every possible BlockName and Direction.
     *
     * @return  {@code true} if a new Move, leading to a new BlockSet is
     *          found, {@code false} otherwise
     */
    private boolean findNewMove() {
        final BlockSet newBlockSet = game.blocks();

        for (final Block block : newBlockSet) {

            for (final Direction direction : block.movePattern()) {

                final Move newMove = new Move(block.name(), direction);

                // Check if nextMove is not a valid Move -> next iteration
                if (!game.isValidMove(newBlockSet, newMove)) {
                    continue;
                }

                // Check if it is a known BlockSet -> next iteration
                if (savedBlockSets.contains(new BlockSet(newBlockSet))) {   // TODO: why new BlockSet() and not just 'newBlockSet'?
                    // reverse the last Move to continue looking for new Moves
                    game.isValidMove(newBlockSet, newMove.reverse());
                    continue;
                }

                // -> play this move on the GameField
                game.isValidMove(newMove);

                // save the new found BlockSet and add the Move to the moveList
                savedBlockSets.add(new BlockSet(newBlockSet));
                moveList.add(newMove);

                if (show) game.draw(0);

                // new Move found
                return true;

            }    // end for loop Direction
        }    // end for loop Block

        // no new Move found
        return false;
    }

    // -------------------------------------------------------------------------
    // REVERSE GAME
    // -------------------------------------------------------------------------

    /**
     * Reverses the Game to its Starting Position.
     */
    private void reverseGame() {
        final Iterator<Move> descIterator = ((LinkedList<Move>) moveList).descendingIterator();

        while (descIterator.hasNext()) {
            game.isValidMove(descIterator.next().reverse());
        }

        return;
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

        // Object must be DeapthFirstSerch at this Point
        final DepthFirstSearch other = (DepthFirstSearch) obj;

        return (show == other.show)
            && (delay == other.delay)
            && ((moveList == other.moveList)
                || ((moveList != null)
                    && moveList.equals(other.moveList)))
            && ((savedBlockSets == other.savedBlockSets)
                || ((savedBlockSets != null)
                    &&savedBlockSets.equals(other.savedBlockSets)))
            && ((game == other.game)
                || ((game != null)
                    && game.equals(other.game)));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + Boolean.hashCode(show);
        hash = PRIME * hash + Integer.hashCode(delay);
        hash = PRIME * hash + ((     moveList == null) ? 0 :       moveList.hashCode());
        hash = PRIME * hash + ((savedBlockSets== null) ? 0 : savedBlockSets.hashCode());
        hash = PRIME * hash + ((         game == null) ? 0 :           game.hashCode());

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
                DepthFirstSearch [\
                show=%b, \
                delay=%d, \
                game=%s, \
                moveList=%s, \
                savedBlockSets=%s\
                ]\
                """
                .formatted(
                    show,
                    delay,
                    game,
                    moveList,
                    savedBlockSets
                );
    }

}   // Depth First Search class
