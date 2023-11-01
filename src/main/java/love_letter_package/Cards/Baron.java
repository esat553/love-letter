package love_letter_package.Cards;

public class Baron extends Card{
    public Baron() {
        super("Baron", 3, ": Vergleiche deine Handkarte " +
                "mit der eines Mitspielers. Der Spieler mit " +
                "dem niedrigeren Wert scheidet aus.");
    }

    @Override
    public void effectAction() {
        System.out.println("Platzhalter");
    }
}
