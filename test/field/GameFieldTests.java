package field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import block.Block;
import block.BlockInfo;
import block.Position;
import block.PositionList;
import block.PositionListInfo;
import canvas.CanvasInfo;
import canvas.Rectangle;

public class GameFieldTests {

    private GameField gameField;

    @BeforeEach
    public void setUp() {
        final int height = 5;
        final int width = 5;
        final CanvasInfo canvasInfo =
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
        final PositionList winCondition =
            new PositionList(
                List.of(
                    new Position(0, 0),
                    new Position(1, 0)
                )
            );
        gameField = new GameField(height, width, winCondition, canvasInfo);
    }

    // -------------------------------------------------------------------------
    // Check Win Condition

    @Test
    @DisplayName("checkWinCondition Should return true When the PositionLists match")
    public void checkWinCondition_true() {
        final BlockSet blocks = new BlockSet();
        blocks.add(
            new Block(
                new BlockInfo(
                    "R1",
                    null,
                    null,
                    false,
                    new PositionListInfo(
                        new Position(0, 0),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );

        assertTrue(gameField.checkWinCondition(blocks));
    }

    @Test
    @DisplayName("checkWinCondition Should return false When the PositionLists do not match")
    public void checkWinCondition_false() {
        final BlockSet blocks = new BlockSet();
        blocks.add(
            new Block(
                new BlockInfo(
                    "R1",
                    null,
                    null,
                    true,
                    new PositionListInfo(
                        new Position(1, 0),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );

        assertFalse(gameField.checkWinCondition(blocks));
    }

    // -------------------------------------------------------------------------
    // Valid Move 

    @Test
    @DisplayName("isValidMove Should return true When the Move can be played")
    public void validMove_true() {
        final BlockSet blocks = new BlockSet();
        blocks.add(
            new Block(
                new BlockInfo(
                    "R1",
                    null,
                    MovePattern.ALL_DIRECTIONS,
                    false,
                    new PositionListInfo(
                        new Position(0, 0),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );
        blocks.add(
            new Block(
                new BlockInfo(
                    "R2",
                    null,
                    MovePattern.ALL_DIRECTIONS,
                    false,
                    new PositionListInfo(
                        new Position(0, 1),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );

        assertTrue(gameField.isValidMove(blocks, new Move("R1", Direction.R)));
    }

    @Test
    @DisplayName("isValidMove Should return false When the Move can not be played")
    public void validMove_false() {
        final BlockSet blocks = new BlockSet();
        blocks.add(
            new Block(
                new BlockInfo(
                    "R1",
                    null,
                    null,
                    false,
                    new PositionListInfo(
                        new Position(0, 0),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );
        blocks.add(
            new Block(
                new BlockInfo(
                    "R2",
                    null,
                    null,
                    false,
                    new PositionListInfo(
                        new Position(0, 1),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );

        assertFalse(gameField.isValidMove(blocks, new Move("R1", Direction.U)));
    }

    // -------------------------------------------------------------------------
    // Is In Intervall 

    @Test
    @DisplayName("isInIntervall should return true when the Position is inside the GameField.")
    public void isInInterval_true() {
        final Position position = new Position(3, 2);

        assertTrue(gameField.isInInterval(position));
    }

    @Test
    @DisplayName("isInIntervall should return false when the Position is outside the GameField.")
    public void isInInterval_false() {
        final Position position = new Position(6, 2);

        assertFalse(gameField.isInInterval(position));
    }

    // -------------------------------------------------------------------------
    // IS COLLISION FREE

    @Test
    @DisplayName("isCollisionFree true")
    public void isCollisionFree_true() {
        final BlockSet blocks = new BlockSet();
        blocks.add(
            new Block(
                new BlockInfo(
                    "R1",
                    null,
                    null,
                    false,
                    new PositionListInfo(
                        new Position(0, 0),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );
        blocks.add(
            new Block(
                new BlockInfo(
                    "R2",
                    null,
                    null,
                    false,
                    new PositionListInfo(
                        new Position(0, 1),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );

        assertTrue(gameField.isCollisionFree(blocks, new Move("R1", Direction.R)));
    }

    @Test
    @DisplayName("isCollisionFree false")
    public void isCollisionFree_false() {
        final BlockSet blocks = new BlockSet();
        blocks.add(
            new Block(
                new BlockInfo(
                    "R1",
                    null,
                    null,
                    false,
                    new PositionListInfo(
                        new Position(0, 0),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );
        blocks.add(
            new Block(
                new BlockInfo(
                    "R2",
                    null,
                    null,
                    false,
                    new PositionListInfo(
                        new Position(0, 1),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );

        assertFalse(gameField.isCollisionFree(blocks, new Move("R1", Direction.U)));
    }

}   // Game Field Test class
