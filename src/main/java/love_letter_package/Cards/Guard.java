package love_letter_package.Cards;

import love_letter_package.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Guard extends Card{

    public Guard() {
        super("Wächterin", 1, "Errätst du die Handkarte " +
                "eines Mitspielers, scheidet dieser aus. " +
                "Gilt nicht für „Wächterin“!");
    }


    @Override
    public Player[] effectAction(Player[] playerArray, int indexOfPlayer) {
        Player currentPlayer = playerArray[indexOfPlayer];
        Scanner scanner = new Scanner(System.in);

        // Wähle zuerst einen Spieler aus
        System.out.println("Wähle einen Spieler aus, bei dem du die Karte erraten möchtest:");
        ArrayList<Player> availablePlayers = new ArrayList<>();

        for (int i = 0; i < playerArray.length; i++) {
            if (!playerArray[i].isOutOfRound() && i != indexOfPlayer && !playerArray[i].hasProtection()) {
                availablePlayers.add(playerArray[i]);
            }
        }

        if (availablePlayers.isEmpty()) {
            System.out.println("Es gibt keinen Spieler, bei dem du Raten kannst.");
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
                chosenIndex = -1;
                System.out.println("Bitte eine gültige Zahl eingeben! Befehle sind erst später wieder möglich");
            }
        } while (chosenIndex < 0 || chosenIndex >= availablePlayers.size());

        Player chosenPlayer = availablePlayers.get(chosenIndex);

        // Errate die Karte des gewählten Spielers
        System.out.println("Welche Karte hat " + chosenPlayer.getName());
        System.out.println("Prinzessin Annette, Gräfin, König, Prinz, Zofe, Baron, Priester und Wächterin?");
        String guessedCardName = scanner.nextLine();

        // Wenn die Vermutung korrekt ist, setze den Spieler aus dem Spiel
        if (guessedCardName.equals(chosenPlayer.getHand().get(0).getName())) {
            chosenPlayer.setOutOfRound(true);
            System.out.println("Richtig geraten! " + chosenPlayer.getName() + " ist nun aus dieser Runde ausgeschieden.");
        } else {
            System.out.println("Falsch geraten!");
        }

        return playerArray;
    }
}
