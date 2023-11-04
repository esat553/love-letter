package love_letter_package;

import love_letter_package.Cards.*;
import java.util.ArrayList;
import java.util.Random;

public class CardDeck {
    private ArrayList<Card> cardDeck;

    public CardDeck() {
        this.cardDeck = newCardDeck();
    }

    private ArrayList<Card> newCardDeck() {
        Card[] unshuffledList = {
                new Princess(), new Countess(), new King(), new Prince(),
                new Prince(), new Handmaid(), new Handmaid(), new Baron(),
                new Baron(), new Priest(), new Priest(), new Guard(),
                new Guard(), new Guard(), new Guard(), new Guard()
        };
        ArrayList<Card> newCardDeck = new ArrayList<Card>();
        Random randomNumber = new Random();
        for (int i = 15; i >= 0; i--) {
            int zufallsZahl = randomNumber.nextInt(i + 1);
            newCardDeck.add(unshuffledList[zufallsZahl]);
        }

        return newCardDeck;
    }


    public boolean isEmpty() {
        return cardDeck.isEmpty();
    }

    public void getNewCardFromDeck(Player player){
        Card newCard = cardDeck.get(0);
        cardDeck.remove(0);
        player.addCardToHand(newCard);
        System.out.println("Sie zogen aus dem Stapel: " + newCard.getName());
    }

    public Card drawCardFromDeck(){
        Card drewedCard = cardDeck.get(0);
        cardDeck.remove(0);
        return drewedCard;
    }

}
