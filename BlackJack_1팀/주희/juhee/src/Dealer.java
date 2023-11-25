import java.util.ArrayList;
import java.util.List;

class Dealer {
    private List<Card> hand = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public String getVisibleCard() {
        if (!hand.isEmpty()) {
            return hand.get(0).toString();
        }
        return "";
    }

    public List<Card> getHand() {
        return hand;
    }

    public int calculateScore() {
        int score = 0;
        int aceCount = 0;

        for (Card card :hand) {
            score += card.getValue();
            if (card.getValue() == 1) {
                aceCount++;
            }
        }

        while (score < 21 && aceCount > 0) {
            score += 10;
            aceCount--;
        }

        return score;
    }
}