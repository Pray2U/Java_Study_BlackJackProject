import java.util.Scanner;


public class Game {
	private Deck gameDeck;
	private Player player;
	private Dealer dealer;
	
    public void startGame() {
    	Scanner sc = new Scanner(System.in);
    	gameDeck = new Deck();
        gameDeck.Shuffle();
        player = new Player();
        dealer = new Dealer();
        player.draw(gameDeck, 2);
        dealer.draw(gameDeck, 2);

        while(true) {
            if (player.handCount > 21) {
                System.out.println("Dealer Win...");
                printGame(true);
                break;
            }

            printGame(true);

            System.out.print("Hit or Stand? (H/S):");

            String input = sc.next();
            if (input.equals("H") || input.equals("h")) {
                player.hit(gameDeck);
            } else if (input.equals("S") || input.equals("s")) {
                printGame(false);
                //딜러가 17이 넘을때까지 드로우
                dealer.hit(gameDeck);
                //승패 결정
                printGameResult();
                break;
            }
        }
    }

    private void printGameResult(){
        if (dealer.handCount > 21 || (21 - dealer.handCount) > (21 - player.handCount)) {
            System.out.println("Player Win...");
            printGame(false);
        } else if (dealer.handCount == player.handCount) {
            System.out.println("Tie...");
            printGame(false);
        } else {
            System.out.println("Dealer Win...");
            printGame(false);
        }
    }

        
   private void printGame(boolean dealerHide) {
       System.out.println("---------------------Jack's BlackJack Game---------------------");
       dealer.printDealer(dealerHide);
       player.printPlayer();
       System.out.println("---------------------------------------------------------------");


   }

}

