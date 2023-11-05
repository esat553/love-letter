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

        // Errate die Karte des gewählten Spielers
        System.out.println("Welche Karte hat " + chosenPlayer.getName());
        String guessedCardName = scanner.nextLine().trim();

        // Wenn die Vermutung korrekt ist, setze den Spieler aus dem Spiel
        if (guessedCardName.equalsIgnoreCase(chosenPlayer.getHand().get(0).getName())) {
            chosenPlayer.setOutOfRound(true);
            System.out.println("Richtig geraten! " + chosenPlayer.getName() + " ist nun aus dieser Runde ausgeschieden.");
        } else {
            System.out.println("Falsch geraten!");
        }

        return playerArray;
    }
}
