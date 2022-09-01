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
    
    /** the Game */
    private final Game game;

    /** List of Moves for the final solution. */
    private List<Move> solutionMoveList;
    
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
        final Instant time = Instant.now();

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
        final Duration duration = Duration.between(time, Instant.now());
        System.out.println("END\n");

        // print result
        System.out.println(resultToString(duration));

        // Show solution
        System.out.println("show solution");
        game.showSolution(solutionMoveList, delay);

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

        // Extract the BlockSet out of the GameState
        final BlockSet newBlockSet = gameState.blockSet();

        for (final Block block : newBlockSet) {

            for (final Direction direction : block.movePattern()) {

                final Move newMove = new Move(block.name(), direction);

                // Check if nextMove is not a valid Move -> next iteration
                if (!game.isValidMove(newBlockSet, newMove)) {
                    continue;
                }

                /* Check if it is an uknown BlockSet:
                 * -> copy MoveList and add new Move
                 * -> save the BlockSet
                 * -> create a new GameState and add it to the GameStateQueue
                 */
                if (!savedBlockSets.contains(new BlockSet(newBlockSet))) {  // TODO: why new BlockSet(...) and not just 'newBlockSet'?

                    final List<Move> newMoveList =
                        GameState.addMoveToNewList(gameState.moveList(), newMove);

                    savedBlockSets.add(new BlockSet(newBlockSet));
                    gameStateQueue.add(new GameState(newBlockSet, newMoveList));

                    // check if a Solution was found -> save the current GameState + MoveList and return
                    if (game.checkWinCondition(newBlockSet)) {
                        foundASolution = true;
                        solutionMoveList = newMoveList;
                        return;
                    }
                }

                // reverse the last Move to continue looking for new Moves
                game.isValidMove(newBlockSet, newMove.reverse());

            }    // end for loop Direction
        }    // end for loop Block

        return;
    }

    // -------------------------------------------------------------------------
    // PRINT RESULT
    // -------------------------------------------------------------------------

    /**
     * Prints the result information with number of states, number of moves and
     * time it took to solve this puzzle.
     *
     * @param duration  The duration from start to end
     */
    private String resultToString(final Duration duration) {
        return
            """
            Number of states saved:
            %d

            Number of moves made:
            %d

            Time to solve:
            %d seconds, %d milliseconds
            """.formatted(
                savedBlockSets.size(),
                solutionMoveList.size(),
                duration.toSecondsPart(),
                duration.toMillisPart()
            );
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

        // Object must be BreadthFirstSearch at this Point
        BFS_WithThreads other = (BFS_WithThreads) obj;

        return (foundASolution == other.foundASolution)
            && (delay == other.delay)
            && ((gameStateQueue == other.gameStateQueue)
                || ((gameStateQueue != null)
                    && gameStateQueue.equals(other.gameStateQueue)))
            && ((savedBlockSets == other.savedBlockSets)
                || ((savedBlockSets != null)
                    && savedBlockSets.equals(other.savedBlockSets)))
            && ((solutionMoveList == other.solutionMoveList)
                || ((solutionMoveList != null)
                    && solutionMoveList.equals(other.solutionMoveList)))
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

        hash = PRIME * hash + Boolean.hashCode(foundASolution);
        hash = PRIME * hash + Integer.hashCode(delay);
        hash = PRIME * hash + ((gameStateQueue   == null) ? 0 :   gameStateQueue.hashCode());
        hash = PRIME * hash + ((savedBlockSets   == null) ? 0 :   savedBlockSets.hashCode());
        hash = PRIME * hash + ((solutionMoveList == null) ? 0 : solutionMoveList.hashCode());
        hash = PRIME * hash + ((            game == null) ? 0 :             game.hashCode());

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
        return
            """
            BFS_WithThreads [\
            NTHREADS=%d, \
            foundASolution=%b, \
            delay=%d, \
            game=%s, \
            solutionMoveList=%s, \
            savedBlockSets=%s, \
            gameStateQueue=%s\
            ]\
            """.formatted(
                NTHREADS,
                foundASolution,
                delay,
                game,
                solutionMoveList,
                savedBlockSets,
                gameStateQueue
            );
    }

}   // Breadth First Search - With Threads class
