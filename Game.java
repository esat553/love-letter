import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    int playerCount;
    private static ArrayList<Card> cardDeck;
    Player[] playerArray;

    //The game initializer
    public Game(String[] playerNames) {
        this.playerCount = playerNames.length;
        this.cardDeck = newCardDeck();
        this.playerArray = new Player[this.playerCount];
        for (int i = 0; i < this.playerCount; i++) {
            this.playerArray[i] = new Player(playerNames[i]);
        }
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

    public void startGame() {
        boolean gameRunning = true;
        Player currentPlayer = this.playerArray[0];
        System.out.println("Befehle können nun eingegeben werden");
        while (gameRunning) {
            while (!cardDeck.isEmpty()){

            }
        }


    }

    private static void scanForOptions(){
        String helpMessage ="Zur Auswahl stehen folgende Befehle:\n" +
                "\\start -      Ein neues Spiel starten\n" +
                "\\playCard -   Eine Karte Spielen\n" +
                "\\showHand -   Die Hand des aktuellen Spielers anzeigen\n" +
                "\\showScor -   Den Spielstand aller Spieler anzeigen\n";
        String nichtIngameMessage = "Dieser Befehl ist gerade nicht verfügbar du bist nicht in einem Spiel!";

        Scanner scanOptions = new Scanner(System.in);
        String input = scanOptions.nextLine();
        while (input.charAt(0) != '\\'){
            System.out.println("bitte gueltigen Befehl eingeben und nicht das '\\' vergessen!");
        }
            if (input.equals("\\help")) {
                System.out.println(helpMessage);
            }else if (input.equals("\\start")) {

            } else if (input.equals("\\playCard")){

            } else if (input.equals("\\showHand")) {

            } else if (input.equals("\\showScore")){

            } else {
                System.out.println("Faslche Eingabe!\n\nBitte gebe erneut einen Befehl ein");
        }

    }


}