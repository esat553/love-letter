package love_letter_package;

import love_letter_package.Cards.*;

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
        int currentPlayerNumber = 0;
        System.out.println("Befehle können nun eingegeben werden");
        while (gameRunning) {
            while (!cardDeck.isEmpty()){
                Player currentPlayer = this.playerArray[currentPlayerNumber];
                scanForOptions(currentPlayer);
            }
        }


    }

    private static void scanForOptions(Player currentPlayer){
        String helpMessage ="Zur Auswahl stehen folgende Befehle:\n" +
                "\\start -      Ein neues Spiel starten\n" +
                "\\playCard -   Eine Karte Spielen\n" +
                "\\showHand -   Die Hand des aktuellen Spielers anzeigen\n" +
                "\\showScore -   Den Spielstand aller Spieler anzeigen\n";
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
                playCard();
            } else if (input.equals("\\showHand")) {
                showHand(currentPlayer);
            } else if (input.equals("\\showScore")){
                showScore();
            } else {
                System.out.println("bitte gueltigen Befehl eingeben und nicht das '\\' vergessen!");
        }

    }

    private static void newGame(){
        System.out.println("Das hier ist eine Platzhalterfunktion. Spiel geht weiter.");
    }

    private static void playCard() {
        System.out.println("Das hier ist eine Platzhalterfunktion. Karte gespiel");
        System.out.println("Nächster Spieler müsste drankommen");
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

}