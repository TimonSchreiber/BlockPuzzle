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

    /** Winning PositionList */
    private final PositionList WIN_POSITIONS;

    // Info for the Zeichenblatt
    private final CanvasInfo CANVAS_INFO;

    /** Canvas to draw this GameField on */
    private Zeichenblatt canvas;

    // -------------------------------------------------------------------------
    // CONSTRUCTORS
    // -------------------------------------------------------------------------

    /**
     * Class constructor.
     */
    public GameField(int height, int width, PositionList winPositions, CanvasInfo canvasInfo) {
        this.HEIGHT        = height;
        this.WIDTH         = width;
        this.WIN_POSITIONS = winPositions;
        this.CANVAS_INFO   = canvasInfo;
    }

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    /**
     * Checks if the BlockSet satisfies the winning condition.
     *
     * @return  {@code true} if the MainBlocks reached the winning Position
     *          defined by WIN_AREA, {@code false} otherwise.
     */
    public boolean checkWinCondition(BlockSet blockSet) {
        /*
         * Get every MainBlocks PositionList and check if every Position is in
         * the WIN_POSITIONS PositionList. If not, return false.
         */
        for (final Block block : blockSet.getMainBlocks()) {

            for (final Position position : block.positions()) {

                if (!WIN_POSITIONS.contains(position)) {
                    return false;
                }
            }
        }

        // Every Position of every MainBlock is inside the WIN_POSITIONS
        // PositionList.
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
    public boolean isInInterval(Position position) {
        return     (position.x() <  WIDTH )
                && (position.x() >= 0     )
                && (position.y() <  HEIGHT)
                && (position.y() >= 0     );
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
    public boolean isCollisionFree(BlockSet blockSet, Move move) {
        final Block block = blockSet.getBlockBy(move.name());

        // check if the Move is valid by checking if the Block exists.
        if (block == null) {
            System.err.println("Invalid BlockName: " + move);
            return false;
        }

        // iterate over every Position the Block has
        for (final Position pos : block.positions()) {

            // move the current Position
            final Position position = pos.moveTowards(move.direction());

            // Checks if the new Position is outside the GameField
            if (!isInInterval(position)) {
                return false;
            }

            /*
             * Checks if there is already a Block at the new Positions.
             * If yes, check if it has a different BlockName.
             * -> Move collides with another Block
             */
            final String name = blockSet.getNameBy(position);
            if ((name != null) && !name.equals(block.name())) {
                return false;
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
    public boolean isValidMove(BlockSet blockSet, Move move) {
        // check if the Move can be made
        // if not return false
        if (!isCollisionFree(blockSet, move)) {
            return false;
        }

        // play the Move
        blockSet.makeMove(move);

        // return successfull
        return true;
    }

    // -------------------------------------------------------------------------
    // PRINT
    // -------------------------------------------------------------------------

    /**
     * Prints the Name of each Block for each Position on the Console and "__"
     * if there is no Block.
     *
     * @param blockSet  The BlockSet
     */
    public void print(BlockSet blockSet) {

        // y value
        for (int i = (HEIGHT - 1); i >= 0; --i) {
            // x value
            for (int j = 0; j < WIDTH; ++j) {
                // BlockName to print
                final String blockName = blockSet.getNameBy(new Position(j, i));

                // print BlockName or "__", and a space at the start and end
                System.out.print(" " + ((blockName == null) ? "__" : blockName) + " ");
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
     * Draws the GameField and BlockSet onto a Zeichenblatt with a time-delay.
     *
     * @param blockSet  The BlockSet
     * @param delay     The time delay
     */
    public void draw(BlockSet blockSet, int delay) {
        final int    WINDOW_SIZE_FACTOR = 64;
        final int    BLOCKSIZE          =  1;
        final double OFFSET             =  0.5;

        if (canvas == null) {
            // new Zeichenblatt.java
            canvas = new Zeichenblatt(
                (WIDTH  + BLOCKSIZE) * WINDOW_SIZE_FACTOR,
                (HEIGHT + BLOCKSIZE) * WINDOW_SIZE_FACTOR
            );

            canvas.benutzerkoordinaten(
                0.0,
                0.0,
                WIDTH  + BLOCKSIZE,
                HEIGHT + BLOCKSIZE
            );

        } else {
            canvas.loeschen();
        }

        // draw light grey square (outline)
        canvas.setVordergrundFarbe(CANVAS_INFO.outsideColor());
        canvas.rechteck(
            WIDTH  + BLOCKSIZE,
            HEIGHT + BLOCKSIZE
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
                    BLOCKSIZE,
                    BLOCKSIZE
                );
            }
        }

        // show
        canvas.anzeigen();

        // pause
        canvas.pause(delay);

        return;
    }

    // -------------------------------------------------------------------------
    // EQUALS AND HASH-CODE
    // -------------------------------------------------------------------------

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        // Object must be GameField at this Point
        final GameField other = (GameField) obj;

        return (HEIGHT == other.HEIGHT)
            && (WIDTH == other.WIDTH)
            && ((WIN_POSITIONS == other.WIN_POSITIONS)
                || ((WIN_POSITIONS != null)
                    && WIN_POSITIONS.equals(other.WIN_POSITIONS)))
            && ((CANVAS_INFO == other.CANVAS_INFO)
                || ((CANVAS_INFO != null)
                    && CANVAS_INFO.equals(other.CANVAS_INFO)));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 7;

        hash = PRIME * hash + Integer.hashCode(HEIGHT);
        hash = PRIME * hash + Integer.hashCode(WIDTH);
        hash = PRIME * hash + ((WIN_POSITIONS == null) ? 0 : WIN_POSITIONS.hashCode());
        hash = PRIME * hash + ((  CANVAS_INFO == null) ? 0 :   CANVAS_INFO.hashCode());

        return hash;
    }

    // -------------------------------------------------------------------------
    // TO STRING
    // -------------------------------------------------------------------------

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return """
                GameField [\
                HEIGHT=%s, \
                WIDTH=%s, \
                WIN_POSITIONS=%s, \
                CANVAS_INFO=%s\
                ]\
                """.formatted(
                    HEIGHT,
                    WIDTH,
                    WIN_POSITIONS,
                    CANVAS_INFO
                );
    }

}
