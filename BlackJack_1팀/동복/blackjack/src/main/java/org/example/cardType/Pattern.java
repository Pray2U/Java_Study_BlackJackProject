package org.example.cardType;

public enum Pattern {

    SPADE("♠"),
    HEART("♥"),
    CLOVER("♣"),
    DIAMOND("♦");

    private final String symbol;

    private Pattern(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

}
