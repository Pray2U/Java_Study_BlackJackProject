package org.example.cardType;

public enum Denomination {
    ONE("A",11),
    TWO("2",2),
    THREE("3",3),
    FOUR("4",4),
    FIVE("5",5),
    SIX("6",6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10",10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    final String Symbol;
    final int score;

    private Denomination(String symbol, int score) {
        Symbol = symbol;
        this.score = score;
    }

    public String getSymbol() {
        return Symbol;
    }

    public int getScore() {
        return score;
    }
}
