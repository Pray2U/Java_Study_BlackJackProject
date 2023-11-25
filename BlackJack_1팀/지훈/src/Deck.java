import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Deck implements Drawable {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    private void initializeDeck() {
        Card.Rank[] ranks = Card.Rank.values();
        Card.Suit[] suits = Card.Suit.values();

        for (Card.Suit suit : suits) {
            for (Card.Rank rank : ranks) {
                cards.add(new Card(rank, suit));
            }
        }
    }
    private void shuffleDeck() {
        Collections.shuffle(cards);
    }

    @Override
    public Card drawCard() throws DeckEmptyException {
        if (cards.isEmpty()) {
            throw new DeckEmptyException("Deck is empty.");
        }
        return cards.remove(cards.size() - 1);
    }
}
