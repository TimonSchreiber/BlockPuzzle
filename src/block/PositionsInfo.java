package block;

import field.Direction;

public record PositionsInfo(Position position,
                            int size,
                            Direction direction,
                            boolean isElbow) {
    
}
