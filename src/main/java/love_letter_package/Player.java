package love_letter_package;

import love_letter_package.Cards.*;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int score;

    private boolean protection;

    private boolean outOfRound;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.score = 0;
        this.protection = false;
        this.outOfRound = false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public Card getCard(int index){
        return hand.get(index);
    }

    public int getScore() {
        return score;
    }

    public String getCardString(int index){
        return hand.get(index).getName();
    }

    public String getHandAsString() {
        String returnString = "";
        if (!hand.isEmpty()) {
            for (int i = 0; i < hand.size(); i++) {
                Card currentCard = hand.get(i);
                returnString += "Karte " + (i + 1) + ": " + currentCard.getName() + " Nähe zur Prinzessin: " + currentCard.getClosenessToPrincess()
                        + "\nEffect: " + currentCard.getEffect() +"\n\n";
            }
        }
        return returnString;
    }

    public boolean isOutOfRound() {
        return outOfRound;
    }

    public boolean hasProtection() {
        return protection;
    }

    // Zusätzlich Setter-Methoden:
    public void setOutOfRound(boolean outOfRound) {
        this.outOfRound = outOfRound;
    }

    public void setProtection(boolean protection) {
        this.protection = protection;
    }

    public void addCardToHand (Card card){
        this.hand.add(card);
    }

    public void removeCardFromHand(int index) {
        hand.remove(index);
    }

    public void increaseScore(){
        score++;
    }

    public void clearHand(){
        this.hand.clear();
    }

    public boolean playerHasCountessAndKingOrPrince() {
        boolean hasCountess = false;
        boolean hasKingOrPrince = false;

        for (Card card : hand) {
            if (card instanceof Countess) {
                hasCountess = true;
            }
            if (card instanceof King || card instanceof Prince) {
                hasKingOrPrince = true;
            }
        }

        return hasCountess && hasKingOrPrince;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
}

