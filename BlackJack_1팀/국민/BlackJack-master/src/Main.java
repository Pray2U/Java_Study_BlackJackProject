import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            System.out.println("게임 시작 하시겠습니까? (Y / N) : ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();

            if(input.equalsIgnoreCase("Y")){
                Game game = new Game();
            }
            else if(input.equalsIgnoreCase("N")) {
                break;
            }
            else{
                System.out.println("잘못 입력하셨습니다.");
            }
        }
    }
}
