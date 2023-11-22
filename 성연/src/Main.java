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

    public List<Card> getPlayer_card() {
        return player_card;
    }

    public int playerScore() {
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

class Dealer {
    public List<Card> dealer_card;
    private boolean firstCardHidden;

    public Dealer() {
        dealer_card = new ArrayList<>();
        firstCardHidden = true;
    }

    public void addCard(Card card) {
        dealer_card.add(card);
    }

    public List<Card> getDealer_card() {
        return dealer_card;
    }

    public void revealFirstCard() {
        firstCardHidden = false;
    }

    public boolean isFirstCardHidden() {
        return firstCardHidden;
    }

    public int dealerScore() {
        int score = 0;
        int numAces = 0;

        for (Card card : dealer_card) {
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

class BlackJack {
    public CardDeck deck = new CardDeck();
    public Dealer dealer = new Dealer();
    public Player player = new Player();
    public Scanner scanner = new Scanner(System.in);

    public void InitialCards() { // 딜러와 플레이어에게 초기 카드 2장 나눠줌
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
    }

    public void displayCards(boolean hideDealerCard) {
        System.out.println(
                "# Dealer: " + (hideDealerCard ? (dealer.isFirstCardHidden() ? "X" : dealer.getDealer_card()) : dealer.getDealer_card()) +
                "\n# Player: " + player.getPlayer_card() +
                "\n------------------------------------------"
        );
    }

    public boolean getPlayerChoice() {
        System.out.print("Hit or Stand? (H/S): ");
        String input = scanner.next();

        if ("H".equals(input) || "h".equals(input)) {
            player.addCard(deck.drawCard());
            return true;
        } else if ("S".equals(input) || "s".equals(input)) {
            return false;
        } else {
            System.out.println("Invalid choice.");
            return getPlayerChoice();
        }
    }

    public void finishDealerCard() {
        dealer.revealFirstCard();
    }

    public void Winner() {
        int playerScore = player.playerScore();
        int dealerScore = dealer.dealerScore();

        if (playerScore > 21 || (dealerScore <= 21 && dealerScore >= playerScore)) {
            System.out.println("Dealer Wins...");
        } else {
            System.out.println("Player Wins!");
        }
    }

    public void startGame() {
        System.out.println("------------- BlackJack Game -------------");
        InitialCards();
        displayCards(true);

        while (getPlayerChoice()) {
            displayCards(true);
        }

        finishDealerCard();
        displayCards(false);
        Winner();
    }
}

public class Main {
    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.startGame();
    }
}