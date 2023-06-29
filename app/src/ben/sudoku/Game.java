package ben.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Math;

public class Game {
    private final static int GRID_SIZE_OFFSET = 1;
    private final static int ZERO = 0;
    private List<GridSquare> squares = new ArrayList<GridSquare>(); //sort this by row?
    private int gridSize; // this must be a square, or something that we then square.
    private int[] boundaries;

    public Game (int gridSize) {
        this.gridSize = gridSize;
        for (int i = 1; i < this.squareGridSize() + GRID_SIZE_OFFSET; i++) {
            // make the columns
            for(int j = 1; j < this.squareGridSize() + GRID_SIZE_OFFSET; j++){
                squares.add(new GridSquare(j, i));
            }
        }
        int gridBoundary = gridSize;
        this.boundaries = new int[gridSize];
        for (int i = 0; i < gridSize; i++) {
            this.boundaries[i] = gridBoundary;
            //System.out.println("boundary at " + this.boundaries[i]);
            gridBoundary += gridSize;
        }
    }
    public List<GridSquare> getSquares (){
        return squares;
    }
    public int squareGridSize(){
        return this.gridSize * this.gridSize;
    }

    public void printGameGrid() {
        // this will work until we have real graphics.
        int rowCount = 1;
        int colCount = 1;
        String asterisks = "";
        // for every column in the grid, we gain three spaces.
        // number of asterisks = (number of columns * 3) + square of grid size
        // number of columns = box size - 1? cuz box size is box count.
        for (int i = 0; i < this.squareGridSize() + ((this.gridSize - 1) * 3); i++) {
            asterisks += "-";
        }
        for (GridSquare square : squares){
            if (square.getYCoordinate() > rowCount) {
                System.out.print("\n");
                if (rowCount % this.gridSize == ZERO && rowCount != this.squareGridSize()){
                    System.out.print(asterisks + "\n");
                }
            }
            if ((colCount % this.gridSize == ZERO)
                    && (colCount != this.squareGridSize())) {
                System.out.print(" | ");
            }
            if (square.getValue() == 0) {
                System.out.print(" ");
            } else {
                System.out.print(square.getValue());
            }
            rowCount = square.getYCoordinate();
            colCount = square.getXCoordinate();
        }
    }
    public void updateSquareInList (String proposedValue){
        // parse input
        int y = Integer.parseInt(proposedValue.charAt(0) + "");
        int x = Integer.parseInt(proposedValue.charAt(2) + "");
        int val = Integer.parseInt(proposedValue.charAt(4) + "");
        // this is SUPER NOT IDEAL.
        // but it is SUPER GOOD ENOUGH.
        for (GridSquare square : squares){
            int squareX = square.getXCoordinate();
            int squareY = square.getYCoordinate();
            if(y == squareY && x == squareX){
                if(updateIsValid(square, val)){
                    square.setValue(val);
                }
            }
        }
    }

    public boolean updateIsValid (GridSquare square, int proposedValue){
        // an update is valid IF:
            // no squares in the same row have the same value - get the bois where x is the same and check 'em.
            // no squares in the same column have the same value - get the bois where y is the same and check 'em.
            // no squares in the same box have the same value - uhh... box definition method required.

        // We don't care about the current square, though.
        // I don't know if this approach accounts for resetting a square's current value.
        // we have to check whether the square is a starter square too.

        List<List<GridSquare>> colsRowsBoxes = new ArrayList<List<GridSquare>>();
        colsRowsBoxes.add(findRowMembers(square));
        colsRowsBoxes.add(findColumnMembers(square));
        colsRowsBoxes.add(findBoxMembers(square));
        for (List<GridSquare> list : colsRowsBoxes){
            if (getValuesFromListOfSquares(list).contains(proposedValue)){
                return false;
            }
        }
        return true;
    }
    public List<GridSquare> findBoxMembers(GridSquare square){
        List<GridSquare> box = new ArrayList<GridSquare>();
        // the count of boundaries is gridSize.
        // the location of boundaries is gridSize.
        // if you're on a boundary, your range is from where you're at to gridSize minus gridSize minus 1.
        // if you're not on a boundary, you gotta find the nearest boundary.
        int x = square.getXCoordinate();
        int y = square.getYCoordinate();
        int upperXBoxBoundary = findBoxUpperBoundary(x);
        int lowerXBoxBoundary = findBoxUpperBoundary(x) - (gridSize - 1);
        int upperYBoxBoundary = findBoxUpperBoundary(y);
        int lowerYBoxBoundary = findBoxUpperBoundary(y) - (gridSize - 1);
        for (GridSquare currentSquare : this.squares){
            if(currentSquare.getXCoordinate() <= upperXBoxBoundary
                    && currentSquare.getXCoordinate() >= lowerXBoxBoundary
                    && currentSquare.getYCoordinate() <= upperYBoxBoundary
                    && currentSquare.getYCoordinate() >= lowerYBoxBoundary ) {
                box.add(currentSquare);
            }
        }
        return box;
    }
    public int findBoxUpperBoundary (int coordinate){
        int upperBoundary = 0;
        if (coordinate % gridSize == 0){
            // if mod gridsize is zero, you're on the upper boundary - return yourself.
            upperBoundary = coordinate;
        } else {
            for (int boundary : this.boundaries){
                if (coordinate <= boundary){
                    // dependent on order, but that's probably fine.
                    upperBoundary = boundary;
                    return upperBoundary;
                }
            }
        }
        return upperBoundary;
    }
    public List<GridSquare> findRowMembers (GridSquare square){
        List<GridSquare> row = new ArrayList<GridSquare>();
        int squareYCoordinate = square.getYCoordinate();
        for (GridSquare currentSquare : getSquares()){
            if (currentSquare.getYCoordinate() == squareYCoordinate ){
                row.add(currentSquare);
            }
        }
        return row;
    }
    public List<GridSquare> findColumnMembers(GridSquare square){
        List<GridSquare> col = new ArrayList<GridSquare>();
        int squareXCoordinate = square.getXCoordinate();
        for (GridSquare currentSquare : getSquares()){
            if (currentSquare.getXCoordinate() == squareXCoordinate){
                col.add(currentSquare);
            }
        }
        return col;
    }

    public List<Integer> getValuesFromListOfSquares (List<GridSquare> squares){
        List <Integer> valueList = new ArrayList<Integer>();
        for (GridSquare square : squares){
            valueList.add(square.getValue());
        }
        return valueList;
    }




}
