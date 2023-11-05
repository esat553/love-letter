package love_letter_package.Cards;

import love_letter_package.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Baron extends Card{
    public Baron() {
        super("Baron", 3, ": Vergleiche deine Handkarte " +
                "mit der eines Mitspielers. Der Spieler mit " +
                "dem niedrigeren Wert scheidet aus.");
    }


    @Override
    public Player[] effectAction(Player[] playerArray, int indexOfPlayer) {
        Player currentPlayer = playerArray[indexOfPlayer];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wähle einen Spieler aus, um die Karten zu vergleichen:");
        ArrayList<Player> availablePlayers = new ArrayList<>();

        for (int i = 0; i < playerArray.length; i++) {
            if (!playerArray[i].isOutOfRound() && i != indexOfPlayer && !playerArray[i].hasProtection()) {
                availablePlayers.add(playerArray[i]);
            }
        }

        if (availablePlayers.isEmpty()) {
            System.out.println("Es gibt keinen Spieler, mit dem du Karten vergleichen kannst.");
            return playerArray; // Beendet die Aktion frühzeitig
        }


        int chosenIndex;
        do {
            for (int i = 0; i < availablePlayers.size(); i++) {
                System.out.println((i + 1) + ". " + availablePlayers.get(i).getName());
            }
            try {
                String userInput = scanner.nextLine();
                chosenIndex = Integer.parseInt(userInput) - 1;
            } catch (NumberFormatException e) {
                chosenIndex = -1;  // ungültige Eingabe
            }
        } while (chosenIndex < 0 || chosenIndex >= availablePlayers.size());

        Player chosenPlayer = availablePlayers.get(chosenIndex);

        int currentPlayerClosenessToPrincess = currentPlayer.getHand().get(0).getClosenessToPrincess(); // Direktes Abrufen des Kartenwerts
        int chosenPlayerClosenessToPrincess = chosenPlayer.getHand().get(0).getClosenessToPrincess(); // Direktes Abrufen des Kartenwerts

        System.out.println("Die Karte von " + currentPlayer.getName() + " ist: " +
                currentPlayer.getHand().get(0).getName() + " mit einer Entfernung von: " +
                currentPlayer.getHand().get(0).getClosenessToPrincess());
        System.out.println("Die Karte von " + chosenPlayer.getName() + " ist: " +
                chosenPlayer.getHand().get(0).getName() + " mit einer Entfernung von: " +
                chosenPlayer.getHand().get(0).getClosenessToPrincess());

        if (currentPlayerClosenessToPrincess > chosenPlayerClosenessToPrincess) {
            System.out.println(chosenPlayer.getName() + " scheidet aus, weil seine Karte niedriger ist!");
            chosenPlayer.setOutOfRound(true);
        } else if (currentPlayerClosenessToPrincess < chosenPlayerClosenessToPrincess) {
            System.out.println(currentPlayer.getName() + " scheidet aus, weil seine Karte niedriger ist!");
            currentPlayer.setOutOfRound(true);
        } else {
            System.out.println("Beide Karten haben denselben Wert! Niemand scheidet aus.");
        }

        return playerArray;
    }


}
