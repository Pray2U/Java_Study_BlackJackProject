package org.example;

public class Main {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame();
        Player player = new Player();
        blackjackGame.runGame(player);
    }
}