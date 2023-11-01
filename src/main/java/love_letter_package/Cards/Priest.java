package love_letter_package.Cards;

public class Priest extends Card{
    public Priest() {
        super("Priester", 2, "Schaue dir die die Handkarte von einem deiner Mitspieler an");
    }

    @Override
    public void effectAction() {
        System.out.println("Placeholder");
    }
}
