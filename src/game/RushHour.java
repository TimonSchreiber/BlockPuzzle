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

public final class RushHour {

    // -------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -------------------------------------------------------------------------

    // TODO: 1# pass this into the GameField
    /** preset game values for size */
    // private static final int SIZE = 6;

    /** Winning Positions {@code PositionList}*/
    // private static final
    // PositionList WINNING_SQUARES =
    //     new PositionList(
    //         List.of(
    //             new Position(4, 3),
    //             new Position(5, 3)
    //         )
    //     );

    /** List of a List of {@code BlockInfos} */
    private static final
        List<List<BlockInfo>> START_POSITION_LIST
            = new ArrayList<>();

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    private final int gameNumber;
    private final GameField gameField;  // Height=5; Width=5

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor with a gameNumber.
     *
     * @param gameNumber    the gameNumber
     */
    public RushHour(final int gameNumber) {
        this.gameNumber = gameNumber;
        this.gameField = new GameField();

        RushHour.START_POSITION_LIST
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
        // TODO: INFO: for now the red car has a different name than the
        // other cars of the same size.
        // Can it still be in the first place? Maybe change the way Blocks are ordered

        // TODO: add a 3 long block to the BlockTypes

        /* Game 0 - 26 Moves    // TODO: check the car names/car order
         *
         * Y1 __ __ B3 __ __
         * Y1 __ __ B3 B5 B5
         * Y1 R1 R1 B2 __ __
         * __ __ Y2 B2 B4 B4
         * __ __ Y2 B1 B1 B6
         * __ __ Y2 __ __ B6
         */

        RushHour.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(1, 3), 2, Direction.R),     // R1
                    new BlockInfo(new Position(0, 3), 3, Direction.U),     // Y1   FIXME: for now not working: a Block of size 3 will be an elbow
                    new BlockInfo(new Position(3, 0), 3, Direction.U),     // Y2
                    new BlockInfo(new Position(3, 1), 2, Direction.R),     // B1
                    new BlockInfo(new Position(3, 2), 2, Direction.U),     // B2
                    new BlockInfo(new Position(3, 4), 2, Direction.U),     // B3
                    new BlockInfo(new Position(4, 2), 2, Direction.R),     // B4
                    new BlockInfo(new Position(4, 4), 2, Direction.R),     // B5
                    new BlockInfo(new Position(5, 0), 2, Direction.U)      // B6
                    )
                )
            );    // Game 0
        
        // ---------------------------------------------------------------------

    }   // static

    // =========================================================================
    
}   // Rush Hour class
