package field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import block.Block;
import block.BlockInfo;
import block.Position;
import block.PositionsInfo;

public class BlockSetTests {

    /* Game 0 - 2 Moves
     * Starter 1
     * __ M2 M3 __ __
     * __ __ __ M1 __
     * __ __ __ R1 __
     * __ __ __ __ __
     * __ __ __ __ __
     */
    private final List<BlockInfo> startPosition =
        new ArrayList<>(
            List.of(
                new BlockInfo(
                    "R1",
                    null,
                    MovePattern.ALL_DIRECTIONS,
                    true,
                    new PositionsInfo(new Position(3, 2), 1, Direction.D, false)),
                new BlockInfo(
                    "M1",
                    null,
                    MovePattern.NO_DIRECTIONS,
                    false,
                    new PositionsInfo(new Position(3, 3), 1, Direction.D, false)),
                new BlockInfo(
                    "M2",
                    null,
                    MovePattern.NO_DIRECTIONS,
                    false,
                    new PositionsInfo(new Position(1, 4), 1, Direction.D, false)),
                new BlockInfo(
                    "M3",
                    null,
                    MovePattern.NO_DIRECTIONS,
                    false,
                    new PositionsInfo(new Position(2, 4), 1, Direction.D, false))
                )
            );
    private BlockSet blockSet;

    @BeforeEach
    public void setUp() {
        blockSet = new BlockSet();
        startPosition.forEach(
            blockInfo -> blockSet.add(new Block(blockInfo))
        );
    }

    // -------------------------------------------------------------------------
    // Constructor

    // TODO: This test will fail when rabbits learn how to move (see test below)
    @Test
    @DisplayName("The copy constructor should return a deep copy")
    public void newBlockSetCreatesADeepCopy() {
        final BlockSet expected = new BlockSet(blockSet);

        final Move move = new Move("R1", Direction.D);
        blockSet.makeMove(move);
        final BlockSet actual = new BlockSet(blockSet);

        assertNotEquals(expected, actual);
    }

    // -------------------------------------------------------------------------
    // Getter

    @Test
    @DisplayName("isBlock Should be true When there is a Block at the Position")
    public void isBlock_true() {
        final Position position = new Position(3, 2);
        
        assertTrue(blockSet.isBlock(position));
    }

    @Test
    @DisplayName("isBlock Should be false When there is no Block at the Position")
    public void isBlock_false() {
        final Position position = new Position(3, 4);

        assertFalse(blockSet.isBlock(position));
    }

    @Test
    @DisplayName("getBlockName Should return the blockName of the Block at the Position")
    public void getBlockName_correctPosition() {
        final String expected = "M2";
        
        final Position position = new Position(1, 4);
        final String actual = blockSet.getBlockName(position);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getBlockName Should return null if there is no Block at the Position")
    public void getBlockName_incorrectPosition() {
        final Position position = new Position(0, 4);
        final String blockName = blockSet.getBlockName(position);

        assertNull(blockName);
    }

    @Test
    @DisplayName("getBlock Should return the Block with the blockName")
    public void getBlock_correctBlockName() {
        final Block expected = new Block(
            new BlockInfo(
                "M2",
                null,
                MovePattern.NO_DIRECTIONS,
                false,
                new PositionsInfo(new Position(1, 4), 1, Direction.D, false))
        );

        final String blockName = "M2";
        final Block actual = blockSet.getBlock(blockName);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getBlock Should return null if there is no Block with the blockName")
    public void getBlock_incorrectBlockName() {

        final String blockName = "T2";
        final Block block = blockSet.getBlock(blockName);

        assertNull(block);
    }

    // -------------------------------------------------------------------------
    // Move

    // FIXME: this Test will fail, once the Rabbits move the way they are supposed to do.
    // Change move.Direction to 'U' and position to (3, 4)
    @Test
    @DisplayName("Move the correct Block into the correct Direction")
    public void makeMoveWithValidBlock() {
        final Position position = new Position(3, 1);

        final Move move = new Move("R1", Direction.D);
        blockSet.makeMove(move);

        assertTrue(blockSet.isBlock(position));
    }

    @Test
    @DisplayName("Move a nonexistant Block and nothing changes")
    public void makeMoveWithInvalidBlock() {
        final BlockSet expected = new BlockSet(blockSet);

        final Move move = new Move("T1", Direction.D);
        blockSet.makeMove(move);
        final BlockSet actual = new BlockSet(blockSet);

        assertEquals(expected, actual);
    }

}   // Block Set Test class
