package game;

import field.BlockSet;
import field.GameField;
import field.Move;

// TODO: make this class 'sealed'?
public /* sealed */ abstract class Game 
    /* permits DirtyDozen, JumpingRabbits, RushHour */ {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    protected final BlockSet blockSet;
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
        this.blockSet = blockSet;
        this.gameField = gameField;
        this.setUp(gameNumber);
    }

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    public BlockSet blockSet() {
        return new BlockSet(this.blockSet);
    }

    // -------------------------------------------------------------------------
    // ABSTRACT METHODS
    // -------------------------------------------------------------------------

    protected abstract void setUp(final int gameNumber);

    // -------------------------------------------------------------------------
    // FORWARDING - METHODS
    // -------------------------------------------------------------------------

    // check this BlockSet
    public boolean checkWinCondition() {
        return this.gameField.checkWinCondition(this.blockSet);
    }

    // check a BlockSet
    public boolean checkWinCondition(final BlockSet blockSet) {
        return this.gameField.checkWinCondition(blockSet);
    }

    // ----------------

    // move this BlockSet
    public boolean isValidMove(final Move move) {
        return this.gameField.isValidMove(this.blockSet, move);
    }

    // move a BlockSet
    public boolean isValidMove(final BlockSet blockSet, final Move move) {
        return this.gameField.isValidMove(blockSet, move);
    }

    // ----------------

    // print this BlockSet
    public void print() {
        this.gameField.print(this.blockSet);
    }

    // print a BlockSet
    public void print(final BlockSet blockSet) {
        this.gameField.print(blockSet);
    }
    
    // ----------------

    // draw this BlockSet
    public void draw(final int delay) {
        this.gameField.draw(this.blockSet, delay);
    }

    // Draw a BlockSet
    public void draw(final BlockSet blockSet, final int delay) {
        this.gameField.draw(blockSet, delay);
    }

    // =========================================================================

}   // Game abstract class
