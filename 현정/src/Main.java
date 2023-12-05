import java.util.*;
        import java.util.ArrayList;
        import java.util.Random;
        import java.util.List;

// Card 클래스
class Card {
    private String suit; // 무늬
    private String rank; // 숫자

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

// Card Deck 클래스
class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"♠", "♥", "♣", "♦"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    // 카드를 무작위로 섞는 메서드
    public void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < cards.size(); i++) {
            int j = rand.nextInt(cards.size());
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }


    // 카드 덱에서 한 장의 카드를 뽑아내는 메서드
    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        } else {
            return null; // 덱이 비어있을 때
        }
    }
}

// 플레이어 클래스
class Player {
    private List<Card> CardPack; // 카드 패
    private int score; // 점수

    public Player() {
        CardPack = new ArrayList<>();
        score = 0;
    }

    public void addCard(Card card) {
        CardPack.add(card);
        uqdateScore();
    } // Player의 CardPack에 새로운 카드를 추가하는 역할

    public List<Card> getCardPack() {
        return CardPack;
    } // Player의 손에 있는 카드 리스트 반환

    public int getScore() {
        return score;
    } // Player의 현재 점수를 반환

    public void uqdateScore() {
        score = 0;
        int numAces = 0;

        for (Card card : CardPack) {
            String rank = card.getRank();
            if (rank.equals("A")) {
                numAces++;
                score += 11;
            } else if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
                score += 10;
            } else {
                score += Integer.parseInt(rank);
            }
        } // 플레이어의 현재 점수를 업데이트 하는 역할

        while (numAces > 0 && score > 21) {
            score -= 10;
            numAces--;
        } // A 카드를 11점으로 계산했을 경우 21점이 넘어가면 1점으로 재계산
    }
}

// 메인 클래스
public class BlackjackGame {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        Player player = new Player();
        Player dealer = new Player();

        // 플레이어와 딜러에게 초기 카드 나눠주기
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());

        // 게임 시작
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("플레이어의 카드: " + player.getCardPack() + " (점수: " + player.getScore() + ")");
            System.out.println("딜러의 카드: [" + dealer.getCardPack().get(0) + ", ??]");

            // 플레이어의 선택 (Hit 또는 Stand)
            System.out.print("계속 카드를 받으시겠습니까? Hit (H) or Stand (S)");
            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("H")) {
                Card card = deck.drawCard();
                player.addCard(card);
                System.out.println("플레이어가 뽑은 카드 " + card);
                if (player.getScore() > 21) {
                    System.out.println("플레이어가 버스트 했습니다! 딜러의 승리.");
                    break;
                }
            } else if (choice.equals("S")) {
                // 딜러의 턴
                while (dealer.getScore() < 17) {
                    Card card = deck.drawCard();
                    dealer.addCard(card);
                    System.out.println("딜러가 뽑은 카드 " + card);
                }

                System.out.println("딜러의 카드: " + dealer.getCardPack() + " (점수: " + dealer.getScore() + ")");

                if (dealer.getScore() > 21 || dealer.getScore() < player.getScore()) {
                    System.out.println("플레이어 승리!");
                } else if (dealer.getScore() == player.getScore()) {
                    System.out.println("동점입니다!");
                } else {
                    System.out.println("딜러 승리!");
                }
                break;
            }
        }

        scanner.close();
    }
}
