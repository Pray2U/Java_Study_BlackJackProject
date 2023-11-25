import java.util.ArrayList;
import java.util.List;

class Player implements Participant {
    private List<Card> playerHand;

    public Player() {
        playerHand = new ArrayList<>();
    }

    @Override
    public void initialDeal(Deck deck) throws DeckEmptyException {
        playerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());
    }

    @Override
    public void drawCard(Deck deck) throws DeckEmptyException {
        playerHand.add(deck.drawCard());
    }

    @Override
    public int calculateHandScore() {
        int score = 0;
        int numAces = 0;

        for (Card card : playerHand) {
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

    public String getPlayerHandAsString() {
        StringBuilder handString = new StringBuilder();

        for (Card card : playerHand) {
            handString.append(card.toString()).append(" ");
        }

        return handString.toString().trim();
    }
}
