import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import block.Position;
import field.Directions;

public class PositionTests {

    @Test
    @DisplayName("Move to the right and up")
    public void moveTowards() {
        Position actual = new Position(2, 3);
        Position expected = new Position(3, 4);

        actual = actual.moveTowards(Directions.R, Directions.U);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("p1 is greater than p2")
    public void compareTo() {
        Position p1 = new Position(1, 1);
        Position p2 = new Position(0, 0);

        int compare = p1.compareTo(p2);

        assertEquals(1, compare);
    }
}
