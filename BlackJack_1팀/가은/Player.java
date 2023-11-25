import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player{
    Score playerScore = new Score();
    int playerScoreValue = 0;
    private List<String> playerCards;

    public Player() {
        playerCards = new ArrayList<>();
    }

    public void addCard(String card) {
        playerCards.add(card);
    }
    public List<String> getPlayerCards() {
        return playerCards;
    }


    public void playerTurn(Game game, Card card) {
        while (true) {
            int playerScoreValue = playerScore.calculateScore(playerCards);
            if (playerScoreValue > 21) {
                break;
            }

            game.printPlayer();
            System.out.print("Hit or Stand? (H/S): ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine().toUpperCase();

            if (!choice.equals("H") && !choice.equals("S")) {
                throw new IllegalArgumentException("Invalid choice. Please enter 'H' for Hit or 'S' for Stand.");
            }

            // Hit 선택
            if (choice.equals("H")) {
                // 플레이어에게 카드 1장 추가
                playerCards.add(card.getDeck().remove(card.getDeck().size() - 1));
            }
            // Stand 선택
            else if (choice.equals("S")) {
                break;
            }
        }
        playerScoreValue = playerScore.getScore();
        playerScore.calculateScore(getPlayerCards());
    }
}