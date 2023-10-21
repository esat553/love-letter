import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {
        String welcomeingMessage =
                "Wilkommen beim Love Letter Spiel!\n" +
                "Um abzurufen, welche Befehle zur verfuegung stehen, kann immer im Spiel einfach \\help" +
                " eingegeben werden\n" +
                "(außer es wird etwas anderes angegeben!\n" +
                "Wie wuenschen dir noch sehr viel Spaß!\n";
        scanForOptions();
        System.out.println(welcomeingMessage);
        startGame();
    }


    private static void startGame() {
        int playerCount;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Wie viele Spieler von 2-4 seid ihr denn? (jetzt ist es nicht möglich nach hilfe zu fragen)");
        playerCount = scanner.nextInt();
        while (playerCount < 2 || playerCount > 4) {
            System.out.println("Leider werden nur 2-4 Spieler unterstützt :(\n" +
                               "Bitte gebe eine Zahl zwischen 2 und 4 ein");
            playerCount = scanner.nextInt();
        }


    }

    private static void scanForOptions(){
        Scanner scanOptions = new Scanner(System.in);
        String input = scanOptions.nextLine();
        while (input.charAt(0) != '\\'){
            System.out.println("bitte gueltigen Befehl eingeben und nicht das '\\' vergessen!");
        }
        if (input == "\\help") {

        } else if (true) {
            
        }
    }

}

