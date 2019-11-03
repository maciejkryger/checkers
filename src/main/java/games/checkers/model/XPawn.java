package games.checkers.model;

public class XPawn extends Pawn {

    private String name;
    private String sign;
    private int capturedPawns=0;

    public XPawn() {
    }

    @Override
    public String getName(String language) {
        if(language.equals("PL")){
            return "Krzyżyk";
        }else{
            return "Cross";
        }
    }

    @Override
    public String getSign() {
        return "X";
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
        this.capturedPawns=capturedPawns;
    }
}
