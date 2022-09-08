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
    protected final BlockSet blockSet;

    /** A GameField to play on. */
    protected final GameField gameField;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor with a BlockSet, a GameField, and a game number.
     *
     * @param blockSet      The BlockSet
     * @param gameField     The GameField
     * @param gameNumber    the game number
     */
    protected Game(BlockSet blockSet, GameField gameField, int gameNumber) {
        this.blockSet  = blockSet;
        this.gameField = gameField;

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
    protected abstract void setUp(int gameNumber);

    // -------------------------------------------------------------------------
    // SHOW SOLUTION
    // -------------------------------------------------------------------------
    
    /**
     * Shows the Solution from Start to End with a time delay between two Moves.
     *
     * @param moveList  a List of the Moves to the solution
     */
    public void showSolution(List<Move> moveList, int delay) {

        System.out.println("show solution");

        int i = 0;

        draw(5 * delay);

        for (final Move move : moveList) {
            isValidMove(move);
            draw(delay);
            System.out.println(++i + "/" + moveList.size() + ": " + move);
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
        return gameField.checkWinCondition(blockSet);
    }

    // check a BlockSet
    public boolean checkWinCondition(BlockSet blockSet) {
        return gameField.checkWinCondition(blockSet);
    }

    // ----------------
    // is valid move

    // move this BlockSet
    public boolean isValidMove(Move move) {
        return gameField.isValidMove(blockSet, move);
    }

    // move a BlockSet
    public boolean isValidMove(BlockSet blockSet, Move move) {
        return gameField.isValidMove(blockSet, move);
    }

    // ----------------
    // print / draw

    // print this BlockSet
    public void print() {
        gameField.print(blockSet);
    }

    // draw this BlockSet
    public void draw(int delay) {
        gameField.draw(blockSet, delay);
    }

}
