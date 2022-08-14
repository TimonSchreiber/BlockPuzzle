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
import field.GameState;
import field.Move;
import game.Game;

/**
 * Class for solving a Logic Puzzle with Theads and the BreadthFirstSearch
 * method.
 */
public class BFS_WithThreads {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /** If a solution is found. */
    private boolean foundASolution = false;

    /** LinkedBlocking Deque of GameStates to queue the states. */
    private final BlockingQueue<GameState> gameStateQueue;

    /** ConcurrentHashSet of BlockSets to save every unique state. */
    private final Set<BlockSet> savedBlockSets;

    /** GameState for saving the final Solution. */
    private GameState solution;

    /** the Game */
    private final Game game;

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
    public BFS_WithThreads(final Game game/* TODO: more arguments? */) {

        this.savedBlockSets = ConcurrentHashMap.newKeySet();

        this.gameStateQueue = new LinkedBlockingDeque<>();

        this.game = game;

        // TODO: is creating a new BlockSet neccessary? this is already a copy.
        this.savedBlockSets.add(game.blockSet());
        this.gameStateQueue.add(new GameState(game.blockSet()));
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
        final BlockSet tmpBlockSet = gameState.blockSet();
        final List<Move> tmpMoveList = gameState.moves();

        for (final Block block : tmpBlockSet) {

            for (final Direction direction : block.movePattern()) {

                final Move newMove = new Move(block.blockName(), direction);

                // Check if nextMove is not a valid Move -> next iteration
                if (!game.isValidMove(tmpBlockSet, newMove)) {
                    continue;
                }

                /* Check if it is an uknown BlockSet:
                 * -> copy MoveList and add new Move
                 * -> save the BlockSet
                 * -> create a new GameState and add it to the GameStateQueue
                 */
                if (!savedBlockSets.contains(tmpBlockSet)) {

                    final List<Move> newMoveList = GameState.addMoveToNewList(tmpMoveList, newMove);

                    savedBlockSets.add(new BlockSet(tmpBlockSet));
                    gameStateQueue.add(new GameState(tmpBlockSet, newMoveList));

                    // check if a Solution was found -> save the current GameState + MoveList and return
                    if (game.checkWinCondition(tmpBlockSet)) {
                        foundASolution = true;
                        solution = new GameState(new BlockSet(tmpBlockSet), newMoveList);
                        return;
                    }
                }

                // reverse the last Move to continue looking for new Moves
                game.isValidMove(tmpBlockSet, newMove.reverse());

            }    // end for loop Direction
        }    // end for loop Block

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

        while (!foundASolution) {

            // Thread start
            try {
                final GameState nextGameState = gameStateQueue.take();
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

            // TODO: error handling
            // FIXME: how to check if there is no solution but without #isEmpty()
            // if (gameStateQueue.isEmpty()) {
            //     System.out.println("No Solution Found!");
            //     return;
            // }
            // else {} TODO: what are the other cases to check for?

        }

        // Stop timer
        final Duration d = Duration.between(t, Instant.now());

        // print result
        System.out.println("END");

        System.out.println("\nNumber of states saved:\n" + savedBlockSets.size());

        System.out.println("\nNumber of moves for the Solution:\n" + solution.moves().size());

        System.out.println("\nTime to solve:\n"
            + d.toSecondsPart() + " seconds, "
            + d.toMillisPart() + " milliseconds");

        // Show solution
        System.out.println("\nshow solution");
        showSolution(solution.moves());

        return;

    } // end solve()

    // =========================================================================
    // SHOW-SOLUTION - METHOD
    // =========================================================================

    /** TODO: maybe make this a method of Game? (for all Game classes)
     * Shows the Solution from Start to End with a time delay between two
     * {@code Moves}.
     *
     * @param moveList      a List of the {@code Moves} to the solution
     */
    private void showSolution(final List<Move> moveList) {

        int i = 0;

        game.draw(1000);

        final Instant t = Instant.now();

        for (final Move move : moveList) {
            game.isValidMove(move);
            game.draw(100);
            System.out.println(++i + "/" + moveList.size() + ": " + move);
        }

        final Duration d = Duration.between(t, Instant.now());
        System.out.println("\nTime to show Solution:\n"
            + d.toSecondsPart() + " seconds, "
            + d.toMillisPart() + " milliseconds");

        return;
    }

    // =========================================================================

}   // Breadth First Search - With Threads class
