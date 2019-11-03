package games.checkers.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 02.11.2019 13:25
 * *
 * @className: Board
 * *
 * *
 ******************************************************/
public class Board {

    private Map<Integer, String> board = new HashMap<>();
    private int[] initialPawnLocationTab = {1, 3, 5, 7, 8, 10, 12, 14, 17, 19, 21, 23, 24, 26, 28, 30, 33, 35, 37, 39, 40, 42, 44, 46, 49, 51, 53, 55, 56, 58, 60, 62};
    public final static String EMPTY_FIELD = " ";

    public Board() {
    this.board = createBoard();
    }



    public Map<Integer, String> createBoard() {
        for (int i = 0; i < 64; i++) {
            board.put(i, null);
            for (int j = 0; j < initialPawnLocationTab.length; j++) {
                if (i < 24 && i == initialPawnLocationTab[j]) {
                    board.replace(i, new XPawn().getSign());
                } else if (i >= 24 && i < 40 && i == initialPawnLocationTab[j]) {
                    board.replace(i, EMPTY_FIELD);
                } else if (i >= 40 && i < 64 && i == initialPawnLocationTab[j]) {
                    board.replace(i, new OPawn().getSign());
                }
            }
        }
        return board;
    }

    public Map<Integer, String> getBoard() {
        return board;
    }

    public void setBoard(Map<Integer, String> board) {
        this.board = board;
    }
}
