import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import block.BlockTypes;

public class BlockTypesTests {

    // =========================================================================
    // SQUARE
    // =========================================================================

    @Test
    public void name_Square() {
        assertEquals("G", BlockTypes.getName(1));
    }

    @Test
    public void color_Square() {
        assertEquals(Color.GREEN, BlockTypes.getColor(1));
    }

    // =========================================================================
    // RECTANGE
    // =========================================================================

    @Test
    public void name_Rectangle() {
        assertEquals("B", BlockTypes.getName(2));
    }

    @Test
    public void color_Rectangle() {
        assertEquals(Color.BLUE, BlockTypes.getColor(2));
    }

    // =========================================================================
    // ELBOW
    // =========================================================================

    @Test
    public void name_Elbow() {
        assertEquals("Y", BlockTypes.getName(3));
    }

    @Test
    public void color_Elbow() {
        assertEquals(Color.YELLOW, BlockTypes.getColor(3));
    }

    // =========================================================================
    // LARGE SQUARE
    // =========================================================================

    @Test
    public void name_LargeSquare() {
        assertEquals("G", BlockTypes.getName(1));
    }

    @Test
    public void color_LargeSquare() {
        assertEquals(Color.RED, BlockTypes.getColor(4));
    }
}
