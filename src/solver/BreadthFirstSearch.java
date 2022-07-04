package solver;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import block.Block;
import field.BlockSet;
import field.Directions;
import field.GameField;
import field.Move;
import game.Game;

public class BreadthFirstSearch {
    
	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

    private final Queue<GameState> gameStateQueue;

    private final Set<BlockSet> savedBlockSets;   // TODO: maybe a map to connect BlockSets to MoveLists

    private final Game game;

    // =========================================================================
	// CONSTRUCTOR
	// =========================================================================

    public BreadthFirstSearch(final int gameNumber) {

        this.savedBlockSets = new HashSet<>();

        this.gameStateQueue = new ArrayDeque<>();    // TODO: is ArrayDeque good? maybe LinkedList instead?

		this.game = new Game(gameNumber);

        this.savedBlockSets.add(this.game.field().blocks());
        this.gameStateQueue.add(new GameState(this.game.field().blocks()));
    }

	// =========================================================================
	// FIND-NEW-MOVE - METHOD
	// =========================================================================

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

                    // TODO: is there a way to try the same Move again?

                }

                // reverse tghe last Move to continue looking for new Moves
                gameField.isValidMove(newMove.reverse());
            }
        }

        return;
    }

    // =========================================================================
	// SOLVE - METHOD
	// =========================================================================

    /**
     * Tries to solve the {@code BlockPuzzle}:
     */
    public void solve() {
        System.out.println("START\n");

        // Deconstruct the next GameState Object
        GameState nextGameState = this.gameStateQueue.remove();
        // GameField we look at right now
        GameField gameField = new GameField(nextGameState.blockSet()); // TODO: new GameField(new BlockSet(...)) ???
        
        // Start timer
        final Instant t = Instant.now();
            
        while (!gameField.checkWinnigCondition()) {
            
            // Call findNewMove to add GameStates to GameStateQueue
            this.findNewMove(nextGameState);

            // FIXME: error handling
            if (this.gameStateQueue.isEmpty()) {
                System.out.println("No Solution Found!");
                return;
            }
            // else {} TODO: what are the other cases to check for?

            nextGameState = this.gameStateQueue.remove();
            gameField = new GameField(nextGameState.blockSet());
        }

        // Stop timer
        final Duration d = Duration.between(t, Instant.now());
        
        // print result
        System.out.println("END\n");
        System.out.println("\nNumber of states saved:\n" + this.savedBlockSets.size());

        // result MoveList
        final List<Move> moveList = GameState.copyList(nextGameState.moveList());

        System.out.println("\nNumber of moves for the Solution:\n" + moveList.size());
		System.out.println("\nTime to solve:\n"
			+ d.toSecondsPart() + " seconds, "
			+ d.toMillisPart() + " milliseconds");

        // gameField.draw(1000);	// TODO: draw(this.delay);

		// Show solution
		System.out.println("\nshow solution");
		this.showSolution(moveList);		// FIXME time delay

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

        
        this.game.field().draw(1000);
		final Instant t = Instant.now();
		int i = 0;
        
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
    // Inner Class GameState    TODO: make this a separate class?
    // =========================================================================

    record GameState(BlockSet blockSet, List<Move> moveList) {

        // -------------------------------------------------------------------------
        // CONSTRUCTOR
        // -------------------------------------------------------------------------

        GameState(final BlockSet blockSet) {
            this(blockSet, new LinkedList<>());
        }

        // -------------------------------------------------------------------------
        // DEEP-COPY-LIST - METHOD
        // -------------------------------------------------------------------------

        /**
         * TODO: maybe make this a member method of GameState?
         * @param list
         * @return
         */
        static List<Move> copyList(final List<Move> list) {
            final List<Move> newList = new LinkedList<>();
            final Iterator<Move> iterator = list.iterator();

            while (iterator.hasNext()) {
                final Move tmp = iterator.next();
                newList.add(new Move(tmp.name(), tmp.direction()));
            }

            return newList;
        }

    }   // GameState class
        
    // =========================================================================

}   // BreadthFirstSearch class
