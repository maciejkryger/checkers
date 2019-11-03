package games.checkers.controller;

import games.checkers.console.CheckersConsoleMessages;
import games.checkers.model.*;
import games.checkers.service.GameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/************************************************************
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 ************************************************************/

@WebServlet(value = "")
public class GameController extends HttpServlet {

    private GameService gameService = new GameService();
    private String currentPawnPosition;
    private String nextPawnPosition;
    private Pawn currentPawn;
    private Map boardMap;
    private int step;
    private CheckersConsoleMessages info;
    private String language;
    private boolean isMoveCorrect;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        step = 0;
        System.out.println("step: " + step);
        gameService.setGameStep(step);
        currentPawn = gameService.setCurrentPawnMove(step);
        System.out.println(currentPawn.getSign());
        boardMap = gameService.createBoard();
        req.setAttribute("currentPawn", currentPawn.getSign());
        req.setAttribute("board", boardMap);
        getServletContext().getRequestDispatcher("/checkers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        currentPawnPosition = req.getParameter("moveFrom");
        nextPawnPosition = req.getParameter("moveWhere");
        do {
            boardMap = gameService.moveValidator(boardMap, currentPawn, currentPawnPosition, nextPawnPosition);
            step = gameService.getGameStep();
            currentPawn = gameService.setCurrentPawnMove(step);
            isMoveCorrect = gameService.isMoveCorrect();
            System.out.println("is move correct? " + isMoveCorrect);
            System.out.println("step: " + step);
            System.out.println(currentPawn.getSign());
            req.setAttribute("isMoveCorrect", isMoveCorrect);
            req.setAttribute("currentPawn", currentPawn.getSign());
            req.setAttribute("board", boardMap);
            getServletContext().getRequestDispatcher("/checkers.jsp").forward(req, resp);
        } while (gameService.isNotFinished(boardMap));
        req.setAttribute("winner", gameService.winner(boardMap).getFamilyPawnSign());
        getServletContext().getRequestDispatcher("/checkers.jsp").forward(req, resp);
    }
}
