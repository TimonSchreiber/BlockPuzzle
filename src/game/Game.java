package game;

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
    protected final BlockSet blockSet;

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
        this.blockSet   = blockSet;
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
    public BlockSet blockSet() {
        return new BlockSet(blockSet);
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
    // FORWARDING - METHODS
    // -------------------------------------------------------------------------

    // ----------------
    // check win condition

    // TODO:
    // check this BlockSet
    public boolean checkWinCondition() {
        return gameField.checkWinCondition(blockSet);
    }

    // check a BlockSet
    public boolean checkWinCondition(final BlockSet blockSet) {
        return gameField.checkWinCondition(blockSet);
    }

    // ----------------
    // is valid move

    // move this BlockSet
    public boolean isValidMove(final Move move) {
        return gameField.isValidMove(blockSet, move);
    }

    // move a BlockSet
    public boolean isValidMove(final BlockSet blockSet, final Move move) {
        return gameField.isValidMove(blockSet, move);
    }

    // ----------------
    // print / draw

    // TODO: used for debugging?
    // print this BlockSet
    public void print() {
        gameField.print(blockSet);
    }

    // draw this BlockSet
    public void draw(final int delay) {
        gameField.draw(blockSet, delay);
    }

    // =========================================================================

}   // Game abstract class
