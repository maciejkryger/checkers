package games.checkers.model;

public class OPawn extends Pawn {

    private String name;
    private String sign;
    private int capturedPawns = 0;

    public OPawn() {
    }

    @Override
    public String getName(String language) {
        if (language.equals("PL")) {
            return "Kółko";
        } else {
            return "Circle";
        }
    }

    @Override
    public String getSign() {
        return "O";
    }

    @Override
    public String getFamilyPawnSign() {
        return getSign();
    }

    @Override
    public String getType() {
        return "Pawn";
    }

    @Override
    public int getCapturedPawns() {
        return capturedPawns;
    }

    @Override
    public void setCapturePawns(int capturedPawns) {
        this.capturedPawns = capturedPawns;
    }

}
