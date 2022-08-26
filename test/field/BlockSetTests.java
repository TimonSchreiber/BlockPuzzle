package field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import block.Block;
import block.BlockInfo;
import block.Position;
import block.PositionListInfo;

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
        List.of(
            new BlockInfo(
                "R1",
                null,
                MovePattern.ALL_DIRECTIONS,
                true,
                new PositionListInfo(new Position(3, 2), 1, Direction.D, false)),
            new BlockInfo(
                "M1",
                null,
                MovePattern.NO_DIRECTIONS,
                false,
                new PositionListInfo(new Position(3, 3), 1, Direction.D, false)),
            new BlockInfo(
                "M2",
                null,
                MovePattern.NO_DIRECTIONS,
                false,
                new PositionListInfo(new Position(1, 4), 1, Direction.D, false)),
            new BlockInfo(
                "M3",
                null,
                MovePattern.NO_DIRECTIONS,
                false,
                new PositionListInfo(new Position(2, 4), 1, Direction.D, false))
    );

    private BlockSet blocks;

    @BeforeEach
    public void setUp() {
        blocks = new BlockSet();
        startPosition.forEach(
            blockInfo -> blocks.add(new Block(blockInfo))
        );
    }

    // -------------------------------------------------------------------------
    // Constructor

    @Test
    @DisplayName("The copy constructor should return a deep copy")
    public void newBlockSetCreatesADeepCopy() {
        final BlockSet expected = new BlockSet(blocks);
        final BlockSet actual = new BlockSet(expected);

        final Move move = new Move("R1", Direction.U);
        actual.makeMove(move);

        assertNotEquals(expected, actual);
    }

    // -------------------------------------------------------------------------
    // Getter

    @Test
    @DisplayName("getBlockName Should return the blockName of the Block at the Position")
    public void getBlockName_correctPosition() {
        final String expected = "M2";
        
        final Position position = new Position(1, 4);
        final String actual = blocks.getNameByPosition(position);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getBlockName Should return null if there is no Block at the Position")
    public void getBlockName_incorrectPosition() {
        final Position position = new Position(0, 4);
        final String blockName = blocks.getNameByPosition(position);

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
                new PositionListInfo(new Position(1, 4), 1, Direction.D, false))
        );

        final String blockName = "M2";
        final Block actual = blocks.getBlockByName(blockName);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getBlock Should return null if there is no Block with the blockName")
    public void getBlock_incorrectBlockName() {
        final String blockName = "T2";
        final Block block = blocks.getBlockByName(blockName);

        assertNull(block);
    }

    @Test
    @DisplayName("getMainBlocks Should return a new(?) BlockSet with all the MainBlocks")
    public void getMainBlocks() {
        final BlockSet actual = blocks.getMainBlocks();
        final BlockSet expected = new BlockSet();
        expected.add(
            new Block(
                new BlockInfo(
                    "R1",
                    null,
                    MovePattern.ALL_DIRECTIONS,
                    true,
                    new PositionListInfo(
                        new Position(3, 2),
                        1,
                        Direction.D,
                        false))));

        assertEquals(expected, actual);
    }

    // -------------------------------------------------------------------------
    // Move

    @Test
    @DisplayName("Move the correct Block into the correct Direction")
    public void makeMoveWithValidBlock() {
        final Position position = new Position(3, 1);

        final Move move = new Move("R1", Direction.D);
        blocks.makeMove(move);

        assertNotNull(blocks.getNameByPosition(position));
    }

    @Test
    @DisplayName("Move a nonexistant Block and nothing changes")
    public void makeMoveWithInvalidBlock() {
        final BlockSet expected = new BlockSet(blocks);

        final Move move = new Move("T1", Direction.D);
        blocks.makeMove(move);
        final BlockSet actual = new BlockSet(blocks);

        assertEquals(expected, actual);
    }

}   // Block Set Test class
