package games.checkers.repository;

import games.checkers.model.Board;
import games.checkers.model.OPawn;
import games.checkers.model.Pawn;
import games.checkers.model.XPawn;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;


public class CheckersLogicTest {

    CheckersLogic checkersLogic = new CheckersLogic();

    @Test
    public void shouldSetCurrentPawnToO() {

        //give
        int step=0;

        //when
        Pawn currentPawn = checkersLogic.setCurrentPawn(step);

        //then
        Assert.assertEquals(new OPawn().getSign(), currentPawn.getSign());
    }

    @Test
    public void shouldSetCurrentPawnToX() {

        //give
        int step=1;

        //when
        Pawn currentPawn = checkersLogic.setCurrentPawn(step);

        //then
        Assert.assertEquals(new XPawn().getSign(), currentPawn.getSign());
    }


    @Test
    public void shouldMovePawn() {

        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        int currentPositionIndex = 1;
        int nextPositionIndex = 3;
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);

        //when
        checkersLogic.movePawn(boardMap, currentPositionIndex, nextPositionIndex);
        String after = boardMap.get(nextPositionIndex);
        System.out.println("after: " + after);
        String current2 = boardMap.get(currentPositionIndex);
        System.out.println("current after: " + current2);

        //then
        assertEquals("X", after);
        assertEquals(" ", current2);
    }


    @Test
    public void changePawnPositionToPawnIndex() {

        //when
        int pierwsza = checkersLogic.changePawnPositionToPawnIndex("a1");
        int ostatnia = checkersLogic.changePawnPositionToPawnIndex("h8");
        int dziesiata = checkersLogic.changePawnPositionToPawnIndex("c2");

        //then
        assertEquals(0, pierwsza);
        assertEquals(63, ostatnia);
        assertEquals(10, dziesiata);
    }

    @Test
    public void shouldGetMoveByMoveValidator() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        String currentPosition = "b3";
        String nextPosition = "c4";

        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);

        //when
        boardMap = checkersLogic.moveValidator(boardMap, pawn, currentPosition, nextPosition);
        String afterNext = boardMap.get(nextPositionIndex);
        String afterCurrent = boardMap.get(currentPositionIndex);

        //then
        assertEquals("pozycja mapy current powinna być równa pozycji afterNext", current, afterNext);
        assertEquals("pozycja mapy afterCurrent powinna być pusta", " ", afterCurrent);
    }


    @Test
    public void shouldDontPassMoveByMoveValidator() {

        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        String currentPosition = "b3";
        String nextPosition = "d5";

        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boardMap = checkersLogic.moveValidator(boardMap, pawn, currentPosition, nextPosition);
        String afterNext = boardMap.get(nextPositionIndex);
        String afterCurrent = boardMap.get(currentPositionIndex);

        //then
        assertNotEquals("pozycja mapy current nie powinna być równa pozycji afterNext", current, afterNext);
        assertNotEquals("pozycja mapy afterCurrent nie powinna być pusta", " ", afterCurrent);
        assertEquals("current jest równie afterCurrent", current, afterCurrent);
        assertEquals("next jest równie afternext", next, afterNext);
    }

    @Test
    public void shouldDontPassMoveByMoveValidatorWhenNextIsNull() {

        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        String currentPosition = "b3";
        String nextPosition = "c5";

        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boardMap = checkersLogic.moveValidator(boardMap, pawn, currentPosition, nextPosition);
        String afterNext = boardMap.get(nextPositionIndex);
        String afterCurrent = boardMap.get(currentPositionIndex);

        //then
        assertNotEquals("pozycja mapy current nie powinna być równa pozycji afterNext", current, afterNext);
        assertNotEquals("pozycja mapy afterCurrent nie powinna być pusta", " ", afterCurrent);
        assertEquals("current jest równie afterCurrent", current, afterCurrent);
        assertEquals("next jest równie afternext", next, afterNext);
    }

    @Test
    public void shouldDontPassMoveByMoveValidatorWhenNextIsAnyPawn() {

        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        String currentPosition = "a2";
        String nextPosition = "b3";

        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boardMap = checkersLogic.moveValidator(boardMap, pawn, currentPosition, nextPosition);
        String afterNext = boardMap.get(nextPositionIndex);
        System.out.println("AfterNext: " + afterNext);
        String afterCurrent = boardMap.get(currentPositionIndex);
        System.out.println("AfterCurrent: " + afterCurrent);

        //then
        assertNotEquals("pozycja mapy afterCurrent nie powinna być pusta", " ", afterCurrent);
        assertEquals("current jest równa afterCurrent", current, afterCurrent);
        assertEquals("next jest równy afternext", next, afterNext);
    }


    @Test
    public void shouldReturnTrueWhenAreFieldsForPawnValidated() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        String currentPosition = "b3";
        String nextPosition = "c4";

        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boolean result = checkersLogic.areFieldsForPawnValidated(boardMap, pawn, currentPositionIndex, nextPositionIndex);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalsePawnInFieldChecker() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        String currentPosition = "b3";
        boardMap.replace(26, "O");
        String nextPosition = "c4";

        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boolean result = checkersLogic.areFieldsForPawnValidated(boardMap, pawn, currentPositionIndex, nextPositionIndex);
        System.out.println(result);

        assertFalse(result);

    }

    @Test
    public void rulesForMove() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        String currentPosition = "b3";
        String nextPosition = "c5";
        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boolean result = checkersLogic.movePawnWithRules(boardMap, pawn, currentPositionIndex, nextPositionIndex);

        //then
        assertFalse(result);
    }

    @Test
    public void rulesForMoveForXLady() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        String currentPosition = "b3";
        boardMap.replace(17,"K");
        String nextPosition = "c4";
        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boolean result = checkersLogic.movePawnWithRules(boardMap, pawn, currentPositionIndex, nextPositionIndex);

        //then
        assertTrue(result);
    }

    @Test
    public void rulesForMoveForDLady() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new OPawn();
        String currentPosition = "b3";
        boardMap.replace(17,"D");
        String nextPosition = "c4";
        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boolean result = checkersLogic.movePawnWithRules(boardMap, pawn, currentPositionIndex, nextPositionIndex);

        //then
        assertTrue(result);
    }


    @Test
    public void shouldCaptureThePawn() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        String currentPosition = "b3";
        boardMap.replace(26,"O");
        String nextPosition = "d5";
        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String pawnToCapture = boardMap.get(26);
        System.out.println("Pawn to Capture: "+pawnToCapture);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boolean result = checkersLogic.captureThePawn(boardMap, pawn, currentPositionIndex, nextPositionIndex);
        System.out.println("result: "+ result);

        //then
        assertTrue(result);
    }



    @Test
    public void shouldntCaptureThePawn() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        String currentPosition = "b3";
        boardMap.replace(26,"X");
        String nextPosition = "d5";
        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String pawnToCapture = boardMap.get(26);
        System.out.println("Pawn to Capture: "+pawnToCapture);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boolean result = checkersLogic.captureThePawn(boardMap, pawn, currentPositionIndex, nextPositionIndex);
        System.out.println("result: "+ result);

        //then
        assertFalse(result);
    }

    @Test
    public void shouldCaptureThePawnWhenLady() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        boardMap.replace(17,"K");
        String currentPosition = "b3";
        boardMap.replace(26,"O");
        String nextPosition = "d5";
        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String pawnToCapture = boardMap.get(26);
        System.out.println("Pawn to Capture: "+pawnToCapture);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boolean result = checkersLogic.captureThePawn(boardMap, pawn, currentPositionIndex, nextPositionIndex);
        System.out.println("result: "+ result);

        //then
        assertTrue(result);
    }

    @Test
    public void shouldCaptureThePawnWhenLadyCaptureLady() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        boardMap.replace(17,"K");
        String currentPosition = "b3";
        boardMap.replace(26,"D");
        String nextPosition = "d5";
        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String pawnToCapture = boardMap.get(26);
        System.out.println("Pawn to Capture: "+pawnToCapture);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boolean result = checkersLogic.captureThePawn(boardMap, pawn, currentPositionIndex, nextPositionIndex);
        System.out.println("result: "+ result);

        //then
        assertTrue(result);
    }

    @Test
    public void shouldCaptureThePawnWhenPawnCaptureLady() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        boardMap.replace(17,"K");
        String currentPosition = "b3";
        boardMap.replace(26,"O");
        String nextPosition = "d5";
        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String pawnToCapture = boardMap.get(26);
        System.out.println("Pawn to Capture: "+pawnToCapture);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boolean result = checkersLogic.captureThePawn(boardMap, pawn, currentPositionIndex, nextPositionIndex);
        System.out.println("result: "+ result);

        //then
        assertTrue(result);
    }

    @Test
    public void shouldCaptureThePawnWhenLadyPawnCapturePawn() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn pawn = new XPawn();
        String currentPosition = "b3";
        boardMap.replace(26,"D");
        String nextPosition = "d5";
        int currentPositionIndex = checkersLogic.changePawnPositionToPawnIndex(currentPosition);
        int nextPositionIndex = checkersLogic.changePawnPositionToPawnIndex(nextPosition);
        String current = boardMap.get(currentPositionIndex);
        System.out.println("current: " + current);
        String pawnToCapture = boardMap.get(26);
        System.out.println("Pawn to Capture: "+pawnToCapture);
        String next = boardMap.get(nextPositionIndex);
        System.out.println("next: " + next);

        //when
        boolean result = checkersLogic.captureThePawn(boardMap, pawn, currentPositionIndex, nextPositionIndex);
        System.out.println("result: "+ result);

        //then
        assertTrue(result);
    }

    @Test
    public void changeXPawnToXLadyPawn() {

        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn currentPawn = new XPawn();
        String current = boardMap.get(62);
        boardMap.replace(62,"X");

        //when
        checkersLogic.changePawnToLadyPawn(boardMap,currentPawn);
        String next = boardMap.get(62);
        System.out.println("current: "+current);
        System.out.println("next: "+next);

        //then
        assertEquals("K",next);
    }


    @Test
    public void dontChangeOPawnToOLadyPawn() {

        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn currentPawn = new OPawn();
        String current = boardMap.get(62);
        boardMap.replace(62,"O");

        //when
        checkersLogic.changePawnToLadyPawn(boardMap,currentPawn);
        String next = boardMap.get(62);
        System.out.println("current: "+current);
        System.out.println("next: "+next);

        //then
        assertNotEquals("D",next);
    }

    @Test
    public void changeOPawnToOLadyPawn() {

        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn currentPawn = new OPawn();
        String current = boardMap.get(1);
        boardMap.replace(1,"O");

        //when
        checkersLogic.changePawnToLadyPawn(boardMap,currentPawn);
        String next = boardMap.get(1);
        System.out.println("current: "+current);
        System.out.println("next: "+next);

        //then
        assertEquals("D",next);
    }


    @Test
    public void dontChangeXPawnToXLadyPawn() {

        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        Pawn currentPawn = new OPawn();
        String current = boardMap.get(1);
        boardMap.replace(1,"X");

        //when
        checkersLogic.changePawnToLadyPawn(boardMap,currentPawn);
        String next = boardMap.get(1);
        System.out.println("current: "+current);
        System.out.println("next: "+next);

        //then
        assertNotEquals("K",next);
    }
    @Test
    public void shouldBeNotFinished() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();


        //when
        boolean isFinished = checkersLogic.isNotFinished(boardMap);
        System.out.println(isFinished);

        //then
        assertTrue(isFinished);
    }

    @Test
    public void shouldNotBeNotFinished() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        for (int i = 0; i < boardMap.size(); i++) {
            boardMap.replace(i, "X");
        }

        //when
        boolean isFinished = checkersLogic.isNotFinished(boardMap);
        System.out.println(isFinished);

        //then
        assertFalse(isFinished);
    }

    @Test
    public void winnerShouldBeX() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        for (int i = 0; i < boardMap.size(); i++) {
            boardMap.replace(i, "X");
        }

        //when
        Pawn winner = checkersLogic.winner(boardMap);
        System.out.println(winner.getSign());

        //then
        assertEquals("X", winner.getSign());

    }

    @Test
    public void winnerShouldBeO() {
        //given
        Board board = new Board();
        Map<Integer, String> boardMap = board.getBoard();
        for (int i = 0; i < boardMap.size(); i++) {
            boardMap.replace(i, "O");
        }

        //when
        Pawn winner = checkersLogic.winner(boardMap);
        System.out.println(winner.getSign());

        //then
        assertEquals("O", winner.getSign());

    }

}