import java.util.ArrayList;
import java.util.List;

class Dealer implements Participant {
    private List<Card> dealerHand;
    public Dealer() {
        dealerHand = new ArrayList<>();
    }
    @Override
    public void initialDeal(Deck deck) throws DeckEmptyException {
        dealerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
    }

    @Override
    public void drawCard(Deck deck) throws DeckEmptyException {
        dealerHand.add(deck.drawCard());
    }

    @Override
    public int calculateHandScore() {
        int score = 0;
        int numAces = 0;

        for (Card card : dealerHand) {
            score += card.getValue();

            if (card.getRank().equals("A")) {
                numAces++;
            }
        }

        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }

        return score;
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }
}
