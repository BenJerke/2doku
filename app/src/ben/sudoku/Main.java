package ben.sudoku;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("What size of boxes do you want in your grid?");
        int gridSize = Integer.parseInt(userInput.nextLine());
        Game game = new Game(gridSize);
        // call the game generator here


        boolean keepPlaying = true;
        while (keepPlaying){
            game.printGameGrid();
            System.out.println("\nPick a square and a value. (row, column, number)");
            String proposedValue = userInput.nextLine();

            if (proposedValue.equalsIgnoreCase("q")){
                System.out.println("See ya!");
                break;
            }

            game.updateSquareInList(proposedValue);
            System.out.println();
            System.out.println();
            //game.printGameGrid();
        }
    }
}
