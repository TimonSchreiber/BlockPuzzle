package block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import field.Direction;

public class PositionListTests {

    // -------------------------------------------------------------------------
    // Construct

    @Test
    @DisplayName("Constructing a Square at (3, 3)")
    public void construct_Square() {
        final PositionList actual =
            new PositionList(
                new PositionsInfo(
                    new Position(3, 3),
                    1,
                    Direction.D,
                    false
                ));

        final PositionList expected =
            new PositionList(
                List.of(
                    new Position(3, 3)
                ));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Constructing a Rectangle of size 2 at (1, 2)")
    public void construct_Rectangle_2() {
        final PositionList actual =
            new PositionList(
                new PositionsInfo(
                    new Position(1, 2),
                    2,
                    Direction.L,
                    false
                ));

        final PositionList expected =
            new PositionList(
                List.of(
                    new Position(0, 2),
                    new Position(1, 2)
                ));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Constructing a Rectangle of size 3 at (4, 0)")
    public void construct_Rectangle_3() {
        final PositionList actual =
            new PositionList(
                new PositionsInfo(
                    new Position(4, 0),
                    3,
                    Direction.R,
                    false
                ));

        final PositionList expected =
            new PositionList(
                List.of(
                    new Position(4, 0),
                    new Position(5, 0),
                    new Position(6, 0)
                ));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Constructing an Elbow-Block at (0, 0)")
    public void construct_Elbow() {
        final PositionList actual =
            new PositionList(
                new PositionsInfo(
                    new Position(0, 0),
                    3,
                    Direction.U,
                    true
                ));

        final PositionList expected =
            new PositionList(
                List.of(
                    new Position(0, 0),
                    new Position(1, 0),
                    new Position(0, 1)
                ));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Constructing a big Square at (0, 3)")
    public void construct_BigSquare() {
        final PositionList actual =
            new PositionList(
                new PositionsInfo(
                    new Position(0, 3),
                    4,
                    Direction.U,
                    true
                ));

        final PositionList expected =
            new PositionList(
                List.of(
                    new Position(0, 3),
                    new Position(1, 3),
                    new Position(0, 4),
                    new Position(1, 4)
                ));

        assertEquals(expected, actual);
    }

    // -------------------------------------------------------------------------
    // Move Towards

    @Test
    @DisplayName("Moving a Rectangle of size 3 to the left")
    public void moveTowards_Rectangle3_Left() {
        final PositionList actual =
            new PositionList(
                new PositionsInfo(
                    new Position(4, 3),
                    3,
                    Direction.R,
                    false
                ));

        final PositionList expected =
            new PositionList(
                List.of(
                    new Position(3, 3),
                    new Position(4, 3),
                    new Position(5, 3)
                ));

        actual.moveTowards(Direction.L);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Moving a Large Square to the right and down")
    public void moveTowards_LargeSquare_RiaghtAndDown() {
        final PositionList actual =
            new PositionList(
                new PositionsInfo(
                    new Position(0, 3),
                    4,
                    Direction.U,
                    true
                ));

        final PositionList expected =
            new PositionList(
                List.of(
                    new Position(1, 2),
                    new Position(2, 2),
                    new Position(1, 3),
                    new Position(2, 3)
                ));

        actual.moveTowards(Direction.R, Direction.D);

        assertEquals(expected, actual);
    }

    // -------------------------------------------------------------------------
    // Forwarding to List<Position>()

    @Test
    @DisplayName("An Elbow at (0, 0)")
    public void contains_true() {
        final Position p = new Position(0, 1);
        final PositionList pl =
            new PositionList(
                new PositionsInfo(
                    new Position(0, 0),
                    3,
                    Direction.U,
                    true
                ));

        assertTrue(pl.contains(p));
    }

    @Test
    public void getSize() {
        final int size = 4;
        final PositionList pl =
            new PositionList(
                new PositionsInfo(
                    new Position(0, 3),
                    4,
                    Direction.U,
                    true
                ));

        assertEquals(size, pl.size());
    }

    // -------------------------------------------------------------------------
    // compare

    @Test
    @DisplayName("Compare to differently sized PositionLists at the same first Position")
    public void compareTo_DifferentSizes_BiggerListComesFirst() {
        final PositionList pl1 =
            new PositionList(
                new PositionsInfo(
                    new Position(2, 3),
                    3,
                    Direction.D,
                    false
                ));

        final PositionList pl2 =
            new PositionList(
                new PositionsInfo(
                    new Position(2, 3),
                    2,
                    Direction.D,
                    false
                ));

                assertEquals(-1, pl1.compareTo(pl2));
    }

    @Test
    @DisplayName("Compare to same sized PositionLists, with same first Position")
    public void compareTo_SameSize() {

        final PositionList pl1 =
            new PositionList(
                new PositionsInfo(
                    new Position(1, 1),
                    3,
                    Direction.R,
                    false
                ));
        final PositionList pl2 =
            new PositionList(
                new PositionsInfo(
                    new Position(2, 3),
                    3,
                    Direction.D,
                    false
                ));

        assertEquals(-1, pl1.compareTo(pl2));
    }

}   // Position List Test class
