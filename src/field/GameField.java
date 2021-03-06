package field;

import java.awt.Color;

import block.Block;
import block.BlockInfo;
import block.Position;
import block.PositionList;

// TODO: maybe make this a record as well?

public final class GameField {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================
	
	/** preset game values for max number of {@code Block}s */
	// private static final int MAX_NUMBER_OF_BLOCKS = 14;
	
	/** Winning Positions {@code PositionList}*/
	private static final
	PositionList WINNING_SQUARES =
	new PositionList(
		new BlockInfo(
			new Position(4, 0),
			4,
			Directions.U));
			
	/** preset game values for height */
	private static final int HEIGHT = 5;
	
	/** preset game values for width */
	private static final int WIDTH = 6;
			
	/** Changes to {@code true} if the victory condition is met */
	// private boolean isWon;
	
	/** {@code BlockSet} to keep track of every {@code Block} */
	private final BlockSet blockSet;

	/** Canvas to draw the {@code GameField} on */
	private Zeichenblatt canvas;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	/**
	 * Class constructor.
	 */
	public GameField() {
		this.blockSet = new BlockSet();
	}

	/**
	 * Class constructor from a {@code BlockSet}.
	 * 
	 * @param blockSet	the {@code BlockSet}
	 */
	public GameField(final BlockSet blockSet) {
		this.blockSet = new BlockSet();
		
		for (final Block block : blockSet) {
			this.placeBlock(new Block(block));
		}
	}

	// =========================================================================
	// GETTER - METHODS
	// =========================================================================

	/** TODO: correct the javadoc (return, description, ...)
	 * Checks if the {@code Block} satisfies the winning condition.
	 * 
	 * {@code true} if the {@code LargeBlock} reached the winning
	 * Position defined by {@link END_Positions}, {@code false} otherwise.
	 * 
	 * @param block		the {@code Block}
	 * @return			{@code true} if the {@code Block} occupies all
	 * 					{@code Position}s defined by {@link END_POSITION},
	 * 					{@code false} otherwise.
	 */
	public boolean checkWinnigCondition(/* Block block */) {
		// int counter = 0;
		
		// for (Position pos : block.positionList()) {
		// 	if (this.WINNING_SQUARES.contains(pos)) {
		// 		counter++;
		// 	}
		// }

		// // Do not set isWon BACK to false if this method is called for a "not won" game (--> parallel execution)
		// if (counter == this.WINNING_SQUARES.getSize()) {
		// 	this.isWon = true;
		// }

		return this.blockSet.getBlock("R1").positionList().equals(WINNING_SQUARES);
	}

	/**
	 * Gets a copy of the {@code BlockSet}.
	 * 
	 * @return	the {@code BlockSet}
	 */
	public BlockSet blocks() {
		return new BlockSet(this.blockSet);
	}

	// =========================================================================
	// PLACE-BLOCK - METHOD
	// =========================================================================

	/**
	 * Places a {@code Block} onto this {@code GameField}.
	 * 
	 * @param block		the {@code Block}
	 */
	public void placeBlock(final Block block) {
		this.blockSet.blocks().add(block);
		return;
	}

    // =========================================================================
    // Is-IN-INTERVAL - METHOD
    // =========================================================================

    /**
     * TODO:
     * 
     * @param position
     * @return
     */
	private boolean isInInterval(final Position position) {
		return
		(position.x() < WIDTH)
			&& (position.x() >= 0)
		&& (position.y() < HEIGHT)
			&& (position.y() >= 0);
	}

	// =========================================================================
	// IS-COLLISION-FREE - METHOD
	// =========================================================================

	/** FIXME: Guarding Clauses for the nested if-statements
	 * 
	 * Checks if the {@code Move} can be performed or if this will result in an
	 * illegal {@code GameField} by overlapping two (or more) {@code Block}s, or
	 * by leaving the boundaries of this {@code GameField}.
	 * 
	 * @param move	the {@code Move}
	 * @return		{@code true} if this {@code Move} can be made, {@code false}
	 * 				otherwise.
	 */
	private boolean isCollisionFree(final Move move) {
		final Block newBlock = new Block(this.blockSet.getBlock(move.name()));

		// newBlock.positionList().moveTowards(move.direction());
		// newBlock.positionList().forEach(position -> {
		// 	// Checks if the new Position is inside the GameField
		// 	if (!this.isInInterval(position)) {
		// 		newBlock = null;
		// 	}

		// 	// Checks if it is the same Block as the one about to be moved
		// 	if (!this.blockSet.getBlockName(position).equals(newBlock.blockName())) {
		// 		collisionFree = false;
		// 	}
		// });

		// FIXME: why check every position on it's own and not all of them together?
		for (final Position position : newBlock.positionList()) {
			final Position newPosition = position.moveTowards(move.direction());
			
			// Checks if the new Position is outside the GameField
			if (!this.isInInterval(newPosition)) {
				return false;	// outside GameField
			}

			// Checks if there is already a Block at this Positions
			// If yes check if it does not have the same name
			if (this.blockSet.isBlock(newPosition) &&
			!this.blockSet.getBlockName(newPosition).equals(newBlock.blockName())) {
				return false;	// collides with a another Block
			}
		}
		
		// inside GameField and no Collision with another Block
		return true;
	}

	// =========================================================================
	// IS-VALID-MOVE - METHOD
	// =========================================================================

	/**
	 * Checks if the {@code Block} defined by the {@code Move} can be moved into
	 * the {@code Direction} of the {@code Move}.
	 * 
	 * @param move	the {@code Move}
	 * @return		{@code true} if the {@code Move} was successful,
	 * 				{@code false} otherwise.
	 */
	public boolean isValidMove(final Move move) {

		if (this.isCollisionFree(move)) {
			this.blockSet.move(move);
			return true;
		}
		
		return false;
	}

	// =========================================================================
	// PRINT - METHOD
	// =========================================================================

	/**
	 * Prints the Name of each {@code Block} for each {@code Position} on the
	 * Console and "__" if there is no {@code Block}.
	 */
	public void print() {
		for (int i = (HEIGHT - 1); i >= 0; i--) {
			
			for (int j = 0; j < WIDTH; j++) {
				
				final Position position = new Position(j, i);
				System.out.print(" ");
				
				if (this.blockSet.isBlock(position)) {
					System.out.print(this.blockSet.getBlockName(position));
				} else {
					System.out.print("__");
				}
				System.out.print(" ");
			}
			System.out.println("\n");
		}
		return;
	}

	// =========================================================================
	// DRAW - METHODS
	// =========================================================================

	/**
	 * Draws the {@code GameField} onto a {@code Zeichenblatt.java} with a
	 * time-delay.
	 * 
	 * @param delay		The time delay
	 */
	public void draw(final int delay) {
		final int SIZE = 64;
		final int ONE = 1;

		final double OFFSET = 0.5;
		
		// new Zeichenblatt.java
		if (this.canvas == null) {
			this.canvas = new Zeichenblatt(
									(WIDTH  + ONE) * SIZE,
									(HEIGHT + ONE) * SIZE);
			this.canvas.benutzerkoordinaten(
										0.0,
										0.0,
										WIDTH  + ONE,
										HEIGHT + ONE);
		} else {
			this.canvas.loeschen();
		}

		// draw light grey square (outline)
		this.canvas.setVordergrundFarbe(Color.lightGray);
		this.canvas.rechteck(
						WIDTH  + ONE,
						HEIGHT + ONE);

		// draw red square in the bottom right corner (marks goal)
		this.canvas.setVordergrundFarbe(Color.red);
		for (final Position position : WINNING_SQUARES) {
			this.canvas.rechteck(
							position.x() + OFFSET,
							position.y(),
							ONE + OFFSET,
							ONE + OFFSET);
		}

		// draw white center square (the game field)
		this.canvas.setVordergrundFarbe(Color.white);
		this.canvas.rechteck(
						OFFSET,
						OFFSET,
						WIDTH,
						HEIGHT);
		
		// draw each {@code Block}
		for (final Block block : this.blockSet) {
			for (final Position position : block.positionList()) {
				this.canvas.setVordergrundFarbe(block.color());
				this.canvas.rechteck(
								position.x() + OFFSET,
								position.y() + OFFSET,
								ONE,
								ONE);
			}
		}
		
		// show
		this.canvas.anzeigen();

		// pause
		this.canvas.pause(delay);
		
		return;
	}

	// =========================================================================

}   // GameField class
