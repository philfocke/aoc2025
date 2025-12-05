package focke.day;

import focke.base.Day;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    protected void solveOne() throws IOException {
        createInput();
        long solvedInput = countFreshIngredients(this.input);
        printResult(1, solvedInput);
    }

    private long countFreshIngredients(List<String> input) {
        Map<Long, Long> freshIngredients = new HashMap<Long, Long>();
        Iterator<String> ite = input.iterator();
        long counter = 0;
        while (ite.hasNext()) {
            String next = ite.next();
            if (next.contains("-")) {
                String[] splitString = next.split("-");
                long start = Long.parseLong(splitString[0]);
                long end = Long.parseLong(splitString[1]);
                for (long i = start; i < end; ++i) {
                    freshIngredients.putIfAbsent(i, 0L);
                    freshIngredients.put(i, freshIngredients.get(i) + 1L);
                }
            } else if (!next.contains("%d") && !next.contains("-") && !next.equals("")) {
                counter += freshIngredients.getOrDefault(Long.parseLong(next), 0L);
            }
        }
        return counter;
    }
    private void cleanInput() {

    }
}
