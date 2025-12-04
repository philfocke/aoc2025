import focke.base.Day;
import focke.day.GiftShop;
import focke.day.Lobby;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        for (int i = 1; i < 13; ++i ) {
//           Day day = new Day(i);
//           day.solveDay();
//        }
//        Day dayTwo = new GiftShop();
//        dayTwo.runTests();
//        dayTwo.solveDay();
        Day dayThree = new Lobby();
        for (int i = 0; i < 10; ++i ) {
            dayThree.solveDay();
        }
    }
}
