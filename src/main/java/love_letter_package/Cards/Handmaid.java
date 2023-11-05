package love_letter_package.Cards;

import love_letter_package.Player;

public class Handmaid extends Card{
    public Handmaid() {
        super("Zofe", 4, "Du bist bis zu deinem nächsten " +
                "Zug geschützt.");
    }


    @Override
    public Player[] effectAction(Player[] playerArray, int indexOfPlayer) {
        playerArray[indexOfPlayer].setProtection(true);
        return playerArray;
    }
}
