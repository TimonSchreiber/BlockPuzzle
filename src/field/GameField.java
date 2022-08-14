package field;

import java.awt.Color;
// import java.util.List;

import block.Block;
import block.Position;
import block.PositionList;

public final class GameField {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /** Winning Positions {@code PositionList}*/
    private final PositionList WIN_CONDITION;

    /** preset game values for this.HEIGHT */
    private final int HEIGHT;

    /** preset game values for this.WIDTH */
    private final int WIDTH;

    // TODO: also define the neccessary properties of the canvas in the Game class
    // e.g. Where the goal/target is

    /** Canvas to draw the {@code GameField} on */
    private Zeichenblatt canvas;

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /**
     * Class constructor.
     */
    public GameField(int height, int width, PositionList winCondition) {
        this.HEIGHT = height;
        this.WIDTH = width;
        this.WIN_CONDITION = winCondition;
    }

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    /** TODO: get a List of Blocks that need to match the winning condition, maybe also a number of rabbits (or 1 if not JumpingRabbits)
     * Checks if the {@code Block} satisfies the winning condition.
     *
     * {@code true} if the {@code LargeBlock} reached the winning Position
     * defined by {@link END_POSITIONS}, {@code false} otherwise.
     *
     * @return
     */
    public boolean checkWinCondition(final BlockSet blockSet) {
        /* 1st Idea how to make this work:
         * int counter = 0;
         * 
         * for (Block block : blockSet.getMainBlocks()) {
         *     for (Position position : block.positions()) {
         *         if (this.WIN_CONDITION.contains(position)) { ++counter; }
         *     }
         * }
         * return counter == this.WIN_CONDITION.size();
         */

        // TODO: "R1" will not always be the winnig Block (Jumping Rabits). Fix this!
        return blockSet
                .getBlock("R1")
                .positionList()
                .equals(this.WIN_CONDITION);
        // TODO: maybe use forwarding here
    }

    // -------------------------------------------------------------------------
    // IS IN INTERVAL
    // -------------------------------------------------------------------------

    /**
     * TODO:
     *
     * @param position
     * @return
     */
    private boolean isInInterval(final Position position) {
        return     (position.x() < this.WIDTH)
                && (position.x() >= 0)
                && (position.y() < this.HEIGHT)
                && (position.y() >= 0);
    }

    // -------------------------------------------------------------------------
    // IS COLLISION FREE
    // -------------------------------------------------------------------------

    /** TODO: make this work with Rabbits
     * Checks if the {@code Move} can be performed or if this will result in an
     * illegal {@code GameField} by overlapping two (or more) {@code Block}s, or
     * by leaving the boundaries of this {@code GameField}.
     *
     * @param move    the {@code Move}
     * @return        {@code true} if this {@code Move} can be made, {@code false}
     *                otherwise.
     */
    private boolean isCollisionFree(final BlockSet blockSet, final Move move) {
        final Block newBlock = new Block(blockSet.getBlock(move.name()));

        // TODO: why check every position on it's own and not all of them together?
        for (final Position position : newBlock.positionList()) {
            final Position tmpPosition = position.moveTowards(move.direction());

            // Checks if the new Position is outside the GameField
            if (!this.isInInterval(tmpPosition)) {
                return false;   // outside GameField
            }

            // Checks if there is already a Block at this Positions
            // If yes, check if it does not have the same name
            if (blockSet.isBlock(tmpPosition) &&
                !blockSet.getBlockName(tmpPosition).equals(newBlock.blockName())) {
                // TODO: Maybe extract the conditions onto variables to make it more readable.
                return false;   // collides with another Block
            }
        }

        // inside GameField and no Collision with another Block
        return true;
    }

    // -------------------------------------------------------------------------
    // IS VALID MOVE
    // -------------------------------------------------------------------------

    /**
     * Checks if the {@code Block} defined by the {@code Move} can be moved into
     * the {@code Direction} of the {@code Move}.
     *
     * @param move  the {@code Move}
     * @return      {@code true} if the {@code Move} was successful,
     *              {@code false} otherwise.
     */
    public boolean isValidMove(final BlockSet blockSet, final Move move) {
        if (this.isCollisionFree(blockSet, move)) {
            blockSet.makeMove(move);
            return true;
        }

        return false;
    }

    // -------------------------------------------------------------------------
    // PRINT
    // -------------------------------------------------------------------------

    /** TODO: is this one used? maybe keep it for debugging in the future
     * Prints the Name of each {@code Block} for each {@code Position} on the
     * Console and "__" if there is no {@code Block}.
     */
    public void print(final BlockSet blockSet) {
        for (int i = (this.HEIGHT - 1); i >= 0; i--) {

            for (int j = 0; j < this.WIDTH; j++) {

                final Position position = new Position(j, i);
                System.out.print(" ");

                if (blockSet.isBlock(position)) {
                    System.out.print(blockSet.getBlockName(position));
                } else {
                    System.out.print("__");
                }
                System.out.print(" ");
            }
            System.out.println("\n");
        }
        return;
    }

    // -------------------------------------------------------------------------
    // DRAW
    // -------------------------------------------------------------------------

    /**
     * Draws the {@code GameField} onto a {@code Zeichenblatt.java} with a
     * time-delay.
     *
     * @param delay     The time delay
     */
    public void draw(final BlockSet blockSet, final int delay) {
        final int SIZE = 64;
        final int ONE = 1;

        final double OFFSET = 0.5;

        // new Zeichenblatt.java
        if (this.canvas == null) {
            this.canvas = new Zeichenblatt(
                                    (this.WIDTH  + ONE) * SIZE,
                                    (this.HEIGHT + ONE) * SIZE);
            this.canvas.benutzerkoordinaten(
                                        0.0,
                                        0.0,
                                        this.WIDTH  + ONE,
                                        this.HEIGHT + ONE);
        } else {
            this.canvas.loeschen();
        }

        // draw light grey square (outline)
        this.canvas.setVordergrundFarbe(Color.lightGray);
        this.canvas.rechteck(
                        this.WIDTH  + ONE,
                        this.HEIGHT + ONE);

        // draw red square in the bottom right corner (marks goal)
        this.canvas.setVordergrundFarbe(Color.red);
        for (final Position position : this.WIN_CONDITION) {
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
                        this.WIDTH,
                        this.HEIGHT);

        // draw each {@code Block}
        for (final Block block : blockSet) {
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
