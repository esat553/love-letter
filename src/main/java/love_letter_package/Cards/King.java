package love_letter_package.Cards;
import love_letter_package.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class King extends Card{

    public King() {
        super("König", 5, "Tausche deine Handkarte mit " +
                "der eines Mitspielers");
    }


    @Override
    public Player[] effectAction(Player[] playerArray, int indexOfPlayer) {
        Player currentPlayer = playerArray[indexOfPlayer];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tausche deine Hand mit einem Spieler deiner Wahl, der noch im Spiel ist und keinen Schutz hat.");
        ArrayList<Player> availablePlayers = new ArrayList<>();

        for (int i = 0; i < playerArray.length; i++) {
            if (!playerArray[i].isOutOfRound() && i != indexOfPlayer && !playerArray[i].hasProtection()) {
                availablePlayers.add(playerArray[i]);
            }
        }

        if (availablePlayers.isEmpty()) {
            System.out.println("Es gibt keinen Spieler, mit dem du tauschen kannst.");
            return playerArray;
        }

        int chosenIndex;
        do {
            System.out.println("Wähle einen Spieler zum Tauschen:");
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
        ArrayList<Card> helpHand = chosenPlayer.getHand();
        chosenPlayer.setHand(currentPlayer.getHand());
        currentPlayer.setHand(helpHand);

        System.out.println(playerArray[indexOfPlayer].getName() + " hat Karten mit " + chosenPlayer.getName() + " getauscht!");

        return playerArray;
    }

}
