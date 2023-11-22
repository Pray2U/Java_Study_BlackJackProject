import java.util.*;
import java.util.ArrayList;
import java.util.Random;

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
        return rank + "of" + suit;
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

    // 플레이어 클래스 구현 예정...
    class Player {

    }

    // 메인 함수 구현 예정...
}



