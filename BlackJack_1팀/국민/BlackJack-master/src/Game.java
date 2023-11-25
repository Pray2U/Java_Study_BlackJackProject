import java.util.Objects;
import java.util.Scanner;
public class Game {
    Dealer dealer;
    Player player;
    Deck deck;
    Scanner scanner = new Scanner(System.in);
    public final int BUSTED_NUM = 21;
    public final int DEALER_NUM = 17;

    protected Game() {
        init();
    }
    private void init() {
        while (true) {
            boolean check = true;
            first();
            check = playerTurn(check);
            if (!check) {//플레이어 스탠
                dealerTurn();
                if (dealer.sum > BUSTED_NUM) {
                    System.out.println("Player Win...");
                    printBoard();
                    break;
                } else {
                    finish();
                    break;
                }
            } else {//플레이어 버스트
                break;
            }
        }
    }
    protected boolean isH(String input) {
        return input.equalsIgnoreCase("H");
    }
    protected boolean isS(String input) {
        return input.equalsIgnoreCase("S");
    }
    protected void printBoard() {
        System.out.print("-------------");
        System.out.print("Jack's BlackJack Game");
        System.out.print("-------------");
        System.out.println();
        System.out.print("# Dealer: ");
        for (String card : dealer.cards.keySet()) {
            System.out.print(card + "  ");
        }
        System.out.println();
        System.out.print("# Player: ");
        for (String card : player.cards.keySet()) {
            System.out.print(card + "  ");
        }
        System.out.println();
        System.out.println("-----------------------------------------------");
        //print_score();
    }
    public boolean isCheck(String input, boolean check) {
        if (isH(input)) {
            player.put(deck);
        } else if (isS(input)) {
            check = false;
        } else {
            System.out.println("잘못입력하셨습니다. ");
        }

        return check;
    }
    private void print_score() {
        System.out.println("점수확인용");
        System.out.println("player : " + player.sum);
        System.out.println("dealer : " + dealer.sum);
    }
    private void showSecret() {
        for (String card : dealer.cards.keySet()) {
            if (Objects.equals(card, "XX")) {
                dealer.cards.remove(card);
                dealer.cards.put(dealer.secretCard.getKey(), dealer.secretCard.getValue());
                dealer.sum = dealer.calculateSum();
                break;
            }
        }
    }
    private void first() {
        dealer = new Dealer();
        player = new Player();
        deck = new Deck();

        dealer.secretPut(deck);
        dealer.put(deck);
        player.put(deck);
        player.put(deck);

    }
    private boolean playerTurn(boolean check) {
        while (true) {
            player.sum = player.calculateSum();
            if (player.sum > BUSTED_NUM) {//플레이가 버스트될 경우
                printBoard();
                System.out.println("Dealer Wins...");
                showSecret();
                printBoard();
                break;
            }
            printBoard();
            System.out.print("Hit or Stand?  (H/S): ");
            String input = scanner.next();
            if (!isCheck(input, check)) {
                check = false;
                break;
            }
        }
        return check;
    }
    private void dealerTurn() {
        showSecret();
        printBoard();
        dealer.sum = dealer.calculateSum();
        if (dealer.sum < DEALER_NUM) {
            while (dealer.sum < DEALER_NUM) {
                try {
                    Thread.sleep(1000);
                    dealer.put(deck);
                    dealer.sum = dealer.calculateSum();
                    printBoard();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        dealer.sum = dealer.calculateSum();
    }
    private void finish(){
        int dealerScore = BUSTED_NUM - dealer.sum;
        int playerScore = BUSTED_NUM - player.sum;
        if(dealerScore > playerScore){
            System.out.println("Player Win...");
            printBoard();
        }
        else if(playerScore > dealerScore){
            System.out.println("Dealer Win...");
            printBoard();
        }
        else{
            System.out.println("Draw...");
            printBoard();
        }
    }
}
