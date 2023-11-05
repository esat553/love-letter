package love_letter_package.Cards;

import love_letter_package.Player;

public abstract class Card {
    private String name;
    private int closenessToPrincess;
    private String effect;

    public Card (String name, int closenessToPrincess, String effect){
        this.name = name;
        this.closenessToPrincess = closenessToPrincess;
        this.effect = effect;
    }



    public String getName() {
        return name;
    }

    public int getClosenessToPrincess() {
        return closenessToPrincess;
    }

    public String getEffect() {
        return effect;
    }


    public String getDataAsString() {
        return (name + " has a Closenesslevel to the princess of " + closenessToPrincess + " and has the\n" +
                "Effect:" + effect);
    }

    public abstract Player[] effectAction(Player[] playerArray, int indexOfPlayer);

}
