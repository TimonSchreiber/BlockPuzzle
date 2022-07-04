package field;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import block.Block;
import block.Position;

public record BlockSet(Set<Block> blocks) implements Iterable<Block> {

	// TODO:
	// return new BlockSet(this.blocks) ???

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================
	
	/**
	 * Class constructor.
	 */
	public BlockSet() {
		this(new TreeSet<>());
	}
	
	/**
	 * Copy constructor.
	 * 
	 * @param blockSet	the {@code BlockSet}
	 */
	public BlockSet(final BlockSet blockSet) {
		this(new TreeSet<>());
		
		for (final Block block : blockSet) {
			this.blocks.add(new Block(block));
		}
	}

	// =========================================================================
	// GETTER - METHODS
	// =========================================================================

	/** TODO
	 * Checks if there is a Block in this {@code BlockSet} with the same
	 * x- and y-coordinates as one of its {@code Block}s.
	 * 
	 * @param position	the {@code Position}
	 * @return			{@code true} if there is one Block which has these 
	 * 					x- and y-coordinates; {@code false} otherwise
	 */
	public boolean isBlock(final Position position) {
		for (final Block block : this.blocks) {
			if (block.positionList().list().contains(position)) {
				return true;
			}
		}
		return false;
	}
	
	/** TODO when this method is called a null check must be implemented?
	 * 
	 * @param position
	 * @return
	 */
	public String getBlockName(final Position position) {
		for (final Block block : this.blocks) {
			if (block.positionList().list().contains(position)) {
				return block.blockName();
			}
		}
		return null;
	}

	/** TODO why return new Block()???
	 * when this method is called a null check must be implemented?
	 * 
	 * @param blockName
	 * @return
	 */
	public Block getBlock(final String name/* TODO: used to be Move move */) {
		for (final Block block : this.blocks) {
			if (block.blockName().equals(name)) {
				return block;
			}
		}
		return null;
	}
	
	// =========================================================================
	//  MOVE - METHOD
	// =========================================================================
	
	/** TODO
	 * 
	 * @param move
	 */
	public void move(final Move move) {
		for (final Block block : this.blocks) {
			if (block.blockName().equals(move.name())) {
				block.moveTowards(move.direction());
				return;
			}
		}
		return;
	}

	// =========================================================================
	// INTERFACE - METHOD
	// =========================================================================
	
	// -------------------------------------------------------------------------
	// ITERABLE
	// -------------------------------------------------------------------------

	/**
	 * Returns an {@code Iterator} over all {@code Block}s.
	 */
	@Override
	public Iterator<Block> iterator() {
		return this.blocks.iterator();
	}

	// =========================================================================
	
}   // BlockSet record
