package love_letter_package.Cards;

import love_letter_package.Player;

public class Princess extends Card{

    public Princess() {
        super ("Prinzessin Annette", 8, "Du verlierst wenn du diese Karte ablegst");
    }

    @Override
    public Player[] effectAction(Player[] playerArray, int indexOfPlayer) {
        playerArray[indexOfPlayer].setOutOfRound(true);
        return playerArray;
    }


}
