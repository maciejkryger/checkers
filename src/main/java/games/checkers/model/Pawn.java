package games.checkers.model;

public abstract class Pawn {

    public abstract String getName(String language);
    public abstract String getSign();
    public abstract String getFamilyPawnSign();
    public abstract String getType();
    public abstract int getCapturedPawns();
    public abstract void setCapturePawns(int capturedPawns);

}
