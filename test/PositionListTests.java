import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import block.BlockInfo;
import block.Position;
import block.PositionList;
import field.Directions;

public class PositionListTests {

    // @Test
    // @DisplayName("Constructing an Elbow-Block at 0,0")
    // public void constructor_Elbow() {
    //     PositionList actual = new PositionList(
    //                             new BlockInfo(
    //                                 new Position(0, 0),
    //                                 3,
    //                                 Direction.U));
    //     PositionList expected = new PositionList(Arrays.asList(
    //                                                 new Position(0, 0),
    //                                                 new Position(1, 0),
    //                                                 new Position(0, 1)));
        
    //     assertEquals(expected, actual);
    // }

    @Test
    public void contains() {
        // Position p = new Position(3, 1);
        // PositionList pl = new PositionList(
        //                     new BlockInfo(
        //                         new Position(2, 1),
        //                         3,
        //                         Direction.U));

        // assertTrue(pl.contains(p));
    }

    @Test
    public void getSize() {
        // PositionList pl = new PositionList(
        //     new BlockInfo(
        //         new Position(2, 1),
        //         3,
        //         Direction.U));

        // assertEquals(3, pl.getSize());
    }

    @Test
    public void getFirst() {
        // Position p = new Position(0, 0);
        // PositionList pl = new PositionList(
        //     new BlockInfo(
        //         new Position(1, 1),
        //         4,
        //         Direction.D));

        // assertEquals(p, pl.getFirst());
    }

    @Test
    @DisplayName("Moving a Large Square to the right and up")
    public void moveTowards() {
        PositionList actual = new PositionList(
            new BlockInfo(
                new Position(1, 1),
                4,
                Directions.D));

        PositionList expected = new PositionList(
            new BlockInfo(
                new Position(2, 2),
                4,
                Directions.D));

        actual.moveTowards(Directions.R, Directions.U);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Compare to differently sized PositionLists")
    public void compareTo_DifferentSizes() {
        PositionList pl1 = new PositionList(
            new BlockInfo(
                new Position(4, 1),
                4,
                Directions.D));

        PositionList pl2 = new PositionList(
            new BlockInfo(
                new Position(3, 2),
                2,
                Directions.R));

                assertEquals(-1, pl1.compareTo(pl2));
    }

    @Test
    @DisplayName("Compare to same sized PositionLists")
    public void compareTo_SameSize() {
        PositionList pl1 = new PositionList(
            new BlockInfo(
                new Position(4, 1),
                4,
                Directions.D));

        PositionList pl2 = new PositionList(
            new BlockInfo(
                new Position(3, 2),
                4,
                Directions.R));

        assertEquals(-1, pl1.compareTo(pl2));
    }
    
}
