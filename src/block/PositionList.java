package block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import field.Directions;

public record PositionList(List<Position> list) implements Iterable<Position>, Comparable<PositionList> {

	// -------------------------------------------------------------------------
	// CONSTRUCTORS
	// -------------------------------------------------------------------------

	/**
	 * TODO: is needed for adding all the positions afterwards in the constructor below.
	 * Maybe change, so that the Positions are added in the same line.
	 */
	public PositionList() {
		this(new ArrayList<Position>());
	}

	/**
	 * TODO:
	 * Class constructor form a {@code BlockInfo}.
	 *
	 * @param blockInfo
	 */
	public PositionList(final BlockInfo blockInfo) {
		this();

		switch (blockInfo.size()) {
			case 4:
				this.list.add(
					blockInfo.position().moveTowards(
						blockInfo.direction(),
						blockInfo.direction().next()));
				// falls through
			case 3:
				this.list.add(
					blockInfo.position().moveTowards(
						blockInfo.direction().next()));
				// falls through
			case 2:
				this.list.add(
					blockInfo.position().moveTowards(
						blockInfo.direction()));
				// falls through
			default:
				this.list.add(
					blockInfo.position());
				break;
		}

		Collections.sort(this.list);

	}

	// -------------------------------------------------------------------------
	// MOVE TOWARDS
	// -------------------------------------------------------------------------

	/**
	 * TODO
	 *
	 * @param directions
	 */
	public void moveTowards(final Directions... directions) {
		this.list.replaceAll(pos -> pos.moveTowards(directions));
	}

	// -------------------------------------------------------------------------
	// FORWARDING - METHODS
	// -------------------------------------------------------------------------

	public boolean contains(final Position position) { return this.list.contains(position); }
	public int size() { return this.list.size(); }

	// =========================================================================
	// INTERFACE - METHODS
	// =========================================================================

	// -------------------------------------------------------------------------
	// ITERABLE
	// -------------------------------------------------------------------------

	/**
	 * Returns an {@code Iterator} over all {@code Position}s.
	 */
	@Override
	public Iterator<Position> iterator() {
		return this.list.iterator();
	}

	// -------------------------------------------------------------------------
	// COMPARABLE
	// -------------------------------------------------------------------------

	/**
	 *
	 */
	@Override
	public int compareTo(PositionList other) {
		return (this.list.size() != other.list.size())
				? -Integer.compare(this.list.size(), other.list.size()) // larger Blocks before smaller Blocks
				: this.list.get(0).compareTo(other.list.get(0));
	}

	// =========================================================================

}   // PositionList record
