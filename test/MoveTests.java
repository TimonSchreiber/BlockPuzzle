import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import field.Direction;
import field.Move;

public class MoveTests {
    
    @Test
    @DisplayName("reverse Should change Direction to U When D")
    public void reverse() {
        Move actual = new Move("G1", Direction.D);
        Move expected = new Move("G1", Direction.U);

        actual = actual.reverse();

        assertEquals(expected, actual);
    }
}
