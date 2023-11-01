package love_letter_package.Cards;

public class Guard extends Card{

    public Guard() {
        super("Wächterin", 1, "Errätst du die Handkarte " +
                "eines Mitspielers, scheidet dieser aus. " +
                "Gilt nicht für „Wächterin“!");
    }

    @Override
    public void effectAction() {
        System.out.println("Placeholder");
    }
}
