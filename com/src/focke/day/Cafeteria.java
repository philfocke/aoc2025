package focke.day;

import focke.base.Day;

import java.io.IOException;
import java.util.*;

public class Cafeteria extends Day {
    public Cafeteria() throws IOException {
        super(5);
    }

    @Override
    protected boolean runTestOne() throws IOException {
        createTestInput();
        long a = countFreshIngredients(testInput);
        assert(3L == a) : "Should be 3 is: " + a;
        return true;
    }

    @Override
    protected boolean runTestTwo() throws IOException {
        createTestInput();
        long solvedInput = createFreshIngredientsRange(this.testInput);
        assert (solvedInput == 14) : "Should be 14 is : " + solvedInput ;
        return true;
    }

    @Override
    protected void solveTwo() throws IOException {
       createInput();
       long solvedInput = createFreshIngredientsRange(this.input);
       printResult(2, solvedInput);
    }

    @Override
    protected void solveOne() throws IOException {
        createInput();
        long solvedInput = countFreshIngredients(this.input);
        printResult(1, solvedInput);
    }

    private long createFreshIngredientsRange(List<String> input) {
        // Range -> Check auf Anfang und Ende Füge zu Range hinzu wenn sie existiert..
        // Sobald fertig einmal ein sort über jedes Element? damit ich nicht die kleinste range 0-10 am Ende habe und dort nie etwas hinzugefügt wird.
        // Anfang Ende ->
        // Iteriere über meine momentane List von Longs[] wenn ein Anfang in range gefunden wird das kleiner ist füge hinzu, wenn ein Ende gefunden wird dass in der Range ist füge das hinzu
        // Oder sortiere ich erst über den Anfang? -> strings
        List<Long[]> freshIngredients = new ArrayList<Long[]>();
        Iterator<String> ite = input.iterator();
        while (ite.hasNext()) {
            String next = ite.next();
            if (next.contains("-")) {
                freshIngredients.add(getRange(next));
            } else if (!next.contains("%d") && !next.contains("-") && !next.equals("")) {
                List<Long[]> minimizedList = minimizeFreshIngredientsRange(freshIngredients);
                long counter = 0;
                for (Long[] x : minimizedList) {
                    // 250,520 -> 520,250
                    // -1 vergessen XD
                    counter += Math.subtractExact(x[1] , x[0] - 1);
                }
                return counter;
            }
        }
        return 0;
    }

    private List<Long[]> minimizeFreshIngredientsRange(List<Long[]> list) {
        // Range c 170 - 240
        // Range a 230 - 520,
        // Range b 333 - > 780
        // neuer Anfang muss größer= alterAnfang sein und neues Ende muss größer gleich altes Ende sein dann beide getauscht
        // sonst wird ein neues Array erstellt da wir Anfang sortiert haben sollten wir daher zuende sein oder?
        // Mehr math lib benutzen

        if (list.isEmpty()) return new ArrayList<>();

        list.sort(Comparator.comparingLong(a -> a[0]));

        List<Long[]> merged = new ArrayList<>();
        Long[] current = list.get(0);

        // Brauche kein [0] Check weil sorted, dann höhere zahl
        for (int i = 1; i < list.size(); ++i) {
            Long[] next = list.get(i);
            if (next[0] <= current[1]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                merged.add(current);
                current = next;
            }
        }
        merged.add(current);
        return merged;
    }



    private long countFreshIngredients(List<String> input) {
        // Range a und b nicht mehr über ganze Range -> ist sowieso ein bereich ...
        List<Long[]> freshIngredients = new ArrayList<Long[]>();
        Iterator<String> ite = input.iterator();
        long counter = 0;
        while (ite.hasNext()) {
            String next = ite.next();
            if (next.contains("-")) {
                freshIngredients.add(getRange(next));
            } else if (!next.contains("%d") && !next.contains("-") && !next.equals("")) {
                counter += checkInRange(freshIngredients, Long.parseLong(next)) ? 1 : 0;
            }
        }
        return counter;
    }

    private boolean checkInRange(List<Long[]> list, long num) {
        for (Long[] arr : list) {
            if (num >= arr[0] && num <= arr[1]) {
                return true;
            }
        }
        return false;
    }
    private Long[] getRange(String string) {
        String[] splitString = string.split("-");
        long start = Long.parseLong(splitString[0]);
        long end = Long.parseLong(splitString[1]);
        return new Long[]{start, end};
    }

}
