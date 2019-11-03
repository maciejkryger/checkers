package games.checkers.repository;

import games.checkers.console.CheckersConsoleBoard;
import games.checkers.console.CheckersConsoleMessages;
import games.checkers.model.*;

import java.util.Map;
import java.util.Scanner;

import static games.checkers.model.Board.EMPTY_FIELD;

public class CheckersLogic {


    private CheckersConsoleBoard board;
    private String currentPawnPosition;
    private int currentPawnPositionIndex;
    private String nextPawnPosition;
    private int nextPawnPositionIndex;
    private Pawn currentPawn;
    private Pawn xPawn;
    private Pawn xLadyPawn;
    private Pawn oPawn;
    private Pawn oLadyPawn;
    private Map boardMap;
    private BoardPosition boardPosition;
    private int step;
    private CheckersConsoleMessages info;
    private String language = "ENG";
    private boolean isMoveCorrect;


    public CheckersLogic() {
        xPawn = new XPawn();
        oPawn = new OPawn();
        xLadyPawn = new XLadyPawn();
        oLadyPawn = new OLadyPawn();
        isMoveCorrect = true;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public boolean isMoveCorrect() {
        return isMoveCorrect;
    }


    public void start() {
        step = 0;
        board = new CheckersConsoleBoard();
        info = new CheckersConsoleMessages();
        boardMap = board.createBoard();
        language = languageInput();
        do {
            board.gameNameBoard(language);
            board.displayBoard(boardMap);
            setCurrentPawn(step);
            moveInput();
            moveValidator(boardMap, currentPawn, currentPawnPosition, nextPawnPosition);
        } while (isNotFinished(boardMap));
        info.theWinnerIs(winner(boardMap), language);
    }

    public String languageInput() {
        Scanner languageScanner = new Scanner(System.in);
        info.chooseLanguage();
        return languageScanner.next().toUpperCase();
    }

    public Pawn setCurrentPawn(int step) {
        if (step == 0 || step % 2 == 0) {
            currentPawn = oPawn;
        } else {
            currentPawn = xPawn;
        }
        return currentPawn;
    }


    public void moveInput() {
        Scanner moveScanner = new Scanner(System.in);
        info.putPawnToMove(language);
        currentPawnPosition = moveScanner.next();
        info.wherePawnToMove(language);
        nextPawnPosition = moveScanner.next();
    }

    public void movePawn(Map<Integer,String> boardMap, int currentPawnPositionIndex, int nextPawnPositionIndex) {
        String signToMove = boardMap.get(currentPawnPositionIndex);
        boardMap.replace(currentPawnPositionIndex, EMPTY_FIELD);
        boardMap.replace(nextPawnPositionIndex, signToMove);

    }


    public int changePawnPositionToPawnIndex(String pawnPosition) {
        boardPosition = new BoardPosition();
        Map boardPositionMap = boardPosition.getBoardPositionMap();
        int pawnPositionIndex = 0;
        for (int i = 0; i < boardPositionMap.size(); i++) {
            if (pawnPosition.equals(boardPositionMap.get(i))) {
                pawnPositionIndex = i;
            }
        }
        return pawnPositionIndex;
    }

    public Map moveValidator(Map<Integer,String> boardMap, Pawn currentPawn, String currentPawnPosition, String nextPawnPosition) {
        currentPawnPositionIndex = changePawnPositionToPawnIndex(currentPawnPosition);
        nextPawnPositionIndex = changePawnPositionToPawnIndex(nextPawnPosition);
        if (areFieldsForPawnValidated(boardMap, currentPawn, currentPawnPositionIndex, nextPawnPositionIndex)
                && (captureThePawn(boardMap, currentPawn, currentPawnPositionIndex, nextPawnPositionIndex)
                || movePawnWithRules(boardMap, currentPawn, currentPawnPositionIndex, nextPawnPositionIndex))) {
            changePawnToLadyPawn(boardMap, currentPawn);
            isMoveCorrect = true;
            step++;
            return boardMap;
        } else {
            isMoveCorrect = false;
//            info.wrongMove(language);
        }
        return boardMap;
    }

    public boolean areFieldsForPawnValidated(Map<Integer,String> boardMap, Pawn currentPawn, int currentPawnPositionIndex, int nextPawnPositionIndex) {
        if (boardMap.get(currentPawnPositionIndex) == null || boardMap.get(nextPawnPositionIndex) == null) {
//            info.wrongField(language);
            return false;
        }
        if (currentPawn.getSign() == xPawn.getSign()) {
            return isCorrectPawnInFields(boardMap, currentPawn, currentPawnPositionIndex, nextPawnPositionIndex, xLadyPawn, oPawn, oLadyPawn);
        } else if (currentPawn.getSign() == oPawn.getSign()) {
            return isCorrectPawnInFields(boardMap, currentPawn, currentPawnPositionIndex, nextPawnPositionIndex, oLadyPawn, xPawn, xLadyPawn);
        } else {
            return false;
        }
    }

    private boolean isCorrectPawnInFields(Map<Integer,String> boardMap, Pawn currentPawn, int currentPawnPositionIndex, int nextPawnPositionIndex, Pawn LadyPawn, Pawn opponentPawn, Pawn opponentLadyPawn) {
        if ((boardMap.get(currentPawnPositionIndex) == currentPawn.getSign()
                || boardMap.get(currentPawnPositionIndex) == LadyPawn.getSign())
                && boardMap.get(nextPawnPositionIndex) != null
                && boardMap.get(nextPawnPositionIndex) != opponentPawn.getSign()
                && boardMap.get(nextPawnPositionIndex) != opponentLadyPawn.getSign()
                && boardMap.get(nextPawnPositionIndex) != boardMap.get(currentPawnPositionIndex)) {
            return true;
        } else {
//                info.wrongField(language);
            return false;
        }
    }

    public boolean movePawnWithRules(Map<Integer,String> boardMap, Pawn currentPawn, int currentPawnPositionIndex, int nextPawnPositionIndex) {
        if (currentPawn.getSign() == xPawn.getSign() && (boardMap.get(currentPawnPositionIndex) != xLadyPawn.getSign())) {
            if (
                    nextPawnPositionIndex > currentPawnPositionIndex
                            && (currentPawnPositionIndex + 7 == nextPawnPositionIndex
                            || currentPawnPositionIndex + 9 == nextPawnPositionIndex)
                            && ((boardMap.get(currentPawnPositionIndex + 7) == EMPTY_FIELD
                            || (boardMap.get(currentPawnPositionIndex + 9) == EMPTY_FIELD)))
            ) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                return true;
            } else {
                return false;
            }
        } else if (currentPawn.getSign() == oPawn.getSign() && boardMap.get(currentPawnPositionIndex) != oLadyPawn.getSign()) {
            if (
                    nextPawnPositionIndex < currentPawnPositionIndex
                            && (currentPawnPositionIndex - 7 == nextPawnPositionIndex
                            || currentPawnPositionIndex - 9 == nextPawnPositionIndex)
                            && ((boardMap.get(currentPawnPositionIndex - 7) == EMPTY_FIELD
                            || (boardMap.get(currentPawnPositionIndex - 9) == EMPTY_FIELD)))
            ) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                return true;
            } else {
                return false;
            }
        } else if ((boardMap.get(currentPawnPositionIndex) == xLadyPawn.getSign()) || boardMap.get(currentPawnPositionIndex) == oLadyPawn.getSign()) {
            if (((currentPawnPositionIndex + 7 == nextPawnPositionIndex
                    || currentPawnPositionIndex + 9 == nextPawnPositionIndex)
                    || (currentPawnPositionIndex - 7 == nextPawnPositionIndex
                    || currentPawnPositionIndex - 9 == nextPawnPositionIndex))
                    && ((boardMap.get(currentPawnPositionIndex + 7) == EMPTY_FIELD)
                    || (boardMap.get(currentPawnPositionIndex + 9) == EMPTY_FIELD)
                    || (boardMap.get(currentPawnPositionIndex - 7) == EMPTY_FIELD)
                    || (boardMap.get(currentPawnPositionIndex - 9) == EMPTY_FIELD))) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean captureThePawn(Map<Integer,String> boardMap, Pawn currentPawn, int currentPawnPositionIndex, int nextPawnPositionIndex) {
        if (currentPawn.getSign() == xPawn.getSign() && boardMap.get(currentPawnPositionIndex)!= xLadyPawn.getSign()) {
            if ((boardMap.get(currentPawnPositionIndex + 9) == oPawn.getSign() || boardMap.get(currentPawnPositionIndex + 9) == oLadyPawn.getSign())
                    && boardMap.get(currentPawnPositionIndex + 18) == EMPTY_FIELD) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                boardMap.replace(nextPawnPositionIndex - 9, EMPTY_FIELD);
                return true;
            } else if ((boardMap.get(currentPawnPositionIndex + 7) == oPawn.getSign() || boardMap.get(currentPawnPositionIndex + 7) == oLadyPawn.getSign())
                    && boardMap.get(currentPawnPositionIndex + 14) == EMPTY_FIELD) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                boardMap.replace(nextPawnPositionIndex - 7, EMPTY_FIELD);
                return true;
            } else {
                return false;
            }
        } else if (currentPawn.getSign() == oPawn.getSign() && boardMap.get(currentPawnPositionIndex) != oLadyPawn.getSign()) {
            if ((boardMap.get(currentPawnPositionIndex - 9) == xPawn.getSign() || boardMap.get(currentPawnPositionIndex - 9) == xLadyPawn.getSign())
                    && boardMap.get(currentPawnPositionIndex - 18) == EMPTY_FIELD) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                boardMap.replace(nextPawnPositionIndex + 9, EMPTY_FIELD);
                return true;
            } else if ((boardMap.get(currentPawnPositionIndex - 7) == xPawn.getSign()|| boardMap.get(currentPawnPositionIndex - 7) == xLadyPawn.getSign())
                    && boardMap.get(currentPawnPositionIndex - 14) == EMPTY_FIELD) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                boardMap.replace(nextPawnPositionIndex + 7, EMPTY_FIELD);
                return true;
            } else {
                return false;
            }
        } else if (boardMap.get(currentPawnPositionIndex)== xLadyPawn.getSign()) {
            if ((boardMap.get(currentPawnPositionIndex + 9) == oPawn.getSign() || boardMap.get(currentPawnPositionIndex + 9) == oLadyPawn.getSign())
                    && boardMap.get(currentPawnPositionIndex + 18) == EMPTY_FIELD) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                boardMap.replace(nextPawnPositionIndex - 9, EMPTY_FIELD);
                return true;
            } else if ((boardMap.get(currentPawnPositionIndex + 7) == oPawn.getSign() || boardMap.get(currentPawnPositionIndex + 9) == oLadyPawn.getSign())
                    && boardMap.get(currentPawnPositionIndex + 14) == EMPTY_FIELD) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                boardMap.replace(nextPawnPositionIndex - 7, EMPTY_FIELD);
                return true;
            } else if ((boardMap.get(currentPawnPositionIndex - 9) == oPawn.getSign() || boardMap.get(currentPawnPositionIndex + 9) == oLadyPawn.getSign())
                    && boardMap.get(currentPawnPositionIndex - 18) == EMPTY_FIELD) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                boardMap.replace(nextPawnPositionIndex + 9, EMPTY_FIELD);
                return true;
            } else if ((boardMap.get(currentPawnPositionIndex - 7) == oPawn.getSign() || boardMap.get(currentPawnPositionIndex + 9) == oLadyPawn.getSign())
                    && boardMap.get(currentPawnPositionIndex - 14) == EMPTY_FIELD) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                boardMap.replace(nextPawnPositionIndex + 7, EMPTY_FIELD);
                return true;
            } else {
                return false;
            }
        } else if (boardMap.get(currentPawnPositionIndex) == oLadyPawn.getSign()) {
            if ((boardMap.get(currentPawnPositionIndex + 9) == xPawn.getSign() ||boardMap.get(currentPawnPositionIndex + 9) == xLadyPawn.getSign())
                    && boardMap.get(currentPawnPositionIndex + 18) == EMPTY_FIELD) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                boardMap.replace(nextPawnPositionIndex - 9, EMPTY_FIELD);
                return true;
            } else if ((boardMap.get(currentPawnPositionIndex + 7) == xPawn.getSign()||boardMap.get(currentPawnPositionIndex + 7) == xLadyPawn.getSign())
                    && boardMap.get(currentPawnPositionIndex + 14) == EMPTY_FIELD) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                boardMap.replace(nextPawnPositionIndex - 7, EMPTY_FIELD);
                return true;
            } else if ((boardMap.get(currentPawnPositionIndex - 9) == xPawn.getSign() || boardMap.get(currentPawnPositionIndex - 9) == xLadyPawn.getSign())
                    && boardMap.get(currentPawnPositionIndex - 18) == EMPTY_FIELD) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                boardMap.replace(nextPawnPositionIndex + 9, EMPTY_FIELD);
                return true;
            } else if ((boardMap.get(currentPawnPositionIndex - 7) == xPawn.getSign() || boardMap.get(currentPawnPositionIndex -7) == xLadyPawn.getSign())
                    && boardMap.get(currentPawnPositionIndex - 14) == EMPTY_FIELD) {
                movePawn(boardMap, currentPawnPositionIndex, nextPawnPositionIndex);
                boardMap.replace(nextPawnPositionIndex + 7, EMPTY_FIELD);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

        public void changePawnToLadyPawn (Map<Integer,String> boardMap, Pawn currentPawn){
            if (currentPawn.getSign() == xPawn.getSign()) {
                if (boardMap.get(56) == currentPawn.getSign()) {
                    boardMap.replace(56, xLadyPawn.getSign());
                } else if (boardMap.get(58) == currentPawn.getSign()) {
                    boardMap.replace(58, xLadyPawn.getSign());
                } else if (boardMap.get(60) == currentPawn.getSign()) {
                    boardMap.replace(60, xLadyPawn.getSign());
                } else if (boardMap.get(62) == currentPawn.getSign()) {
                    boardMap.replace(62, xLadyPawn.getSign());
                }
            } else if (currentPawn.getSign() == oPawn.getSign()) {
                if (boardMap.get(1) == currentPawn.getSign()) {
                    boardMap.replace(1, oLadyPawn.getSign());
                } else if (boardMap.get(3) == currentPawn.getSign()) {
                    boardMap.replace(3, oLadyPawn.getSign());
                } else if (boardMap.get(5) == currentPawn.getSign()) {
                    boardMap.replace(5, oLadyPawn.getSign());
                } else if (boardMap.get(7) == currentPawn.getSign()) {
                    boardMap.replace(7, oLadyPawn.getSign());
                }
            }
        }

        public boolean isNotFinished (Map<Integer,String> boardMap){
            int resultX = 0;
            int resultO = 0;
            for (int i = 0; i < boardMap.size(); i++) {
                if (boardMap.get(i) == xPawn.getSign() || boardMap.get(i) == xLadyPawn.getSign()) {
                    resultX++;
                } else if (boardMap.get(i) == oPawn.getSign() || boardMap.get(i) == oLadyPawn.getSign()) {
                    resultO++;
                }
            }
            if (resultX == 0 || resultO == 0) {
                System.out.println("resultX: " + resultX);
                System.out.println("resultO: " + resultO);
                return false;
            } else {
                return true;
            }
        }

        public Pawn winner (Map<Integer,String> boardMap){
            int resultX = 0;
            int resultO = 0;
            for (int i = 0; i < boardMap.size() * 2; i++) {
                if (boardMap.get(i) == new XPawn().getSign() || boardMap.get(i) == new XLadyPawn().getSign()) {
                    resultX++;
                } else if (boardMap.get(i) == new OPawn().getSign() || boardMap.get(i) == new OLadyPawn().getSign()) {
                    resultO++;
                }
            }
            if (resultX > resultO) {
                return new XPawn();
            } else {
                return new OPawn();
            }
        }
    }
