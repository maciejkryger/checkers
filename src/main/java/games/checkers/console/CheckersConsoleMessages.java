package games.checkers.console;

import games.checkers.model.Pawn;

public class CheckersConsoleMessages {

    String language;

    public CheckersConsoleMessages() {
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void wrongMove(String language){
        if (language.equals("PL")){
            System.out.println("błędny ruch !");
        }else {
            System.out.println("wrong move !");
        }
    }


    public void putPawnToMove(String language) {
        if(language.equals("PL")){
            System.out.print("współrzędne pionka do przesunięcia: ");
        }else {
            System.out.print("point of pawn you move: ");
        }
    }

    public void wherePawnToMove(String language) {
        if(language.equals("PL")){
            System.out.print("współrzędne pola docelowego: ");
        }else {
            System.out.print("point where move: ");
        }
    }

    public void chooseLanguage() {
        System.out.print("Wybierz język(PL)/Chooce language(EN): ");
    }

    public void whoMoveIs(Pawn currentPawn, String language) {
        if(language.equals("PL")){
            System.out.print("Teraz jest ruch pionka ");
        }else {
            System.out.print("Now is the move of pawn ");
        }
        System.out.println(currentPawn.getName(language));
    }


    public void wrongField(String language) {
        if(language.equals("PL")){
            System.out.println("wybrałeś błędne pola, spróbuj ponownie !");
        }else {
            System.out.println("wrong fields, try again !");
        }
    }

    public void theWinnerIs(Pawn winner, String language) {
        if(language.equals("PL")){
            System.out.println("Koniec gry !");
            System.out.println("Wygrały pionki "+winner.getName(language));
        }else {
            System.out.println("Game over !");
            System.out.println("The winner is "+winner.getName(language));
        }
    }
}
