import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            playBlackjack(scanner);
            System.out.print("다시 하시겠습니까? (y/n): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("y");
        }

        scanner.close();
    }

    public static void playBlackjack(Scanner scanner) {
        List<String> deck = initializeDeck();
        Collections.shuffle(deck);

        List<String> playerHand = new ArrayList<>();
        List<String> dealerHand = new ArrayList<>();

        initialDeal(deck, playerHand, dealerHand);

        boolean playerBusted = playerTurn(scanner, deck, playerHand);

        if (!playerBusted) {
            dealerTurn(deck, dealerHand);
            displayHands(playerHand, dealerHand);
            determineWinner(playerHand, dealerHand);
        }
    }

    public static List<String> initializeDeck() {
        List<String> deck = new ArrayList<>();
        String[] suits = {"스페이드", "다이아몬드", "하트", "클로버"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " " + suit);
            }
        }

        return deck;
    }

    public static void initialDeal(List<String> deck, List<String> playerHand, List<String> dealerHand) {
        for (int i = 0; i < 2; i++) {
            playerHand.add(deck.remove(deck.size() - 1));
            dealerHand.add(deck.remove(deck.size() - 1));
        }
    }

    public static boolean playerTurn(Scanner scanner, List<String> deck, List<String> playerHand) {
        while (true) {
            displayHands(playerHand, Collections.singletonList(deck.get(deck.size() - 1)));
            System.out.print("히트 (h) 또는 스탠드 (s): ");
            String choice = scanner.next().toLowerCase();

            if ("h".equals(choice)) {
                playerHand.add(deck.remove(deck.size() - 1));
                int playerValue = calculateHandValue(playerHand);
                if (playerValue > 21) {
                    displayHands(playerHand, Collections.emptyList());
                    System.out.println("플레이어 버스트!");
                    return true;
                }
            } else if ("s".equals(choice)) {
                break;
            }
        }
        return false;
    }

    public static void dealerTurn(List<String> deck, List<String> dealerHand) {
        while (calculateHandValue(dealerHand) < 17) {
            dealerHand.add(deck.remove(deck.size() - 1));
        }
    }

    public static int calculateHandValue(List<String> hand) {
        int value = 0;
        int aces = 0;

        for (String card : hand) {
            String rank = card.split(" ")[0];
            if ("A".equals(rank)) {
                aces++;
                value += 11;
            } else if (rank.matches("[KQJ]")) {
                value += 10;
            } else {
                value += Integer.parseInt(rank);
            }
        }

        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }

        return value;
    }

    public static void displayHands(List<String> playerHand, List<String> dealerHand) {
        System.out.println("플레이어 카드: " + playerHand);
        System.out.println("딜러 카드: " + dealerHand);
    }

    public static void determineWinner(List<String> playerHand, List<String> dealerHand) {
        int playerValue = calculateHandValue(playerHand);
        int dealerValue = calculateHandValue(dealerHand);

        if (playerValue > 21) {
            System.out.println("딜러 승리 (플레이어 버스트)!");
        } else if (dealerValue > 21) {
            System.out.println("플레이어 승리 (딜러 버스트)!");
        } else if (playerValue > dealerValue) {
            System.out.println("플레이어 승리!");
        } else if (playerValue < dealerValue) {
            System.out.println("딜러 승리!");
        } else {
            System.out.println("무승부!");
        }
    }
}
