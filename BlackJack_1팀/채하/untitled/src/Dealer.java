import java.util.ArrayList;

public class Dealer implements InterfaceGamer {
    public int handCount;
    private ArrayList<Card> handDeck;
    public Dealer(){
        handDeck = new ArrayList<Card>();
        handCount = 0;
    }
    public void sumHand(){
        int countA = 0;
        int answer = 0;
        for (Card card : handDeck) {
            if (card.number > 10) {
                answer += 10;
            }else if(card.number == 1){
                countA += 1;
                answer += 11;
            }else{
                answer += card.number;
            }
        }
        if (answer > 21){
            handCount =  (answer - (10*countA));
        }else{
            handCount = answer;
        }

    }
    @Override
    public void draw(Deck gameDeck,int num) {
        try {
            for (int i = 0; i < num; i++) {
                this.handDeck.add(gameDeck.Open());
            }
        }catch (IllegalArgumentException e){
            System.out.println("덱에 카드가 없습니다.");
        }
        sumHand();
    }
    @Override
    public void hit(Deck gameDeck) {
        while(handCount< 17) {
            draw(gameDeck, 1);
        }
    }

    public void printDealer(boolean dealerHide){
        System.out.print("# Dealer: ");
        if(dealerHide) {
            handDeck.get(0).showCard();
            System.out.println(" XX");
        }
        else {
            for (Card cd : handDeck) {
                cd.showCard();
            }
            System.out.println();
        }
    }


}
