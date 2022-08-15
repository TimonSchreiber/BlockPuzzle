package field;

import block.Block;
import block.Position;
import block.PositionList;

/**
 * Simulates a GameField with a width and height.
 */
public final class GameField {

    // -------------------------------------------------------------------------
    // ATTRIBUTES
    // -------------------------------------------------------------------------

    /** Height of this GameField */
    private final int HEIGHT;

    /**Width of this GameField */
    private final int WIDTH;

    private final CanvasInfo CANVAS_INFO;

    /** Winning PositionList */
    private final PositionList WIN_CONDITION;

    /** Canvas to draw this GameField on */
    private Zeichenblatt canvas;

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /**
     * Class constructor.
     */
    public GameField(int height, int width, PositionList winCondition, CanvasInfo canvasInfo) {
        this.HEIGHT         = height;
        this.WIDTH          = width;
        this.WIN_CONDITION  = winCondition;
        this.CANVAS_INFO    = canvasInfo;
    }

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    /** TODO: get a List of Blocks that need to match the winning condition, maybe also a number of rabbits (or 1 if not JumpingRabbits)
     * Checks if the Block satisfies the winning condition.
     *
     * @return  {@code true} if the MainBlocks reached the winning Position
     *          defined by WIN_CONDITION, {@code false} otherwise.
     */
    public boolean checkWinCondition(final BlockSet blockSet) {
        /* 1st Idea how to make this work:
         * int counter = 0;
         *
         * for (Block block : blockSet.getMainBlocks()) {
         *     for (Position position : block.positions()) {
         *         if (WIN_CONDITION.contains(position)) { ++counter; }
         *     }
         * }
         * return counter == WIN_CONDITION.size();
         */

        // TODO: "R1" will not always be the winnig Block (Jumping Rabits). Fix this!
        return blockSet
                .getBlock("R1")
                .positionList()
                .equals(WIN_CONDITION);
        // TODO: maybe use forwarding here
    }

    // -------------------------------------------------------------------------
    // IS IN INTERVAL
    // -------------------------------------------------------------------------

    /**
     * Checks if the specified Position is inside the dimensions of this
     * GameField.
     *
     * @param position  The Position to be checked.
     * @return          {@code true} if the Position is inside this GameField,
     *                  {@code false} otherwise.
     */
    private boolean isInInterval(final Position position) {
        return     (position.x() < WIDTH)
                && (position.x() >= 0)
                && (position.y() < HEIGHT)
                && (position.y() >= 0);
    }

    // -------------------------------------------------------------------------
    // IS COLLISION FREE
    // -------------------------------------------------------------------------

    /** TODO: make this work with Rabbits
     * Checks if the Move can be performed or if this will result in an illegal
     * BlockSet by overlapping two (or more) Blockss, or by leaving the
     * boundaries of this GameField.
     *
     * @param move    the Move
     * @return        {@code true} if this Move can be made, {@code false}
     *                otherwise.
     */
    private boolean isCollisionFree(final BlockSet blockSet, final Move move) {
        final Block newBlock = new Block(blockSet.getBlock(move.name()));

        // TODO: why check every position on it's own and not all of them together?
        for (final Position position : newBlock.positionList()) {
            final Position tmpPosition = position.moveTowards(move.direction());

            // Checks if the new Position is outside the GameField
            if (!isInInterval(tmpPosition)) {
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
     * Checks if the Block specified by the Move can be moved into the Direction
     * of the Move.
     *
     * @param move  the Move
     * @return      {@code true} if the Move was successful, {@code false}
     *              otherwise.
     */
    public boolean isValidMove(final BlockSet blockSet, final Move move) {
        if (isCollisionFree(blockSet, move)) {
            blockSet.makeMove(move);
            return true;
        }

        return false;
    }

    // -------------------------------------------------------------------------
    // PRINT
    // -------------------------------------------------------------------------

    /**
     * Prints the Name of each Block for each Position on the Console and "__"
     * if there is no Block.
     */
    public void print(final BlockSet blockSet) {
        for (int i = (HEIGHT - 1); i >= 0; i--) {

            for (int j = 0; j < WIDTH; j++) {

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
     * Draws the GameField onto a Zeichenblatt with a time-delay.
     *
     * @param delay     The time delay
     */
    public void draw(final BlockSet blockSet, final int delay) {
        final int SIZE = 64;
        final int ONE = 1;

        final double OFFSET = .5;

        // new Zeichenblatt.java
        if (canvas == null) {
            canvas = new Zeichenblatt(
                (WIDTH  + ONE) * SIZE,
                (HEIGHT + ONE) * SIZE
            );

            canvas.benutzerkoordinaten(
                0.0,
                0.0,
                WIDTH  + ONE,
                HEIGHT + ONE
            );
        } else {
            canvas.loeschen();
        }

        // draw light grey square (outline)
        canvas.setVordergrundFarbe(CANVAS_INFO.outsideColor());
        canvas.rechteck(
            WIDTH  + ONE,
            HEIGHT + ONE
        );

        // draw white center square (the game field)
        canvas.setVordergrundFarbe(CANVAS_INFO.insideColor());
        canvas.rechteck(
            OFFSET,
            OFFSET,
            WIDTH,
            HEIGHT
        );

        // draw red square in the bottom right corner (marks goal)
        canvas.setVordergrundFarbe(CANVAS_INFO.winColor());
        for (final Rectangle rectangle : CANVAS_INFO.winArea()) {
            canvas.rechteck(
                rectangle.minX(),
                rectangle.minY(),
                rectangle.width(),
                rectangle.height()
            );
        }

        // draw each Block
        for (final Block block : blockSet) {
            for (final Position position : block.positionList()) {
                canvas.setVordergrundFarbe(block.color());
                canvas.rechteck(
                    position.x() + OFFSET,
                    position.y() + OFFSET,
                    ONE,
                    ONE
                );
            }
        }

        // show
        canvas.anzeigen();

        // pause
        canvas.pause(delay);

        return;
    }

    // =========================================================================

}   // Game Field class
