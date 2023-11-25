package org.example;

import org.example.cardType.Denomination;
import org.example.cardType.Pattern;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private Stack<Card> Cards = new Stack<>();

    public void setDeck(){
        for(Pattern pattern: Pattern.values()){
            for(Denomination denomination:Denomination.values()){
                Cards.add(new Card(pattern, denomination));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(Cards);
    }

    public Card draw() throws IndexOutOfBoundsException{
        return Cards.pop();
    }

}
