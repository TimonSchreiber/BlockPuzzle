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

    private final int delay;
    private final boolean show;

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
     * Creates a new GameSolver for the starting Position gameID.
     *
     * @param gameNumber    Starting Position
     */
    public DepthFirstSearch(final Game game, final int delay, final boolean show) {

        this.delay = delay;
        this.show = show;

        this.savedBlockSets = new HashSet<>();

        this.moveList = new LinkedList<>();

        this.game = game;

        this.savedBlockSets.add(game.blockSet());
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

                // XXX: this was changed from (tmpBlockSet) to (new BlockSet(tmpBlockSet))
                // Why does it work now?

                // Check if it is a known BlockSet -> next iteration
                if (savedBlockSets.contains(new BlockSet(tmpBlockSet)/* <- why new BlockSet and not just 'tmpBlockSet'? */ )) {
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
    // SOLVE
    // -------------------------------------------------------------------------

    /**
     * Tries to solve the BlockPuzzle.
     */
    public void solve() {
        System.out.println("START\n");

        // Start timer
        final Instant t = Instant.now();

        // Check for game.field.isWon() to become true
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

        game.draw(delay);    // TODO: wait two seconds before showing the solution

        // Show solution
        System.out.println("\nshow solution");
        reverseGame();
        game.showSolution(moveList);

        return;
    }

    // -------------------------------------------------------------------------
    // REVERSE GAME
    // -------------------------------------------------------------------------

    /**
     * Reverses the Game to its Starting Position.
     */
    private void reverseGame() {
        final Iterator<Move> iterator = ((LinkedList<Move>) moveList).descendingIterator();

        while (iterator.hasNext()) {
            game.isValidMove(iterator.next().reverse());
        }

        return;
    }

}   // Depth First Search class
