import java.util.ArrayList;
import java.util.List;

class Player {
    private List<Card> hand = new ArrayList<>();

    public Player() {
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int calculateScore() {
        int score = 0;
        int aceCount = 0;

        for (Card card : hand) {
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