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
 * Puzzles:
 *  - DirtyDozen    - 13 Games in total (number 0 to 12)
 *  - JumpinRabbits -  6 Games in total (number 0 to  5)
 *  - RushHour      -  4 Games in total (number 0 to  3)
 *
 * solving methods:
 *  - Depth First Search
 *  - Breadth First Search
 *  - Breadth fFirst Search with Multi-Threads
 *  - Manual Mode
 *
 * @version     1.1     16 Aug 2022
 * @author      Timon Schreiber
 */
public class App {

    public static void main(String[] args) {

        try (final Scanner scanner = new Scanner(System.in)) {
            final String gameType   = "J";  // [D=DirtyDozen; J=HumpingRabbits; R=RushHour]
            final String solvType   = "T";  // [D=Depth; B=Breadth; T=Threads; M=Manual]
            final int gameNumber    = 5;
            final int delay         = 200;

            System.out.println("\nGame Number: " + gameNumber);

            final Game game =
                switch (gameType.toUpperCase().charAt(0)) {
                    case 'D' -> new DirtyDozen(     gameNumber);
                    case 'J' -> new JumpingRabbits( gameNumber);
                    case 'R' -> new RushHour(       gameNumber);
                    default  -> throw new IllegalStateException("Invalid GameType: " + gameType);
                };

            System.out.println("\nBoard:");
            game.print();

            switch (solvType.toUpperCase().charAt(0)) {
                case 'D' -> new DepthFirstSearch(   game, (delay/10), false ).solve();
                case 'B' -> new BreadthFirstSearch( game,  delay            ).solve();
                case 'T' -> new BFS_WithThreads(    game,  delay            ).solve();
                case 'M' -> new ManualMode(         game,  delay,   scanner ).play();
                default  -> throw new IllegalStateException("Invalid SolveType: " + solvType);
            };
        }

        // ---------------------------------------------------------------------

        // switch (args.length) {
        // case 3:
        //     type = args[0];
        //     number = Integer.parseInt(args[1]);
        //     delay = Integer.parseInt(args[2]);
        //     show = Boolean.parseBoolean(args[3]);
        //     break;
        // default:
        //     // Enter Solve Type
        //     System.out.println("Depth- or Breadth-first search? [D/B]");
        //     type = sc.nextLine().toUpperCase();

        //     // Enter Game Number
        //     System.out.println("Enter the Game-Number you want to play:");
        //     number = sc.nextInt();

        //     // Enter Delay
        //     System.out.println("Enter the time you want to see the boeard [ms]:");
        //     delay = sc.nextInt();

        //     // Enter Show
        //     System.out.println("Enter if you want to see the board while it is solved [true/false]:");
        //     show = sc.nextBoolean();
        //     break;
        // }

        return;
    }   // end Main

}   // end class
