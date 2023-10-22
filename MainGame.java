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
        int playerCount = askPlayerCount();
        Game currentGame = new Game.initializeGame(playerCount);
    }


    private static int askPlayerCount() {
        int playerCount;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wie viele Spieler von 2-4 seid ihr denn? (jetzt ist es nicht möglich nach hilfe zu fragen)");
        playerCount = scanner.nextInt();
        while (playerCount < 2 || playerCount > 4) {
            System.out.println("Leider werden nur 2-4 Spieler unterstützt :(\n" +
                               "Bitte gebe eine Zahl zwischen 2 und 4 ein");
            playerCount = scanner.nextInt();
        }
        return playerCount;


    }
/*
    private static void scanForOptions(boolean ingame){
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

        if (ingame) {
            if (input.equals("\\help")) {
                System.out.println(helpMessage);
                scanForOptions(true);
            } else if (input.equals("\\start")) {
                System.out.println("Du befindest dich gerade in einem Spiel! Bist du sicher, dass du ein neues Starten möchtest? (ja/nein)");
                String answer = scanOptions.nextLine();
                if (answer.equals("ja")){
                    startGame();
                } else {
                    scanForOptions(true);
                }
            } else if (input.equals("\\playCard")){
                playCard();
            } else if (input.equals("\\showHand")) {
                showHand();
            } else if (input.equals("\\showScore")){
                showScore();
            } else {
                System.out.println("Falsche Eingabe!\n\nBitte gebe erneut einen Befehl ein");
                scanForOptions(ingame);
            }
        } else {
            if (input.equals("\\help")) {
                System.out.println(helpMessage);
            }else if (input.equals("\\start")) {

            } else if (input.equals("\\playCard")){

            } else if (input.equals("\\showHand")) {

            } else if (input.equals("\\showScore")){

            } else {
                System.out.println("Faslche Eingabe!\n\nBitte gebe erneut einen Befehl ein");
                scanForOptions(ingame);
            }
        }

    }
    */
}

