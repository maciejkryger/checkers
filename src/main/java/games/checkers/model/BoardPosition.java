package games.checkers.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 02.11.2019 13:30
 * *
 * @className: BoardPosition
 * *
 * *
 ******************************************************/
public class BoardPosition {

    private Map<Integer, String> boardPositionMap = new HashMap<>();

    public BoardPosition() {
        this.boardPositionMap=createBoardPositionMap();
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

    public Map<Integer, String> getBoardPositionMap() {
        return boardPositionMap;
    }
}
