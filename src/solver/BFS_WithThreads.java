package solver;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import block.Block;
import field.BlockSet;
import field.Direction;
import field.GameField;
import field.GameState;
import field.Move;
import game.DirtyDozen;

public class BFS_WithThreads {
// =========================================================================
    // ATTRIBUTES
    // =========================================================================

    private boolean foundASolution = false;

    private final BlockingQueue<GameState> gameStateQueue;

    private final Set<BlockSet> savedBlockSets;

    private GameState solution;

    private final DirtyDozen game;

    // Thread variables
    private static final int NTHREADS = 6;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================

    /**
     * TODO
     * @param gameNumber
     */
    public BFS_WithThreads(final int gameNumber) {

        this.savedBlockSets = ConcurrentHashMap.newKeySet();

        this.gameStateQueue = new LinkedBlockingDeque<>();

        this.game = new DirtyDozen(gameNumber);

        this.savedBlockSets.add(new BlockSet(this.game.blocks()));
        this.gameStateQueue.add(new GameState(this.game.blocks()));
    }

    // =========================================================================
    // FIND-NEW-MOVE - METHOD
    // =========================================================================

    /**
     * TODO
     * @param gameState
     */
    private void findNewMove(final GameState gameState) {

        // Deconstruction
        final GameField gameField = new GameField(gameState.blockSet());
        final List<Move> moveList = GameState.copyList(gameState.moves());

        for (final Block block : gameField.blocks()) {

            // for (final Direction direction : Direction.values()) {   TODO: old form: delete if not needed
            for (final Direction direction : block.movePattern()) {

                final Move newMove = new Move(block.blockName(), direction);

                /*
                 * Check if nextMove is not a valid Move
                 * -> next iteration
                 */
                if (!gameField.isValidMove(newMove)
                    /* || (!moveList.isEmpty() && newMove.equals(moveList.getLast().reverse())) */) {
                    continue;
                }

                // Check if it is a known BlockSet -> next iteration
                if (this.savedBlockSets.contains(gameField.blocks())) {
                    // reverse the last Move to continue looking for new Moves
                    gameField.isValidMove(newMove.reverse());
                    continue;
                }

                /* Check if it is an uknown BlockSet:
                 * -> copy MoveList and add new Move
                 * -> save the BlockSet
                 * -> create a new GameState and add it to the GameStateQueue
                 */
                if (!this.savedBlockSets.contains(gameField.blocks())) {

                    final List<Move> newMoveList = GameState.copyList(moveList);
                    newMoveList.add(newMove);

                    this.savedBlockSets.add(new BlockSet(gameField.blocks()));
                    this.gameStateQueue.add(new GameState(gameField.blocks(), newMoveList));

                    // check if a Solution was found -> save the current GameState + MoveList and return
                    if (gameField.checkWinnigCondition()) {
                        this.foundASolution = true;
                        this.solution = new GameState(new BlockSet(gameField.blocks()), newMoveList);
                        return;
                    }

                    // TODO: is there a way to try the same Move again?
                }

                // reverse the last Move to continue looking for new Moves
                gameField.isValidMove(newMove.reverse());

            }    // end for loop Directions
        }    // end for loop Blocks

        return;
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

        while (!this.foundASolution) {

            // Thread start
            try {
                final GameState nextGameState = this.gameStateQueue.take();
                Runnable task = new Runnable() {
                    public void run() {
                        findNewMove(nextGameState);
                    }
                };

                exec.execute(task);
            } catch (InterruptedException ie) {
                System.err.println("Interrupted Expection Thrown!");
                return;
            }
            // Thread end

            // FIXME: how to check if there is no solution but withiout #isEmpty()
            // TODO: error handling
            // if (this.gameStateQueue.isEmpty()) {
            //     System.out.println("No Solution Found!");
            //     return;
            // }
            // else {} TODO: what are the other cases to check for?

        }

        // Stop timer
        final Duration d = Duration.between(t, Instant.now());

        // print result
        System.out.println("END");

        System.out.println("\nNumber of states saved:\n" + this.savedBlockSets.size());

        System.out.println("\nNumber of moves for the Solution:\n" + this.solution.moves().size());

        System.out.println("\nTime to solve:\n"
            + d.toSecondsPart() + " seconds, "
            + d.toMillisPart() + " milliseconds");

        // Show solution
        System.out.println("\nshow solution");
        this.showSolution(this.solution.moves());

        return;
    } // end solve()

    // =========================================================================
    // SHOW-SOLUTION - METHOD
    // =========================================================================

    /**
     * Shows the Solution from Start to End with a time delay between two
     * {@code Moves}.
     *
     * @param moveList      a List of the {@code Moves} to the solution
     */
    private void showSolution(final List<Move> moveList) {

        int i = 0;

        this.game.draw(1000);

        final Instant t = Instant.now();

        for (final Move move : moveList) {
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

}   // BreadthFirstSearch_WithThreads class
