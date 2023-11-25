import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Dealer {
    private List<String> dealerCards;
    Score dealerScore = new Score();
    int dealerScoreValue = 0;

    public Dealer() {
        dealerCards = new ArrayList<>();
    }

    public void addCard(String card) {
        dealerCards.add(card);
    }

    public List<String> getDealerCards() {
        return dealerCards;
    }
    public void dealerTurn(Game game, Card card) {
        while (dealerScore.calculateScore(getDealerCards()) <= 16) {
            if (card.getDeck().isEmpty()) {
                break; // 덱이 비어있을 경우 반복문 종료
            }
            addCard(card.getDeck().remove(card.getDeck().size() - 1));
            dealerScore.calculateScore(getDealerCards());
        }
        game.printDealer();
        dealerScoreValue = dealerScore.getScore();
    }

}