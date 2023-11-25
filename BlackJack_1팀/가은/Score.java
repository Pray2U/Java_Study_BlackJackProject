import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Score {
    private int score;
    private int numAces;

    public Score() {
        score = 0;
        numAces = 0;
    }

    public int getScore() {
        return score;
    }

    public int calculateScore(List<String> cards) {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("Empty card found in the list.");
        }
        int score = 0;
        int numAces = 0;

        // 카드를 하나씩 확인하면서 점수 계산
        for (String card : cards) {
            String cardValue = card.substring(0, card.length() - 3); // 숫자 부분만 추출
            if (cardValue.equals("A")) {
                score += 11;
                numAces++;
            } else if (cardValue.equals("K") || cardValue.equals("Q") || cardValue.equals("J")) {
                score += 10;
            } else {
                score += Integer.parseInt(cardValue);
            }
        }

        // Ace 처리
        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }

        return score;
    }
}