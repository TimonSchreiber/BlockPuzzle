package block;

import java.awt.Color;

import field.Directions;

/**
 * TODO: maybe change the color creation, so that we get a name and create the color, and not a color and create the name
 */
public record Block(String blockName, Color color, PositionList positionList) implements Comparable<Block> {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================
	
	/** static counter for different types of {@code Block}s */
	private static final int[] BLOCK_COUNTER = new int[BlockTypes.getSize()];

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================
	
	/**
	 * Class constructor with size and {@code BlockName}.
	 * 
	 * @param blockInfo
	 */
	public Block(final BlockInfo blockInfo) {
		this(
			BlockTypes.getName(blockInfo.size()) + ++Block.BLOCK_COUNTER[blockInfo.size() - 1],
			Block.createColor(blockInfo.size()),
			new PositionList(blockInfo));
	}

	/** TODO is needed?	block.positions is (un-)mutable??
	 * Copy constructor.
	 * 
	 * @param block		the {@code Block}
	 */
	public Block(final Block block) {
		this(
			block.blockName,
			block.color,
			new PositionList());
		
		this.positionList.list().addAll(block.positionList.list());	// block.positionList
	}
	
	// =========================================================================
	// CREATE-COLOR - METHOD
	// =========================================================================
	
	/**
	 * Creates the {@code Color} for the {@code Block} by repeatedly using
	 * {@link Color#darker()} for each {@code Block} of the same
	 * {@code BlockType} that was already created.
	 * 
	 * @param size	the size
	 * @return		a darker {@code Color}
	 */
	private static Color createColor(final int size) {
		Color tmpClr = BlockTypes.getColor(size);
		
		for (int i = 1; i < Block.BLOCK_COUNTER[size - 1]; i++) {
			tmpClr = tmpClr.darker();
		}
		
		return tmpClr;
	}

	// =========================================================================
	// MOVE - METHOD
	// =========================================================================

	/** TODO is void here OK? or do i need to return a new Block?
	 * 
	 * Moves this {@code Block} by changing every {@code Position}
	 * to an adjacent coordinate.
	 * 
	 * @param directions		the {@code Direction}s
	 */
	public void moveTowards(final Directions... directions) {
		this.positionList.moveTowards(directions);
		return;
	}

	// =========================================================================
	// RESET-BLOCK-COUNTER - METHOD
	// =========================================================================

	// FIXME: sofar not used
	public void resetBlockNames() {
		for (int i = 0; i < BlockTypes.getSize(); i++) {
			Block.BLOCK_COUNTER[i] = 0;
		}
	}
	
	// =========================================================================
	// EQUALS AND HASH-CODE - METHODS
	// =========================================================================
	
	/** TODO
	 * Overrides the equals method, so that neither the name, nor the color will
	 * be used in the equals method.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if ((obj == null) || (this.getClass() != obj.getClass())) {
			return false;
		}
		
		// Object must be Block at this point
		Block other = (Block) obj;
		
		return	(this.positionList.list().size() == other.positionList.list().size())	// XXX
//				((this.BLOCK_NAME == other.BLOCK_NAME)
//					|| ((this.BLOCK_NAME != null)
//						&& this.BLOCK_NAME.equals(other.BLOCK_NAME)))
//				&& ((this.COLOR == other.COLOR)
//						|| ((this.COLOR != null)
//							&& this.COLOR.equals(other.COLOR)))
				&& ((this.positionList == other.positionList)
						|| ((this.positionList != null)
							&& this.positionList.equals(other.positionList)));
	}
	
	/** TODO
	 * Overrides the hashCode method to match the equals method.
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = 7;
		
		hash = PRIME * hash + this.positionList.list().size();	// XXX
//		hash = PRIME * hash + ((this.BLOCK_NAME == null)
//									? 0
//									: this.BLOCK_NAME.hashCode());
//		hash = PRIME * hash + ((this.COLOR == null)
//									? 0
//									: this.COLOR.hashCode());
		hash = PRIME * hash + ((this.positionList == null)
									? 0
									: this.positionList.hashCode());
		
		return hash;
	}

	// =========================================================================
	// INTERFACE - METHOD
	// =========================================================================
	
	// -------------------------------------------------------------------------
	// COMPARABLE
	// -------------------------------------------------------------------------
	
	/** TODO
	 * 
	 */
	@Override
	public int compareTo(Block other) {
		return this.positionList.compareTo(other.positionList);
	}

	// =========================================================================

}	// Block record
