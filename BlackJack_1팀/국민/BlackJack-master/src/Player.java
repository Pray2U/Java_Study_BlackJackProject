
import java.util.HashMap;
import java.util.Map;

public class Player implements Person {
    public int sum;

    Map<String, Integer> cards;
    protected Player(){
        cards = new HashMap<>();
    }

    @Override
    public int calculateSum() {
        int cal_sum = 0;
        for (Integer value : cards.values()) {
            cal_sum += value;
        }
        if(cal_sum > 21){
            for (String value : cards.keySet()) {
                if(cards.get(value) == 11){
                    cards.put(value,1);
                    cal_sum -= 10;
                    break;
                }
            }
        }
        return cal_sum;
    }

    // 카드의 값에 따라 숫자로 변환하여 반환하는 메소드
    private int getValue(String card) {
        String rank = card.substring(0, card.length() - 3); // 카드의 랭크 부분 추출
        if (rank.equals("A")) {
            return 11; // Ace는 11로 계산
        } else if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
            return 10; // King, Queen, Jack은 10으로 계산
        } else {
            return Integer.parseInt(rank); // 숫자 카드는 해당 숫자로 계산
        }
    }
    @Override
    public void put(Deck deck){
        String card = deck.pop();
        this.cards.put(card, getValue(card));
    }


}
