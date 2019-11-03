package games.checkers.console;


import games.checkers.repository.CheckersLogic;

public class CheckersConsoleMain {


    public static void main(String[] args) {
        CheckersLogic game = new CheckersLogic();
        game.start();
    }
}
