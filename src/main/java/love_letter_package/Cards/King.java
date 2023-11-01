package love_letter_package.Cards;

public class King extends Card{

    public King() {
        super("König", 5, "Tausche deine Handkarte mit " +
                "der eines Mitspielers");
    }

    @Override
    public void effectAction() {
        System.out.println("Placeholder");
    }
}
