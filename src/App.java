import java.util.Scanner;

import game.DirtyDozen;
import game.Game;
import game.JumpingRabbits;
import game.RushHour;
import solver.BFS_WithThreads;
import solver.BreadthFirstSearch;
import solver.DepthFirstSearch;
import solver.ManualMode;

/**
 * This App provides three different Logic-Puzzles and also four different
 * ways for solving.
 *
 * <p>Puzzles:
 * <ul>
 *   <li>DirtyDozen    - 13 Games in total (number 0 to 12)
 *   <li>JumpinRabbits -  6 Games in total (number 0 to  5)
 *   <li>RushHour      -  4 Games in total (number 0 to  3)
 * </ul>
 *
 * Solving Methods:
 * <ul>
 *  <li>Depth First Search
 *  <li>Breadth First Search
 *  <li>Breadth fFirst Search with Multi-Threads
 *  <li>Manual Mode
 * </ul>
 *
 * @version     1.1     16 Aug 2022
 * @author      Timon Schreiber
 */
public class App {

    public static void main(String[] args) {

        // ---------------------------------------------------------------------
        // Variables

        /** D=DirtyDozen; J=HumpingRabbits; R=RushHour */
        final String gameType;

        /** D=Depth; B=Breadth; T=Threads; M=Manual */
        final String solverType;

        /** The key of the games' BlockInfo-Map */
        final int gameNumber;

        /** Time in milliseconds between two moves */
        final int delay;

        /** The Game to play */
        final Game game;

        try (final Scanner scanner = new Scanner(System.in)) {
            gameType    = "D";
            solverType  = "B";
            gameNumber  = 12;
            delay       = 200;

            game = getGame(gameType.toUpperCase().charAt(0), gameNumber);

            printGameNumber(gameNumber);

            printBoard(game);

            solve(solverType, game, delay, scanner);
        }

        return;
    }   // Main

    // ---------------------------------------------------------------------

    /**
     * Print the board of the Game on the console.
     *
     * @param game  The Game
     */
    private static void printBoard(Game game) {
        System.out.println("\nBoard:");
        game.print();
        return;
    }

    /**
     * Print the GameNumber on the console.
     *
     * @param gameNumber    the game number
     */
    private static void printGameNumber(int gameNumber) {
        System.out.println("\nGame Number: " + gameNumber);
        return;
    }

    /**
     * Call the #solve()/#play() method depending on the solveType.
     *
     * @param solvType  The solver type
     * @param game      The Game
     * @param delay     The Time delay
     * @param scanner   The Scanner
     */
    private static void solve(String solvType, Game game, int delay, Scanner scanner) {
        switch (solvType.toUpperCase().charAt(0)) {
            case 'D' -> new DepthFirstSearch(   game, (delay/10), false ).solve();
            case 'B' -> new BreadthFirstSearch( game,  delay            ).solve();
            case 'T' -> new BFS_WithThreads(    game,  delay            ).solve();
            case 'M' -> new ManualMode(         game,  delay,   scanner ).play();
            default  -> throw new IllegalStateException("Invalid SolveType: " + solvType);
        };
        return;
    }

    /**
     * Returns the Game depending on the game type.
     *
     * @param gameType      The game type
     * @param gameNumber    The game number
     * @return              Returns a new instance of the specified Game class.
     */
    private static Game getGame(char gameType, int gameNumber) {
        return switch (gameType) {
            case 'D' -> new DirtyDozen(     gameNumber);
            case 'J' -> new JumpingRabbits( gameNumber);
            case 'R' -> new RushHour(       gameNumber);
            default  -> throw new IllegalStateException("Invalid GameType: " + gameType);
        };
    }

}
