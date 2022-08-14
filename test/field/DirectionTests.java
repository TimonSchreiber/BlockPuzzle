package field;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DirectionTests {

    @Test
    @DisplayName("next Should return D When R")
    public void next() {
        Direction actual = Direction.R;
        Direction expected = Direction.D;

        actual = actual.next();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("reverse Should return D When U")
    public void reverse() {
        Direction actual = Direction.U;
        Direction expected = Direction.D;

        actual = actual.reverse();

        assertEquals(expected, actual);
    }
    
}   // Direction Test class
