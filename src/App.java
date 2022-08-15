import java.util.Scanner;
import game.DirtyDozen;
import game.Game;
import game.JumpingRabbits;
import game.RushHour;
import solver.BFS_WithThreads;
import solver.BreadthFirstSearch;
import solver.DepthFirstSearch;

public class App {

    public static void main(String[] args) throws Exception {

        try (final Scanner scanner = new Scanner(System.in)) {
            String gameType = "RushHour";  // D=DirtyDozen; J=HumpingRabbits; R=RushHour
            String solvType = "Breadth";  // D=Depth; B=Breadth; T=Threads
            int number = 3;

            System.out.println("Game Number: " + number);

            int delay = 0;
            boolean show = false;

            Game game;

            switch (gameType.toUpperCase().charAt(0)) {
                case 'D':
                    game = new DirtyDozen(number);
                    break;
                case 'J':
                    game = new JumpingRabbits(number);
                    break;
                case 'R':
                    game = new RushHour(number);
                    break;
                default:
                    game = new DirtyDozen(number);  // TODO: what to do in the default case?
                    break;

            }

            // TODO: maybe create a Game instance here and pass it to the Solver

            switch (solvType.toUpperCase().charAt(0)) {
                case 'D':
                    new DepthFirstSearch(game, delay, show).solve();
                    break;
                case 'B':
                    new BreadthFirstSearch(game).solve();
                    break;
                case 'T':
                    new BFS_WithThreads(game).solve();
                    break;
                default:
                    System.err.println("Invalid input!");
            }
        }

        // ---------------------------------------------------------------------

        // switch (args.length) {
        //      case 3:
        //           type = args[0];
        //           number = Integer.parseInt(args[1]);
        //           delay = Integer.parseInt(args[2]);
        //           show = Boolean.parseBoolean(args[3]);
        //           break;
        //      default:
        //           // Enter Solve Type
        //           System.out.println("Depth- or Breadth-first search? [D/B]");
        //           type = sc.nextLine().toUpperCase();

        //           // Enter Game Number
        //           System.out.println("Enter the Game-Number you want to play:");
        //           number = sc.nextInt();

        //           // Enter Delay
        //           System.out.println("Enter the time you want to see the boeard [ms]:");
        //           delay = sc.nextInt();

        //           // Enter Show
        //           System.out.println("Enter if you want to see the board while it is solved [true/false]:");
        //           show = sc.nextBoolean();
        //           break;

        // }

        return;
        }

}   // end class
