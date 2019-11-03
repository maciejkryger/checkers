package games.checkers.model;

public class OLadyPawn extends OPawn{

    private String name;
    private String sign;
    private int capturedPawns;

    @Override
    public String getName(String language) {
        if(language.equals("PL")){
            return "Damka Kółko";
        }else{
            return "Lady Circle";
        }
    }

    @Override
    public String getSign() {
        return "D";
    }

    @Override
    public String getFamilyPawnSign() {
        return "O";
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
