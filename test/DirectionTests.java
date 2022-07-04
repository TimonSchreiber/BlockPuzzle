import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import field.Directions;

public class DirectionTests {

    @Test
    @DisplayName("getSize Should be 4")
    public void getSize() {
        assertEquals(4, Directions.getSize());
    }

    @Test
    @DisplayName("next Should return D When R")
    public void next() {
        Directions actual = Directions.R;
        Directions expected = Directions.D;

        actual = actual.next();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("reverse Should return D When U")
    public void reverse() {
        Directions actual = Directions.U;
        Directions expected = Directions.D;

        actual = actual.reverse();

        assertEquals(expected, actual);
    }
    
}
