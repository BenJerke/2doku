package ben.sudoku.exception;

import ben.sudoku.Game;

public class GameException extends RuntimeException {
    public GameException(String message){ super(message); }
}
