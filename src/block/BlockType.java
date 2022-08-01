package block;

import java.awt.Color;

public enum BlockType {

    // -------------------------------------------------------------------------
    // CONSTANTS
    // -------------------------------------------------------------------------

    SQUARE          ("G", Color.green),
    RECTANGLE       ("B", Color.blue),
    ELBOW           ("Y", Color.yellow),
    LARGE_SQUARE    ("R", Color.red);

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /** A {@code String} with the first letter of the {@code Color} */
    private final String NAME;

    /** the {@code Color} */
    private final Color COLOR;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Enum constructor.
     *
     * @param name
     * @param color
     */
    private BlockType(String name, Color color) {
        this.NAME = name;
        this.COLOR = color;
    }

    // -------------------------------------------------------------------------
    // ATTRBUTES
    // -------------------------------------------------------------------------

    /**
     * Array of {@code BlockType}s with the value of every {@code BlockType}
     * constant.
     */
    private static final BlockType[] VALUES = BlockType.values();

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    /** // TODO: is thi used?
     * Gets the number of {@code BlockType} constants.
     *
     * @return    the number of {@code BlockType} constants
     */
    public static int getSize() {
        return BlockType.VALUES.length;
    }

    /**
     * TODO:
     *
     * @param blockSize
     * @return
     */
    public static String getName(int blockSize) {
        return BlockType.VALUES[blockSize - 1].NAME;
    }

    /** TODO
     *
     * @param blockSize
     * @return
     */
    public static Color getColor(int blockSize) {
        return BlockType.VALUES[blockSize - 1].COLOR;
    }

    // =========================================================================

}    // BlockTypes enum
