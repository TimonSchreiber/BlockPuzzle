package solver;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import field.Direction;
import field.Move;
import game.Game;

/**
 * Play the Game on your own.
 */
public final class ManualMode {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    // TODO: try to make undo/redo possible?
    // private final List<Consumer<ManualMode>> unDoList;
    // private final List<Consumer<ManualMode>> reDoList;

    /** A solution is found */
    private boolean foundASolution = false;

    /** Time delay between two Moves when showing the Solution */
    private final int delay;

    /** LinkedList to save every Move. */
    private final List<Move> moveList;

    /** A scanner */
    private final Scanner scanner;

    /** the Game */
    private final Game game;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor with a Game object.
     *
     * @param game  The Game
     */
    public ManualMode(final Game game, final int delay, final Scanner scanner) {
        this.moveList   = new LinkedList<>();
        this.game       = game;
        this.delay      = delay;
        this.scanner    = scanner;
    }

    // -------------------------------------------------------------------------
    // PLAY
    // -------------------------------------------------------------------------

    /**
     * Allows the user to play the Game on their own.
     */
    public void play() {

        while(!foundASolution) {

            game.draw(0);

            // get input BlockName
            System.out.println("Enter a BlockName:");
            final String blockName = scanner.nextLine().toUpperCase();

            switch (blockName) {
                case "CLEAR":   clear(); continue;
                case "UNDO":    undo(); continue;
                case "REDO":    redo(); continue;
                default:        break;
                // maybe add a 'repeat' case so that the user does not have to write the same move again.
            }

            // get Direction
            System.out.println("Enter a Direction [U/D/L/R]:");
            String directionString = scanner.nextLine().toUpperCase();

            // TODO: validate input

            // create Move (TODO: add move to undo/redo List?)
            final Direction direction = Enum.valueOf(Direction.class, directionString);
            final Move move = new Move(blockName, direction);

            // try the move
            if (game.isValidMove(move)) {
                System.out.println(move);
                moveList.add(new Move(move));

            } else {
                System.out.println("Not a legal move: " + move + "\nTry something else.");
                continue;
            }

            // check win condition
            foundASolution = game.checkWinCondition();

        }   // end while loop

        // the Game is won
        System.out.println("*** You Won! ***");
        game.draw(0);

        // reverse the Game
        reverseGame();

        // show the solution
        game.showSolution(moveList, delay);

        return;
    }

    // -------------------------------------------------------------------------
    // REVERSE GAME AND CLEAR
    // -------------------------------------------------------------------------

    /**
     * Reverses the Game to its start position.
     */
    private void reverseGame() {
        final Iterator<Move> descIterator = ((LinkedList<Move>) moveList).descendingIterator();

        while (descIterator.hasNext()) {
            game.isValidMove(descIterator.next().reverse());
        }

        return;
    }

    /**
     * Reverses the Game to its start position and clears all moves.
     */
    private void clear() {
        reverseGame();
        moveList.clear();
        // TODO: also clear undoList and redoList.
        return;
    }

    // -------------------------------------------------------------------------
    // UNDO AND REDO
    // -------------------------------------------------------------------------

    /**
     * Undoes the Last Move.
     */
    private void undo() {
        // TODO: implement this undo method
        return;
    }

    /**
     * Redoes the last undone Move.
     */
    private void redo() {
        // TODO: implement this redo method
        return;
    }


}   // Manual Mode class
