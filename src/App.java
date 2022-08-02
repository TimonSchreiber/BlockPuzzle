import java.util.Scanner;
import java.awt.Color;

import block.Block;
import field.Direction;
import field.MovePattern;
import game.DirtyDozen;
import game.JumpingRabbits;
import game.RushHour;
import solver.BFS_WithThreads;
import solver.BreadthFirstSearch;
import solver.DepthFirstSearch;

public class App {

    public static void main(String[] args) throws Exception {

        // System.out.println("Yellow:\t\t" + Color.YELLOW);
        // System.out.println("Yellow.D:\t" + Color.YELLOW.darker());
        // System.out.println("Blue:\t\t" + Color.BLUE);
        // System.out.println("Cyan:\t\t" + Color.CYAN);
        // System.out.println("Magenta:\t" + Color.MAGENTA);
        // System.out.println("Dark_G:\t\t" + Color.DARK_GRAY);
        // System.out.println("Gray:\t\t" + Color.GRAY);
        // System.out.println("Pink:\t\t" + Color.PINK);
        // System.out.println("Pink.B\t" + Color.PINK.brighter());
        // System.out.println("Pink.D\t" + Color.PINK.darker());
        
        // RushHour game = new RushHour(2);

        // System.out.println("BlockSet:");
        // for (Block block : game.blocks()) {
        //     System.out.println(block.blockName());
        // }

        // System variables
        // New Scanner
        try (final Scanner scanner = new Scanner(System.in)) {
            String type = "t";
            int number = 6;
            System.out.println("Game Number: " + number);     // TODO: delete?
            int delay = 0;
            boolean show = false;

            // TODO: maybe create a Game instance here and pass it to the Solver

            switch (type.toUpperCase().charAt(0)) {
                case 'D':
                    new DepthFirstSearch(number, delay, show).solve();
                    break;
                case 'B':
                    new BreadthFirstSearch(number).solve();
                    break;
                case 'T':
                    new BFS_WithThreads(number).solve();
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
