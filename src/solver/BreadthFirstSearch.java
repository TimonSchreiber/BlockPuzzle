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
import field.Directions;
import field.GameField;
import field.GameState;
import field.Move;
import game.Game;

public class BreadthFirstSearch {
    
	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

    private boolean foundASolution = false;
    private GameState solution;

    private final Queue<GameState> gameStateQueue;

    private final Set<BlockSet> savedBlockSets;

    private final Game game;

    // =========================================================================
	// CONSTRUCTOR
	// =========================================================================

    /**
     * TODO
     * @param gameNumber
     */
    public BreadthFirstSearch(final int gameNumber) {

        this.savedBlockSets = new HashSet<>();

        this.gameStateQueue = new ArrayDeque<>();

		this.game = new Game(gameNumber);

        this.savedBlockSets.add(this.game.field().blocks());
        this.gameStateQueue.add(new GameState(this.game.field().blocks()));
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
        final List<Move> moveList = GameState.copyList(gameState.moveList());

        for (final Block block : gameField.blocks()) {

            for (final Directions direction : Directions.values()) {

                final Move newMove = new Move(block.blockName(), direction);

                /*
                 * Check if nextMove is not a valid Move equal or the moveList
                 * is not empty and is the previous Move reversed
                 * -> next iteration
                 */
                if (!gameField.isValidMove(newMove)
                    /* || (!moveList.isEmpty() && newMove.equals(moveList.getLast().reverse())) */) {
                    continue;
                }
                
                /*
                 * Check if it is an unknown BlockSet
                 * -> copy MoveList and add new Move
                 * -> save the BlockSet
                 * -> create a new GameState and add it to the GameStateQueue
                 */
                if (!this.savedBlockSets.contains(gameField.blocks())) {
                    // Copy moveListand add newMOve
                    final List<Move> newMoveList = GameState.copyList(moveList);
                    newMoveList.add(newMove);
                    
                    this.savedBlockSets.add(gameField.blocks());
                    this.gameStateQueue.add(new GameState(gameField.blocks(), newMoveList));    // TODO: maybe new GameState(new BlockSet(), ...)
                    
                    // check if a Solution was found -> save the current GameState + MoveList and return
                    if (gameField.checkWinnigCondition()) {
                        this.foundASolution = true;
                        this.solution = new GameState(gameField.blocks(), newMoveList);
                        return;
                    }

                    // TODO: is there a way to try the same Move again?
                }

                // reverse the last Move to continue looking for new Moves
                gameField.isValidMove(newMove.reverse());
            }
        }

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

            // Deconstruct the next GameState Object
            final GameState nextGameState = this.gameStateQueue.remove();
            
            // Call findNewMove to add GameStates to GameStateQueue
            this.findNewMove(nextGameState);

            // TODO: error handling
            if (this.gameStateQueue.isEmpty()) {
                System.out.println("No Solution Found!");
                return;
            }
            // else {} TODO: what are the other cases to check for?
        }

        // Stop timer
        final Duration d = Duration.between(t, Instant.now());
        
        // print result
        System.out.println("END");
        
        System.out.println("\nNumber of states saved:\n" + this.savedBlockSets.size());

        System.out.println("\nNumber of moves for the Solution:\n" + this.solution.moveList().size());

		System.out.println("\nTime to solve:\n"
			+ d.toSecondsPart() + " seconds, "
			+ d.toMillisPart() + " milliseconds");

		// Show solution
		System.out.println("\nshow solution");
		this.showSolution(this.solution.moveList());		// FIXME time delay

        return;

    } // end solve()

    // =========================================================================
    // SHOW-SOLUTION - METHOD
    // =========================================================================

	/**
     * TODO:
	 * Shows the Solution from Start to End with a time delay between two
     * {@code Moves}.
     * 
     * @param moveList      a List of the {@code Moves} to the solution
     */
	private void showSolution(final List<Move> moveList) {

		int i = 0;

        this.game.field().draw(1000);
		
        final Instant t = Instant.now();
        
        // Starting position
        // System.out.println("\nState " + i + "/" + moveList.size());
        // this.game.field().print();

		for (final Move move : moveList) {
			this.game.field().isValidMove(move);
			this.game.field().draw(100);
            System.out.println(++i + "/" + moveList.size() + ": " + move);
		}

		final Duration d = Duration.between(t, Instant.now());
		System.out.println("\nTime to show Solution:\n"
            + d.toSecondsPart() + " seconds, "
            + d.toMillisPart() + " milliseconds");

		return;
	}
        
    // =========================================================================

}   // BreadthFirstSearch class
