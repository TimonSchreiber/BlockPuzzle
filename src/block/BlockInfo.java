package block;

import java.awt.Color;

import field.MovePattern;

/**
 * Holds all the information needed to create a new Block.
 */
public record BlockInfo(
    String name,
    Color color,
    MovePattern movePattern,
    boolean isMainBlock,
    PositionListInfo positionListInfo
) {
}   // Block Info record
