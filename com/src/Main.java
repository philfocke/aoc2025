import focke.base.Day;
import focke.day.*;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        runAll();

    }
    public static void runAll() throws IOException {
        List<Day> days = new ArrayList<Day>();
        days.add(new GiftShop());
        days.add(new Lobby());
        days.add(new Cafeteria());
        days.add(new PrintingDepartment());
        days.add(new TrashCompactor());


        for (Day day : days) {
            day.solveDay();
        }
    }
}
