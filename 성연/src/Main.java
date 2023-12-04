import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Card {
    public String suit; // 카드 무늬
    public String rank; // 카드 숫자

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue() {
        switch (rank) {
            case "A":
                return 11;
            case "K":
            case "Q":
            case "J":
                return 10;
            default:
                return Integer.parseInt(rank);
        }
    }

    @Override
    public String toString() {
        return rank + "(" + suit + ")";
    }
}

class CardDeck {
    public List<Card> cards;

    public CardDeck() {
        cards = new ArrayList<>();
        String[] suits = {"♥", "♠", "◆", "♣"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }

        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }

        return cards.remove(0);
    }
}

class Player {
    public List<Card> player_card;

    public Player() {
        player_card = new ArrayList<>();
    }

    public void addCard(Card card) {
        player_card.add(card);
    }

    public List<Card> getCard() {
        return player_card;
    }

    public int Score() {
        int score = 0;
        int numAces = 0;

        for (Card card : player_card) {
            score += card.getValue();
            if ("A".equals(card.rank)) {
                numAces++;
            }
        }

        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }

        return score;
    }
}

class Dealer extends Player {
    private boolean firstCardHidden;

    public Dealer() {
        firstCardHidden = true;
    }

    public void revealFirstCard() {
        firstCardHidden = false;
    }

    public String getDealerCard() {
        if (firstCardHidden) {
            return "XX " + super.getCard().get(1); // 첫 번째 카드는 비공개로 출력
        } else {
            return super.getCard().toString();
        }
    }
}

class GameResult {
    public void displayGameResult(BlackJack game) {
        int playerScore = game.player.Score();
        int dealerScore = game.dealer.Score();

        System.out.println("Player's Score: " + playerScore);
        System.out.println("Dealer's Score: " + dealerScore);

        if (playerScore > 21 || (dealerScore <= 21 && dealerScore > playerScore)) {
            System.out.println("Dealer Wins...");
        } else {
            System.out.println("Player Wins!");
        }
    }
}

class BlackJack {
    public CardDeck deck = new CardDeck();
    public Dealer dealer = new Dealer();
    public Player player = new Player();
    public Scanner scanner = new Scanner(System.in);
    public boolean gameEnd = false;

    public void InitialCards() { // 딜러와 플레이어에게 초기 카드 2장 나눠줌
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
    }

    public void displayCards(boolean HiddenCard) {
        System.out.println(
                "# Dealer: " + (HiddenCard ? dealer.getDealerCard() : dealer.getCard()) +
                "\n# Player: " + player.getCard() +
                "\n------------------------------------------"
        );
    }

    public boolean getPlayerChoice() {
        if (gameEnd) {
            return false; // 게임 종료 시 플레이어는 카드를 더 받을 수 없음
        }

        System.out.print("Hit or Stand? (H/S): ");
        String input = scanner.next();

        if ("H".equals(input) || "h".equals(input)) {
            player.addCard(deck.drawCard());

            int playerScore = player.Score();
            if (playerScore > 21) {
                System.out.println("※ Player Busted! Game Over. ※");
                gameEnd = true; // 게임 종료 플래그 설정
                return false; // 게임 종료
            }

            return true; // 계속 진행
        } else if ("S".equals(input) || "s".equals(input)) {
            return false;
        } else {
            System.out.println("Invalid choice.");
            return getPlayerChoice();
        }
    }

    public void finishDealerCard() {
        dealer.revealFirstCard();
        displayCards(false);

        while (!gameEnd && dealer.Score() < 17) {
            dealer.addCard(deck.drawCard());
            displayCards(false);
        }
    }

    public void displayGameResult() {
        GameResult gameResult = new GameResult();
        gameResult.displayGameResult(this);
    }

    public void startGame() {
        System.out.println("------------- BlackJack Game -------------");
        InitialCards();
        displayCards(true);

        while (getPlayerChoice()) {
            displayCards(true);
        }

        finishDealerCard();
        displayGameResult();
    }
}

public class Main {
    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.startGame();
    }
}
