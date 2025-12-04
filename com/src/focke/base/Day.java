package focke.base;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class Day {
    protected List<String> input;
    protected final int day;

    public Day(int day) throws IOException {
        this.day = day;
    }

    public void createInput() throws IOException {
        String dayString;
        if (day < 10) {
            dayString = "0" + day;
        } else {
            dayString = String.valueOf(day);
        }
        final String PATH = "resources/day-" + dayString + "/input.txt";
        this.input = Files.readAllLines(Path.of(PATH));
    }

    protected boolean runTestOne() throws IOException {
        System.out.println("TestingOne not yet implemented");
        return true;
    }

    public void runTests() throws IOException {
        System.out.println("Running Tests Day: " + day);
        if (runTestOne() && runTestTwo()) {
            System.out.println("Tests Passed");
        } else {
            System.out.println("Tests Failed");
        }
    }
    protected boolean runTestTwo() throws IOException {
        System.out.println("TestingTwo not yet implemented");
        return true;
    }
    protected void solveOne() throws IOException {
        System.out.println("SolveOne not yet implemented");
    }

    protected void solveTwo() throws IOException {
        System.out.println("SolveTwo not yet implemented");
    }


    protected void solve(int n) throws IOException {
        if (n == 1) {
            solveOne();
        } else if (n == 2) {
            solveTwo();
        } else {
            System.err.println("ERROR: Wrong Tasknumber!");
        }
    }

    //TODO timer

    protected void printResult(int task, long result) {
        System.out.printf("Task: %d %n", task);
        System.out.printf("Result: %d %n", result);
        System.out.println();
    }

    protected void printBigResult(int task, BigInteger result) {
        System.out.printf("Task: %d %n", task);
        System.out.println("Result: " + result);
        System.out.println();
    }


    public void solveDay() throws IOException {
        createInput();
        System.out.printf("Day [[%d]] \n", day);
        for (int i = 1; i < 3; ++i) {
            var x = Instant.now();
            //long start = System.currentTimeMillis();
            solve(i);
            //long end = System.currentTimeMillis();
            System.out.println(Duration.between(x, Instant.now()));
            //System.out.printf("Solved in %d ms %n",  end - start);
            System.out.println();
        }

        System.out.println("------------------------------------------------");
        System.out.println();
    }
}

