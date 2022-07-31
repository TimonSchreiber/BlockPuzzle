package block;

import field.Directions;

public record BlockInfo(Position position, int size, Directions direction) {
    // TODO:
    // maybe change size to Name, have two constructors:
    //      - one with name for StartingPositions
    //      - one with size and automatic naming for manual game creation

    // BlockInfo(Position position, String name, Direction direction)   (Alternative 1)
    // BlockInfo(PositionList positionList, String name)                (Alternative 2)

}   // BlockInfo record
