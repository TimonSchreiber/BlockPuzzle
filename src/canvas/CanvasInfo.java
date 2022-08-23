package canvas;

import java.awt.Color;
import java.util.List;

/**
 * A class for holding all the information needed for the canvas to draw on.
 */
public record CanvasInfo(
    Color outsideColor,
    Color insideColor,
    Color winColor,
    List<Rectangle> winArea
) {
}   // Canvas Info record
