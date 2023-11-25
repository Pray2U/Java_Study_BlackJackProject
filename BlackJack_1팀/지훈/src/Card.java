public class Card {
    private Rank rank;
    private Suit suit;
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        return rank.getValue();
    }

    public String toString() {
        return rank.getLabel() + "(" + suit.getPattern() + ")";
    }

    public enum Rank {
        ACE("A", 11),
        TWO("2", 2),
        THREE("3", 3),
        FOUR("4", 4),
        FIVE("5", 5),
        SIX("6", 6),
        SEVEN("7", 7),
        EIGHT("8", 8),
        NINE("9", 9),
        TEN("10", 10),
        JACK("J", 10),
        QUEEN("Q", 10),
        KING("K", 10);
        private final String label;
        private final int value;
        Rank(String label, int value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }
        public int getValue() {
            return value;
        }
    }
    public enum Suit {
        SPADES("♠"),
        CLUBS("♣"),
        DIAMONDS("◆"),
        HEARTS("♥");

        private final String pattern;

        Suit(String pattern) {
            this.pattern = pattern;
        }
        public String getPattern() {
            return pattern;
        }
    }
}
