package game;

import java.util.List;

import block.Block;
import block.BlockInfo;
import block.PositionList;
import field.BlockSet;
import field.GameField;
import field.Move;

public abstract class Game {

    // -------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -------------------------------------------------------------------------

    // TODO: pass this into the GameField
    /** preset game values for height */
    protected /* static */ final int HEIGHT;
    /** preset game values for width */
    protected /* static */ final int WIDTH;

    /** Winning Positions {@code PositionList}*/
    protected /* static */ final
    PositionList WINNING_SQUARES;

    protected /* static */ final
    List<List<BlockInfo>> START_POSITION_LIST;

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    protected final int gameNumber;
    protected final GameField gameField;


    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor with a gameNumber.
     *
     * @param gameNumber    the gameNumber
     */
    protected Game( final int height,
                    final int width,
                    final PositionList winCondition,
                    final List<List<BlockInfo>> startPositions,
                    final int gameNumber) {
        this.HEIGHT = height;
        this.WIDTH = width;
        this.WINNING_SQUARES = winCondition;
        this.START_POSITION_LIST = startPositions;
        this.gameNumber = gameNumber;

        this.gameField = new GameField();

        this.START_POSITION_LIST
            .get(this.gameNumber)
            .forEach(blockInfo -> {
                this.gameField.placeBlock(new Block(blockInfo));
                this.gameField.draw(500);    // FIXME: make this a choice of the user -> if(...) {draw()}
                // or if(..) {delay = xxx}
            });

        this.gameField.draw(1000);    // FIXME: delete?
    }

    // -------------------------------------------------------------------------
    // FORWARDING - METHODS
    // -------------------------------------------------------------------------

    public abstract boolean checkWinnigCondition();
    public abstract boolean isValidMove(final Move move);
    public abstract BlockSet blocks();

    public abstract void print();
    public abstract void draw(final int delay);

    // =========================================================================

}   // Game abstract class
