import focke.base.Day;
import focke.day.Cafeteria;
import focke.day.GiftShop;
import focke.day.Lobby;
import focke.day.PrintingDepartment;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //runAll();
        Day dayFour = new PrintingDepartment();
        dayFour.solveDay();

    }
    public static void runAll() throws IOException {
        List<Day> days = new ArrayList<Day>();
        days.add(new GiftShop());
        days.add(new Lobby());
        days.add(new Cafeteria());
        days.add(new PrintingDepartment());
    }
}
