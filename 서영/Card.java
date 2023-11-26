public class Card {
    public enum Suit {
        SPADES("♠"), CLUBS("♣"), DIAMONDS("◆"), HEARTS("♥");

        private final String symbol;

        Suit(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }
    public int getBlackjackValue() {
        if (value.equals("A")) {
            return 11; // 에이스는 기본적으로 11점
        } else if (value.equals("J") || value.equals("Q") || value.equals("K")) {
            return 10; // J, Q, K는 10점
        } else {
            return Integer.parseInt(value); // 2~10은 각 숫자에 해당하는 점수
        }
    }
    private final Suit suit;
    private final String value;

    public Card(Suit suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return suit.toString() + value;
    }
}
