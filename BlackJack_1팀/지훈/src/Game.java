import java.util.Scanner;

class Game {
    private Dealer dealer;
    private Player player;
    private Deck deck;
    private Scanner scanner;

    public Game() {
        deck = new Deck();
        dealer = new Dealer();
        player = new Player();
        scanner = new Scanner(System.in);
    }

    public void start() {
        try {
            dealer.initialDeal(deck);
            player.initialDeal(deck);

            printHands(false);

            if (player.calculateHandScore() == 21) {
                System.out.println("Player wins!");
                return;
            }
            playerTurn();
            dealerTurn();
            determineWinner();
        } catch (DeckEmptyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void playerTurn() throws DeckEmptyException{
        while (true) {
            System.out.print("Hit or Stand? (H/S): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            try {
                if (choice.equals("h")) {
                    player.drawCard(deck);
                    printHands(false);

                    int playerScore = player.calculateHandScore();
                    if (playerScore > 21) {
                        System.out.println("Player busted! Dealer wins!");
                        return;
                    }
                } else if (choice.equals("s")) {
                    return;
                } else {
                        throw new IllegalArgumentException("Invalid input. Please try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void dealerTurn() throws DeckEmptyException {
        printHands(true);
        while (dealer.calculateHandScore() < 17) {
            dealer.drawCard(deck);
            printHands(true);
        }
    }

    public void determineWinner() {
        int playerScore = player.calculateHandScore();
        int dealerScore = dealer.calculateHandScore();

        System.out.println("Player's score: " + playerScore);
        System.out.println("Dealer's score: " + dealerScore);

        if (playerScore > 21) {
            System.out.println("Player busted! Dealer wins!");
        } else if (dealerScore > 21) {
            System.out.println("Dealer busted! Player wins!");
        } else if (playerScore > dealerScore) {
            System.out.println("Player wins!");
        } else if (dealerScore > playerScore) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public void printHands(boolean revealDealerCard) {
        System.out.println("---------------Jack's BlackJack Game---------------");
        System.out.print("# Dealer: ");

        if (revealDealerCard) {
            for (Card card : dealer.getDealerHand()) {
                System.out.print(card.toString() + " ");
            }
        } else {
            System.out.print(dealer.getDealerHand().get(0).toString() + " XX");
        }
        System.out.println("\n# Player: " + player.getPlayerHandAsString());
        System.out.println();
    }
}
