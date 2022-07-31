package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import block.Block;
import block.BlockInfo;
import block.Position;
import field.BlockSet;
import field.Directions;
import field.GameField;
import field.Move;

public final class JumpingRabbits {

    // -------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -------------------------------------------------------------------------

    // TODO: 1# pass this into the GameField
    private static final int SIZE = 5;

    /** List of a List of {@code BlockInfos} */
    private static final
        List<List<BlockInfo>> START_POSITION_LIST
            = new ArrayList<>();

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

        /* Game 0 - 13 Moves
         *
         * Y3 Y3 Y4 B1 B2 __
         * Y3 Y4 Y4 B1 B2 __
         * Y1 Y1 G5 G6 R1 R1
         * Y1 Y2 G3 G4 R1 R1
         * Y2 Y2 G1 G2 B3 B3
         */

        JumpingRabbits.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(4, 2), 4, Directions.R),     // R4
                    new BlockInfo(new Position(1, 0), 3, Directions.L),     // Y1
                    new BlockInfo(new Position(0, 2), 3, Directions.R),     // Y2
                    new BlockInfo(new Position(0, 4), 3, Directions.R),     // Y3
                    new BlockInfo(new Position(2, 3), 3, Directions.L),     // Y4
                    new BlockInfo(new Position(3, 3), 2, Directions.U),     // B1
                    new BlockInfo(new Position(4, 3), 2, Directions.U),     // B2
                    new BlockInfo(new Position(4, 0), 2, Directions.R),     // B3
                    new BlockInfo(new Position(2, 0), 1, Directions.D),     // G1
                    new BlockInfo(new Position(3, 0), 1, Directions.D),     // G2
                    new BlockInfo(new Position(2, 1), 1, Directions.D),     // G3
                    new BlockInfo(new Position(3, 1), 1, Directions.D),     // G4
                    new BlockInfo(new Position(2, 2), 1, Directions.D),     // G5
                    new BlockInfo(new Position(3, 2), 1, Directions.D)      // G6
                    )
                )
            );    // Game 0
        
        // ---------------------------------------------------------------------

    }   // static

    // =========================================================================
    
}   // Jumping Rabbits class
