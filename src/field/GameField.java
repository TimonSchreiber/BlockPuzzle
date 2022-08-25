package field;

import block.Block;
import block.Position;
import block.PositionList;
import canvas.CanvasInfo;
import canvas.Rectangle;
import canvas.Zeichenblatt;

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

    /**
     * Checks if the BlockSet satisfies the winning condition.
     *
     * @return  {@code true} if the MainBlocks reached the winning Position
     *          defined by WIN_CONDITION, {@code false} otherwise.
     */
    public boolean checkWinCondition(final BlockSet blockSet) {
        /* 
         * Get every MainBlocks PositionList and check if every Position is in
         * the WinCondition. If not, return false.
         * If the end of the outer loop is reached return true.
         */
        for (Block block : blockSet.getMainBlocks()) {

            for (Position position : block.positions()) {

                if (!WIN_CONDITION.contains(position)) {
                    return false;
                }
            }
        }

        return true;
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
    public boolean isInInterval(final Position position) {
        return     (position.x() < WIDTH)
                && (position.x() >= 0)
                && (position.y() < HEIGHT)
                && (position.y() >= 0);
    }

    // -------------------------------------------------------------------------
    // IS COLLISION FREE
    // -------------------------------------------------------------------------

    /**
     * Checks if the Move can be performed or if this will result in an illegal
     * BlockSet by overlapping two (or more) Blocks, or by leaving the
     * boundaries of this GameField.
     *
     * @param blockSet  The BlockSet
     * @param move      The Move
     * @return          {@code true} if this Move can be made, {@code false}
     *                  otherwise.
     */
    public boolean isCollisionFree(final BlockSet blockSet, final Move move) {
        final Block tmpBlock = blockSet.getBlockByName(move.name());

        // check if the Move is valid.
        if (tmpBlock == null) {
            System.err.println("Invalid BlockName: " + move);
            return false;
        }

        for (final Position position : tmpBlock.positions()) {
            final Position tmpPosition = position.moveTowards(move.direction());

            // Checks if the new Position is outside the GameField
            if (!isInInterval(tmpPosition)) {
                return false;   // outside GameField
            }

            // Checks if there is already a Block at this Positions
            // If yes, check if it does not have the same name
            if (blockSet.isBlock(tmpPosition)
                && !blockSet.getNameByPosition(tmpPosition).equals(tmpBlock.name())) {
                return false;   // collides with another Block
            }
        }

        // inside GameField and no collision with another Block.
        return true;
    }

    // -------------------------------------------------------------------------
    // IS VALID MOVE
    // -------------------------------------------------------------------------

    /**
     * Checks if the Block specified by the Move can be moved into the Direction
     * of the Move.
     *
     * @param blockSet  The BlockSet
     * @param move      The Move
     * @return          {@code true} if the Move was successful, {@code false}
     *                  otherwise.
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

                // BlockName to print
                final String possibleBlockName =
                    blockSet.getNameByPosition(new Position(j, i));

                // print BlockName, or "__" and a space at the end
                System.out.print(((possibleBlockName == null) ? "__" : possibleBlockName) + " ");

            }
            // double linebreak at the end
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
            for (final Position position : block.positions()) {
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

}   // Game Field class
