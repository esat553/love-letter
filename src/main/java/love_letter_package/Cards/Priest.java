package love_letter_package.Cards;

import love_letter_package.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Priest extends Card{
    public Priest() {
        super("Priester", 2, "Schaue dir die die Handkarte von einem deiner Mitspieler an");
    }


    @Override
    public Player[] effectAction(Player[] playerArray, int indexOfPlayer) {
        Player currentPlayer = playerArray[indexOfPlayer];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wähle einen Spieler aus, dessen Karte du sehen möchtest:");
        ArrayList<Player> availablePlayers = new ArrayList<>();

        for (int i = 0; i < playerArray.length; i++) {
            if (!playerArray[i].isOutOfRound() && i != indexOfPlayer && !playerArray[i].hasProtection()) {
                availablePlayers.add(playerArray[i]);
            }
        }
        if (availablePlayers.isEmpty()) {
            System.out.println("Es gibt keinen Spieler, den du anschauen kannst.");
            return playerArray;
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

        // Zeigt die Karte des ausgewählten Spielers
        System.out.println(chosenPlayer.getName() + " hat die Karte: " + chosenPlayer.getHand().get(0).getName());

        return playerArray;
    }

}
