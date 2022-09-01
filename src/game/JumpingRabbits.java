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
import field.Move;
import field.MovePattern;

/**
 * The Game Jumping Rabbits.
 */
public final class JumpingRabbits extends Game {

    // -------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -------------------------------------------------------------------------

    /** Value for the GameField size (quadratic GameField) */
    private static final int SIZE = 5;

    /** Preset win condition as a PositionList */
    private static final
    PositionList WIN_POSITIONS =
        new PositionList(
            List.of(
                new Position(0, 0),
                new Position(0, 4),
                new Position(2, 2),
                new Position(4, 0),
                new Position(4, 4)
            )
        );

    /** Canvas Info */
    private static final
    CanvasInfo CANVAS_INFO =
        new CanvasInfo(
            new Color(139,  69,  19),   // saddlebrown
            new Color(  0, 178,   0),   // dark green
            new Color(205, 133,  63),   // peru
            List.of(
                // bottom left
                new Rectangle(0.375, 0.375, 1.25, 1.25),
                // bottom right
                new Rectangle(4.375, 0.375, 1.25, 1.25),
                // in the middle
                new Rectangle(2.375, 2.375, 1.25, 1.25),
                // top left
                new Rectangle(0.375, 4.375, 1.25, 1.25),
                // top right
                new Rectangle(4.375, 4.375, 1.25, 1.25)
            )
        );

    /** Map of a List of BlockInfos. */
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
    public JumpingRabbits(final int gameNumber) {
        super(
            new BlockSet(),
            new GameField(SIZE, SIZE, WIN_POSITIONS, CANVAS_INFO),
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
                    blockSet.add(new Block(blockInfo));
                    gameField.draw(blockSet, 100);
                }
            );
    }

    // -------------------------------------------------------------------------
    // IS VALID MOVE
    // -------------------------------------------------------------------------

    // Delegate to the method below.
    @Override
    public boolean isValidMove(final Move move) {
        return isValidMove(blockSet, move);
    }

    // If the Block is a mainBlock (aka Rabbit) it needs to jump over another
    // Block to move.
    @Override
    public boolean isValidMove(final BlockSet blockSet, final Move move) {

        final Block block = blockSet.getBlockByName(move.name());

        // check if the Move is valid.
        if (block == null) {
            System.err.println("Invalid BlockName: " + move);
            return false;
        }
        // check if the normal way to move can be applied
        // -> forward to gameField.isValidMove()
        if (!block.isMainBlock()) {
            return gameField.isValidMove(blockSet, move);
        }

        // check if there is no Block next to this one to jump over
        // -> return false
        if (gameField.isCollisionFree(blockSet, move)) {
            return false;
        }

        // count how far the rabbit needs to jump
        int numberOfMoves = 0;
        Position newPosition = null;

        // there is only ONE Position in this List (Rabbits have a size of 1)
        for (final Position position : block.positions()) {
            newPosition = new Position(position);

            do {
                // move to the next Position
                newPosition = newPosition.moveTowards(move.direction());

                //count one up
                ++numberOfMoves;
            } while (gameField.isInInterval(newPosition)
                    && (blockSet.getNameByPosition(newPosition) != null));

        }

        // check if the while loop aborted because an empty Position was found,
        // or because the GameField border was left.
        if ((newPosition != null) && !gameField.isInInterval(newPosition)) {
            return false;
        }

        // skip over all the Blocks in between
        for (int i = 0; i < numberOfMoves; ++i) {
            blockSet.makeMove(move);
        }

        return true;
    }

    // -------------------------------------------------------------------------
    // EQUALS AND HASH-CODE
    // -------------------------------------------------------------------------

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        // Object must be JumpingRabbits at this point
        final JumpingRabbits other = (JumpingRabbits) obj;

        return ((blockSet == other.blockSet)
                || (( blockSet != null)
                    && blockSet.equals(other.blockSet)))
            && ((gameField == other.gameField)
                || ((gameField != null)
                    && gameField.equals(other.gameField)));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + (( blockSet == null) ? 0 :  blockSet.hashCode());
        hash = PRIME * hash + ((gameField == null) ? 0 : gameField.hashCode());

        return hash;
    }

    // -------------------------------------------------------------------------
    // TO STRING
    // -------------------------------------------------------------------------

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return
            """
            JumpingRabbits [\
            blockSet=%s, \
            gameField=%s\
            ]\
            """.formatted(
                blockSet,
                gameField
            );
    }

    // -------------------------------------------------------------------------
    // STATIC-BLOCK
    // -------------------------------------------------------------------------

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

        final Color RABBIT_1    = new Color(  0,   0, 255); // Blue
        final Color RABBIT_2    = new Color(192, 192, 192); // Light Gray
        final Color RABBIT_3    = new Color(  0, 255, 255); // Cyan
        final Color RABBIT_4    = new Color(255,   0, 255); // Magenta

        final Color FOX_1       = new Color(255, 200,   0); // Orange
        final Color FOX_2       = new Color(255, 140,   0);

        final Color MUSHROOM_1  = new Color(255,   0,   0); // red
        final Color MUSHROOM_2  = new Color(178,   0,   0);
        final Color MUSHROOM_3  = new Color(124,   0,   0);

        // ---------------------------------------------------------------------
        // POSITIONS

        final Position P0_0 = new Position(0, 0);
        final Position P0_1 = new Position(0, 1);
        final Position P0_2 = new Position(0, 2);
        final Position P0_3 = new Position(0, 3);

        final Position P1_0 = new Position(1, 0);
        final Position P1_1 = new Position(1, 1);
        final Position P1_3 = new Position(1, 3);
        final Position P1_4 = new Position(1, 4);

        final Position P2_0 = new Position(2, 0);
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

        // ---------------------------------------------------------------------
        // GAMES

        INT_TO_BLOCKINFOS_MAP =
            Map.ofEntries(
                Map.entry(
                /* Game 0 - 2 Moves
                * Starter 1
                * __ M2 M3 __ __
                * __ __ __ M1 __
                * __ __ __ R1 __
                * __ __ __ __ __
                * __ __ __ __ __
                */
                    0,
                    List.of(
                        new BlockInfo(
                            R1,
                            RABBIT_1,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(
                                P3_2,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            M1,
                            MUSHROOM_1,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P3_3,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            M2,
                            MUSHROOM_2,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P1_4,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            M3,
                            MUSHROOM_3,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P2_4,
                                1,
                                Direction.D,
                                false
                            )
                        )
                    )
                ),
                Map.entry(
                /* Game 1 - 4 Moves
                * Junior 13
                * __ __ __ M3 __
                * __ M2 R1 F1 F1
                * __ __ M1 __ __
                * __ __ __ __ __
                * __ __ __ __ __
                */
                    1,
                    List.of(
                        new BlockInfo(
                            R1,
                            RABBIT_1,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(
                                P2_3,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            F1,
                            FOX_1,
                            MovePattern.RIGHT_LEFT_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P3_3,
                                2,
                                Direction.R,
                                false
                            )
                        ),
                        new BlockInfo(
                            M1,
                            MUSHROOM_1,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P2_2,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            M2,
                            MUSHROOM_2,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P1_3,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            M3,
                            MUSHROOM_3,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P3_4,
                                1,
                                Direction.D,
                                false
                            )
                        )
                    )
                ),
                Map.entry(
                /* Game 2 - 13 Moves
                * Expert 35
                * __ F1 __ F2 __
                * __ F1 __ F2 M3
                * __ __ __ __ M2
                * R1 __ __ R2 __
                * __ __ __ __ M1
                */
                    2,
                    List.of(
                        new BlockInfo(
                            R1,
                            RABBIT_1,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(
                                P0_1,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            R2,
                            RABBIT_2,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(
                                P3_1,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            F1,
                            FOX_1,
                            MovePattern.DOWN_UP_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P1_3,
                                2,
                                Direction.U,
                                false
                            )
                        ),
                        new BlockInfo(
                            F2,
                            FOX_2,
                            MovePattern.DOWN_UP_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P3_3,
                                2,
                                Direction.U,
                                false
                            )
                        ),
                        new BlockInfo(
                            M1,
                            MUSHROOM_1,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P4_0,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            M2,
                            MUSHROOM_2,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P4_2,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            M3,
                            MUSHROOM_3,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P4_3,
                                1,
                                Direction.D,
                                false
                            )
                        )
                    )
                ),
                Map.entry(
                /* Game 3 - 21 Moves
                * Master 57
                * __ F2 __ __ __
                * M3 F2 __ __ __
                * __ __ M2 __ __
                * F1 F1 __ __ __
                * M1 __ R1 __ __
                */
                    3,
                    List.of(
                        new BlockInfo(
                            R1,
                            RABBIT_1,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(
                                P2_0,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            F1,
                            FOX_1,
                            MovePattern.RIGHT_LEFT_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P0_1,
                                2,
                                Direction.R,
                                false
                            )
                        ),
                        new BlockInfo(
                            F2,
                            FOX_2,
                            MovePattern.DOWN_UP_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P1_3,
                                2,
                                Direction.U,
                                false
                            )
                        ),
                        new BlockInfo(
                            M1,
                            MUSHROOM_1,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P0_0,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            M2,
                            MUSHROOM_2,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P2_2,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            M3,
                            MUSHROOM_3,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P0_3,
                                1,
                                Direction.D,
                                false
                            )
                        )
                    )
                ),
                Map.entry(
                /* Game 4 - 36 Moves
                * Wizard 79
                * __ __ __ __ R3
                * F2 F2 __ R2 __
                * __ F1 M2 __ __
                * __ F1 __ __ __
                * __ __ __ R1 M1
                */
                    4,
                    List.of(
                        new BlockInfo(
                            R1,
                            RABBIT_1,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(
                                P3_0,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            R2,
                            RABBIT_2,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(
                                P3_3,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            R3,
                            RABBIT_3,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(
                                P4_4,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            F1,
                            FOX_1,
                            MovePattern.DOWN_UP_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P1_1,
                                2,
                                Direction.U,
                                false
                            )
                        ),
                        new BlockInfo(
                            F2,
                            FOX_2,
                            MovePattern.RIGHT_LEFT_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P0_3,
                                2,
                                Direction.R,
                                false
                            )
                        ),
                        new BlockInfo(
                            M1,
                            MUSHROOM_1,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P4_0,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            M2,
                            MUSHROOM_2,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P2_2,
                                1,
                                Direction.D,
                                false
                            )
                        )
                    )
                ),
                Map.entry(
                /* Game 5 - 77 Moves
                * Wizard 100
                * __ M3 __ __ R4
                * __ __ F1 F1 __
                * R2 __ M2 __ R3
                * __ __ __ __ M1
                * __ R1 __ __ __
                */
                    5,
                    List.of(
                        new BlockInfo(
                            R1,
                            RABBIT_1,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(
                                P1_0,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            R2,
                            RABBIT_2,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(
                                P0_2,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            R3,
                            RABBIT_3,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(
                                P4_2,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            R4,
                            RABBIT_4,
                            MovePattern.ALL_DIRECTIONS,
                            true,
                            new PositionListInfo(
                                P4_4,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            F1,
                            FOX_1,
                            MovePattern.RIGHT_LEFT_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P2_3,
                                2,
                                Direction.R,
                                false
                            )
                        ),
                        new BlockInfo(
                            M1,
                            MUSHROOM_1,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P4_1,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            M2,
                            MUSHROOM_2,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P2_2,
                                1,
                                Direction.D,
                                false
                            )
                        ),
                        new BlockInfo(
                            M3,
                            MUSHROOM_3,
                            MovePattern.NO_DIRECTIONS,
                            false,
                            new PositionListInfo(
                                P1_4,
                                1,
                                Direction.D,
                                false
                            )
                        )
                    )
                )
            );

    }   // static

}   // Jumping Rabbits class
