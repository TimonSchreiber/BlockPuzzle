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
    // TODO: how to differentiate between Mushrooms and Rabbits?

    static {

        // ---------------------------------------------------------------------
        // STRINGS

        /** Rabbits */
        final String R1 = "R1";
        final String R2 = "R2";
        final String R3 = "R3";
        final String R4 = "R4";

        /** Foxes */
        final String F1 = "F1";
        final String F2 = "F2";

        /** Mushrooms */
        final String M1 = "M1";
        final String M2 = "M2";
        final String M3 = "M3";

        // ---------------------------------------------------------------------
        // COLORS

        final Color RABBIT_1    = new Color( 64,  64,  64); // Dark Grey
        final Color RABBIT_2    = new Color(255, 255,   0); // Yellow
        final Color RABBIT_3    = new Color(192, 192, 192); // Light Gray
        final Color RABBIT_4    = new Color(  0,   0,   0); // Black

        final Color FOX_1       = new Color(255, 200,   0); // ORANGE
        final Color FOX_2       = new Color(255, 140,   0);

        final Color MUSHROOM_1  = new Color(255,   0,   0); // RED
        final Color MUSHROOM_2  = new Color(178,   0,   0);
        final Color MUSHROOM_3  = new Color(124,   0,   0);

        // ---------------------------------------------------------------------
        // TODO: POSITIONS?

        // ---------------------------------------------------------------------
        // GAMES

        /* Game 0 - 2 Moves
         * Starter 1
         * __ M2 M3 __ __
         * __ __ __ M1 __
         * __ __ __ R1 __
         * __ __ __ __ __
         * __ __ __ __ __
         */

        JumpingRabbits.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(
                        R1,
                        RABBIT_1,
                        MovePattern.allDirections,
                        true,
                        new PositionsInfo(new Position(3, 2), 1, Direction.D, false)),
                    new BlockInfo(
                        M1,
                        MUSHROOM_1,
                        MovePattern.NO_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(3, 3), 1, Direction.D, false)),
                    new BlockInfo(
                        M2,
                        MUSHROOM_2,
                        MovePattern.NO_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(1, 4), 1, Direction.D, false)),
                    new BlockInfo(
                        M3,
                        MUSHROOM_3,
                        MovePattern.NO_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(2, 4), 1, Direction.D, false))
                    )
                )
            );    // Game 0

        // ---------------------------------------------------------------------

        /* Game 1 - 4 Moves
         * Junior 13
         * __ __ __ M3 __
         * __ M2 R1 F1 F1
         * __ __ M1 __ __
         * __ __ __ __ __
         * __ __ __ __ __
         */

        JumpingRabbits.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(
                        R1,
                        RABBIT_1,
                        MovePattern.allDirections,
                        true,
                        new PositionsInfo(new Position(2, 3), 1, Direction.D, false)),
                    new BlockInfo(
                        F1,
                        FOX_1,
                        MovePattern.RIGHT_LEFT_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(3, 3), 2, Direction.R, false)),
                    new BlockInfo(
                        M1,
                        MUSHROOM_1,
                        MovePattern.NO_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(2, 2), 1, Direction.D, false)),
                    new BlockInfo(
                        M2,
                        MUSHROOM_2,
                        MovePattern.NO_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(1, 3), 1, Direction.D, false)),
                    new BlockInfo(
                        M3,
                        MUSHROOM_3,
                        MovePattern.NO_DIRECTIONS,
                        false,
                        new PositionsInfo(new Position(3, 4), 1, Direction.D, false))
                    )
                )
            );    // Game 1

            // -----------------------------------------------------------------

            /* Game 2 - 13 Moves
             * Expert 35
             * __ F1 __ F2 __
             * __ F1 __ F2 M3
             * __ __ __ __ M2
             * R1 __ __ R2 __
             * __ __ __ __ M1
             */

            JumpingRabbits.START_POSITION_LIST.add(
                new ArrayList<>(
                    Arrays.asList(
                        new BlockInfo(
                            R1,
                            RABBIT_1,
                            MovePattern.allDirections,
                            true,
                            new PositionsInfo(new Position(0, 1), 1, Direction.D, false)),
                        new BlockInfo(
                            R2,
                            RABBIT_2,
                            MovePattern.allDirections,
                            true,
                            new PositionsInfo(new Position(3, 1), 1, Direction.D, false)),
                        new BlockInfo(
                            F1,
                            FOX_1,
                            MovePattern.DOWN_UP_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(1, 3), 2, Direction.U, false)),
                        new BlockInfo(
                            F2,
                            FOX_2,
                            MovePattern.DOWN_UP_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(3, 3), 2, Direction.U, false)),
                        new BlockInfo(
                            M1,
                            MUSHROOM_1,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(4, 0), 1, Direction.D, false)),
                        new BlockInfo(
                            M2,
                            MUSHROOM_2,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(4, 2), 1, Direction.D, false)),
                        new BlockInfo(
                            M3,
                            MUSHROOM_3,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(4, 3), 1, Direction.D, false))
                        )
                    )
                );    // Game 2

            // -----------------------------------------------------------------

            /* Game 3 - 21 Moves
             * Master 57
             * __ F2 __ __ __
             * M3 F2 __ __ __
             * __ __ M2 __ __
             * F1 F1 __ __ __
             * M1 __ R1 __ __
             */

            JumpingRabbits.START_POSITION_LIST.add(
                new ArrayList<>(
                    Arrays.asList(
                        new BlockInfo(
                            R1,
                            RABBIT_1,
                            MovePattern.allDirections,
                            true,
                            new PositionsInfo(new Position(2, 0), 1, Direction.D, false)),
                        new BlockInfo(
                            F1,
                            FOX_1,
                            MovePattern.RIGHT_LEFT_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(0, 1), 2, Direction.R, false)),
                        new BlockInfo(
                            F2,
                            FOX_2,
                            MovePattern.DOWN_UP_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(1, 3), 2, Direction.U, false)),
                        new BlockInfo(
                            M1,
                            MUSHROOM_1,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(0, 0), 1, Direction.D, false)),
                        new BlockInfo(
                            M2,
                            MUSHROOM_2,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(2, 2), 1, Direction.D, false)),
                        new BlockInfo(
                            M3,
                            MUSHROOM_3,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(0, 3), 1, Direction.D, false))
                        )
                    )
                );    // Game 3

            // -----------------------------------------------------------------

            /* Game 4 - 36 Moves
             * Wizard 79
             * __ __ __ __ R3
             * F2 F2 __ R2 __
             * __ F1 M2 __ __
             * __ F1 __ __ __
             * __ __ __ R1 M1
             */

            JumpingRabbits.START_POSITION_LIST.add(
                new ArrayList<>(
                    Arrays.asList(
                        new BlockInfo(
                            R1,
                            RABBIT_1,
                            MovePattern.allDirections,
                            true,
                            new PositionsInfo(new Position(3, 0), 1, Direction.D, false)),
                        new BlockInfo(
                            R2,
                            RABBIT_2,
                            MovePattern.allDirections,
                            true,
                            new PositionsInfo(new Position(3, 3), 1, Direction.D, false)),
                        new BlockInfo(
                            R3,
                            RABBIT_3,
                            MovePattern.allDirections,
                            true,
                            new PositionsInfo(new Position(4, 4), 1, Direction.D, false)),
                        new BlockInfo(
                            F1,
                            FOX_1,
                            MovePattern.DOWN_UP_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(1, 1), 2, Direction.U, false)),
                        new BlockInfo(
                            F2,
                            FOX_2,
                            MovePattern.RIGHT_LEFT_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(0, 3), 2, Direction.U, false)),
                        new BlockInfo(
                            M1,
                            MUSHROOM_1,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(4, 0), 1, Direction.D, false)),
                        new BlockInfo(
                            M2,
                            MUSHROOM_2,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(2, 2), 1, Direction.D, false))
                        )
                    )
                );    // Game 4

            // -----------------------------------------------------------------

            /* Game 5 - 77 Moves
             * Wizard 100
             * __ M3 __ __ R4
             * __ __ F1 F1 __
             * R2 __ M2 __ R3
             * __ __ __ __ M1
             * __ R1 __ __ __
             */

            JumpingRabbits.START_POSITION_LIST.add(
                new ArrayList<>(
                    Arrays.asList(
                        new BlockInfo(
                            R1,
                            RABBIT_1,
                            MovePattern.allDirections,
                            true,
                            new PositionsInfo(new Position(1, 0), 1, Direction.D, false)),
                        new BlockInfo(
                            R2,
                            RABBIT_2,
                            MovePattern.allDirections,
                            true,
                            new PositionsInfo(new Position(0, 2), 1, Direction.D, false)),
                        new BlockInfo(
                            R3,
                            RABBIT_3,
                            MovePattern.allDirections,
                            true,
                            new PositionsInfo(new Position(4, 2), 1, Direction.D, false)),
                        new BlockInfo(
                            R4,
                            RABBIT_4,
                            MovePattern.allDirections,
                            true,
                            new PositionsInfo(new Position(4, 4), 1, Direction.D, false)),
                        new BlockInfo(
                            F1,
                            FOX_1,
                            MovePattern.RIGHT_LEFT_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(2, 3), 2, Direction.R, false)),
                        new BlockInfo(
                            M1,
                            MUSHROOM_1,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(4, 1), 1, Direction.D, false)),
                        new BlockInfo(
                            M2,
                            MUSHROOM_2,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(2, 2), 1, Direction.D, false)),
                        new BlockInfo(
                            M3,
                            MUSHROOM_3,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionsInfo(new Position(1, 4), 1, Direction.D, false))
                        )
                    )
                );    // Game 5

            // -----------------------------------------------------------------

    }   // static

    // =========================================================================

}   // Jumping Rabbits class
