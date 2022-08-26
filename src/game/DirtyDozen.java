package game;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import block.Block;
import block.BlockInfo;
import block.Position;
import block.PositionList;
import block.PositionListInfo;
import canvas.CanvasInfo;
import canvas.Rectangle;
import field.BlockSet;
import field.Direction;
import field.GameField;
import field.MovePattern;

/**
 * A Logic Puzzle based on this post:
 * https://trainyourprogrammer.de/java-254-holzblock-schiebe-spiel-das-gemeine-dutzend-.html
 */
public final class DirtyDozen extends Game {

    // -------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -------------------------------------------------------------------------

    /** Preset game values for height */
    private static final int HEIGHT = 5;

    /** Preset game values for width */
    private static final int WIDTH = 6;

    /** Preset win condition as a PositionList */
    private static final
    PositionList WIN_POSITIONS =
        new PositionList(
            List.of(
                new Position(4, 0),
                new Position(5, 0),
                new Position(4, 1),
                new Position(5, 1)
            )
        );

    /** Canvas Info */
    private static final
    CanvasInfo CANVAS_INFO =
        new CanvasInfo(
            Color.LIGHT_GRAY,
            Color.WHITE,
            Color.RED,
            List.of(
                // bottom right around the center of the GameField
                new Rectangle(4.5, 0.0, 2.5, 0.5),  // bottom
                new Rectangle(6.5, 0.5, 0.5, 2.0)   // right
            )
        );

    /** Map of a List of BlockInfos */
    private static final
    Map<Integer, List<BlockInfo>> INT_TO_BLOCKINFOS_MAP;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Class constructor with a gameNumber.
     *
     * @param gameNumber    the gameNumber
     */
    public DirtyDozen(final int gameNumber) {
        super(
            new BlockSet(),
            new GameField(HEIGHT, WIDTH, WIN_POSITIONS, CANVAS_INFO),
            gameNumber
        );
    }

    // -------------------------------------------------------------------------
    // SET UP
    // -------------------------------------------------------------------------

    @Override
    protected void setUp(final int gameNumber) {
        INT_TO_BLOCKINFOS_MAP
            .get(gameNumber)
            .forEach(
                blockInfo -> {
                    blocks.add(new Block(blockInfo));
                    gameField.draw(blocks, 100);
                }
            );
    }

    // -------------------------------------------------------------------------
    // STATIC BLOCK
    // -------------------------------------------------------------------------

    static {

        // ---------------------------------------------------------------------
        // STRINGS

        // Big Square (2x2)
        final String R1 = "R1";

        // Elbow (2x2)
        final String Y1 = "Y1";
        final String Y2 = "Y2";
        final String Y3 = "Y3";
        final String Y4 = "Y4";

        // Rectangle (1x2)
        final String B1 = "B1";
        final String B2 = "B2";
        final String B3 = "B3";

        // Square (1x1)
        final String G1 = "G1";
        final String G2 = "G2";
        final String G3 = "G3";
        final String G4 = "G4";
        final String G5 = "G5";
        final String G6 = "G6";

        // ---------------------------------------------------------------------
        // COLORS

        final Color BIG_SQUARE  = new Color(255,   0,   0); // Red

        final Color ELBOW_1     = new Color(255, 255,   0); // Yellow
        final Color ELBOW_2     = new Color(178, 178,   0);
        final Color ELBOW_3     = new Color(124, 124,   0);
        final Color ELBOW_4     = new Color( 86,  86,   0);

        final Color RECTANGLE_1 = new Color(  0,   0, 255); // Blue
        final Color RECTANGLE_2 = new Color(  0,   0, 178);
        final Color RECTANGLE_3 = new Color(  0,   0, 124);

        final Color SQUARE_1    = new Color(  0, 255,   0); // Green
        final Color SQUARE_2    = new Color(  0, 178,   0);
        final Color SQUARE_3    = new Color(  0, 124,   0);
        final Color SQUARE_4    = new Color(  0,  86,   0);
        final Color SQUARE_5    = new Color(  0,  60,   0);
        final Color SQUARE_6    = new Color(  0,  42,   0);

        // ---------------------------------------------------------------------
        // POSITIONS

        final Position P0_0 = new Position(0, 0);
        final Position P0_1 = new Position(0, 1);
        final Position P0_2 = new Position(0, 2);
        final Position P0_3 = new Position(0, 3);
        final Position P0_4 = new Position(0, 4);

        final Position P1_0 = new Position(1, 0);
        final Position P1_1 = new Position(1, 1);
        final Position P1_2 = new Position(1, 2);
        final Position P1_3 = new Position(1, 3);
        final Position P1_4 = new Position(1, 4);

        final Position P2_0 = new Position(2, 0);
        final Position P2_1 = new Position(2, 1);
        final Position P2_2 = new Position(2, 2);
        final Position P2_3 = new Position(2, 3);
        final Position P2_4 = new Position(2, 4);

        final Position P3_0 = new Position(3, 0);
        final Position P3_1 = new Position(3, 1);
        final Position P3_2 = new Position(3, 2);
        final Position P3_3 = new Position(3, 3);
        final Position P3_4 = new Position(3, 4);

        final Position P4_0 = new Position(4, 0);
        final Position P4_1 = new Position(4, 1);
        final Position P4_2 = new Position(4, 2);
        final Position P4_3 = new Position(4, 3);
        final Position P4_4 = new Position(4, 4);

        final Position P5_0 = new Position(5, 0);
        final Position P5_1 = new Position(5, 1);
        final Position P5_2 = new Position(5, 2);
        final Position P5_3 = new Position(5, 3);
        final Position P5_4 = new Position(5, 4);

        // ---------------------------------------------------------------------
        // GAMES

        INT_TO_BLOCKINFOS_MAP =
            Map.ofEntries(
                Map.entry(
                /* Game 0 - 13 Moves
                *
                * Y3 Y3 Y4 B2 B3 __
                * Y3 Y4 Y4 B2 B3 __
                * Y2 Y2 G5 G6 R1 R1
                * Y2 Y1 G3 G4 R1 R1
                * Y1 Y1 G1 G2 B1 B1
                */
                    0,
                    List.of(
                        new BlockInfo(
                            R1,
                            BIG_SQUARE,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(P4_1, 4, Direction.U, true)
                        ),
                        new BlockInfo(
                            Y1,
                            ELBOW_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P1_0, 3, Direction.L, true)
                        ),
                        new BlockInfo(
                            Y2,
                            ELBOW_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_2, 3, Direction.R, true)
                        ),
                        new BlockInfo(
                            Y3,
                            ELBOW_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_4, 3, Direction.R, true)
                        ),
                        new BlockInfo(
                            Y4,
                            ELBOW_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_3, 3, Direction.L, true)
                        ),
                        new BlockInfo(
                            B1,
                            RECTANGLE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_0, 2, Direction.R, false)
                        ),
                        new BlockInfo(
                            B2,
                            RECTANGLE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_3, 2, Direction.U, false)
                        ),
                        new BlockInfo(
                            B3,
                            RECTANGLE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_3, 2, Direction.U, false)
                        ),
                        new BlockInfo(
                            G1,
                            SQUARE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_0, 1, Direction.D, false)
                        ),
                        new BlockInfo(
                            G2,
                            SQUARE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_0, 1, Direction.D, false)
                        ),
                        new BlockInfo(
                            G3,
                            SQUARE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_1, 1, Direction.D, false)
                        ),
                        new BlockInfo(
                            G4,
                            SQUARE_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_1, 1, Direction.D, false)
                        ),
                        new BlockInfo(
                            G5,
                            SQUARE_5,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_2, 1, Direction.D, false)
                        ),
                        new BlockInfo(
                            G6,
                            SQUARE_6,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_2, 1, Direction.D, false)
                        )
                    )
                ),
                Map.entry(
                /* Game 1 - 43 Moves
                *
                * R1 R1 G5 G6 B3 B3
                * R1 R1 G3 G4 B2 B2
                * __ __ G1 G2 B1 B1
                * Y1 Y1 Y2 Y3 Y3 Y4
                * Y1 Y2 Y2 Y3 Y4 Y4
                */
                    1,
                    List.of(
                        new BlockInfo(
                            R1,
                            BIG_SQUARE,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(P0_3, 4, Direction.U, true)),
                        new BlockInfo(
                            Y1,
                            ELBOW_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_1, 3, Direction.R, true)),
                        new BlockInfo(
                            Y2,
                            ELBOW_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_0, 3, Direction.L, true)),
                        new BlockInfo(
                            Y3,
                            ELBOW_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_1, 3, Direction.R, true)),
                        new BlockInfo(
                            Y4,
                            ELBOW_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_0, 3, Direction.L, true)),
                        new BlockInfo(
                            B1,
                            RECTANGLE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_2, 2, Direction.R, false)),
                        new BlockInfo(
                            B2,
                            RECTANGLE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_3, 2, Direction.R, false)),
                        new BlockInfo(
                            B3,
                            RECTANGLE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_4, 2, Direction.R, false)),
                        new BlockInfo(
                            G1,
                            SQUARE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G2,
                            SQUARE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G3,
                            SQUARE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G4,
                            SQUARE_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G5,
                            SQUARE_5,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_4, 1, Direction.D, false)),
                        new BlockInfo(
                            G6,
                            SQUARE_6,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_4, 1, Direction.D, false))
                    )
                ),
                Map.entry(
                /* Game 2 - 52 Moves
                *
                * R1 R1 G4 G5 G6 __
                * R1 R1 G1 G2 G3 __
                * B1 B1 B2 B2 B3 B3
                * Y1 Y1 Y2 Y3 Y3 Y4
                * Y1 Y2 Y2 Y3 Y4 Y4
                */
                    2,
                    List.of(
                        new BlockInfo(
                            R1,
                            BIG_SQUARE,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(P0_3, 4, Direction.U, true)),
                        new BlockInfo(
                            Y1,
                            ELBOW_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_1, 3, Direction.R, true)),
                        new BlockInfo(
                            Y2,
                            ELBOW_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_0, 3, Direction.L, true)),
                        new BlockInfo(
                            Y3,
                            ELBOW_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_1, 3, Direction.R, true)),
                        new BlockInfo(
                            Y4,
                            ELBOW_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_0, 3, Direction.L, true)),
                        new BlockInfo(
                            B1,
                            RECTANGLE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_2, 2, Direction.R, false)),
                        new BlockInfo(
                            B2,
                            RECTANGLE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_2, 2, Direction.R, false)),
                        new BlockInfo(
                            B3,
                            RECTANGLE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_2, 2, Direction.R, false)),
                        new BlockInfo(
                            G1,
                            SQUARE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G2,
                            SQUARE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G3,
                            SQUARE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G4,
                            SQUARE_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_4, 1, Direction.D, false)),
                        new BlockInfo(
                            G5,
                            SQUARE_5,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_4, 1, Direction.D, false)),
                        new BlockInfo(
                            G6,
                            SQUARE_6,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_4, 1, Direction.D, false))
                    )
                ),
                Map.entry(
                /* Game 3 - 58 Moves
                *
                * R1 R1 G6 Y3 Y3 Y4
                * R1 R1 G5 Y3 Y4 Y4
                * B3 B3 G4 Y1 Y1 Y2
                * B2 B2 G3 Y1 Y2 Y2
                * B1 B1 G1 G2 __ __
                */
                    3,
                    List.of(
                        new BlockInfo(
                            R1,
                            BIG_SQUARE,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(P0_3, 4, Direction.U, true)),
                        new BlockInfo(
                            Y1,
                            ELBOW_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_2, 3, Direction.R, true)),
                        new BlockInfo(
                            Y2,
                            ELBOW_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_1, 3, Direction.L, true)),
                        new BlockInfo(
                            Y3,
                            ELBOW_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_4, 3, Direction.R, true)),
                        new BlockInfo(
                            Y4,
                            ELBOW_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_3, 3, Direction.L, true)),
                        new BlockInfo(
                            B1,
                            RECTANGLE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_0, 2, Direction.R, false)),
                        new BlockInfo(
                            B2,
                            RECTANGLE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_1, 2, Direction.R, false)),
                        new BlockInfo(
                            B3,
                            RECTANGLE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_2, 2, Direction.R, false)),
                        new BlockInfo(
                            G1,
                            SQUARE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_0, 1, Direction.D, false)),
                        new BlockInfo(
                            G2,
                            SQUARE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_0, 1, Direction.D, false)),
                        new BlockInfo(
                            G3,
                            SQUARE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_1, 1, Direction.D, false)),
                        new BlockInfo(
                            G4,
                            SQUARE_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G5,
                            SQUARE_5,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G6,
                            SQUARE_6,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_4, 1, Direction.D, false))
                    )
                ),
                Map.entry(
                /* Game 4 - 60 Moves
                *
                * R1 R1 Y3 Y3 Y4 G6
                * R1 R1 Y3 Y4 Y4 G5
                * Y2 Y2 B3 B3 G3 G4
                * Y2 Y1 B2 B2 G2 __
                * Y1 Y1 B1 B1 G1 __
                */
                    4,
                    List.of(
                        new BlockInfo(
                            R1,
                            BIG_SQUARE,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(P0_3, 4, Direction.U, true)),
                        new BlockInfo(
                            Y1,
                            ELBOW_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P1_0, 3, Direction.L, true)),
                        new BlockInfo(
                            Y2,
                            ELBOW_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_2, 3, Direction.R, true)),
                        new BlockInfo(
                            Y3,
                            ELBOW_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_4, 3, Direction.R, true)),
                        new BlockInfo(
                            Y4,
                            ELBOW_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_3, 3, Direction.L, true)),
                        new BlockInfo(
                            B1,
                            RECTANGLE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_0, 2, Direction.R, false)),
                        new BlockInfo(
                            B2,
                            RECTANGLE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_1, 2, Direction.R, false)),
                        new BlockInfo(
                            B3,
                            RECTANGLE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_2, 2, Direction.R, false)),
                        new BlockInfo(
                            G1,
                            SQUARE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_0, 1, Direction.D, false)),
                        new BlockInfo(
                            G2,
                            SQUARE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_1, 1, Direction.D, false)),
                        new BlockInfo(
                            G3,
                            SQUARE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G4,
                            SQUARE_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G5,
                            SQUARE_5,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G6,
                            SQUARE_6,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_4, 1, Direction.D, false))
                    )
                ),
                Map.entry(
                /* Game 5 - 64 Moves
                *
                * R1 R1 B3 B3 G5 G6
                * R1 R1 B2 B2 G4 G4
                * __ __ B1 B1 G1 G2
                * Y1 Y1 Y2 Y3 Y3 Y4
                * Y1 Y2 Y2 Y3 Y4 Y4
                */
                    5,
                    List.of(
                        new BlockInfo(
                            R1,
                            BIG_SQUARE,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(P0_3, 4, Direction.U, true)),
                        new BlockInfo(
                            Y1,
                            ELBOW_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_1, 3, Direction.R, true)),
                        new BlockInfo(
                            Y2,
                            ELBOW_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_0, 3, Direction.L, true)),
                        new BlockInfo(
                            Y3,
                            ELBOW_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_1, 3, Direction.R, true)),
                        new BlockInfo(
                            Y4,
                            ELBOW_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_0, 3, Direction.L, true)),
                        new BlockInfo(
                            B1,
                            RECTANGLE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_2, 2, Direction.R, false)),
                        new BlockInfo(
                            B2,
                            RECTANGLE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_3, 2, Direction.R, false)),
                        new BlockInfo(
                            B3,
                            RECTANGLE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_4, 2, Direction.R, false)),
                        new BlockInfo(
                            G1,
                            SQUARE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G2,
                            SQUARE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G3,
                            SQUARE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G4,
                            SQUARE_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G5,
                            SQUARE_5,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_4, 1, Direction.D, false)),
                        new BlockInfo(
                            G6,
                            SQUARE_6,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_4, 1, Direction.D, false))
                    )
                ),
                Map.entry(
                /* Game 6 - 75 Moves
                *
                * R1 R1 G4 G5 G6 __
                * R1 R1 G1 G2 G3 __
                * B3 B3 Y3 Y3 Y4 Y4
                * B2 B2 Y3 Y1 Y4 Y2
                * B1 B1 Y1 Y1 Y2 Y2
                */
                    6,
                    List.of(
                        new BlockInfo(
                            R1,
                            BIG_SQUARE,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(P0_3, 4, Direction.U, true)),
                        new BlockInfo(
                            Y1,
                            ELBOW_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_0, 3, Direction.L, true)),
                        new BlockInfo(
                            Y2,
                            ELBOW_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_0, 3, Direction.L, true)),
                        new BlockInfo(
                            Y3,
                            ELBOW_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_2, 3, Direction.R, true)),
                        new BlockInfo(
                            Y4,
                            ELBOW_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_2, 3, Direction.R, true)),
                        new BlockInfo(
                            B1,
                            RECTANGLE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_0, 2, Direction.R, false)),
                        new BlockInfo(
                            B2,
                            RECTANGLE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_1, 2, Direction.R, false)),
                        new BlockInfo(
                            B3,
                            RECTANGLE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_2, 2, Direction.R, false)),
                        new BlockInfo(
                            G1,
                            SQUARE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G2,
                            SQUARE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G3,
                            SQUARE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G4,
                            SQUARE_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_4, 1, Direction.D, false)),
                        new BlockInfo(
                            G5,
                            SQUARE_5,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_4, 1, Direction.D, false)),
                        new BlockInfo(
                            G6,
                            SQUARE_6,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_4, 1, Direction.D, false))
                    )
                ),
                Map.entry(
                /* Game 7 - 76 Moves
                *
                * R1 R1 G6 Y3 Y3 Y4
                * R1 R1 G5 Y3 Y4 Y4
                * B3 B3 G3 G4 __ __
                * B2 B2 G2 Y1 Y1 Y2
                * B1 B1 G1 Y1 Y2 Y2
                */
                    7,
                    List.of(
                        new BlockInfo(
                            R1,
                            BIG_SQUARE,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(P0_3, 4, Direction.U, true)),
                        new BlockInfo(
                            Y1,
                            ELBOW_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_1, 3, Direction.R, true)),
                        new BlockInfo(
                            Y2,
                            ELBOW_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_0, 3, Direction.L, true)),
                        new BlockInfo(
                            Y3,
                            ELBOW_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_4, 3, Direction.R, true)),
                        new BlockInfo(
                            Y4,
                            ELBOW_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_3, 3, Direction.L, true)),
                        new BlockInfo(
                            B1,
                            RECTANGLE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_0, 2, Direction.R, false)),
                        new BlockInfo(
                            B2,
                            RECTANGLE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_1, 2, Direction.R, false)),
                        new BlockInfo(
                            B3,
                            RECTANGLE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_2, 2, Direction.R, false)),
                        new BlockInfo(
                            G1,
                            SQUARE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_0, 1, Direction.D, false)),
                        new BlockInfo(
                            G2,
                            SQUARE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_1, 1, Direction.D, false)),
                        new BlockInfo(
                            G3,
                            SQUARE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G4,
                            SQUARE_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G5,
                            SQUARE_5,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G6,
                            SQUARE_6,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_4, 1, Direction.D, false))
                    )
                ),
                Map.entry(
                /* Game 8 - 80 Moves
                *
                * R1 R1 __ Y3 Y3 Y4
                * R1 R1 __ Y3 Y4 Y4
                * G5 G6 B3 B3 Y2 Y2
                * G3 G4 B2 B2 Y2 Y1
                * G1 G2 B1 B1 Y1 Y1
                */
                    8,
                    List.of(
                        new BlockInfo(
                            R1,
                            BIG_SQUARE,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(P0_3, 4, Direction.U, true)),
                        new BlockInfo(
                            Y1,
                            ELBOW_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_0, 3, Direction.L, true)),
                        new BlockInfo(
                            Y2,
                            ELBOW_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_2, 3, Direction.R, true)),
                        new BlockInfo(
                            Y3,
                            ELBOW_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_4, 3, Direction.R, true)),
                        new BlockInfo(
                            Y4,
                            ELBOW_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_3, 3, Direction.L, true)),
                        new BlockInfo(
                            B1,
                            RECTANGLE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_0, 2, Direction.R, false)),
                        new BlockInfo(
                            B2,
                            RECTANGLE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_1, 2, Direction.R, false)),
                        new BlockInfo(
                            B3,
                            RECTANGLE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_2, 2, Direction.R, false)),
                        new BlockInfo(
                            G1,
                            SQUARE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_0, 1, Direction.D, false)),
                        new BlockInfo(
                            G2,
                            SQUARE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P1_0, 1, Direction.D, false)),
                        new BlockInfo(
                            G3,
                            SQUARE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_1, 1, Direction.D, false)),
                        new BlockInfo(
                            G4,
                            SQUARE_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P1_1, 1, Direction.D, false)),
                        new BlockInfo(
                            G5,
                            SQUARE_5,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G6,
                            SQUARE_6,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P1_2, 1, Direction.D, false))
                    )
                ),
                Map.entry(
                /* Game 9 - 99 Moves
                *
                * R1 R1 Y4 Y4 G5 G6
                * R1 R1 Y4 Y3 G3 G4
                * B3 B3 Y3 Y3 G1 G2
                * B2 B2 Y1 Y1 Y2 __
                * B1 B1 Y1 Y2 Y2 __
                */
                    9,
                    List.of(
                        new BlockInfo(
                            R1,
                            BIG_SQUARE,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(P0_3, 4, Direction.U, true)),
                        new BlockInfo(
                            Y1,
                            ELBOW_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_1, 3, Direction.R, true)),
                        new BlockInfo(
                            Y2,
                            ELBOW_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_0, 3, Direction.L, true)),
                        new BlockInfo(
                            Y3,
                            ELBOW_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_2, 3, Direction.L, true)),
                        new BlockInfo(
                            Y4,
                            ELBOW_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_4, 3, Direction.R, true)),
                        new BlockInfo(
                            B1,
                            RECTANGLE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_0, 2, Direction.R, false)),
                        new BlockInfo(
                            B2,
                            RECTANGLE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_1, 2, Direction.R, false)),
                        new BlockInfo(
                            B3,
                            RECTANGLE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_2, 2, Direction.R, false)),
                        new BlockInfo(
                            G1,
                            SQUARE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G2,
                            SQUARE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G3,
                            SQUARE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G4,
                            SQUARE_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G5,
                            SQUARE_5,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_4, 1, Direction.D, false)),
                        new BlockInfo(
                            G6,
                            SQUARE_6,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_4, 1, Direction.D, false))
                    )
                ),
                Map.entry(
                /* Game 10 - 128 Moves
                *
                * __ __ G6 Y3 Y3 Y4
                * G3 G4 G5 Y3 Y4 Y4
                * G1 G2 R1 R1 Y2 Y2
                * B3 B3 R1 R1 Y2 Y1
                * B1 B1 B2 B2 Y1 Y1
                */
                    10,
                    List.of(
                        new BlockInfo(
                            R1,
                            BIG_SQUARE,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(P2_1, 4, Direction.U, true)),
                        new BlockInfo(
                            Y1,
                            ELBOW_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_0, 3, Direction.L, true)),
                        new BlockInfo(
                            Y2,
                            ELBOW_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_2, 3, Direction.R, true)),
                        new BlockInfo(
                            Y3,
                            ELBOW_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_4, 3, Direction.R, true)),
                        new BlockInfo(
                            Y4,
                            ELBOW_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_3, 3, Direction.L, true)),
                        new BlockInfo(
                            B1,
                            RECTANGLE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_0, 2, Direction.R, false)),
                        new BlockInfo(
                            B2,
                            RECTANGLE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_0, 2, Direction.R, false)),
                        new BlockInfo(
                            B3,
                            RECTANGLE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_1, 2, Direction.R, false)),
                        new BlockInfo(
                            G1,
                            SQUARE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G2,
                            SQUARE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P1_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G3,
                            SQUARE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G4,
                            SQUARE_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P1_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G5,
                            SQUARE_5,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G6,
                            SQUARE_6,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_4, 1, Direction.D, false))
                    )
                ),
                Map.entry(
                /* Game 11 - 132 Moves
                *
                * R1 R1 __ Y4 Y4 G6
                * R1 R1 __ Y4 G5 Y3
                * B4 B4 G3 G4 Y3 Y3
                * Y1 Y1 Y2 G2 B2 B2
                * Y1 Y2 Y2 G1 B2 B2
                */
                    11,
                    List.of(
                        new BlockInfo(
                            R1,
                            BIG_SQUARE,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(P0_3, 4, Direction.U, true)),
                        new BlockInfo(
                            Y1,
                            ELBOW_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_1, 3, Direction.R, true)),
                        new BlockInfo(
                            Y2,
                            ELBOW_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_0, 3, Direction.L, true)),
                        new BlockInfo(
                            Y3,
                            ELBOW_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_2, 3, Direction.L, true)),
                        new BlockInfo(
                            Y4,
                            ELBOW_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_4, 3, Direction.R, true)),
                        new BlockInfo(
                            B1,
                            RECTANGLE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_0, 2, Direction.U, false)),
                        new BlockInfo(
                            B2,
                            RECTANGLE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_0, 2, Direction.U, false)),
                        new BlockInfo(
                            B3,
                            RECTANGLE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_2, 2, Direction.R, false)),
                        new BlockInfo(
                            G1,
                            SQUARE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_0, 1, Direction.D, false)),
                        new BlockInfo(
                            G2,
                            SQUARE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_1, 1, Direction.D, false)),
                        new BlockInfo(
                            G3,
                            SQUARE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G4,
                            SQUARE_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G5,
                            SQUARE_5,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_3, 1, Direction.D, false)),
                        new BlockInfo(
                            G6,
                            SQUARE_6,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_4, 1, Direction.D, false))
                    )
                ),
                Map.entry(
                /* Game 12 - 263 Moves
                *
                * __ Y2 Y2 Y3 Y4 Y4
                * __ Y2 Y3 Y3 Y4 B3
                * G5 G6 R1 R1 B2 B3
                * G3 G4 R1 R1 B2 Y1
                * G1 G2 B1 B1 Y1 Y1
                */
                    12,
                    List.of(
                        new BlockInfo(
                            R1,
                            BIG_SQUARE,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(P2_1, 4, Direction.U, true)),
                        new BlockInfo(
                            Y1,
                            ELBOW_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_0, 3, Direction.L, true)),
                        new BlockInfo(
                            Y2,
                            ELBOW_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P1_4, 3, Direction.R, true)),
                        new BlockInfo(
                            Y3,
                            ELBOW_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P3_3, 3, Direction.L, true)),
                        new BlockInfo(
                            Y4,
                            ELBOW_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_4, 3, Direction.R, true)),
                        new BlockInfo(
                            B1,
                            RECTANGLE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P2_0, 2, Direction.R, false)),
                        new BlockInfo(
                            B2,
                            RECTANGLE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P4_1, 2, Direction.U, false)),
                        new BlockInfo(
                            B3,
                            RECTANGLE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P5_2, 2, Direction.U, false)),
                            new BlockInfo(
                            G1,
                            SQUARE_1,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_0, 1, Direction.D, false)),
                        new BlockInfo(
                            G2,
                            SQUARE_2,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P1_0, 1, Direction.D, false)),
                        new BlockInfo(
                            G3,
                            SQUARE_3,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_1, 1, Direction.D, false)),
                        new BlockInfo(
                            G4,
                            SQUARE_4,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P1_1, 1, Direction.D, false)),
                        new BlockInfo(
                            G5,
                            SQUARE_5,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P0_2, 1, Direction.D, false)),
                        new BlockInfo(
                            G6,
                            SQUARE_6,
                            MovePattern.ALL_DIRECTIONS,
                            false,
                            new PositionListInfo(P1_2, 1, Direction.D, false))
                    )
                )
            );

        // ---------------------------------------------------------------------

    }   // static

}    // Dirty Dozen class
