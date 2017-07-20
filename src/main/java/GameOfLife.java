import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by vsanjekar on 7/19/17.
 */
public class GameOfLife {
    char[][] universe;
    int dimension;

    public static final char ALIVE = '*';
    public static final char DEAD = '-';

    GameOfLife() {
        dimension = 25;
    }

    GameOfLife(int d) {
        dimension = d;
    }

    public void initializeUniverse() {
        universe = new char[dimension][dimension];

        Random random = new Random();
        for (int i=0; i< dimension; i++) {
            for (int j=0; j< dimension; j++) {
                if (random.nextFloat() > 0.8) {
                    universe[i][j] = ALIVE;
                }
                else {
                    universe[i][j] = DEAD;
                }
            }
        }
    }

    public void initializeUniverse(char[][] newUniverse) {
        if (newUniverse.length != dimension) {
            System.out.println("Wrong universe size");
            System.exit(-1);
        }

        universe = newUniverse;
    }

    public void printUniverse() {
        for (int i=0; i< dimension; i++) {
            for (int j=0; j< dimension; j++) {
                System.out.print(universe[i][j]+" ");
            }
            System.out.println();
        }
    }

    public int getAliveCellNeighbors(int i, int j) {
        int count = 0;

        if (!(i >=0 && i < dimension && j>= 0 && j < dimension))
            return -1;

        if (i-1 >= 0) {
            for (int k = j-1; k<= j+1; k++) {
                if (k >=0 && k < dimension && universe[i-1][k]==ALIVE)
                    count++;
            }
        }

        if (i >= 0 && i < dimension) {
            if (j-1 >= 0 && j-1 < dimension && universe[i][j-1]==ALIVE)
                count ++;

            if (j+1 >= 0 && j+1 < dimension && universe[i][j+1]==ALIVE)
                count ++;
        }

        if (i+1 < dimension) {
            for (int k = j-1; k<= j+1; k++) {
                if (k >=0 && k < dimension && universe[i+1][k]==ALIVE)
                    count++;
            }
        }

        return count;
    }

    public char[][] getNextUniverseState() {

        char[][] newUniverse = new char[dimension][dimension];
        for (int i=0; i< dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int neighbors = getAliveCellNeighbors(i,j);
                if (universe[i][j] == ALIVE) {
                    if (neighbors < 2)
                        newUniverse[i][j] = DEAD;
                    else if (neighbors > 3)
                        newUniverse[i][j] = DEAD;
                    else
                        newUniverse[i][j] = ALIVE;
                } else {
                    if (neighbors == 3)
                        newUniverse[i][j] = ALIVE;
                    else
                        newUniverse[i][j] = DEAD;
                }
            }
        }

        return newUniverse;
    }

    public void play() {
        while (true) {
            printUniverse();
            universe = getNextUniverseState();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                // Runtime.getRuntime().exec("clear");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        GameOfLife game1 = new GameOfLife();
        // game1.initializeUniverse();
        // game1.play();

        GameOfLife game2 = new GameOfLife(5);
        game2.initializeUniverse(new char[][]{
                {'-','-','-','-','-'},
                {'-','-','-','-','-'},
                {'-','-','-','*','-'},
                {'-','-','-','-','*'},
                {'-','-','*','*','*'}
        });
        game2.play();
    }
}
