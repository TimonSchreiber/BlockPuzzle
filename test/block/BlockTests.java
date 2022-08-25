package block;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import field.Direction;
import field.MovePattern;

public class BlockTests {

    // -------------------------------------------------------------------------
    // Getter

    @Test
    @DisplayName("Test all getter methods")
    public void getters() {
        final Block actual =
            new Block(
                new BlockInfo(
                    "T4",
                    new Color(255, 0, 0),
                    MovePattern.ALL_DIRECTIONS,
                    true,
                    new PositionListInfo(new Position(3, 3), 3, Direction.U, true))
            );

        final String expectedName = "T4";
        final Color expectedColor = new Color(255, 0, 0);
        final PositionList expectedPositions = new PositionList(
            List.of(
                new Position(3, 3),
                new Position(4, 3),
                new Position(3, 4)
            )
        );
        final MovePattern expectedMovePattern = MovePattern.ALL_DIRECTIONS;

        assertAll(
            () -> assertTrue(actual.isMainBlock()),
            () -> assertEquals(expectedName, actual.name()),
            () -> assertEquals(expectedColor, actual.color()),
            () -> assertEquals(expectedPositions, actual.positions()),
            () -> assertEquals(expectedMovePattern, actual.movePattern())
        );
    }

    // -------------------------------------------------------------------------
    // Forwarding

    @Test
    @DisplayName("Block contains a Position")
    public void contains() {
        final Position pos = new Position(3, 5);
        final Block block =
            new Block(
                new BlockInfo(
                    "R2",
                    new Color(155, 0, 0),
                    MovePattern.ALL_DIRECTIONS,
                    false,
                    new PositionListInfo(new Position(3, 3), 3, Direction.U, false))
            );

            assertTrue(block.containsPosition(pos));
    }

    @Test
    @DisplayName("Move Square down twice")
    public void moveTowards() {
        final Block actual =
            new Block(
                new BlockInfo(
                    "R2",
                    new Color(155, 0, 0),
                    MovePattern.ALL_DIRECTIONS,
                    false,
                    new PositionListInfo(new Position(3, 3), 3, Direction.U, false))
            );

        final Block expected =
            new Block(
                new BlockInfo(
                    "R2",
                    new Color(155, 0, 0),
                    MovePattern.ALL_DIRECTIONS,
                    false,
                    new PositionListInfo(new Position(3, 1), 3, Direction.U, false))
            );

        actual.moveTowards(Direction.D, Direction.D);

        assertEquals(expected, actual);
    }

    // -------------------------------------------------------------------------
    // equals

    @Test
    @DisplayName("Equals Should be true When two Blocks have the same PositionList, MainBlock-Flag and MovePattern")
    public void equals() {
        final Block block1 =
            new Block(
                new BlockInfo(
                    "R2",
                    new Color(155, 0, 0),
                    MovePattern.ALL_DIRECTIONS,
                    false,
                    new PositionListInfo(new Position(3, 3), 3, Direction.D, false))
            );

        final Block block2 =
            new Block(
                new BlockInfo(
                    "G4",
                    new Color(123, 0, 43),
                    MovePattern.ALL_DIRECTIONS,
                    false,
                    new PositionListInfo(new Position(3, 1), 3, Direction.U, false))
            );

        assertEquals(block1, block2);
    }

    @Test
    @DisplayName("Equals Should be false When two Blocks have different PositionLists")
    public void equals_differentPositionList() {
        final Block block1 =
            new Block(
                new BlockInfo(
                    "R2",
                    new Color(155, 0, 0),
                    MovePattern.ALL_DIRECTIONS,
                    false,
                    new PositionListInfo(new Position(3, 3), 2, Direction.D, false))
            );

        final Block block2 =
            new Block(
                new BlockInfo(
                    "G4",
                    new Color(123, 0, 43),
                    MovePattern.ALL_DIRECTIONS,
                    false,
                    new PositionListInfo(new Position(3, 1), 3, Direction.U, false))
            );

        assertNotEquals(block1, block2);
    }

    @Test
    @DisplayName("Equals Should be false When two Blocks have different MainBlock-Flags")
    public void equals_differentMainBlockFlag() {
        final Block block1 =
            new Block(
                new BlockInfo(
                    "R2",
                    new Color(155, 0, 0),
                    MovePattern.DOWN_UP_DIRECTIONS,
                    false,
                    new PositionListInfo(new Position(3, 3), 3, Direction.D, false))
            );

        final Block block2 =
            new Block(
                new BlockInfo(
                    "G4",
                    new Color(123, 0, 43),
                    MovePattern.DOWN_UP_DIRECTIONS,
                    true,
                    new PositionListInfo(new Position(3, 1), 3, Direction.U, false))
            );

        assertNotEquals(block1, block2);
    }

    @Test
    @DisplayName("Equals Should be true When two Blocks have the different MOvePattern")
    public void equals_differentMovePattern() {
        final Block block1 =
            new Block(
                new BlockInfo(
                    "R2",
                    new Color(155, 0, 0),
                    MovePattern.NO_DIRECTIONS,
                    false,
                    new PositionListInfo(new Position(3, 3), 3, Direction.D, false))
            );

        final Block block2 =
            new Block(
                new BlockInfo(
                    "G4",
                    new Color(123, 0, 43),
                    MovePattern.RIGHT_LEFT_DIRECTIONS,
                    false,
                    new PositionListInfo(new Position(3, 1), 3, Direction.U, false))
            );

        assertNotEquals(block1, block2);
    }

} // Block Test class
