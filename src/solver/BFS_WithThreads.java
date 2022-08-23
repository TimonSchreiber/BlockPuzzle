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

    /** Time delay between two Moves when showing the Solution */
    private final int delay;

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

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor with a Game Object, and a time dleay.
     *
     * @param game      The Game
     * @param delay     The time delay
     */
    public BFS_WithThreads(final Game game, final int delay) {
        this.savedBlockSets = ConcurrentHashMap.newKeySet();
        this.gameStateQueue = new LinkedBlockingDeque<>();
        this.game           = game;
        this.delay          = delay;
    }

    // -------------------------------------------------------------------------
    // SOLVE
    // -------------------------------------------------------------------------

    /**
     * Tries to solve the BlockPuzzle.
     */
    public void solve() {
        System.out.println("START\n");

        // add current BlockSet to the Map and Queue
        savedBlockSets.add(game.blockSet());
        gameStateQueue.add(new GameState(game.blockSet()));

        // Start timer
        final Instant t = Instant.now();

        while (!foundASolution) {

            // Thread start
            try {
                final GameState nextGameState = gameStateQueue.take();
                final Runnable task =
                    new Runnable() {
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
        game.showSolution(solution.moves(), delay);

        return;
    }

    // -------------------------------------------------------------------------
    // FIND NEW MOVE
    // -------------------------------------------------------------------------

    /**
     * Find all the possible Moves of this GameState which will not lead to a
     * know BlockSet and add them to the Queue.
     *
     * @param gameState     The GameState
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
                if (!savedBlockSets.contains(new BlockSet(tmpBlockSet))) {  // TODO: why new BlockSet() and not just 'tmpBlockSet'?

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

}   // Breadth First Search - With Threads class
