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

    /** HashSet of BlockSets to save every unique state. */
    private final Set<BlockSet> savedBlockSets;

    /** LinkedList to save every Move. */
    private final List<Move> moveList;

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
        this.savedBlockSets = new HashSet<>();
        this.moveList       = new LinkedList<>();
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
        this.savedBlockSets.add(game.blockSet());

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
        final BlockSet tmpBlockSet = game.blockSet();

        for (final Block block : tmpBlockSet) {

            for (final Direction direction : block.movePattern()) {

                final Move newMove = new Move(block.blockName(), direction);

                // Check if nextMove is not a valid Move -> next iteration
                if (!game.isValidMove(tmpBlockSet, newMove)) {
                    continue;
                }

                // Check if it is a known BlockSet -> next iteration
                if (savedBlockSets.contains(new BlockSet(tmpBlockSet))) {   // TODO: why new BlockSet() and not just 'tmpBlockSet'?
                    // reverse the last Move to continue looking for new Moves
                    game.isValidMove(tmpBlockSet, newMove.reverse());
                    continue;
                }

                // -> play this move on the GameField
                game.isValidMove(newMove);

                savedBlockSets.add(new BlockSet(tmpBlockSet));
                moveList.add(newMove);

                if (show) {
                    game.draw(0);        // FIXME: this shows the GameField while it is solved delete for best time
                }

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

}   // Depth First Search class
