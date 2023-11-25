import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"♠", "♥", "♦", "♣"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                int value = (i < 9) ? i + 1 : 10;
                Card card = new Card(suit, ranks[i], value);
                cards.add(card);
            }
        }
    }
    public Card drawCard() {
        Random random = new Random();
        int index = random.nextInt(cards.size());
        return cards.remove(index);
    }
}