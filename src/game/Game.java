package game;

import java.util.List;

import field.BlockSet;
import field.GameField;
import field.Move;

/**
 * An abstract sealed class for defining what a Game is.
 */
public sealed abstract class Game
    permits DirtyDozen, JumpingRabbits, RushHour {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /** A BlockSet. */
    protected final BlockSet blocks;

    /** A GameField to play on. */
    protected final GameField gameField;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor with a gameNumber.
     *
     * @param gameNumber    the gameNumber
     */
    protected Game(final BlockSet blockSet, final GameField gameField, final int gameNumber) {
        this.blocks     = blockSet;
        this.gameField  = gameField;

        this.setUp(gameNumber);
    }

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    /**
     * Returns a new BlockSet.
     *
     * @return  The BlockSet
     */
    public BlockSet blocks() {
        return new BlockSet(blocks);
    }

    // -------------------------------------------------------------------------
    // ABSTRACT METHODS
    // -------------------------------------------------------------------------

    /**
     * Loads a specific BlockSet into this Game.
     *
     * @param gameNumber    The Game specifier.
     */
    protected abstract void setUp(final int gameNumber);

    // -------------------------------------------------------------------------
    // SHOW SOLUTION
    // -------------------------------------------------------------------------
    
    /**
     * Shows the Solution from Start to End with a time delay between two Moves.
     *
     * @param moves     a List of the Moves to the solution
     */
    public void showSolution(final List<Move> moves, final int delay) {

        int i = 0;

        draw(5 * delay);

        for (final Move move : moves) {
            isValidMove(move);
            draw(delay);
            System.out.println(++i + "/" + moves.size() + ": " + move);
        }

        return;
    }

    // -------------------------------------------------------------------------
    // FORWARDING - METHODS
    // -------------------------------------------------------------------------

    // ----------------
    // check win condition

    // check this BlockSet
    public boolean checkWinCondition() {
        return gameField.checkWinCondition(blocks);
    }

    // check a BlockSet
    public boolean checkWinCondition(final BlockSet blocks) {
        return gameField.checkWinCondition(blocks);
    }

    // ----------------
    // is valid move

    // move this BlockSet
    public boolean isValidMove(final Move move) {
        return gameField.isValidMove(blocks, move);
    }

    // move a BlockSet
    public boolean isValidMove(final BlockSet blocks, final Move move) {
        return gameField.isValidMove(blocks, move);
    }

    // ----------------
    // print / draw

    // print this BlockSet
    public void print() {
        gameField.print(blocks);
    }

    // draw this BlockSet
    public void draw(final int delay) {
        gameField.draw(blocks, delay);
    }

}   // Game abstract class
