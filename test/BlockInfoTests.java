import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import block.BlockInfo;
import block.Position;
import field.Directions;

public class BlockInfoTests {

    @Test
    public void Test() {
        BlockInfo bi = new BlockInfo(new Position(1, 1), 2, Directions.R);

        assertNotNull(bi);
    }
    
}
