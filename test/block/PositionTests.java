package block;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import field.Direction;

public class PositionTests {

    // -------------------------------------------------------------------------
    // Move Towards

    @Test
    @DisplayName("Move to the right")
    public void moveTowardsRight() {
        Position actual = new Position(2, 3);
        Position expected = new Position(3, 3);

        actual = actual.moveTowards(Direction.R);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Move down")
    public void moveTowardsDown() {
        Position actual = new Position(4, 7);
        Position expected = new Position(4, 6);

        actual = actual.moveTowards(Direction.D);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Move to the left")
    public void moveTowardsLeft() {
        Position actual = new Position(1, 5);
        Position expected = new Position(0, 5);

        actual = actual.moveTowards(Direction.L);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Move up")
    public void moveTowardsUp() {
        Position actual = new Position(1, 1);
        Position expected = new Position(1, 2);

        actual = actual.moveTowards(Direction.U);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Move to the right and up")
    public void moveTowardsRightAndUp() {
        Position actual = new Position(3, 2);
        Position expected = new Position(4, 3);

        actual = actual.moveTowards(Direction.R, Direction.U);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Move to the left and down")
    public void moveTowardsLeftAndDown() {
        Position actual = new Position(4, 2);
        Position expected = new Position(3, 1);

        actual = actual.moveTowards(Direction.L, Direction.D);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Move to the left and right")
    public void moveTowardsLeftAndRight() {
        Position actual = new Position(1, 4);
        Position expected = new Position(1, 4);

        actual = actual.moveTowards(Direction.L, Direction.R);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Move up and down")
    public void moveTowardsUpAndDown() {
        Position actual = new Position(2, 4);
        Position expected = new Position(2, 4);

        actual = actual.moveTowards(Direction.U, Direction.D);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Move up, down, left and right")
    public void moveTowardsUpDownLeftAndRight() {
        Position actual = new Position(5, 1);
        Position expected = new Position(5, 1);

        actual = actual.moveTowards(Direction.U, Direction.D, Direction.L, Direction.R);

        assertEquals(expected, actual);
    }

    // -------------------------------------------------------------------------
    // compare

    @Test
    @DisplayName("p1 is greater than p2, same x value")
    public void compareToSameXValue() {
        Position p1 = new Position(3, 5);
        Position p2 = new Position(3, 0);

        int compare = p1.compareTo(p2);

        assertEquals(1, compare);
    }

    @Test
    @DisplayName("p1 is greater than p2, same y value")
    public void compareToSameYValue() {
        Position p1 = new Position(4, 1);
        Position p2 = new Position(2, 1);

        int compare = p1.compareTo(p2);

        assertEquals(1, compare);
    }

}   // Position Test class
