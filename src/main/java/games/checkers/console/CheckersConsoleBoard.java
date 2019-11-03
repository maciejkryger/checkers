package games.checkers.console;


import games.checkers.model.OPawn;
import games.checkers.model.XPawn;

import java.util.HashMap;
import java.util.Map;


public class CheckersConsoleBoard {

    private Map<Integer, String> boardGame = new HashMap<>();
    private Map<Integer, String> boardPositionMap = new HashMap<>();
    public int[] initialPawnLocationTab = {1, 3, 5, 7, 8, 10, 12, 14, 17, 19, 21, 23, 24, 26, 28, 30, 33, 35, 37, 39, 40, 42, 44, 46, 49, 51, 53, 55, 56, 58, 60, 62};
    public final static String EMPTY_FIELD = " ";


    public CheckersConsoleBoard() {
        boardGame.clear();
    }

    public void gameNameBoard(String language) {
        StringBuilder builder = new StringBuilder();
        if (language.equals("PL")) {
            builder
                    .append("---------------------------------------------------------------------\n")
                    .append("|                  --->        WARCABY       <---                   |\n")
                    .append("---------------------------------------------------------------------");
        } else {
            builder
                    .append("---------------------------------------------------------------------\n")
                    .append("|                  --->     CHECKER GAME     <---                   |\n")
                    .append("---------------------------------------------------------------------");
        }
        System.out.println(builder);
    }

    public Map<Integer, String> createBoard() {
        for (int i = 0; i < 64; i++) {
            boardGame.put(i,null);
            for (int j = 0; j < initialPawnLocationTab.length; j++) {
                if (i < 24 && i == initialPawnLocationTab[j]) {
                    boardGame.replace(i, new XPawn().getSign());
                } else if (i >= 24 && i < 40 && i == initialPawnLocationTab[j]) {
                    boardGame.replace(i, EMPTY_FIELD);
                } else if (i >= 40 && i < 64 && i == initialPawnLocationTab[j]) {
                    boardGame.replace(i, new OPawn().getSign());
                }
            }
        }
        return boardGame;
    }


    public void displayBoard(Map<Integer, String> boardGame) {
        StringBuilder boardBuilder = new StringBuilder();
        boardBuilder
                .append("      a       b       c       d       e       f       g       h\n")
                .append("   ---------------------------------------------------------------\n")
                .append("1 |       |   " + boardGame.get(1) + "   |       |   " + boardGame.get(3) + "   |       |   " + boardGame.get(5) + "   |       |   " + boardGame.get(7) + "   | 1\n")
                .append("   ---------------------------------------------------------------\n")
                .append("2 |   " + boardGame.get(8) + "   |       |   " + boardGame.get(10) + "   |       |   " + boardGame.get(12) + "   |       |   " + boardGame.get(14) + "   |       | 2\n")
                .append("   ---------------------------------------------------------------\n")
                .append("3 |       |   " + boardGame.get(17) + "   |       |   " + boardGame.get(19) + "   |       |   " + boardGame.get(21) + "   |       |   " + boardGame.get(23) + "   | 3\n")
                .append("   ---------------------------------------------------------------\n")
                .append("4 |   " + boardGame.get(24) + "   |       |   " + boardGame.get(26) + "   |       |   " + boardGame.get(28) + "   |       |   " + boardGame.get(30) + "   |       | 4\n")
                .append("   ---------------------------------------------------------------\n")
                .append("5 |       |   " + boardGame.get(33) + "   |       |   " + boardGame.get(35) + "   |       |   " + boardGame.get(37) + "   |       |   " + boardGame.get(39) + "   | 5\n")
                .append("   ---------------------------------------------------------------\n")
                .append("6 |   " + boardGame.get(40) + "   |       |   " + boardGame.get(42) + "   |       |   " + boardGame.get(44) + "   |       |   " + boardGame.get(46) + "   |       | 6\n")
                .append("   ---------------------------------------------------------------\n")
                .append("7 |       |   " + boardGame.get(49) + "   |       |   " + boardGame.get(51) + "   |       |   " + boardGame.get(53) + "   |       |   " + boardGame.get(55) + "   | 7\n")
                .append("   ---------------------------------------------------------------\n")
                .append("8 |   " + boardGame.get(56) +"   |       |   " + boardGame.get(58) + "   |       |   " + boardGame.get(60) + "   |       |   " + boardGame.get(62) + "   |       | 8\n")
//                .append("  |       |       |       |       |       |       |       |       |\n")
                .append("   ---------------------------------------------------------------\n")
                .append("      a       b       c       d       e       f       g       h\n");
        System.out.print(boardBuilder);
    }

    public Map<Integer, String> createBoardPositionMap() {
        int counter1 = 0;
        char signPoziom = 'a';
        int signPion = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardPositionMap.put(counter1, String.valueOf(String.valueOf(signPoziom) + "" + signPion));
                signPoziom++;
                counter1++;
            }
            signPoziom = 'a';
            signPion++;
        }
        return boardPositionMap;
    }

    protected void displayHelpBoardMap(){
        int counter2 = 0;
        for (int i = 0; i <8; i++) {
            for (int j = 0; j < 8; j++) {
                String result;
                if(boardGame.get(counter2) ==null){
                    result="#";
                }else {
                    result=boardGame.get(counter2);
                };
                System.out.print("|"+counter2+result);
                counter2++;
            }
            System.out.println("|");
        }
    }
}
