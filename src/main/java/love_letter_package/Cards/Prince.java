package love_letter_package.Cards;

import love_letter_package.Player;

public class Prince extends Card{


    public Prince() {
        super("Prinz", 5, "Suche einen Spieler aus. Dieser legt seine Hand ab und" +
                " zeiht eine neue Karte");
    }


    @Override
    public Player[] effectAction(Player[] playerArray, int indexOfPlayer) {
        return playerArray;
    }
}
