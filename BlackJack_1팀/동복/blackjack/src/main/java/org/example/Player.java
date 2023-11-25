package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player implements Gamer{
    private static final int SCORE_OF_ACE = 10;
    private List<Card> cards = new ArrayList<>();
    private int score = 0;
    private int numberOfMinus = 0;

    private Card draw(Deck deck){
        Card card = deck.draw();
        cards.add(card);
        return card;
    }

    @Override
    public void hit(Deck deck){
        Card card = draw(deck);
        plusScore(card.getDenomination().getScore());
    }

    private void plusScore(int score){
        this.score += score;

        if(this.score > 21 && getNumberOfACE() > numberOfMinus){
            this.score -= SCORE_OF_ACE;
            numberOfMinus++;
        }
    }

    public int getScore() {
        return score;
    }

    @Override
    public void showCards(){
        System.out.print("Player: ");

        for(Card card : cards){
            System.out.print(card.getDenomination().getSymbol() + "(" + card.getPattern().getSymbol() + ")  ");
        }

        System.out.println();
    }

    private int getNumberOfACE(){

        int number = 0;

        for(Card card : cards){
            if(card.getDenomination().getSymbol().equals("A")){
                number++;
            }
        }

        return number;
    }
}
