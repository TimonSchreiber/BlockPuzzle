package block;

import field.Direction;

/**
 * Holds all the information needed for creating the PositionList of a Block.
 */
public record PositionsInfo(Position position,
                            int size,
                            Direction direction,
                            boolean isElbow) {
}   // Positions Info record
