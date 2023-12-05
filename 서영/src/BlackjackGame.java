import java.util.*;

// 카드 클래스
class Card {
    private String suit;
    private String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String toString() {
        return value + "(" + suit + ")";
    }

    public int getValue() {
        if (value.equals("A")) return 11;
        if (value.equals("K") || value.equals("Q") || value.equals("J")) return 10;
        return Integer.parseInt(value);
    }
}

// 카드 덱 클래스
class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        String[] suits = {"♠", "♣", "◆", "♥"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (String suit : suits) {
            for (String value : values) {
                cards.add(new Card(suit, value));
            }
        }
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.remove(cards.size() - 1);
    }
}

// 핸드 클래스
class Hand {
    public List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = 0;
        int aceCount = 0;
        for (Card card : cards) {
            int value = card.getValue();
            if (value == 11) {
                aceCount++;
            }
            score += value;
        }
        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }
        return score;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}

// 블랙잭 게임 클래스
public class BlackjackGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();
        boolean playerBusted = false;

        // 초기 카드 배분
        playerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        // 초기 카드 배분 후 21점 체크
        checkForBlackjack(playerHand, dealerHand);

        // 플레이어의 턴
        while (true) {
            System.out.println("------------- Jack's BlackJack Game -------------");
            System.out.println("# Dealer: " + dealerHand.cards.get(0) + "  XX");
            System.out.println("# Player: " + playerHand);
            System.out.println("-------------------------------------------------");
            System.out.print("Hit or Stand? (H/S): ");
            String action = scanner.nextLine();
            if (action.equalsIgnoreCase("H")) {
                playerHand.addCard(deck.drawCard());
                if (playerHand.getScore() > 21) {
                    playerBusted = true;
                    break;
                }
                // 플레이어의 턴 후 21점 체크
                checkForBlackjack(playerHand, dealerHand);
            } else {
                break;
            }
        }

        // 딜러의 턴
        while (!playerBusted && dealerHand.getScore() < 17) {
            dealerHand.addCard(deck.drawCard());

            // 딜러의 턴 후 21점 체크
            checkForBlackjack(playerHand, dealerHand);
        }

        // 결과 출력
        System.out.println("------------- Jack's BlackJack Game -------------");
        System.out.println("# Dealer: " + dealerHand + " (" + dealerHand.getScore() + ")");
        System.out.println("# Player: " + playerHand + " (" + playerHand.getScore() + ")");
        System.out.println("-------------------------------------------------");

        if (playerBusted) {
            System.out.println("Player busts. Dealer wins.");
        } else if (dealerHand.getScore() > 21 || playerHand.getScore() > dealerHand.getScore()) {
            System.out.println("Player wins!");
        } else if (playerHand.getScore() < dealerHand.getScore()) {
            System.out.println("Dealer wins...");
        } else {
            System.out.println("It's a tie.");
        }
    }

    private static void checkForBlackjack(Hand playerHand, Hand dealerHand) {
        if (playerHand.getScore() == 21 || dealerHand.getScore() == 21) {
            System.out.println("Blackjack! Game Over.");
            System.out.println("# Dealer: " + dealerHand + " (" + dealerHand.getScore() + ")");
            System.out.println("# Player: " + playerHand + " (" + playerHand.getScore() + ")");
            if (playerHand.getScore() == 21) {
                System.out.println("Player wins with a Blackjack!");
            } else {
                System.out.println("Dealer wins with a Blackjack!");
            }
            System.exit(0);
        }
    }
}
