package field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import block.Block;
import block.BlockInfo;
import block.Position;
import block.PositionList;
import block.PositionsInfo;

public class GameFieldTests {

    private GameField gameField;

    @BeforeEach
    public void setUp() {
        final int height = 5;
        final int width = 5;
        final PositionList winCondition =
            new PositionList(
                List.of(
                    new Position(0, 0),
                    new Position(1, 0)
                )
            );
        gameField = new GameField(height, width, winCondition);
    }

    // -------------------------------------------------------------------------
    // Check Win Condition

    @Test
    @DisplayName("checkWinCondition Should return true When the PositionLists match")
    public void checkWinCondition_true() {
        final BlockSet blockSet = new BlockSet();
        blockSet.add(
            new Block(
                new BlockInfo(
                    "R1",
                    null,
                    null,
                    false,
                    new PositionsInfo(
                        new Position(0, 0),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );

        assertTrue(gameField.checkWinCondition(blockSet));
    }

    @Test
    @DisplayName("checkWinCondition Should return false When the PositionLists do not match")
    public void checkWinCondition_false() {
        final BlockSet blockSet = new BlockSet();
        blockSet.add(
            new Block(
                new BlockInfo(
                    "R1",
                    null,
                    null,
                    false,
                    new PositionsInfo(
                        new Position(1, 0),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );

        assertFalse(gameField.checkWinCondition(blockSet));
    }

    // -------------------------------------------------------------------------
    // Valid Move 

    @Test
    @DisplayName("isValidMove Should return true When the Move can be played")
    public void validMove_true() {
        final BlockSet blockSet = new BlockSet();
        blockSet.add(
            new Block(
                new BlockInfo(
                    "R1",
                    null,
                    null,
                    false,
                    new PositionsInfo(
                        new Position(0, 0),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );
        blockSet.add(
            new Block(
                new BlockInfo(
                    "R2",
                    null,
                    null,
                    false,
                    new PositionsInfo(
                        new Position(0, 1),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );

        assertTrue(gameField.isValidMove(blockSet, new Move("R1", Direction.R)));
    }

    @Test
    @DisplayName("isValidMove Should return false When the Move can not be played")
    public void validMove_false() {
        final BlockSet blockSet = new BlockSet();
        blockSet.add(
            new Block(
                new BlockInfo(
                    "R1",
                    null,
                    null,
                    false,
                    new PositionsInfo(
                        new Position(0, 0),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );
        blockSet.add(
            new Block(
                new BlockInfo(
                    "R2",
                    null,
                    null,
                    false,
                    new PositionsInfo(
                        new Position(0, 1),
                        2,
                        Direction.R,
                        false
                    )
                )
            )
        );

        assertFalse(gameField.isValidMove(blockSet, new Move("R1", Direction.U)));
    }

    // TODO: can I test game draw() method, or one of the otehr private methods (isInIntervall or isCollisionFree)?

}   // Game Field Test class