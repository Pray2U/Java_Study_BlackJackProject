public class Card {
    private String suits; // 카드 모양
    private String ranks; // 카드 숫자
    private int value; // 카드 점수

    public Card(String suit, String rank, int value) {
        this.suits = suit;
        this.ranks = rank;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return ranks + "(" + suits + ")";
    }
}
