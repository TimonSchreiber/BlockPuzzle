package solver;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import block.Block;
import field.BlockSet;
import field.Direction;
import field.GameState;
import field.Move;
import game.Game;

/**
 * Class for solving a Logic Puzzle with the BreadthFirstSearch method.
 */
public class BreadthFirstSearch {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /** If a solution is found. */
    private boolean foundASolution = false;

    /** Time delay between two Moves when showing the Solution */
    private final int delay;

    /** ArrayDeque of GameStates to queue the states. */
    private final Queue<GameState> gameStateQueue;

    /** HashSet of BlockSets to save every unique state. */
    private final Set<BlockSet> savedBlockSets;

    /** The Game */
    private final Game game;

    /** List of Moves for the final solution. */
    private List<Move> solutionMoveList;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor with a Game object.
     *
     * @param game  The Game
     */
    public BreadthFirstSearch(Game game, int delay) {
        this.savedBlockSets = new HashSet<>();
        this.gameStateQueue = new ArrayDeque<>();
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
        
        // add current BlockSet to the Map and Queue
        this.savedBlockSets.add(game.blockSet());
        this.gameStateQueue.add(new GameState(game.blockSet()));
        
        // Start timer
        System.out.println("START\n");
        final Instant time = Instant.now();

        while (!foundASolution) {

            // Get the first GameState in the Queue
            final GameState nextGameState = gameStateQueue.remove();

            // Call findNewMove to add GameStates to GameStateQueue
            findNewMove(nextGameState);

            // error handling
            if (gameStateQueue.isEmpty()) {
                System.out.println("No Solution Found!");
                return;
            }
        }

        // Stop timer
        final Duration duration = Duration.between(time, Instant.now());
        System.out.println("END\n");

        // print result
        System.out.println(resultToString(duration));

        // Show solution
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
    private void findNewMove(GameState gameState) {

        // Extract the BlockSet out of the GameState
        final BlockSet blockSet = gameState.blockSet();

        for (final Block block : blockSet) {

            for (final Direction direction : block.movePattern()) {

                final Move move = new Move(block.name(), direction);

                // Check if nextMove is not a valid Move -> next iteration
                if (!game.isValidMove(blockSet, move)) {
                    continue;
                }

                /* Check if it is an unknown BlockSet:
                 * -> copy MoveList and add new Move
                 * -> save the BlockSet
                 * -> create a new GameState and add it to the GameStateQueue
                 */
                if (!savedBlockSets.contains(new BlockSet(blockSet))) {  // TODO: why new BlockSet(...) and not just 'blockSet'?

                    final List<Move> newMoveList =
                        GameState.addMoveToNewList(gameState.moveList(), move);

                    savedBlockSets.add(new BlockSet(blockSet));
                    gameStateQueue.add(new GameState(blockSet, newMoveList));

                    // check if a Solution was found -> save the current GameState + MoveList and return
                    if (game.checkWinCondition(blockSet)) {
                        foundASolution = true;
                        solutionMoveList = newMoveList;
                        return;
                    }
                }

                // reverse the last Move to continue looking for new Moves
                game.isValidMove(blockSet, move.reverse());

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
    private String resultToString(Duration duration) {
        return """
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
        final BreadthFirstSearch other = (BreadthFirstSearch) obj;

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
        return """
                BreadthFirstSearch [\
                foundASolution=%b, \
                delay=%d, \
                game=%s, \
                solutionMoveList=%s, \
                savedBlockSets=%s, \
                gameStateQueue=%s\
                ]\
                """.formatted(
                    foundASolution,
                    delay,
                    game,
                    solutionMoveList,
                    savedBlockSets,
                    gameStateQueue
                );
    }

}
