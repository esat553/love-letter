/**
 * Das Kartendeck ist eine recht Simple Klasse und war ursprünglich in der Game Klasse enthalten,
 * jedoch ist mit der Steigenden Anzahl an Methoden die übersichtlichkeit des Codes schlechter gewoorden
 * deshalb wurde sie als eigene Klasse implementiert. Sie besteht hauptsächlich aus einer ArrayList, die
 * das Kartendeck darstellt.
 */
package love_letter_package;

import love_letter_package.Cards.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class CardDeck {
    private ArrayList<Card> cardDeck;

    public CardDeck() {
        this.cardDeck = newCardDeck();
    }

    //For readability Arrays is being used as a data structure for shuffling of the deck

    /**
     * Mithilfe dieser Methode wird das Kartendeck mit genau den Karten wie unten angeführt erstellt.
     * @return
     */
    private ArrayList<Card> newCardDeck() {
        // Erstellt ein Array von Card-Objekten
        Card[] cardsArray = {
                new Princess(), new Countess(), new King(), new Prince(),
                new Prince(), new Handmaid(), new Handmaid(), new Baron(),
                new Baron(), new Priest(), new Priest(), new Guard(),
                new Guard(), new Guard(), new Guard(), new Guard()
        };

        // Wandelt das Array in eine ArrayList um
        ArrayList<Card> unshuffledList = new ArrayList<Card>(Arrays.asList(cardsArray));

        ArrayList<Card> newCardDeck = new ArrayList<Card>();
        Random randomNumber = new Random();

        while (!unshuffledList.isEmpty()) {
            int zufallsZahl = randomNumber.nextInt(unshuffledList.size());
            newCardDeck.add(unshuffledList.remove(zufallsZahl));
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

    //Debugging function
    public void showCardDeck() {
        for (int i = 0; i < cardDeck.size(); i++) {
            System.out.println(cardDeck.get(i).getName());
        }
    }

}
