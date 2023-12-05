import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame {

    public static void main(String[] args) {
        // 사용자 입력을 위한 스캐너 생성
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            // 블랙잭 게임 시작
            playBlackjack(scanner);
            
            // 게임을 다시 시작할지 물어봄
            System.out.print("다시 하시겠습니까? (y/n): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("y");
        }

        // 스캐너 종료
        scanner.close();
    }

    public static void playBlackjack(Scanner scanner) {
        // 카드 덱 초기화 및 섞기
        List<String> deck = initializeDeck();
        Collections.shuffle(deck);

        // 플레이어와 딜러의 초기 카드 핸드 생성
        List<String> playerHand = new ArrayList<>();
        List<String> dealerHand = new ArrayList<>();

        // 초기 카드 나눠주기
        initialDeal(deck, playerHand, dealerHand);

        // 플레이어가 버스트되었는지 여부를 체크하기 위한 변수
        boolean playerBusted = playerTurn(scanner, deck, playerHand);

        if (!playerBusted) {
            // 딜러의 차례 처리
            dealerTurn(deck, dealerHand);
            
            // 플레이어와 딜러의 카드를 화면에 출력
            displayHands(playerHand, dealerHand);
            
            // 승자 결정
            determineWinner(playerHand, dealerHand);
        }
    }

    // 카드 덱 초기화
    public static List<String> initializeDeck() {
        List<String> deck = new ArrayList<>();
        String[] suits = {"스페이드", "다이아몬드", "하트", "클로버"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        // 모든 조합의 카드 생성
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " " + suit);
            }
        }

        return deck;
    }

    // 초기 카드 나눠주기
    public static void initialDeal(List<String> deck, List<String> playerHand, List<String> dealerHand) {
        // 두 번씩 카드를 나눠주기
        for (int i = 0; i < 2; i++) {
            playerHand.add(deck.remove(deck.size() - 1));
            dealerHand.add(deck.remove(deck.size() - 1));
        }
    }

    // 플레이어 차례 처리
    public static boolean playerTurn(Scanner scanner, List<String> deck, List<String> playerHand) {
        while (true) {
            // 현재 카드를 플레이어와 딜러에게 보여주고 선택 받기
            displayHands(playerHand, Collections.singletonList(deck.get(deck.size() - 1)));
            System.out.print("히트 (h) 또는 스탠드 (s): ");
            String choice = scanner.next().toLowerCase();

            if ("h".equals(choice)) {
                // 플레이어가 히트 선택시 새로운 카드를 받고, 핸드의 합계를 체크하여 버스트 여부 확인
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

    // 딜러 차례 처리
    public static void dealerTurn(List<String> deck, List<String> dealerHand) {
        while (calculateHandValue(dealerHand) < 17) {
            // 딜러가 17 이하일 때 계속해서 카드를 뽑음
            dealerHand.add(deck.remove(deck.size() - 1));
        }
    }

    // 핸드의 총 점수 계산
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

        // 에이스 처리
        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }

        return value;
    }

    // 현재 카드 보여주기
    public static void displayHands(List<String> playerHand, List<String> dealerHand) {
        System.out.println("플레이어 카드: " + playerHand);
        System.out.println("딜러 카드: " + dealerHand);
    }

    // 승자 결정
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
