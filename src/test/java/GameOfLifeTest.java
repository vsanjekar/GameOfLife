import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vsanjekar on 7/19/17.
 */
public class GameOfLifeTest {

    @Test
    public void testGetAliveCellNeighbors() throws Exception {
        GameOfLife game = new GameOfLife(5);
        game.initializeUniverse(new char[][]{
                {'*','*','*','*','*'},
                {'*','*','*','-','*'},
                {'*','*','*','*','*'},
                {'-','-','*','*','*'},
                {'*','-','*','*','*'}
        });

        assertEquals(game.getAliveCellNeighbors(0,0), 3);
        assertEquals(game.getAliveCellNeighbors(1,0), 5);
        assertEquals(game.getAliveCellNeighbors(0,1), 5);
        assertEquals(game.getAliveCellNeighbors(1,1), 8);

        assertEquals(game.getAliveCellNeighbors(4,4), 3);
        assertEquals(game.getAliveCellNeighbors(3,4), 5);
        assertEquals(game.getAliveCellNeighbors(4,3), 5);

        assertEquals(game.getAliveCellNeighbors(0,4), 2);
        assertEquals(game.getAliveCellNeighbors(0,3), 4);
        assertEquals(game.getAliveCellNeighbors(1,3), 8);

        assertEquals(game.getAliveCellNeighbors(4,0), 0);
        assertEquals(game.getAliveCellNeighbors(4,1), 3);
        assertEquals(game.getAliveCellNeighbors(3,1), 6);
        assertEquals(game.getAliveCellNeighbors(2,3), 7);

        assertEquals(game.getAliveCellNeighbors(-1,-1), -1);
        assertEquals(game.getAliveCellNeighbors(5,5), -1);

    }

    @Test
    public void testGetNextUniverseState() throws Exception {

    }
}