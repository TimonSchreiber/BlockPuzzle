package field;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import block.Block;
import block.BlockInfo;
import block.Position;
import block.PositionListInfo;

public class GameStateTests {


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

    private GameState gameState;

    @BeforeEach
    public void setUp() {
        BlockSet blockSet = new BlockSet();
        startPosition.forEach(
            blockInfo -> blockSet.add(new Block(blockInfo))
        );
        String R1 = "R1";
        List<Move> moves =
            List.of(
                new Move(R1, Direction.U),
                new Move(R1, Direction.R),
                new Move(R1, Direction.U)
            );

        gameState = new GameState(blockSet, moves);
    }

    // -------------------------------------------------------------------------
    // Getter

    @Test
    @DisplayName("Getters")
    public void getters() {
        BlockSet expectedBlockSet = new BlockSet();
        startPosition.forEach(
            blockInfo -> expectedBlockSet.add(new Block(blockInfo))
        );
        String R1 = "R1";
        List<Move> expectedMoves =
            List.of(
                new Move(R1, Direction.U),
                new Move(R1, Direction.R),
                new Move(R1, Direction.U)
            );

        assertAll(
            () -> assertEquals(expectedBlockSet, gameState.blocks()),
            () -> assertEquals(expectedMoves, gameState.moves())
        );
    }

    // -------------------------------------------------------------------------
    // copy List

    @Test
    @DisplayName("Adds a new Move to a copy of a List")
    public void addMoveToNewList() {
        final String R1 = "R1";
        final Move newMove = new Move(R1, Direction.R);

        List<Move> actual =
            List.of(
                new Move(R1, Direction.U),
                new Move(R1, Direction.R),
                new Move(R1, Direction.U)
            );

        final List<Move> expected =
            List.of(
                new Move(R1, Direction.U),
                new Move(R1, Direction.R),
                new Move(R1, Direction.U),
                new Move(R1, Direction.R)
            );


        actual = GameState.addMoveToNewList(actual, newMove);

        assertEquals(expected, actual);
    }

}   // Game State Test class
