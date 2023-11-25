import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Card {
    private List<String> deck;

    public Card() {
        deck = new ArrayList<>(Arrays.asList(
                "A(♠)", "2(♠)", "3(♠)", "4(♠)", "5(♠)", "6(♠)", "7(♠)", "8(♠)", "9(♠)", "10(♠)", "J(♠)", "Q(♠)", "K(♠)",
                "A(♦)", "2(♦)", "3(♦)", "4(♦)", "5(♦)", "6(♦)", "7(♦)", "8(♦)", "9(♦)", "10(♦)", "J(♦)", "Q(♦)", "K(♦)",
                "A(♥)", "2(♥)", "3(♥)", "4(♥)", "5(♥)", "6(♥)", "7(♥)", "8(♥)", "9(♥)", "10(♥)", "J(♥)", "Q(♥)", "K(♥)",
                "A(♣)", "2(♣)", "3(♣)", "4(♣)", "5(♣)", "6(♣)", "7(♣)", "8(♣)", "9(♣)", "10(♣)", "J(♣)", "Q(♣)", "K(♣)"
        ));
    }
    public List<String> getDeck() {
        return deck;
    }
}