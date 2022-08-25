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

    /** List of Moves for the final solution. */
    private List<Move> solutionMoveList;

    /** The Game */
    private final Game game;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor with a Game object.
     *
     * @param game  The Game
     */
    public BreadthFirstSearch(final Game game, final int delay) {
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
        System.out.println("START\n");

        // add current BlockSet to the Map and Queue
        this.savedBlockSets.add(game.blocks());
        this.gameStateQueue.add(new GameState(game.blocks()));

        // Start timer
        final Instant t = Instant.now();

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
        final Duration d = Duration.between(t, Instant.now());

        // print result
        System.out.println("END");

        System.out.println("\nNumber of states saved:\n" + savedBlockSets.size());

        System.out.println("\nNumber of moves for the Solution:\n" + solutionMoveList.size());

        System.out.println("\nTime to solve:\n"
            + d.toSecondsPart() + " seconds, "
            + d.toMillisPart() + " milliseconds");

        // Show solution
        System.out.println("\nshow solution");
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

        // Deconstruction TODO: push this inside the for loops
        final BlockSet tmpBlockSet = gameState.blocks();
        final List<Move> tmpMoveList = gameState.moves();

        for (final Block block : tmpBlockSet) {

            for (final Direction direction : block.movePattern()) {

                final Move newMove = new Move(block.name(), direction);

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
                        solutionMoveList = newMoveList;
                        return;
                    }
                }

                // reverse the last Move to continue looking for new Moves
                game.isValidMove(tmpBlockSet, newMove.reverse());

            }    // end for loop Direction
        }    // end for loop Block

        return;
    }

}   // Breadth First Search class
