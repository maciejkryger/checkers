package games.checkers.model;

public class XLadyPawn extends XPawn {

    private String name;
    private String sign;
    private int capturedPawns;

    public XLadyPawn() {
        capturedPawns=0;
    }

    @Override
    public String getName(String language) {
        if(language.equals("PL")){
            return "Damka Krzyżyk";
        }else{
            return "Lady Cross";
        }
    }

    @Override
    public String getSign() {
        return "K";
    }

    @Override
    public String getFamilyPawnSign() {
        return "X";
    }

    @Override
    public String getType(){
        return "Lady";
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
