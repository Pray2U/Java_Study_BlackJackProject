import java.util.Stack;
import java.util.Collections;

public class Deck {
    private static final String Clover = "(♣)";
    private static final String Dia = "(♦)";
    private static final String Heart = "(♥)";
    private static final String Spade = "(♠)";

    private Stack<String> cards;

    protected Deck(){
        cards = new Stack<>();
        init();
    }

    private void init(){
        String[] suits = {Clover, Dia, Heart, Spade};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for(String suit : suits){
            for(String rank : ranks){
                cards.push(rank  + suit);
            }
        }
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public void showCards() {
        System.out.println("Deck Cards ");
        for (String card : cards) {
            System.out.println(card);
        }
    }

    public void size(){
        System.out.println(cards.size());
    }

    public String pop(){
        return cards.pop();
    }
}
