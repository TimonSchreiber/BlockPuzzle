package field;

import java.util.List;

public final class MovePattern {

    // -------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // Empty Direction List

    public static final
    List<Direction> NO_DIRECTIONS =
        List.of();

    // -------------------------------------------------------------------------
    // Only Down and Up

    public static final
    List<Direction> DOWN_UP_DIRECTIONS =
        List.of(
            Direction.D,
            Direction.U
        );

    // -------------------------------------------------------------------------
    // Only Right and Left

    public static final
    List<Direction> RIGHT_LEFT_DIRECTIONS =
        List.of(
            Direction.R,
            Direction.L
        );

    // -------------------------------------------------------------------------
    // Right, Down, Left, and Up

    public static final
    List<Direction> ALL_DIRECTIONS =
        List.of(
            Direction.R,
            Direction.D,
            Direction.L,
            Direction.U
        );

    // -------------------------------------------------------------------------
    // Two Steps in every possible and useful combination of Directions

    public static final
    List<List<Direction>> ALL_DIRECTIONS_PLUS =
        List.of(
            List.of(Direction.R),
            List.of(Direction.D),
            List.of(Direction.L),
            List.of(Direction.U),
            List.of(Direction.R, Direction.R),
            List.of(Direction.R, Direction.D),
            List.of(Direction.R, Direction.U),
            List.of(Direction.D, Direction.R),
            List.of(Direction.D, Direction.D),
            List.of(Direction.D, Direction.L),
            List.of(Direction.L, Direction.D),
            List.of(Direction.L, Direction.L),
            List.of(Direction.L, Direction.U),
            List.of(Direction.U, Direction.R),
            List.of(Direction.U, Direction.L),
            List.of(Direction.U, Direction.U)
        );

    // =========================================================================

}   // Move Pattern class
