package org.example;


import java.util.Scanner;

public class BlackjackGame {
    private static final int INITIAL_DRAW_NUMBER = 2;
    private final Scanner scanner = new Scanner(System.in);

    public void runGame(Player player){

        Deck deck = new Deck();
        Dealer dealer = new Dealer();

        try{
            initGame(deck, player, dealer);
            startTurn(deck, player, dealer);
            showCurrentState(player, dealer, true);
        }catch (IndexOutOfBoundsException e){
            System.out.println("게임 중 오류가 발생했습니다. 게임을 재시작 합니다.");
            runGame(player);
        }
    }

    private boolean isScoreOvered(Gamer gamer){
        return gamer.getScore() > 21;
    }

    private void initGame(Deck deck, Gamer player, Gamer dealer){
        deck.setDeck();
        deck.shuffle();
        setGamerInitialState(deck, player, dealer);
    }

    private void setGamerInitialState(Deck deck,Gamer player, Gamer dealer){
        for(int i = 0; i < INITIAL_DRAW_NUMBER; i++){
            player.hit(deck);
            dealer.hit(deck);
        }
    }


    private void showCurrentState(Gamer player, Dealer dealer, boolean finish){
        System.out.println("---------- Jack's BlackJack Game ----------");

        if (finish) {
            dealer.showCards();
        } else {
            dealer.showInitCards();
        }
        player.showCards();

        System.out.println("-------------------------------------------");
    }



    private void startTurn(Deck deck, Gamer player, Dealer dealer){
        showCurrentState(player, dealer, false);

        System.out.println("Hit or Stand? (H/S) :");
        String answer = scanner.next();

        if(answer.equals("h") || answer.equals("H")){
            player.hit(deck);

            if(!isScoreOvered(player)){
                startTurn(deck, player, dealer);
            }
        }else if(answer.equals("s") || answer.equals("S")){
            dealer.hitUpTo17Score(deck);

            if(!isScoreOvered(dealer)){
                printResult(player, dealer);
            }
        }else{
            System.out.println("잘못된 입력입니다. 다시입력해주세요");
            startTurn(deck, player, dealer);
        }
    }
    private void printResult(Gamer player, Gamer dealer){
        if(player.getScore() > dealer.getScore()){
            System.out.println("Player Wins...");
        }else if(player.getScore() < dealer.getScore()){
            System.out.println("Dealer Wins...");
        }else{
            System.out.println("Draws...");
        }
    }
}
