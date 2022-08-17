package game;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import block.Block;
import block.BlockInfo;
import block.Position;
import block.PositionList;
import block.PositionsInfo;
import field.BlockSet;
import field.Direction;
import field.GameField;
import field.MovePattern;
import field.Rectangle;
import field.CanvasInfo;

/**
 * TODO
 */
public final class RushHour extends Game {

    // -------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -------------------------------------------------------------------------

    /** Preset game values for size */
    private static final int SIZE = 6;

    /** Preset win condition as a PositionList */
    private static final
    PositionList WIN_CONDITION =
        new PositionList(
            List.of(
                new Position(4, 3),
                new Position(5, 3)
            )
        );

    /** Describes how to draw the canvas. */
    private static final
    CanvasInfo CANVAS_INFO =
        new CanvasInfo(
            Color.LIGHT_GRAY,
            Color.WHITE,
            Color.RED,
            List.of(
                // middle right outside the center of the GameField
                new Rectangle(6.5, 3.5, 0.5, 1.0)
            )
        );

    /** Map of a List of BlockInfos */
    private static final
    Map<Integer, List<BlockInfo>> START_POSITION_MAP;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor with a gameNumber.
     *
     * @param gameNumber    the gameNumber
     */
    public RushHour(final int gameNumber) {
        super(
            new BlockSet(),
            new GameField(SIZE, SIZE, WIN_CONDITION, CANVAS_INFO),
            gameNumber
        );
    }

    // -------------------------------------------------------------------------
    // SET UP
    // -------------------------------------------------------------------------

    @Override
    protected void setUp(final int gameNumber) {
        START_POSITION_MAP
            .get(gameNumber)
            .forEach(
                blockInfo -> {
                    blockSet.add(new Block(blockInfo));
                    gameField.draw(blockSet, 100);    // FIXME: make this a choice of the user -> if(...) {draw()}
                    // or if(..) {delay = xxx}
                }
            );

        gameField.draw(blockSet, 1000);    // FIXME: delete?
    }

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
        final Color CAR_2 = new Color(  0, 178, 178);  // Dark Cyan
        final Color CAR_3 = new Color(255, 200,   0);  // Orange
        final Color CAR_4 = new Color(255, 140,   0);  // Dark Orange
        final Color CAR_5 = new Color(  0, 255,   0);  // Green
        final Color CAR_6 = new Color(  0, 178,   0);  // Dark Green
        // unlock when needed
        // final Color CAR_7 = new Color(128, 128, 128);  // Gray
        // final Color CAR_8 = new Color( 64,  64,  64);  // Dark Gray
        // final Color CAR_9 = new Color(255, 175, 175);  // Pink
        // final Color CAR_A = new Color(255, 255,   0);  // Light Pink
        // final Color CAR_B = new Color(178, 178,   0);  // Dark Yellow

        /** Trucks */
        final Color TRUCK_1 = new Color(255, 255,   0); // Yellow
        final Color TRUCK_2 = new Color(  0,   0, 255); // Blue
        final Color TRUCK_3 = new Color(  0, 255, 255); // Cyan
        final Color TRUCK_4 = new Color(255,   0, 255); // Magenta

        // ---------------------------------------------------------------------
        // POSITIONS

        final Position P0_0 = new Position(0, 0);
        final Position P0_1 = new Position(0, 1);
        final Position P0_2 = new Position(0, 2);
        final Position P0_3 = new Position(0, 3);
        final Position P0_5 = new Position(0, 5);

        final Position P1_2 = new Position(1, 2);
        final Position P1_3 = new Position(1, 3);
        final Position P1_5 = new Position(1, 5);

        final Position P2_0 = new Position(2, 0);
        final Position P2_1 = new Position(2, 1);
        final Position P2_4 = new Position(2, 4);

        final Position P3_0 = new Position(3, 0);
        final Position P3_2 = new Position(3, 2);
        final Position P3_3 = new Position(3, 3);
        final Position P3_5 = new Position(3, 5);

        final Position P4_1 = new Position(4, 1);
        final Position P4_4 = new Position(4, 4);

        final Position P5_0 = new Position(5, 0);
        final Position P5_1 = new Position(5, 1);
        final Position P5_3 = new Position(5, 3);

        // ---------------------------------------------------------------------
        // GAMES

        START_POSITION_MAP =
        Map.ofEntries(
            Map.entry(
            /* Game 0 - 16 Moves
             * Beginner 1
             * C3 C3 __ __ __ T4
             * T2 __ __ T3 __ T4
             * T2 R1 R1 T3 __ T4
             * T2 __ __ T3 __ __
             * C1 __ __ __ C2 C2
             * C1 __ T1 T1 T1 __
             */
                0,
                List.of(
                    new BlockInfo(
                        R1,
                        RED_CAR,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        true,
                        new PositionsInfo(P1_3, 2, Direction.R, false)),
                    new BlockInfo(
                        C1,
                        CAR_1,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P0_0, 2, Direction.U, false)),
                    new BlockInfo(
                        C2,
                        CAR_2,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P4_1, 2, Direction.R, false)),
                    new BlockInfo(
                        C3,
                        CAR_3,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P0_5, 2, Direction.R, false)),
                    new BlockInfo(
                        T1,
                        TRUCK_1,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P2_0, 3, Direction.R, false)),
                    new BlockInfo(
                        T2,
                        TRUCK_2,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P0_2, 3, Direction.U, false)),
                    new BlockInfo(
                        T3,
                        TRUCK_3,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P3_2, 3, Direction.U, false)),
                    new BlockInfo(
                        T4,
                        TRUCK_4,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P5_3, 3, Direction.U, false))
                )
            ),
            Map.entry(
            /* Game 1 - 56 Moves
             * Intermediate 11
             * T3 C3 C3 T4 __ __
             * T3 __ __ T4 __ __
             * T3 R1 R1 T4 __ __
             * __ __ C2 T2 T2 T2
             * __ __ C2 __ __ C1
             * __ __ T1 T1 T1 C1
             */
                1,
                List.of(
                    new BlockInfo(
                        R1,
                        RED_CAR,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        true,
                        new PositionsInfo(P1_3, 2, Direction.R, false)),
                    new BlockInfo(
                        C1,
                        CAR_1,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P5_0, 2, Direction.U, false)),
                    new BlockInfo(
                        C2,
                        CAR_2,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P2_1, 2, Direction.U, false)),
                    new BlockInfo(
                        C3,
                        CAR_3,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P1_5, 2, Direction.R, false)),
                    new BlockInfo(
                        T1,
                        TRUCK_1,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P2_0, 3, Direction.R, false)),
                    new BlockInfo(
                        T2,
                        TRUCK_2,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P3_2, 3, Direction.R, false)),
                    new BlockInfo(
                        T3,
                        TRUCK_3,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P0_3, 3, Direction.U, false)),
                    new BlockInfo(
                        T4,
                        TRUCK_4,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P3_3, 3, Direction.U, false))
                )
            ),
            Map.entry(
            /* Game 2 - 49 Moves
             * Advanced 21
             * C2 C2 C1 T4 __ __
             * T2 __ C1 T4 __ __
             * T2 R1 R1 T4 __ __
             * T2 T3 T3 T3 __ __
             * __ __ __ __ __ __
             * __ __ __ T1 T1 T1
             */
                2,
                List.of(
                    new BlockInfo(
                        R1,
                        RED_CAR,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        true,
                        new PositionsInfo(P1_3, 2, Direction.R, false)),
                    new BlockInfo(
                        C1,
                        CAR_1,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P2_4, 2, Direction.U, false)),
                    new BlockInfo(
                        C2,
                        CAR_2,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P0_5, 2, Direction.R, false)),
                    new BlockInfo(
                        T1,
                        TRUCK_1,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P3_0, 3, Direction.R, false)),
                    new BlockInfo(
                        T2,
                        TRUCK_2,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P0_2, 3, Direction.U, false)),
                    new BlockInfo(
                        T3,
                        TRUCK_3,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P1_2, 3, Direction.R, false)),
                    new BlockInfo(
                        T4,
                        TRUCK_4,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P3_3, 3, Direction.U, false))
                )
            ),
            Map.entry(
            /* Game 3 - 69 Moves
             * Expert 31
             * C6 C6 __ T4 T4 T4
             * __ __ __ C4 C5 C5
             * C2 R1 R1 C4 __ T3
             * C2 __ T1 C3 C3 T3
             * C1 C1 T1 __ __ T3
             * __ __ T1 T2 T2 T2
             */
                3,
                List.of(
                    new BlockInfo(
                        R1,
                        RED_CAR,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        true,
                        new PositionsInfo(P1_3, 2, Direction.R, false)),
                    new BlockInfo(
                        C1,
                        CAR_1,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P0_1, 2, Direction.R, false)),
                    new BlockInfo(
                        C2,
                        CAR_2,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P0_2, 2, Direction.U, false)),
                    new BlockInfo(
                        C3,
                        CAR_3,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P3_2, 2, Direction.R, false)),
                    new BlockInfo(
                        C4,
                        CAR_4,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P3_3, 2, Direction.U, false)),
                    new BlockInfo(
                        C5,
                        CAR_5,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P4_4, 2, Direction.R, false)),
                    new BlockInfo(
                        C6,
                        CAR_6,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P0_5, 2, Direction.R, false)),
                    new BlockInfo(
                        T1,
                        TRUCK_1,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P2_0, 3, Direction.U, false)),
                    new BlockInfo(
                        T2,
                        TRUCK_2,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P3_0, 3, Direction.R, false)),
                    new BlockInfo(
                        T3,
                        TRUCK_3,
                        MovePattern.DOWN_UP_DIRECTIONS,
                        false,
                        new PositionsInfo(P5_1, 3, Direction.U, false)),
                    new BlockInfo(
                        T4,
                        TRUCK_4,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(P3_5, 3, Direction.R, false))
                )
            )
        );

    }   // static

}   // Rush Hour class
