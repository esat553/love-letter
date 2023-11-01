package love_letter_package;
import love_letter_package.Cards.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static int playerCount;
    private static ArrayList<Card> cardDeck;
    static Player[] playerArray;

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

    public void startGame() {
        boolean gameRunning = true;
        int currentPlayerNumber = 0;
        int playersTurnNumber = 0;
        System.out.println("Jeder Spieler erhält nun eine Karte!");
        for (int i = 0; i < playerArray.length; i ++){
            Card cardFromCarddeck = getCardFromDeck(); //Hier wird eine Karte aus dem Kartendeck genommen
            playerArray[i].addCardToHand(cardFromCarddeck); //Diese Karte wird dann in die Karte des Spielers eingefügt.
            //Man könnte das auch kompakter schreiben, allerdings würde das der Codelesbarkeit schaden.
        }
        System.out.println("Befehle können nun eingegeben werden");
        while (gameRunning) {
            Player currentPlayer = this.playerArray[currentPlayerNumber];
            while (currentPlayerNumber == playersTurnNumber){
                System.out.println("Am Zug ist: "+ currentPlayer.getName());
                scanForOptions(currentPlayer, playersTurnNumber);
                if (cardDeck.isEmpty()) gameRunning =false;
            }
        }


    }

    private static void scanForOptions(Player currentPlayer, int playersTurnNumber){
        String helpMessage ="Zur Auswahl stehen folgende Befehle:\n" +
                "\\start       -   Ein neues Spiel starten\n" +
                "\\playCard    -   Eine Karte spielen. Der Spielzug des aktuellen Spielers wird dadurch beendet\n" +
                "\\showHand    -   Die Hand des aktuellen Spielers anzeigen\n" +
                "\\showScore   -   Den Spielstand aller Spieler anzeigen\n" +
                "\\showPlayers -   Zeigt alle Spieler an mit und zeigt welche\n";
        String nichtIngameMessage = "Dieser Befehl ist gerade nicht verfügbar du bist nicht in einem Spiel!";

        Scanner scanOptions = new Scanner(System.in);
        String input = scanOptions.nextLine();
            if (input.equals("\\help")) {
                System.out.println(helpMessage);
            }else if (input.equals("\\start")) {
                System.out.println("Wollen sie ein neues Spiel starten? (ja/nein)");
                String answer = scanOptions.nextLine();
                if (answer.equals("ja")) {
                    newGame();
                } else {
                    System.out.println("Dann fahren wir mit dem aktuellen Spiel fort");
                }
            } else if (input.equals("\\playCard")){
                playCard(currentPlayer);
                playersTurnNumber = (playersTurnNumber +1) % playerCount;
            } else if (input.equals("\\showHand")) {
                showHand(currentPlayer);
            } else if (input.equals("\\showScore")){
                showScore();
            } else if(input.equals("\\showPlayers")) {
                showPlayers(currentPlayer);
            } else {
                System.out.println("bitte gueltigen Befehl eingeben und nicht das '\\' vergessen!");
        }

    }

    private static void newGame(){
        System.out.println("Das hier ist eine Platzhalterfunktion. Spiel geht weiter.");
    }

    private static void playCard(Player currentPlayer) {
        System.out.println("Welche Karte möchten sie nun aus Ihrem Deck Speilen");
    }

    private void getNewCardFromDeck(Player player){
        Card newCard = cardDeck.get(0);
        cardDeck.remove(0);
        player.addCardToHand(newCard);
        System.out.println("Sie zogen aus dem Stapel: " + newCard.getName());
    }

    private static void showHand(Player currentPlayer){
        System.out.println(currentPlayer.getHandAsString());
    }

    private static void showScore(){
        System.out.println("Score: PLatzerhalter");
        /*for (int i = 0; i < playerArray.length; i++){
            System.out.println("Spieler "+ (i+1) + playerArray[i].getName() + ": " + playerArray[i].getScore());
        }*/
    }

    private static void showPlayers(Player currentPlayer) {
        System.out.println("Spieler:");
        for (int i = 0; i < playerArray.length; i++) {
            if (playerArray[i].equals(currentPlayer)) {
                System.out.println(playerArray[i].getName() + "  <-- Aktuell am Zug");
            } else {
                System.out.println(playerArray[i].getName());
            }
        }
    }
    private Card getCardFromDeck(){
        Card returnCard = cardDeck.get(0);
        cardDeck.remove(0);
        return returnCard;
    }

}