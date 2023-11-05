package love_letter_package.Cards;

import love_letter_package.Player;

public class Countess extends Card{

    public Countess() {
        super("Gräfin", 7, "Wenn du zusätzlich König " +
                "oder Prinz auf der Hand hast, musst du die " +
                "Gräfin ausspielen.");
    }


    @Override
    public Player[] effectAction(Player[] playerArray, int indexOfPlayer) {
        return playerArray;
    }
}
