package love_letter_package;

import love_letter_package.Cards.*;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int score;
    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addCardToHand (Card card){
        this.hand.add(card);
    }

    public String getHandAsString() {
        String returnString = "";
        if (!hand.isEmpty()) {
            for (int i = 0; i < hand.size(); i++) {
                Card currentCard = hand.get(i);
                returnString += "Karte " + (i + 1) + ": " + currentCard.getName() + " Nähe zur Prinzessin: " + currentCard.getClosenessToPrincess()
                        + "\nEffect: " + currentCard.getEffect();
            }
        }
        return returnString;
    }
    public int getScore() {
        return score;
    }
}

