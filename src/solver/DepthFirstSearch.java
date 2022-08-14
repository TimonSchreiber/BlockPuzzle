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

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================

    private final int delay;
    private final boolean show;

    /** {@code HashSet} of {@code BlockSets} to save every unique state. */
    private final Set<BlockSet> savedBlockSets;

    /** {@code LinkedList} to save every {@code Move}. */
    private final List<Move> moveList;

    /** the {@code Game} */
    private final Game game;

    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================

    /**
     * Creates a new {@code GameSolver} for the starting Position gameID
     *
     * @param gameNumber    Starting Position
     */
    public DepthFirstSearch(final Game game, final int delay, final boolean show) {

        this.delay = delay;
        this.show = show;

        this.savedBlockSets = new HashSet<>();

        this.moveList = new LinkedList<>();

        this.game = game;

        this.savedBlockSets.add(this.game.blockSet());
    }

    // =========================================================================
    // FIND-NEW-MOVE - METHOD
    // =========================================================================

    /**
     * Makes a {@code Move} by going through every possible {@code BlockName}
     * and {@code Direction}.
     *
     * @return    {@code true} if a new {@code Move}, leading to a new
     *             {@code BlockSet} is found; {@code false} otherwise
     */
    private boolean findNewMove() {
        final BlockSet tmpBlockSet = this.game.blockSet();

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
                if (this.savedBlockSets.contains(new BlockSet(tmpBlockSet)/* <- why new BlockSet and not just 'tmpBlockSet'? */ )) {
                    // reverse the last Move to continue looking for new Moves
                    game.isValidMove(tmpBlockSet, newMove.reverse());
                    continue;
                }

                // -> play this move on the GameField
                this.game.isValidMove(newMove);

                this.savedBlockSets.add(new BlockSet(tmpBlockSet));
                this.moveList.add(newMove);

                if (show) {
                    this.game.draw(0);        // FIXME: this shows the GameField while it is solved delete for best time
                }

                // new Move found
                return true;

            }    // end for loop Direction
        }    // end for loop Block

        // no new Move found
        return false;
    }

    // =========================================================================
    // SOLVE - METHOD
    // =========================================================================

    /**
     * Tries to solve the {@code BlockPuzzle}.
     */
    public void solve() {
        System.out.println("START\n");

        // Start timer
        final Instant t = Instant.now();

        // Check for game.field.isWon() to become true
        while (!this.game.checkWinCondition()) {

            // If isNewMove() is false, the last Move will be reversed
            while (!this.findNewMove()) {

                if (this.moveList.isEmpty()) {
                    System.out.println("Can't find a move.");
                    return;

                } else if (this.game.isValidMove(((LinkedList<Move>) this.moveList).getLast().reverse())) {
                    ((LinkedList<Move>) this.moveList).pollLast();

                } else {
                    System.out.println("Can't reverse last move.");
                    return;
                }
            }
        }

        // Stop timer
        final Duration d = Duration.between(t, Instant.now());

        // TODO: Maybe change this to a TextBlock """ {text} """ and only sysout once
        System.out.println("END");

        System.out.println("\nNumber of states saved:\n" + this.savedBlockSets.size());

        System.out.println("\nNumber of moves made:\n" + this.moveList.size());

        System.out.println("\nTime to solve:\n"
            + d.toSecondsPart() + " seconds, "
            + d.toMillisPart() + " milliseconds");

        this.game.draw(this.delay);    // TODO: wait two seconds before showing the solution

        // Show solution
        System.out.println("\nshow solution");
        this.reverseGame();
        this.showSolution();        // FIXME time delay

        return;
    }    // end solve()

    // =========================================================================
    // REVERSE-GAME - METHOD
    // =========================================================================

    /** TODO
     * Reverses all Moves
     */
    private void reverseGame() {
        final Iterator<Move> iterator = ((LinkedList<Move>) this.moveList).descendingIterator();

        while (iterator.hasNext()) {
            this.game.isValidMove(iterator.next().reverse());
        }

        return;
    }

    // =========================================================================
    // SHOW-SOLUTION - METHOD
    // =========================================================================

    /** TODO: maybe make this a method of Game? (for all Game classes)
     * Shows the Solution from Start to End with a time delay between two
     * {@code Move}s.
     *
     * @param delay        the time delay in milliseconds
     */
    public void showSolution() {

        int i = 0;

        this.game.draw(1000);

        final Instant t = Instant.now();

        for (final Move move : this.moveList) {
            this.game.isValidMove(move);
            this.game.draw(100);
            System.out.println(++i + "/" + moveList.size() + ": " + move);
        }

        final Duration d = Duration.between(t, Instant.now());
        System.out.println("\nTime to show Solution:\n"
            + d.toSecondsPart() + " seconds, "
            + d.toMillisPart() + " milliseconds");

        return;
    }

    // =========================================================================

}   // Depth First Search class
