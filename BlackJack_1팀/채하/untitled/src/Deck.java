//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> CardDeck = new ArrayList();

    public Deck() {
        String[] Mark = new String[]{"♠", "♣", "♥", "◆"};
        String[] var2 = Mark;
        int var3 = Mark.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String M = var2[var4];

            for(int j = 1; 13 > j; ++j) {
                this.CardDeck.add(new Card(M, j));
            }
        }

    }

    public Card Open() throws IllegalArgumentException {
        Card Opencard = (Card)this.CardDeck.get(0);
        this.CardDeck.remove(0);
        return Opencard;
    }

    public void Shuffle() {
        ArrayList<Card> ShffleDeck = new ArrayList();
        Random random = new Random();

        for(int i = 0; this.CardDeck.size() > i; ++i) {
            int index = random.nextInt(this.CardDeck.size() - 1);
            ShffleDeck.add((Card)this.CardDeck.get(index));
            this.CardDeck.remove(index);
        }

        this.CardDeck = ShffleDeck;
    }
}
