package love_letter_package.Cards;

public class Princess extends Card{

    public Princess() {
        super ("Prinzessin Annette", 8, "Du verlierst wenn du diese Karte ablegst");
    }

    @Override
    public void effectAction() {
        System.out.println("Platzhalter");
    }
}
