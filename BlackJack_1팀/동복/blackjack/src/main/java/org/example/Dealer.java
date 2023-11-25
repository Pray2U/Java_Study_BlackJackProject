package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements Gamer{
    private static final int SCORE_OF_ACE = 10;
    private static final int DEALER_DRAW_MAX_SCORE = 16;
    private List<Card> cards = new ArrayList<>();
    private int score = 0;
    private int numberOfMinus = 0;

    public int getScore() {
        return score;
    }

    private Card draw(Deck deck) {
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
        if(this.score > 21){
            if(getNumberOfACE() > numberOfMinus){
                this.score -= SCORE_OF_ACE;
                numberOfMinus++;
            }
        }

    }

    @Override
    public void showCards(){
        System.out.print("Dealer: ");
        for(Card card : cards) {
            System.out.print(card.getDenomination().getSymbol() + "(" + card.getPattern().getSymbol() + ")  ");
        }
        System.out.println();
    }

    public void showInitCards(){
        System.out.print("Dealer: ");
        System.out.print(cards.get(0).getDenomination().getSymbol() + "(" + cards.get(0).getPattern().getSymbol() + ")  XX");
        System.out.println();
    }

    private int getNumberOfACE(){

        int number = 0;

        for(Card card : cards){
            if(card.getDenomination().getSymbol() == "A"){
                number++;
            }
        }
        return number;
    }

    public void hitUpTo17Score(Deck deck){
        while(this.score <= DEALER_DRAW_MAX_SCORE){
            hit(deck);
        }
    }
}
