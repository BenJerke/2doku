package ben.sudoku;

import ben.sudoku.exception.GameException;

import java.util.List;

public class GridSquare {
    // store current value and calculate potential values when asked
    private final static int EMPTY_SQUARE_VALUE = 0;
    int value = EMPTY_SQUARE_VALUE;
    int xCoordinate;
    int yCoordinate;

    // some squares get values set at the start of the game.
    // those squares should be immutable.
    // this will tell us which squares are starters, and shouldn't be changed.
    private boolean isStartingSquare = false;
    public int getValue() {
        return value;
    }
    public int getXCoordinate(){
        return xCoordinate;
    }
    public int getYCoordinate(){
        return yCoordinate;
    }

    public boolean isStartingSquare(){
        return isStartingSquare;
    }

    public void setStartingSquare(){
        this.isStartingSquare = true;
    }

    public void setValue(int value) throws GameException {
        if (!isStartingSquare) {
            this.value = value;
        } else {
            throw new GameException ("This is a starting square - can't update it.");
        }
    }


    public GridSquare(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

    }
    public GridSquare (int value, int xCoordinate, int yCoordinate) {
        this.value = value;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public void printSquare(){
        System.out.println("X:" + xCoordinate + " Y:" + yCoordinate + " Val:" +value);
    }

}
