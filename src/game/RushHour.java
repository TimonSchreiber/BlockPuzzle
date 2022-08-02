package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import block.Block;
import block.BlockInfo;
import block.Position;
import block.PositionsInfo;
import field.BlockSet;
import field.Direction;
import field.GameField;
import field.Move;
import field.MovePattern;

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
    List<List<BlockInfo>> START_POSITION_LIST =
        new ArrayList<>();

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

        // ---------------------------------------------------------------------
        // STRINGS

        /** Escape Car */
        final String R1 = "R1";

        /** Cars */
        final String C1 = "C1";
        final String C2 = "C2";
        final String C3 = "C3";
        final String C4 = "C4";
        final String C5 = "C5";
        final String C6 = "C6";
        // unlock when needed
        // final String C7 = "C7";
        // final String C8 = "C8";
        // final String C9 = "C9";
        // final String CA = "CA";
        // final String CB = "CB";

        /** Trucks */
        final String T1 = "T1";
        final String T2 = "T2";
        final String T3 = "T3";
        final String T4 = "T4";

        // ---------------------------------------------------------------------
        // COLORS

        /** Escape Car */
        final Color RED_CAR = new Color(255,   0,   0); // Red

        /** Cars */
        final Color CAR_1 = new Color(  0,   0,   0);  // Black
        final Color CAR_2 = new Color(  0,   0, 178);  // Dark Blue
        final Color CAR_3 = new Color(128, 128, 128);  // Gray
        final Color CAR_4 = new Color( 64,  64,  64);  // Dark Gray
        final Color CAR_5 = new Color(  0, 255,   0);  // Green
        final Color CAR_6 = new Color(  0, 178,   0);  // Dark Green
        // unlock when needed
        // final Color CAR_7 = new Color(255, 200,   0);  // Orange
        // final Color CAR_8 = new Color(255, 140,   0);  // Dark Orange
        // final Color CAR_9 = new Color(255, 175, 175);  // Pink
        // final Color CAR_A = new Color(255, 255,   0);  // Light Pink
        // final Color CAR_B = new Color(178, 178,   0);  // Dark Yellow

        /** Trucks */
        final Color TRUCK_1 = new Color(255, 255,   0); // Yellow
        final Color TRUCK_2 = new Color(  0,   0, 255); // Blue
        final Color TRUCK_3 = new Color(  0, 255, 255); // Cyan
        final Color TRUCK_4 = new Color(255,   0, 255); // Magenta

        // ---------------------------------------------------------------------
        // TODO: POSITIONS?

        // ---------------------------------------------------------------------
        // GAMES

        /* Game 0 - 16? Moves
         * Beginner 1
         * C3 C3 __ __ __ T4
         * T2 __ __ T3 __ T4
         * T2 R1 R1 T3 __ T4
         * T2 __ __ T3 __ __
         * C1 __ __ __ C2 C2
         * C1 __ T1 T1 T1 __
         */

        RushHour.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(
                        R1,
                        RED_CAR,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        true,
                        new PositionsInfo(new Position(1, 3), 2, Direction.R, false)),
                    new BlockInfo(
                        C1,
                        CAR_1,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(0, 0), 2, Direction.U, false)),
                    new BlockInfo(
                        C2,
                        CAR_2,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(4, 1), 2, Direction.R, false)),
                    new BlockInfo(
                        C3,
                        CAR_3,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(0, 5), 2, Direction.R, false)),
                    new BlockInfo(
                        T1,
                        TRUCK_1,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(2, 0), 3, Direction.R, false)),
                    new BlockInfo(
                        T2,
                        TRUCK_2,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(0, 2), 3, Direction.U, false)),
                    new BlockInfo(
                        T3,
                        TRUCK_3,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(3, 2), 3, Direction.U, false)),
                    new BlockInfo(
                        T4,
                        TRUCK_4,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(5, 3), 3, Direction.U, false))
                    )
                )
            );    // Game 0

        /* Game 1 - XX Moves
         * Intermediate 11
         * T3 C3 C3 T4 __ __
         * T3 __ __ T4 __ __
         * T3 R1 R1 T4 __ __
         * __ __ C2 T2 T2 T2
         * __ __ C2 __ __ C1
         * __ __ T1 T1 T1 C1
         */

        RushHour.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(
                        R1,
                        RED_CAR,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        true,
                        new PositionsInfo(new Position(1, 3), 2, Direction.R, false)),
                    new BlockInfo(
                        C1,
                        CAR_1,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(5, 0), 2, Direction.U, false)),
                    new BlockInfo(
                        C2,
                        CAR_2,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(2, 1), 2, Direction.U, false)),
                    new BlockInfo(
                        C3,
                        CAR_3,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(1, 5), 2, Direction.R, false)),
                    new BlockInfo(
                        T1,
                        TRUCK_1,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(2, 0), 3, Direction.R, false)),
                    new BlockInfo(
                        T2,
                        TRUCK_2,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(3, 2), 3, Direction.R, false)),
                    new BlockInfo(
                        T3,
                        TRUCK_3,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(0, 3), 3, Direction.U, false)),
                    new BlockInfo(
                        T4,
                        TRUCK_4,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(3, 3), 3, Direction.U, false))
                    )
                )
            );    // Game 1

        /* Game 2 - XX Moves
         * Advanced 21
         * C2 C2 C1 T4 __ __
         * T2 __ C1 T4 __ __
         * T2 R1 R1 T4 __ __
         * T2 T3 T3 T3 __ __
         * __ __ __ __ __ __
         * __ __ __ T1 T1 T1
         */

        RushHour.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(
                        R1,
                        RED_CAR,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        true,
                        new PositionsInfo(new Position(1, 3), 2, Direction.R, false)),
                    new BlockInfo(
                        C1,
                        CAR_1,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(2, 4), 2, Direction.U, false)),
                    new BlockInfo(
                        C2,
                        CAR_2,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(0, 5), 2, Direction.R, false)),
                    new BlockInfo(
                        T1,
                        TRUCK_1,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(3, 0), 3, Direction.R, false)),
                    new BlockInfo(
                        T2,
                        TRUCK_2,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(0, 2), 3, Direction.U, false)),
                    new BlockInfo(
                        T3,
                        TRUCK_3,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(1, 2), 3, Direction.R, false)),
                    new BlockInfo(
                        T4,
                        TRUCK_4,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(3, 3), 3, Direction.U, false))
                    )
                )
            );    // Game 2

        // ---------------------------------------------------------------------

        /* Game 3 - XX Moves
         * Expert 31
         * C6 C6 __ T4 T4 T4
         * __ __ __ C4 C5 C5
         * C2 R1 R1 C4 __ T3
         * C2 __ T1 C3 C3 T3
         * C1 C1 T1 __ __ T3
         * __ __ T1 T2 T2 T2
         */

        RushHour.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(
                        R1,
                        RED_CAR,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        true,
                        new PositionsInfo(new Position(1, 3), 2, Direction.R, false)),
                    new BlockInfo(
                        C1,
                        CAR_1,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(0, 1), 2, Direction.R, false)),
                    new BlockInfo(
                        C2,
                        CAR_2,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(0, 2), 2, Direction.U, false)),
                    new BlockInfo(
                        C3,
                        CAR_3,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(3, 2), 2, Direction.R, false)),
                    new BlockInfo(
                        C4,
                        CAR_4,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(3, 3), 2, Direction.U, false)),
                    new BlockInfo(
                        C5,
                        CAR_5,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(4, 4), 2, Direction.R, false)),
                    new BlockInfo(
                        C6,
                        CAR_6,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(0, 5), 2, Direction.R, false)),
                    new BlockInfo(
                        T1,
                        TRUCK_1,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(2, 0), 3, Direction.U, false)),
                    new BlockInfo(
                        T2,
                        TRUCK_2,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(3, 0), 3, Direction.R, false)),
                    new BlockInfo(
                        T3,
                        TRUCK_3,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(5, 1), 3, Direction.U, false)),
                    new BlockInfo(
                        T4,
                        TRUCK_4,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(3, 5), 3, Direction.R, false))
                    )
                )
            );    // Game 3

        // ---------------------------------------------------------------------

    }   // static

    // =========================================================================

}   // Rush Hour class
