package com.example.boj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class java {

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.startGame();
    }


}

class Card {
    private String suit; // 모양
    private String rank;// a~k
    private int value; // 1~10

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return rank + "(" + suit+")";
    }
}

class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"♠", "♥", "◆", "♣"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                Card card = new Card(suit, ranks[i], values[i]);
                cards.add(card);
            }
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < cards.size(); i++) {
            int j = random.nextInt(cards.size());
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public Card drawCard() {
        if (cards.size() > 0) {
            return cards.remove(cards.size() - 1);
        }
        return null;
    }
}

class Player {
    private List<Card> hand;
    private int score;

    public Player() {
        hand = new ArrayList<>();
        score = 0;
    }

    public void addCard(Card card) {
        hand.add(card);
        score += card.getValue();
    }

    public int getScore() {
        return score;
    }

    public List<Card> getHand() {
        return hand;
    }
}

class BlackjackGame {
    private Deck deck;
    private Player dealer;
    private Player player;

    public BlackjackGame() {
        deck = new Deck();
        dealer = new Player();
        player = new Player();
    }

    public void startGame() {
        deck.shuffle();
        initCards();
        PlayerTurn();
        if (player.getScore() <= 21) {
            DealerTurn();
        }
        end();
    }

    // init 플레이어와 딜러 각각 카드 두장씩 뽑기
    private void initCards() {
        dealer.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        player.addCard(deck.drawCard());

        System.out.println("Dealer: "+ dealer.getHand().get(1)+ " "+"XX");
        System.out.println("Player: " + player.getHand().get(0) + ", " + player.getHand().get(1));
    }


    private void PlayerTurn() {
        Scanner scanner = new Scanner(System.in);
        while (player.getScore() <= 21) {
            System.out.print("Hit or Stand? (H/S):");
            String choice = scanner.nextLine().toUpperCase();
            if (choice.equals("H")) {
                player.addCard(deck.drawCard());
                System.out.println("Dealer: "+ dealer.getHand().get(1)+ " "+"XX");
                System.out.println("Player's cards: " + player.getHand());
            } else if (choice.equals("S")) {
                break;
            }
        }
    }

    private void DealerTurn() {
        while (dealer.getScore() < 17) {
            dealer.addCard(deck.drawCard());
        }
        System.out.println("Dealer's cards: " + dealer.getHand());
    }

    private void end() {
        System.out.println("Player's score: " + player.getScore());
        System.out.println("Dealer's score: " + dealer.getScore());
        if (player.getScore() > 21) {
            System.out.println("Busted! Dealer wins.");
        } else if (dealer.getScore() > 21) {
            System.out.println("Dealer busted! Player wins.");
        } else if (player.getScore() == dealer.getScore()) {
            System.out.println("It's a tie.");
        } else if (player.getScore() > dealer.getScore()) {
            System.out.println("Player wins.");
        } else {
            System.out.println("Dealer wins.");
        }
    }


}