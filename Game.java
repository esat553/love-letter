import java.util.ArrayList;

public class Game {
    int playerCount;
    private ArrayList<Card> cardDeck;
    Spieler[] playerArray;


    public Game initializeGame(int playerCount){
        this.playerCount = playerCount;
        this.cardDeck = newCardeck();
    }

    public int getPlayerCount() {
        return playerCount;
    }

    private static void newCardeck(){
        Card[] unshuffledList = new Card[16];
    }

    public

}
