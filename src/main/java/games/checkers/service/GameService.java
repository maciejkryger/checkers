package games.checkers.service;

import games.checkers.repository.CheckersLogic;
import games.checkers.console.CheckersConsoleBoard;
import games.checkers.model.Pawn;

import java.util.Map;

public class GameService {

    private CheckersLogic checkersLogic = new CheckersLogic();
    private CheckersConsoleBoard board = new CheckersConsoleBoard();


    public Map<Integer,String> createBoard() {
        return board.createBoard();
    }

    public Pawn setCurrentPawnMove(int step) {
        return checkersLogic.setCurrentPawn(step);
    }

    public Map<Integer,String> moveValidator(Map boardMap, Pawn currentPawn, String currentPawnPosition, String nextPawnPosition) {
        return checkersLogic.moveValidator(boardMap, currentPawn, currentPawnPosition, nextPawnPosition);
    }

    public boolean isNotFinished(Map<Integer,String> boardMap) {
        return checkersLogic.isNotFinished(boardMap);
    }

    public Pawn winner(Map<Integer,String> boardMap) {
        return checkersLogic.winner(boardMap);
    }

    public int getGameStep() {
        return checkersLogic.getStep();
    }

    public void setGameStep(int step) {
        checkersLogic.setStep(step);
    }

    public boolean isMoveCorrect() {
        return checkersLogic.isMoveCorrect();
    }

}
