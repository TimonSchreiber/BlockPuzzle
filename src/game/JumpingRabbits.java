package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import block.Block;
import block.BlockInfo;
import block.Position;
import field.BlockSet;
import field.Direction;
import field.GameField;
import field.Move;

public final class JumpingRabbits {

    // -------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -------------------------------------------------------------------------

    // TODO: 1# pass this into the GameField
    /** preset game values for size */
    // private static final int SIZE = 5;

    // TODO: this winning positions only need to match partially (number of rabbits)
    /** Winning Positions {@code PositionList}*/
    // private static final
    // PositionList WINNING_SQUARES =
    //     new PositionList(
    //         List.of(
    //             new Position(0, 0),
    //             new Position(0, 4),
    //             new Position(2, 2),
    //             new Position(4, 0),
    //             new Position(4, 4)
    //         )
    //     );

    /** List of a List of {@code BlockInfos} */
    private static final
    List<List<BlockInfo>> START_POSITION_LIST = 
        new ArrayList<>();

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------
    private final int gameNumber;
    private final GameField gameField;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor with a gameNumber.
     *
     * @param gameNumber    the gameNumber
     */
    public JumpingRabbits(final int gameNumber) {
        this.gameNumber = gameNumber;
        this.gameField = new GameField();

        JumpingRabbits.START_POSITION_LIST
            .get(this.gameNumber)
            .forEach(blockInfo -> {
                this.gameField.placeBlock(new Block(blockInfo));
                // this.field.draw(100);    // FIXME: make this a choice of the user -> if(...) {draw()}
                // or if(..) {delay = xxx}
            });

        this.gameField.draw(1000);    // FIXME: delete?
    }

    // -------------------------------------------------------------------------
    // FORWARDING - METHODS
    // -------------------------------------------------------------------------

    public boolean checkWinnigCondition() { return this.gameField.checkWinnigCondition(); }
    public boolean isValidMove(final Move move) { return this.gameField.isValidMove(move); }
    public BlockSet blocks() { return this.gameField.blocks(); }

    public void print() { this.gameField.print(); }
    public void draw(final int delay) { this.gameField.draw(delay); }

    // -------------------------------------------------------------------------
    // STATIC-BLOCK
    // -------------------------------------------------------------------------

    static {
        // TODO: how to differentiate between Mushrooms and Rabbits?

        /* Game 0 - 2 Moves
         *
         * __ M1 M2 __ __
         * __ __ __ M3 __
         * __ __ __ R1 __
         * __ __ __ __ __
         * __ __ __ __ __
         */

        JumpingRabbits.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(3, 2), 1, Direction.R),     // R1
                    new BlockInfo(new Position(1, 4), 1, Direction.R),     // M1
                    new BlockInfo(new Position(2, 4), 1, Direction.R),     // M2
                    new BlockInfo(new Position(3, 3), 1, Direction.R)      // M3
                    )
                )
            );    // Game 0
        
        // ---------------------------------------------------------------------

    }   // static

    // =========================================================================
    
}   // Jumping Rabbits class
