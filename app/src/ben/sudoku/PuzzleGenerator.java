package ben.sudoku;

import java.util.List;
import java.util.Random;
import ben.sudoku.Game;
public class PuzzleGenerator {
    // fill the grid in a valid way
    // remove a bunch of numbers.
    // and then it's a sudoku?
    // hopefully
    public static void makeGame(Game game) {
        // loop through all the squares in the game
        // try setting a random value in each of 'em
        List<GridSquare> squares = game.getSquares();

        for (GridSquare square : squares) {


            // add a random number to the square.
            // if the update isn't valid, try a different random number.

        }



    }

    private int getRandomNumber (int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max - 1) + 1) + 1;
        return randomNum;

    }


}
