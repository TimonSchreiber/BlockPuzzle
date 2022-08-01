package field;

import java.util.List;

public final class MovePattern {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    public static final
    List<Direction> ALL_DIRECTIONS =
        List.of(
            Direction.R,
            Direction.D,
            Direction.L,
            Direction.U
        );

    public static final
    List<Direction> DOWN_UP_DIRECTIONS =
        List.of(
            Direction.D,
            Direction.U
        );

    public static final
    List<Direction> RIGHT_LEFT_DIRECTIONS =
        List.of(
            Direction.R,
            Direction.L
        );

    public static final
    List<Direction> NO_DIRECTIONS =
        List.of();

    // =========================================================================

}   // Move Pattern class
