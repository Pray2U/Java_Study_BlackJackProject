package org.example;

import org.example.cardType.Denomination;
import org.example.cardType.Pattern;

public class Card {
    private Pattern pattern;
    private Denomination denomination;

    public Card(Pattern pattern, Denomination denomination) {
        this.pattern = pattern;
        this.denomination = denomination;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Denomination getDenomination() {
        return denomination;
    }
}
