package block;

import field.Direction;

/**
 * Holds all the information needed for creating the PositionList of a Block.
 */
public record PositionListInfo(
    Position position,
    int size,
    Direction direction,
    boolean isElbow
) {
}   // Position List Info record
