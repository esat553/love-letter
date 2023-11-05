/**
 * @author Esat Avci
 * Das ist die Klasse Game und hier ist die implementierung eines Spiels.
 * Der Hauptteil des Spiels findet im PlayerArray statt, denn das PlayerArray
 * hat alle Spieler enthalten. Die Spieler wiederum machen das Spiel.
 * Wichtig sind hierbei vor allem die Methoden, welche die Runden- und den Spielverlauf
 * definieren.
 */

package love_letter_package;
import love_letter_package.Cards.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static int playerCount;
    private static CardDeck cardDeck;
    static Player[] playerArray; //The players are saved in an array.

    Card [] discardedCards;

    //The game initializer which needs an Array with the player Names to initialize the Game
    public Game(String[] playerNames) {
        this.playerCount = playerNames.length;
        this.playerArray = new Player[this.playerCount];
        for (int i = 0; i < this.playerCount; i++) {
            this.playerArray[i] = new Player(playerNames[i]);
        }
    }

    public int getPlayerCount() {
        return playerCount;
    }

    /**
     *
     * Die StartGame Methode startet wie sie selbst erklärt das Spiel und
     * überprüft nach jeder abgeschlossenen Runde, ob ein Spiel zu ende ist
     * findet dann die Gewinner (Es sind potentiel mehere Gewinner Möglich)
     *
     */
    public void startGame(){
        boolean endOfGame = false;
        int maxPlayerScore = 0;
        while (!endOfGame){
            startRound();
            maxPlayerScore = getMaxPlayerCount();
            endOfGame = isRoundOver(maxPlayerScore);
        }
        System.out.print("\n\n\n");
        System.out.println("Das Spiel ist zu Ende!");
        System.out.print("\n\n");
        printWinners(maxPlayerScore);
    }

    /**
     * Printed die Gewinner eines Spieles aus
     * @param maxPlayerScore
     */
    //helping function for startGame()
    private void printWinners(int maxPlayerScore) {
        // creates a List with the Players with the highest Score
        ArrayList<Player> winners = new ArrayList<>();
        for (int i = 0; i < playerArray.length; i++) {
            if (playerArray[i].getScore() == maxPlayerScore) {
                winners.add(playerArray[i]);
            }
        }

        // Ausgeben der Gewinner
        if (winners.size() == 1) {
            System.out.println("Der Gewinner des Spiels ist: " + winners.get(0).getName() + " mit " + maxPlayerScore + " Punkten!");
        } else {
            System.out.print("Die Gewinner des Spiels sind: ");
            for (int i = 0; i < winners.size(); i++) {
                System.out.print(winners.get(i).getName());
                if (i != winners.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(" mit " + maxPlayerScore + " Punkten!");
        }
    }


    /**
     * Die StartRound Methode startet eine Runde aber wird ausgeführt bis die Runde beendet ist.
     * Das macht sie zu einer der wichtigsten Methoden ingame
     * zuerst eird eine neue Runde initialisiert. Das wird gemacht indem round Running auf true gesetzt wird,
     * alle vorher ausgeschiedenen Spieler wieder mitspielen dürfen,
     * die Hand der Spieler wieder neu verteilt wird und Karten auf den Ablagestapel gelegt werden.
     *
     */
    public void startRound() {
        //Here the rule is being applied, that for 2 players 4 Cards are removed from the Deck and for a
        //Game with more than 2 players only one is removed
        this.cardDeck = new CardDeck();
        boolean roundRunning = true;
        playerArray = setOutOfRoundToDefault(playerArray);
        int currentPlayerNumber = 0;
        int nextPlayersTurnNumber = 0;
        if (playerCount <= 2){
            discardedCards = new Card[4];
            for (int i = 0; i < 4; i++){
                discardedCards[i] = cardDeck.drawCardFromDeck();
            }
        } else {
            discardedCards = new Card[1];
            discardedCards[0] = cardDeck.drawCardFromDeck();
        }
        clearPlayerCardHands();
        System.out.println("Jeder Spieler erhält nun eine Karte!");
        for (int i = 0; i < playerArray.length; i ++){
            Card cardFromCarddeck = cardDeck.drawCardFromDeck(); //Hier wird eine Karte aus dem Kartendeck genommen
            playerArray[i].addCardToHand(cardFromCarddeck); //Diese Karte wird dann in die Karte des Spielers eingefügt.
        }
        System.out.println("Befehle können nun eingegeben werden");
        //This is the algorithm which determines when a Round is over.
        while (roundRunning) {
            currentPlayerNumber = nextPlayersTurnNumber;
            while (currentPlayerNumber == nextPlayersTurnNumber) {
                Player currentPlayer = this.playerArray[currentPlayerNumber];
                //Only if Player is not Out he can play
                if (currentPlayer.isOutOfRound()) {
                    nextPlayersTurnNumber = (currentPlayerNumber + 1) % playerArray.length;
                    continue;
                }
                System.out.println("Am Zug ist: " + currentPlayer.getName());
                //the protection of the Players has to be set on default again
                currentPlayer.setProtection(false);
                currentPlayer.addCardToHand(cardDeck.drawCardFromDeck());
                nextPlayersTurnNumber = scanForOptions(currentPlayer, nextPlayersTurnNumber);
                if (cardDeck.isEmpty() || checkIfOnlyOnePlayer()) roundRunning = false;
            }
        }
        ArrayList<Player> winnerList =  determineWinner(playerArray);
        giveWinnersPoints(winnerList);
        System.out.println("Runde beendet");
        System.out.print("Die gewinner dieser Runde: ");
        for (int i = 0; i < winnerList.size(); i++){
            System.out.print(winnerList.get(i).getName() + " " );
        }
        System.out.print("\n\n");
    }

    /**
     * Die Methode ist für die jeweiligen Eingaben zuständig und um die information zu teilen, ob ein
     * neuer Spieler dran ist wird ein int zurückgegeben. Ein neuer Spieler ist nur bei playCard dran.
     * @param currentPlayer
     * @param playersTurnNumber
     * @return
     */
    private int scanForOptions(Player currentPlayer, int playersTurnNumber){
        String helpMessage ="Zur Auswahl stehen folgende Befehle:\n" +
                "\\start                -   Ein neues Spiel starten\n" +
                "\\playCard             -   Eine Karte spielen. Der Spielzug des aktuellen Spielers wird dadurch beendet\n" +
                "\\showHand             -   Die Hand des aktuellen Spielers anzeigen\n" +
                "\\showScore            -   Den Spielstand aller Spieler anzeigen\n" +
                "\\showPlayers          -   Zeigt alle Spieler an und welcher aktuell am Zug ist\n" +
                "\\showDiscardedCards   -   Zeigt die Karten auf dem Ablagestapel\n" +
                "\\showReferenceCard    -   Zeigt die Referenzkarte mit den Kartentypen\n";

        String nichtIngameMessage = "Dieser Befehl ist gerade nicht verfügbar du bist nicht in einem Spiel!";

        Scanner scanOptions = new Scanner(System.in);
        String input = scanOptions.nextLine();
            if (input.equals("\\help")) {
                System.out.println(helpMessage);
                return playersTurnNumber;
            }else if (input.equals("\\start")) {
                newGame();
                return playersTurnNumber;
            } else if (input.equals("\\playCard")){
                playCard(currentPlayer);
                playersTurnNumber = (playersTurnNumber +1) % playerCount;
                return playersTurnNumber;
            } else if (input.equals("\\showHand")) {
                showHand(currentPlayer);
                return playersTurnNumber;
            } else if (input.equals("\\showScore")){
                showScore();
                return playersTurnNumber;
            } else if(input.equals("\\showPlayers")) {
                showPlayers(currentPlayer);
                return playersTurnNumber;
            } else if (input.equals("\\showDiscardedCards")) {
                showDiscardedCards();
                return playersTurnNumber;
            } else if (input.equals("\\showReferenceCard")) {
                showReferenceCard();
                return playersTurnNumber;
            } else if (input.equals("\\debug")) {
                cardDeck.showCardDeck();
                return playersTurnNumber;
            } else {
                System.out.println("bitte gueltigen Befehl eingeben und nicht das '\\' vergessen!");
                return playersTurnNumber;
        }

    }

    private static void newGame(){
        System.out.println("Sie befinden sich schon in einem Spiel.");
    }

    /**
     * PlayCard ist eine der wichtigsten Funktionen in der Game Methode, denn hier wird der Effekt jeder Karte
     * ausgeführt. Die Effekte vom Prinzen und von der Gräfin müssen hier implementiert werden, da man bei
     * der Gräfin gar nicht die Wahl bekommen darf eine andere Karte zu spielen und da beim spielen vom Prinzen
     * auf das Kartendeck zugegriffen werden muss.
     * @param currentPlayer
     */
    private static void playCard(Player currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        //wenn ein Spieler Countess und Prinz oder König hat wird ersteres Ausgeführt
        if (currentPlayer.playerHasCountessAndKingOrPrince()){
            System.out.println("Du hast die Gräfin und einen König oder Prinzen! Nun musst du also die Gräfin spielen!\n" +
                    "Drücke Enter um das zu tun.");
            scanner.nextLine();
            if (currentPlayer.getHand().get(0) instanceof Countess){
                currentPlayer.removeCardFromHand(0);
            } else currentPlayer.removeCardFromHand(1);
        } else {
            System.out.println("Welche Karte möchten sie nun aus Ihrem Deck Spielen (1 / 2)?");
            System.out.println("Nummer 1: " + currentPlayer.getCardString(0));
            System.out.println("Nummer 2: " + currentPlayer.getCardString(1) + "\n");
            //Nachfolgend wird danach geprüft ob der Spieler eine Zahl eingegeben hat.
            int choice;
            do {
                String userInput = scanner.nextLine();
                //erst muss geprüft werden, ob die eingabe ein int ist
                boolean isNumber = checkIfNumber(userInput);
                if (isNumber) {
                    choice = Integer.parseInt(userInput);
                    if (choice == 1 || choice == 2) {
                        break;
                    } else {
                        System.out.println("Bitte geben Sie 1 oder 2 ein.");
                    }
                } else {
                    System.out.println("Bitte geben Sie eine gültige Zahl ein. Befehle können erst später eigegeben werden");
                }
            } while (true); //Das ist eine Pseudo dauerschleife die beendet wird sobald eine gültige eingabe erfolgt.
            //playerArray = currentPlayer.getCard(choice -1).effectAction(currentPlayer, playerArray);
            Card selectedCard = currentPlayer.getCard(choice - 1);
            currentPlayer.removeCardFromHand(choice - 1);
            if (selectedCard instanceof Prince){
                princeSelected(getIndex(currentPlayer, playerArray));
            }
            playerArray = selectedCard.effectAction(playerArray, getIndex(currentPlayer, playerArray));
        }
    }

    //hilfsFunktion für playcard zum Spielen einer Karte mit effekt


    //hilfsFunkton für playcard
    private static boolean checkIfNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private static void showHand(Player currentPlayer){
        System.out.println(currentPlayer.getHandAsString());
    }

    private static void showScore() {
        for (int i = 0; i < playerArray.length; i++) {
            System.out.println(("Spieler " + (i + 1)) + " " + playerArray[i].getName() + ": " + playerArray[i].getScore());

        }
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

    //helpfunction for detecting Round ending
    private int getMaxPlayerCount() {
        int currentMax = 0;
        for (int i = 0; i < playerCount; i++){
            int currentPlayerPoints = playerArray[i].getScore();
            if (currentPlayerPoints > currentMax){
                currentMax = currentPlayerPoints;
            }

        }
        return currentMax;
    }

    //helpFuncttion for detecting Round Ending
    private boolean isRoundOver(int maxScore) {
        boolean roundOver = false;
        if (
                (playerCount == 2 && maxScore >= 7) ||
                (playerCount == 3 && maxScore >= 5) ||
                (playerCount == 4 && maxScore >= 4)
        ) {
            roundOver = true;
        }
        return roundOver;
    }

    private void showDiscardedCards(){
        System.out.println("Auf dem Ablagedeck liegen:");
        for (int i = 0; i < discardedCards.length; i++){
            System.out.println(discardedCards[i].getName());
        }
    }

    private static void showReferenceCard(){
        String ReferenceCardString =
                "Wächterin      - 1\n"
                + "Priester     - 2\n"
                + "Baron        - 3\n"
                + "Zofe         - 4\n"
                + "Prinz        - 5\n"
                + "König        - 6\n"
                + "Gräfin       - 7\n"
                + "Prinzessin   - 8";

        System.out.println(ReferenceCardString);
    }

    private ArrayList<Player> determineWinner(Player[] playerArray){
        ArrayList<Player> currentlyHighestCardOwners = new ArrayList<>();

        //We determine the first player which is out of the Round
        int startIndex = -1;
        for (int i = 0; i < playerArray.length; i++) {
            if (!playerArray[i].isOutOfRound()) {
                startIndex = i;
                break;
            }
        }
        //the first player which is not outOfRound is initialized as the currentWinner
        currentlyHighestCardOwners.add(playerArray[startIndex]);

        for (int i = startIndex + 1; i < playerArray.length; i++) {
            //don't add players which are out of Round
            if (playerArray[i].isOutOfRound()) {
                continue;
            }

            int closenessToPrincessPrevious = currentlyHighestCardOwners.get(0).getCard(0).getClosenessToPrincess();
            int closenessToPrincessCurrent = playerArray[i].getCard(0).getClosenessToPrincess();

            if (closenessToPrincessCurrent > closenessToPrincessPrevious) {
                currentlyHighestCardOwners.clear();
                currentlyHighestCardOwners.add(playerArray[i]);
            } else if (closenessToPrincessPrevious == closenessToPrincessCurrent) {
                currentlyHighestCardOwners.add(playerArray[i]);
            }
        }

        return currentlyHighestCardOwners;
    }


    private void giveWinnersPoints(ArrayList<Player> winnersList){
        for (int i = 0; i < winnersList.size(); i++){
            Player currentPlayer = winnersList.get(i);
            currentPlayer.increaseScore();
        }
    }

    private void clearPlayerCardHands(){
        for (Player player : playerArray) {
            player.clearHand();
        }
    }

    private boolean checkIfOnlyOnePlayer() {
        int activePlayers = 0;
        for (Player player : playerArray) {
            if (!player.isOutOfRound()) {
                activePlayers++;
            }
        }
        return activePlayers == 1;
    }
    static int getIndex(Player currentPlayer, Player[] playerArray) {
        for (int i = 0; i < playerArray.length; i++) {
            if (playerArray[i] == currentPlayer) {
                return i;
            }
        }
        return -1;
    }

    private Player[] setOutOfRoundToDefault(Player[] playerArray){
        for (Player player: playerArray){
            player.setOutOfRound(false);
        }
        return playerArray;
    }

    private static void princeSelected(int indexOfPlayer){
        System.out.println("Prinz wurde ausgewählt. Wer aus dem Game soll seine hand ablegen und eine neue Karte ziehen?");
        ArrayList<Player> availablePlayers = new ArrayList<>();
        for (int i = 0; i < playerArray.length; i++) {
            if (!playerArray[i].isOutOfRound() && i != indexOfPlayer && !playerArray[i].hasProtection()) {
                availablePlayers.add(playerArray[i]);
            }
        }
        int chosenIndex;
        if (availablePlayers.isEmpty()) {
            chosenIndex = indexOfPlayer;
            System.out.println("Es gibt keinen Spieler, der ausgewählt werden kann, also" +
                    "musst du deine Karten ablegen");
        } else {

            do {
                for (int i = 0; i < availablePlayers.size(); i++) {
                    System.out.println((i + 1) + ". " + availablePlayers.get(i).getName());
                }
                try {
                    Scanner scanner = new Scanner(System.in);
                    String userInput = scanner.nextLine();
                    chosenIndex = Integer.parseInt(userInput) - 1;
                } catch (NumberFormatException e) {
                    chosenIndex = -1;
                    System.out.println("Bitte eine gültige Zahl eingeben! Befehle sind erst später wieder möglich");
                }
            } while (chosenIndex < 0 || chosenIndex >= availablePlayers.size());
        }
        Player chosenPlayer = availablePlayers.get(chosenIndex);
        chosenPlayer.clearHand();
        chosenPlayer.addCardToHand(cardDeck.drawCardFromDeck());
    }


}