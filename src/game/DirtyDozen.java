package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import block.Block;
import block.BlockInfo;
import block.Position;
// import block.PositionList;
import field.BlockSet;
import field.Direction;
import field.GameField;
import field.Move;

public final class DirtyDozen {

    // -------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -------------------------------------------------------------------------

    // TODO: pass this into the GameField
    /** preset game values for height */
    // private static final int HEIGHT = 5;
    /** preset game values for width */
    // private static final int WIDTH = 6;

    /** Winning Positions {@code PositionList}*/
    // private static final
    // PositionList WINNING_SQUARES =
    //     new PositionList(
    //         List.of(
    //             new Position(4, 0),
    //             new Position(5, 0),
    //             new Position(4, 1),
    //             new Position(5, 1)
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
    private final GameField gameField;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor with a gameNumber.
     *
     * @param gameNumber    the gameNumber
     */
    public DirtyDozen(final int gameNumber) {
        this.gameNumber = gameNumber;
        this.gameField = new GameField();

        DirtyDozen.START_POSITION_LIST
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
    // https://trainyourprogrammer.de/java-254-holzblock-schiebe-spiel-das-gemeine-dutzend-.html
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

        DirtyDozen.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(4, 2), 4, Direction.R),     // R1
                    new BlockInfo(new Position(1, 0), 3, Direction.L),     // Y1
                    new BlockInfo(new Position(0, 2), 3, Direction.R),     // Y2
                    new BlockInfo(new Position(0, 4), 3, Direction.R),     // Y3
                    new BlockInfo(new Position(2, 3), 3, Direction.L),     // Y4
                    new BlockInfo(new Position(3, 3), 2, Direction.U),     // B1
                    new BlockInfo(new Position(4, 3), 2, Direction.U),     // B2
                    new BlockInfo(new Position(4, 0), 2, Direction.R),     // B3
                    new BlockInfo(new Position(2, 0), 1, Direction.D),     // G1
                    new BlockInfo(new Position(3, 0), 1, Direction.D),     // G2
                    new BlockInfo(new Position(2, 1), 1, Direction.D),     // G3
                    new BlockInfo(new Position(3, 1), 1, Direction.D),     // G4
                    new BlockInfo(new Position(2, 2), 1, Direction.D),     // G5
                    new BlockInfo(new Position(3, 2), 1, Direction.D)      // G6
                    )
                )
            );    // Game 0

        // ---------------------------------------------------------------------

        /* Game 1 - 43 Moves
         *
         * R1 R1 G5 G6 B3 B3
         * R1 R1 G3 G4 B2 B2
         * __ __ G1 G2 B1 B1
         * Y1 Y1 Y2 Y3 Y3 Y4
         * Y1 Y2 Y2 Y3 Y4 Y4
         */

        DirtyDozen.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(0, 4), 4, Direction.R),
                    new BlockInfo(new Position(0, 1), 3, Direction.R),
                    new BlockInfo(new Position(2, 0), 3, Direction.L),
                    new BlockInfo(new Position(3, 1), 3, Direction.R),
                    new BlockInfo(new Position(5, 0), 3, Direction.L),
                    new BlockInfo(new Position(4, 2), 2, Direction.R),
                    new BlockInfo(new Position(4, 3), 2, Direction.R),
                    new BlockInfo(new Position(4, 4), 2, Direction.R),
                    new BlockInfo(new Position(2, 2), 1, Direction.D),
                    new BlockInfo(new Position(3, 2), 1, Direction.D),
                    new BlockInfo(new Position(2, 3), 1, Direction.D),
                    new BlockInfo(new Position(3, 3), 1, Direction.D),
                    new BlockInfo(new Position(2, 4), 1, Direction.D),
                    new BlockInfo(new Position(3, 4), 1, Direction.D)
                    )
                )
            );    // Game 1

        // ---------------------------------------------------------------------

        /* Game 2 - 52 Moves
         *
         * R1 R1 G4 G5 G6 __
         * R1 R1 G1 G2 G3 __
         * B1 B1 B2 B2 B3 B3
         * Y1 Y1 Y2 Y3 Y3 Y4
         * Y1 Y2 Y2 Y3 Y4 Y4
         */

        DirtyDozen.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(0, 4), 4, Direction.R),
                    new BlockInfo(new Position(0, 1), 3, Direction.R),
                    new BlockInfo(new Position(2, 0), 3, Direction.L),
                    new BlockInfo(new Position(3, 1), 3, Direction.R),
                    new BlockInfo(new Position(5, 0), 3, Direction.L),
                    new BlockInfo(new Position(0, 2), 2, Direction.R),
                    new BlockInfo(new Position(2, 2), 2, Direction.R),
                    new BlockInfo(new Position(4, 2), 2, Direction.R),
                    new BlockInfo(new Position(2, 3), 1, Direction.D),
                    new BlockInfo(new Position(3, 3), 1, Direction.D),
                    new BlockInfo(new Position(4, 3), 1, Direction.D),
                    new BlockInfo(new Position(2, 4), 1, Direction.D),
                    new BlockInfo(new Position(3, 4), 1, Direction.D),
                    new BlockInfo(new Position(4, 4), 1, Direction.D)
                    )
                )
            );    // Game 2

        // ---------------------------------------------------------------------

        /* Game 3 - 58 Moves
         *
         * R1 R1 G6 Y3 Y3 Y4
         * R1 R1 G5 Y3 Y4 Y4
         * B3 B3 G4 Y1 Y1 Y2
         * B2 B2 G4 Y1 Y2 Y2
         * B1 B1 G1 G2 __ __
         */

        DirtyDozen.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(0, 4), 4, Direction.R),
                    new BlockInfo(new Position(3, 2), 3, Direction.R),
                    new BlockInfo(new Position(5, 1), 3, Direction.L),
                    new BlockInfo(new Position(3, 4), 3, Direction.R),
                    new BlockInfo(new Position(5, 3), 3, Direction.L),
                    new BlockInfo(new Position(0, 0), 2, Direction.R),
                    new BlockInfo(new Position(0, 1), 2, Direction.R),
                    new BlockInfo(new Position(0, 2), 2, Direction.R),
                    new BlockInfo(new Position(2, 0), 1, Direction.D),
                    new BlockInfo(new Position(2, 1), 1, Direction.D),
                    new BlockInfo(new Position(2, 2), 1, Direction.D),
                    new BlockInfo(new Position(2, 3), 1, Direction.D),
                    new BlockInfo(new Position(2, 4), 1, Direction.D),
                    new BlockInfo(new Position(3, 0), 1, Direction.D)
                    )
                )
            );    // Game 3

        // ---------------------------------------------------------------------

        /* Game 4 - 60 Moves
         *
         * R1 R1 Y3 Y3 Y4 G6
         * R1 R1 Y3 Y4 Y4 G5
         * Y2 Y2 B3 B3 G3 G4
         * Y2 Y1 B2 B2 G2 __
         * Y1 Y1 B1 B1 G1 __
         */

        DirtyDozen.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(0, 4), 4, Direction.R),
                    new BlockInfo(new Position(1, 0), 3, Direction.L),
                    new BlockInfo(new Position(0, 2), 3, Direction.R),
                    new BlockInfo(new Position(2, 4), 3, Direction.R),
                    new BlockInfo(new Position(4, 3), 3, Direction.L),
                    new BlockInfo(new Position(2, 0), 2, Direction.R),
                    new BlockInfo(new Position(2, 1), 2, Direction.R),
                    new BlockInfo(new Position(2, 2), 2, Direction.R),
                    new BlockInfo(new Position(4, 0), 1, Direction.D),
                    new BlockInfo(new Position(4, 1), 1, Direction.D),
                    new BlockInfo(new Position(4, 2), 1, Direction.D),
                    new BlockInfo(new Position(5, 2), 1, Direction.D),
                    new BlockInfo(new Position(5, 3), 1, Direction.D),
                    new BlockInfo(new Position(5, 4), 1, Direction.D)
                    )
                )
            );    // Game 4

        // ---------------------------------------------------------------------

        /* Game 5 - 64 Moves
         *
         * R1 R1 B3 B3 G5 G6
         * R1 R1 B2 B2 G4 G4
         * __ __ B1 B1 G1 G2
         * Y1 Y1 Y2 Y3 Y3 Y4
         * Y1 Y2 Y2 Y3 Y4 Y4
         */

        DirtyDozen.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(0, 4), 4, Direction.R),
                    new BlockInfo(new Position(0, 1), 3, Direction.R),
                    new BlockInfo(new Position(2, 0), 3, Direction.L),
                    new BlockInfo(new Position(3, 1), 3, Direction.R),
                    new BlockInfo(new Position(5, 0), 3, Direction.L),
                    new BlockInfo(new Position(2, 2), 2, Direction.R),
                    new BlockInfo(new Position(2, 3), 2, Direction.R),
                    new BlockInfo(new Position(2, 4), 2, Direction.R),
                    new BlockInfo(new Position(4, 2), 1, Direction.D),
                    new BlockInfo(new Position(5, 2), 1, Direction.D),
                    new BlockInfo(new Position(4, 3), 1, Direction.D),
                    new BlockInfo(new Position(5, 3), 1, Direction.D),
                    new BlockInfo(new Position(4, 4), 1, Direction.D),
                    new BlockInfo(new Position(5, 4), 1, Direction.D)
                    )
                )
            );    // Game 5

        // ---------------------------------------------------------------------

        /* Game 6 - 75 Moves
         *
         * R1 R1 G4 G5 G6 __
          * R1 R1 G1 G2 G3 __
          * B3 B3 Y2 Y2 Y4 Y4
          * B2 B2 Y2 Y1 Y4 Y3
          * B1 B1 Y1 Y1 Y3 Y3
         */

        DirtyDozen.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(0, 4), 4, Direction.R),
                    new BlockInfo(new Position(3, 0), 3, Direction.L),
                    new BlockInfo(new Position(2, 2), 3, Direction.R),
                    new BlockInfo(new Position(5, 0), 3, Direction.L),
                    new BlockInfo(new Position(4, 2), 3, Direction.R),
                    new BlockInfo(new Position(0, 0), 2, Direction.R),
                    new BlockInfo(new Position(0, 1), 2, Direction.R),
                    new BlockInfo(new Position(0, 2), 2, Direction.R),
                    new BlockInfo(new Position(2, 3), 1, Direction.D),
                    new BlockInfo(new Position(3, 3), 1, Direction.D),
                    new BlockInfo(new Position(4, 3), 1, Direction.D),
                    new BlockInfo(new Position(2, 4), 1, Direction.D),
                    new BlockInfo(new Position(3, 4), 1, Direction.D),
                    new BlockInfo(new Position(4, 4), 1, Direction.D)
                    )
                )
            );    // Game 6

        // ---------------------------------------------------------------------

        /* Game 7 - 76 Moves
         *
         * R1 R1 G6 Y3 Y3 Y4
         * R1 R1 G5 Y3 Y4 Y4
         * B3 B3 G3 G4 __ __
         * B2 B2 G2 Y1 Y1 Y2
         * B1 B1 G1 Y1 Y2 Y2
         */

        DirtyDozen.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(0, 4), 4, Direction.R),
                    new BlockInfo(new Position(3, 1), 3, Direction.R),
                    new BlockInfo(new Position(5, 0), 3, Direction.L),
                    new BlockInfo(new Position(3, 4), 3, Direction.R),
                    new BlockInfo(new Position(5, 3), 3, Direction.L),
                    new BlockInfo(new Position(0, 0), 2, Direction.R),
                    new BlockInfo(new Position(0, 1), 2, Direction.R),
                    new BlockInfo(new Position(0, 2), 2, Direction.R),
                    new BlockInfo(new Position(2, 0), 1, Direction.D),
                    new BlockInfo(new Position(2, 1), 1, Direction.D),
                    new BlockInfo(new Position(2, 2), 1, Direction.D),
                    new BlockInfo(new Position(3, 2), 1, Direction.D),
                    new BlockInfo(new Position(2, 3), 1, Direction.D),
                    new BlockInfo(new Position(2, 4), 1, Direction.D)
                    )
                )
            );    // Game 7

        // ---------------------------------------------------------------------

        /* Game 8 - 80 Moves
         *
         * R1 R1 __ Y1 Y1 Y4
         * R1 R1 __ Y1 Y4 Y4
         * G5 G6 B3 B3 Y3 Y3
         * G3 G4 B2 B2 Y3 Y2
         * G1 G2 B1 B1 Y2 Y2
         */

        DirtyDozen.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(0, 4), 4, Direction.R),
                    new BlockInfo(new Position(3, 4), 3, Direction.R),
                    new BlockInfo(new Position(5, 0), 3, Direction.L),
                    new BlockInfo(new Position(4, 2), 3, Direction.R),
                    new BlockInfo(new Position(5, 3), 3, Direction.L),
                    new BlockInfo(new Position(2, 0), 2, Direction.R),
                    new BlockInfo(new Position(2, 1), 2, Direction.R),
                    new BlockInfo(new Position(2, 2), 2, Direction.R),
                    new BlockInfo(new Position(0, 0), 1, Direction.D),
                    new BlockInfo(new Position(1, 0), 1, Direction.D),
                    new BlockInfo(new Position(0, 1), 1, Direction.D),
                    new BlockInfo(new Position(1, 1), 1, Direction.D),
                    new BlockInfo(new Position(0, 2), 1, Direction.D),
                    new BlockInfo(new Position(1, 2), 1, Direction.D)
                    )
                )
            );    // Game 8

        // ---------------------------------------------------------------------

        /* Game 9 - 99 Moves
         *
         * R1 R1 Y4 Y4 G5 G6
         * R1 R1 Y4 Y3 G3 G4
         * B3 B3 Y3 Y3 G1 G2
         * B2 B2 Y1 Y1 Y2 __
         * B1 B1 Y1 Y2 Y2 __
         */

        DirtyDozen.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(0, 4), 4, Direction.R),
                    new BlockInfo(new Position(2, 1), 3, Direction.R),
                    new BlockInfo(new Position(4, 0), 3, Direction.L),
                    new BlockInfo(new Position(3, 2), 3, Direction.L),
                    new BlockInfo(new Position(2, 4), 3, Direction.R),
                    new BlockInfo(new Position(0, 0), 2, Direction.R),
                    new BlockInfo(new Position(0, 1), 2, Direction.R),
                    new BlockInfo(new Position(0, 2), 2, Direction.R),
                    new BlockInfo(new Position(4, 2), 1, Direction.D),
                    new BlockInfo(new Position(5, 2), 1, Direction.D),
                    new BlockInfo(new Position(4, 3), 1, Direction.D),
                    new BlockInfo(new Position(5, 3), 1, Direction.D),
                    new BlockInfo(new Position(4, 4), 1, Direction.D),
                    new BlockInfo(new Position(5, 4), 1, Direction.D)
                    )
                )
            );    // Game 9

        // ---------------------------------------------------------------------

        /* Game 10 - 128 Moves
         *
         * __ __ G6 Y1 Y1 Y4
         * G3 G4 G5 Y1 Y4 Y4
         * G1 G2 R1 R1 Y3 Y3
         * B2 B2 R1 R1 Y3 Y2
         * B1 B1 B3 B3 Y2 Y2
         */

        DirtyDozen.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(2, 1), 4, Direction.U),
                    new BlockInfo(new Position(3, 4), 3, Direction.R),
                    new BlockInfo(new Position(5, 0), 3, Direction.L),
                    new BlockInfo(new Position(4, 2), 3, Direction.R),
                    new BlockInfo(new Position(5, 3), 3, Direction.L),
                    new BlockInfo(new Position(0, 0), 2, Direction.R),
                    new BlockInfo(new Position(0, 1), 2, Direction.R),
                    new BlockInfo(new Position(2, 0), 2, Direction.R),
                    new BlockInfo(new Position(0, 2), 1, Direction.D),
                    new BlockInfo(new Position(1, 2), 1, Direction.D),
                    new BlockInfo(new Position(0, 3), 1, Direction.D),
                    new BlockInfo(new Position(1, 3), 1, Direction.D),
                    new BlockInfo(new Position(2, 3), 1, Direction.D),
                    new BlockInfo(new Position(2, 4), 1, Direction.D)
                    )
                )
            );    // Game 10

        // ---------------------------------------------------------------------

        /* Game 11 - 132 Moves
         *
         * R1 R1 __ Y3 Y3 G6
         * R1 R1 __ Y3 G5 Y4
         * B1 B1 G3 G4 Y4 Y4
         * Y1 Y1 Y2 G2 B2 B3
         * Y1 Y2 Y2 G1 B2 SB3
         */

        DirtyDozen.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(0, 4), 4, Direction.R),
                    new BlockInfo(new Position(0, 1), 3, Direction.R),
                    new BlockInfo(new Position(2, 0), 3, Direction.L),
                    new BlockInfo(new Position(3, 4), 3, Direction.R),
                    new BlockInfo(new Position(5, 2), 3, Direction.L),
                    new BlockInfo(new Position(0, 2), 2, Direction.R),
                    new BlockInfo(new Position(4, 0), 2, Direction.U),
                    new BlockInfo(new Position(5, 0), 2, Direction.U),
                    new BlockInfo(new Position(3, 0), 1, Direction.D),
                    new BlockInfo(new Position(3, 1), 1, Direction.D),
                    new BlockInfo(new Position(2, 2), 1, Direction.D),
                    new BlockInfo(new Position(3, 2), 1, Direction.D),
                    new BlockInfo(new Position(4, 3), 1, Direction.D),
                    new BlockInfo(new Position(5, 4), 1, Direction.D)
                    )
                )
            );    // Game 11

        // ---------------------------------------------------------------------

        /* Game 12 - 263 Moves
         *
         * __ Y1 Y1 Y2 Y4 Y4
         * __ Y1 Y2 Y2 Y4 B3
         * G5 G6 R1 R1 B2 B3
         * G3 G4 R1 R1 B2 Y3
         * G1 G2 B1 B1 Y3 Y3
         */

        DirtyDozen.START_POSITION_LIST.add(
            new ArrayList<>(
                Arrays.asList(
                    new BlockInfo(new Position(2, 1), 4, Direction.U),
                    new BlockInfo(new Position(1, 4), 3, Direction.R),
                    new BlockInfo(new Position(3, 3), 3, Direction.L),
                    new BlockInfo(new Position(5, 0), 3, Direction.L),
                    new BlockInfo(new Position(4, 4), 3, Direction.R),
                    new BlockInfo(new Position(2, 0), 2, Direction.R),
                    new BlockInfo(new Position(4, 1), 2, Direction.U),
                    new BlockInfo(new Position(5, 2), 2, Direction.U),
                    new BlockInfo(new Position(0, 0), 1, Direction.D),
                    new BlockInfo(new Position(1, 0), 1, Direction.D),
                    new BlockInfo(new Position(0, 1), 1, Direction.D),
                    new BlockInfo(new Position(1, 1), 1, Direction.D),
                    new BlockInfo(new Position(0, 2), 1, Direction.D),
                    new BlockInfo(new Position(1, 2), 1, Direction.D)
                    )
                )
            );    // Game 12

        // ---------------------------------------------------------------------

        // TODO: more Games for testing/showing solutions
        // (e.g. all green Blocks off to the top/left side)

    }   // static

    // =========================================================================

}    // Dirty Dozen class