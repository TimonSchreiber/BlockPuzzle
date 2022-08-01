package block;

import field.Direction;
// (String name, Color color, Position position, int size, Direction direction, MovePattern movePattern, bool isElbow, bool isMainBlock)
public record BlockInfo(Position position, int size, Direction direction) {
    // TODO:
    // maybe change size to Name, have two constructors:
    //      - one with name for StartingPositions
    //      - one with size and automatic naming for manual game creation

    // BlockInfo(Position position, String name, Direction direction)   (Alternative 1)
    // BlockInfo(PositionList positionList, String name)                (Alternative 2)

}   // BlockInfo record
