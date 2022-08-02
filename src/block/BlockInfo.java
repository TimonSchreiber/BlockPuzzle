package block;

import java.awt.Color;

// import field.Direction;
import field.MovePattern;

// --> old record fields <--
// (Position position, int size, Direction direction)
public record BlockInfo(String name,
                        Color color,
                        MovePattern movePattern,
                        boolean isMainBlock,
                        PositionsInfo positionsInfo) {
    // TODO:
    // maybe change size to Name, have two constructors:
    //      - one with name for StartingPositions
    //      - one with size and automatic naming for manual game creation

    // BlockInfo(Position position, String name, Direction direction)   (Alternative 1)
    // BlockInfo(PositionList positionList, String name)                (Alternative 2)

}   // BlockInfo record
