import java.util.Scanner;

class BlackJackGame {
    private Deck deck;
    private Dealer dealer;
    private Player player;

    public BlackJackGame() {
        deck = new Deck();
        dealer = new Dealer();
        player = new Player();
    }

    public void startGame() {
        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());

        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("Dealer: " + dealer.getVisibleCard() + " XX");
            System.out.println("Player: " + player.getHand());

            String choice = getPlayerChoice();
            if (choice.equalsIgnoreCase("H")) { // Hit
                player.addCard(deck.drawCard());
                if (player.calculateScore() > 21) {
                    gameOver = true;
                    revealCards();
                    System.out.println("Player Busted. Dealer Wins...");
                }
            } else if (choice.equalsIgnoreCase("S")) { // Stand
                gameOver = true;
                revealCards();

                int dealerScore = dealer.calculateScore();
                int playerScore = player.calculateScore();

                if (dealerScore > 21) {
                    System.out.println("Dealer Busted. Player Wins!");
                } else if (playerScore > dealerScore) {
                    System.out.println("Player Wins!");
                } else if (playerScore < dealerScore) {
                    System.out.println("Dealer Wins...");
                } else {
                    System.out.println("It's a tie.");
                }
            }
        }
    }

    private void revealCards() {
        System.out.println("Dealer: " + dealer.getHand());
        System.out.println("Player: " + player.getHand());
    }

    private String getPlayerChoice() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!choice.equalsIgnoreCase("H") && !choice.equalsIgnoreCase("S")) {
            System.out.print("Hit or Stand? (H/S): ");
            try {
                choice = scanner.nextLine().toUpperCase();
                if (!choice.equalsIgnoreCase("H") && !choice.equalsIgnoreCase("S")) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter 'H' or 'S'.");
            }
        }

        return choice;
    }
}