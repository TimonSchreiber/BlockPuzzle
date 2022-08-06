package field;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoveTests {

    @Test
    @DisplayName("reverse Should change Direction from D to U")
    public void reverseDtoU() {
        Move actual = new Move("G1", Direction.D);
        Move expected = new Move("G1", Direction.U);

        actual = actual.reverse();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("reverse Should change Direction from R to L")
    public void reverseRtoL() {
        Move actual = new Move("G2", Direction.R);
        Move expected = new Move("G2", Direction.L);

        actual = actual.reverse();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("reverse Should change Direction from L to R")
    public void reverseLtoR() {
        Move actual = new Move("Y1", Direction.L);
        Move expected = new Move("Y1", Direction.R);

        actual = actual.reverse();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("reverse Should change Direction from U to D")
    public void reverseUtoD() {
        Move actual = new Move("B4", Direction.U);
        Move expected = new Move("B4", Direction.D);

        actual = actual.reverse();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("double reverse Should yield the same Direction")
    public void doubleReverseUtoU() {
        Move actual = new Move("B3", Direction.U);
        Move expected = new Move("B3", Direction.U);

        actual = actual.reverse().reverse();

        assertEquals(expected, actual);
    }

}   // Move Test class
