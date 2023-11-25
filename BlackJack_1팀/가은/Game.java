import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Card card;
    private Player player;
    private Dealer dealer;
    private Random random;
    private Score score;
    private int playerScore;
    private int dealerScore;
    private List<String> deck;
    private List<String> playerCards;
    public Game() {
        card = new Card();
        player = new Player();
        dealer = new Dealer();
        random = new Random();
        score = new Score();
        playerCards = new ArrayList<>();
    }

    public void printPlayer() {
        System.out.println("-------- Jack's Blackjack Game --------");
        System.out.println("Dealer: [" + dealer.getDealerCards().get(1) + ", XX]");
        System.out.println("Players: " + player.getPlayerCards());
    }
    public void printDealer() {
        System.out.println("-------- Jack's Blackjack Game --------");
        System.out.println("Dealer's Cards: " + dealer.getDealerCards());
        System.out.println("Players: " + player.getPlayerCards());
    }

    public void playGame() {
        shuffleDeck();
        dealInitialCards(card);
        player.playerTurn(this, card);
        dealer.dealerTurn(this, card);
        printResult();
        System.out.println("--------------------------------------------");
    }

    private void printResult() {
        System.out.println();
        System.out.println("------------------------------------");
        playerScore = score.calculateScore(player.getPlayerCards());
        dealerScore = score.calculateScore(dealer.getDealerCards());
        if (playerScore > 21) {
            System.out.println("Dealer Wins...");
        } else if (dealerScore > 21) {
            System.out.println("Player Wins...");
        } else {
            if (playerScore > dealerScore) {
                System.out.println("Player Wins...");
            } else if (playerScore < dealerScore) {
                System.out.println("Dealer Wins...");
            } else {
                System.out.println("It's a tie.");
            }
        }
    }




    public void dealInitialCards(Card card) {
        for (int i = 0; i < 2; i++) {
            dealer.addCard(card.getDeck().remove(card.getDeck().size() - 1));
            player.addCard(card.getDeck().remove(card.getDeck().size() - 1));
        }
    }
    public void shuffleDeck() {
        for (int i = 0; i < card.getDeck().size(); i++) {
            int j = random.nextInt(card.getDeck().size());
            String temp = card.getDeck().get(i);
            card.getDeck().set(i, card.getDeck().get(j));
            card.getDeck().set(j, temp);
        }
    }
}