import java.util.ArrayList;
import java.util.Random;

public class Game {
    int playerCount;
    private static ArrayList<Card> cardDeck;
    Spieler[] playerArray;


    public Game initializeGame(int playerCount){
        this.playerCount = playerCount;
        this.cardDeck = newCardDeck();
        this.playerArray = newPlayer();
    }

    public int getPlayerCount() {
        return playerCount;
    }

    private static ArrayList<Card> newCardDeck() {
        Card[] unshuffledList = new Card[16];
        ArrayList<Card> newCardDeck = new ArrayList<Card>();
        for (int i = 0; i < 15; i++) {
            unshuffledList[i] = new Card("Prinz", 7, "3 mal im Kreis rennen");
        }
        unshuffledList[15] = new Card("Random Dude", 0, "being normal");
        Random randomNumber = new Random();
        for (int i = 15; i >= 0; i--) {
            int zufallsZahl = randomNumber.nextInt(i + 1);
            newCardDeck.add(unshuffledList[zufallsZahl]);
        }

        return newCardDeck;

    }

}
