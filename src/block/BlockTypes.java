package block;

import java.awt.Color;

public enum BlockTypes {
	
	// =========================================================================
	// CONSTANTS
	// =========================================================================
	
	SQUARE			("G", Color.green),
	RECTANGLE		("B", Color.blue),
	ELBOW			("Y", Color.yellow),
	LARGE_SQUARE	("R", Color.red);
	
	// =========================================================================
	// ATTRIBUTES
	// =========================================================================
	
	/** A {@code String} with the first letter of the {@code Color} */
	private final String NAME;
	
	/** the {@code Color} */
	private final Color COLOR;
	
	// =========================================================================
	// CONSTRUCTOR
	// =========================================================================
	
	/**
	 * Enum constructor.
	 * 
	 * @param name
	 * @param color
	 */
	private BlockTypes(String name, Color color) {
		this.NAME = name;
		this.COLOR = color;
	}
	
	// =========================================================================
	// ATTRBUTES
	// =========================================================================

	/**
	 * Array of {@code BlockType}s with the value of every {@code BlockType}
	 * constant.
	 */
	private static BlockTypes[] VALUES = BlockTypes.values();
	
	// =========================================================================
	// GETTER - METHOD
	// =========================================================================

	/**
	 * Gets the number of {@code BlockType} constants.
	 * 
	 * @return	the number of {@code BlockType} constants
	 */
	public static int getSize() {
		return BlockTypes.VALUES.length;
	}
	
	/**
	 * TODO:
	 * 
	 * @param blockSize
	 * @return
	 */
	public static String getName(int blockSize) {
		return BlockTypes.VALUES[blockSize - 1].NAME;
	}
	
	/** TODO
	 * 
	 * @param blockSize
	 * @return
	 */
	public static Color getColor(int blockSize) {
		return BlockTypes.VALUES[blockSize - 1].COLOR;
	}
	
	// =========================================================================
	
}	// BlockTypes enum
