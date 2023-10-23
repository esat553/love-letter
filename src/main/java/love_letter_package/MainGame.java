package love_letter_package;

import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {
        String welcomingMessage =
                "Wilkommen beim Love Letter Spiel!\n" +
                "Um abzurufen, welche Befehle zur verfuegung stehen, kann immer im Spiel einfach \\help" +
                " eingegeben werden\n" +
                "(außer es wird etwas anderes angegeben!\n" +
                "Dann starten wir doch gleich mit der ersten Runde. Wie wuenschen dir noch sehr viel Spaß!\n";
        System.out.println(welcomingMessage);

        boolean playerWantsGame = true;
        //aktuell ist das noch eine Dauerschleife. Allerdings kann man in zukunft eine Option einbauen, um das Spiel mit
        // \exit zu verlassen.
        do {
            playGame();
            playerWantsGame = askPlayerForGame();
        } while (playerWantsGame);
    }

    private static void playGame() {
        String[] playerNames = askPlayerNames();
        Game currentGame = new Game(playerNames);
        currentGame.startGame();
    }

    private static boolean askPlayerForGame(){
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
        if (input == "\\start") {
            return true;
        } else if (input == "\\help") {
            System.out.println(helpMessage);
            return askPlayerForGame();
        } else {
            System.out.println(nichtIngameMessage);
            return askPlayerForGame();
        }
    }


    private static String[] askPlayerNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wie viele Spieler von 2-4 seid ihr denn? (jetzt ist es nicht möglich nach hilfe zu fragen)");
        int playerCount = scanner.nextInt();
        scanner.nextLine(); //um den Zeilenumbruch zu entfernen (da dieser sonst vom scanner gescannt wird)
        while (playerCount < 2 || playerCount > 4) {
            System.out.println("Leider werden nur 2-4 Spieler unterstützt :(\n" +
                               "Bitte gebe eine Zahl zwischen 2 und 4 ein");
            playerCount = scanner.nextInt();
        }
        scanner.nextLine(); //um den Zeilenumbruch zu entfernen (da dieser sonst vom scanner gescannt wird)
        String[] playerNames = new String[playerCount];
        for (int i = 0; i <playerCount; i++){
            System.out.println("Wie ist der name von Spieler "+ (i +1) +"?");
            playerNames[i] = scanner.nextLine();
        }
        System.out.println("Alles klar! Das Spiel wird erstellt");
        return playerNames;


    }

}

