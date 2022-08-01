import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import block.BlockType;

public class BlockTypesTests {

    // =========================================================================
    // SQUARE
    // =========================================================================

    @Test
    public void name_Square() {
        assertEquals("G", BlockType.getName(1));
    }

    @Test
    public void color_Square() {
        assertEquals(Color.GREEN, BlockType.getColor(1));
    }

    // =========================================================================
    // RECTANGE
    // =========================================================================

    @Test
    public void name_Rectangle() {
        assertEquals("B", BlockType.getName(2));
    }

    @Test
    public void color_Rectangle() {
        assertEquals(Color.BLUE, BlockType.getColor(2));
    }

    // =========================================================================
    // ELBOW
    // =========================================================================

    @Test
    public void name_Elbow() {
        assertEquals("Y", BlockType.getName(3));
    }

    @Test
    public void color_Elbow() {
        assertEquals(Color.YELLOW, BlockType.getColor(3));
    }

    // =========================================================================
    // LARGE SQUARE
    // =========================================================================

    @Test
    public void name_LargeSquare() {
        assertEquals("G", BlockType.getName(1));
    }

    @Test
    public void color_LargeSquare() {
        assertEquals(Color.RED, BlockType.getColor(4));
    }
}
