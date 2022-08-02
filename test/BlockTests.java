import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import block.Block;
import block.BlockInfo;
import block.Position;
import block.PositionList;
import field.Direction;
import game.DirtyDozen;

public class BlockTests {

    //==========================================================================
    // Variables

    /* Game 9 - 99 Moves
        * 
        * R1 R1 Y4 Y4 G5 G6
        * R1 R1 Y4 Y3 G3 G4
        * B3 B3 Y3 Y3 G1 G2
        * B2 B2 Y1 Y1 Y2 __
        * B1 B1 Y1 Y2 Y2 __
        */
    DirtyDozen game = new DirtyDozen(9);

    //==========================================================================
    // Tests

    @Test
    @DisplayName("Move Square down twice")
    public void moveTowards() {

        // Block actual = game.blocks().getBlock("G2");
        // PositionList expected = new PositionList(new BlockInfo(new Position(5, 0), 1, null));

        // actual.moveTowards(Direction.D, Direction.D);

        // assertEquals(expected, actual.positionList());
    }

    @Test
    @DisplayName("Equals Should be true When two Blocks have the same PositionList")
    public void equals_samePositionList() {
        // Block b1 = new Block(
        //     new BlockInfo(
        //         new Position(3, 2),
        //         2,
        //         Direction.D));

        // Block b2 = new Block(
        //     new BlockInfo(
        //         new Position(3, 1),
        //         2,
        //         Direction.U));

        // assertEquals(b1, b2);
    }

    @Test
    @DisplayName("Equal Should be False When the Block is moved")
    public void equals_AfterMove() {
        // Block b1 = new Block(
        //     new BlockInfo(
        //         new Position(3, 2),
        //         2,
        //         Direction.D));
        // Block b2 = new Block(b1);

        // b1.moveTowards(Direction.D, Direction.L);

        // assertNotEquals(b1, b2);
    }

    // =========================================================================
    // BlockSet

    
    // @Test
    // @DisplayName("isBlock(Position) Should be true When there is a Block")
    // public void isBlock_true() {
    //     assertTrue(blockSet.isBlock(p2_3));
    // }

    // @Test
    // @DisplayName("isBlock(Position) Should be false When there is no Block")
    // public void isBlock_false() {
    //     assertFalse(blockSet.isBlock(p7_8));
    // }

    // @Test
    // @DisplayName("getSize Should be 3 When there are 3 Blocks")
    // public void getSize() {
    //     assertEquals(3, blockSet.getSize());
    // }

    // // FIXME: when running all tests the Block "Y1" becomes "Y3" (or something else?)
    // @Test
    // @DisplayName("getBlockName(Position) Should return the BlockName When there is a Block at this Position")
    // public void getBlockName_Blockname() {
    //     assertEquals("Y7", blockSet.getBlockName(p2_4));
    // }

    // @Test
    // @DisplayName("getBlockName(Position) Should return null When there is no Block at this Position")
    // public void getBlockName_null() {
    //     assertNull(blockSet.getBlockName(p7_8));
    // }
    
/*     @Test
    @DisplayName("getBlock(Move) Should return the Block When there is a Block with this move.name()")
    public void getBlock_Block() {
        assertEquals(R1, blockSet.getBlock(m1.name()));
    } */
            

    // @Test
    // @DisplayName("getBlock(Move) Should return null When there is no Block with this move.name()")
    // public void getBlock_null() {
    //     assertNull(blockSet.getBlock(m2.name()));
    // }

    //==========================================================================
    // GameField
    
    // Constructor() test

    // Constructor(BlockSet) test

    // isWon() test ??

    // (private) checkWinningCondition(Block) indirect test??

    // getBlocks() test??

    // (private) isInInterval

/*     @Test
    @DisplayName("isValidMove Should be True When the Move is allowed")
    public void isValidMove_True() {
        Move move = new Move("Y2", Direction.D);

        assertTrue(game.field().isValidMove(move));
    } */

    // @Test
    // @DisplayName("isValidMove Should be False When the Block leaves the GameField")
    // public void isValidMove_Outside_False() {
    //     Move move = new Move("R1", Direction.L);

    //     assertFalse(game.field().isValidMove(move));
    // }

/*     @Test
    @DisplayName("isValidMove Should be False When the Block collides with another Block")
    public void isValidMove_Collision_False() {
        Move move = new Move("B1", Direction.R);

        assertFalse(game.field().isValidMove(move));
    } */


}
