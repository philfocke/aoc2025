package focke.base;

import java.io.IOException;

public class Day {
    private int day;
    private Task taskOne;
    private Task taskTwo;
    public Day(int day) throws IOException {
        this.day = day;
        taskOne = new Task(day, 1);
        taskTwo = new Task(day, 2);
    }

    public boolean runTests() {
        return false;
    }

    public void solveTask(Task task){
        System.out.printf("Starting Task: %s %n", task.getTaskNumber());
        long start = System.currentTimeMillis();
        task.run();
        long end = System.currentTimeMillis();
        System.out.printf("Solved in %d ms %n",  end - start);
        System.out.println();
    }

    public void solveDay() {
        System.out.printf("Day [[%d]] \n", day);
        solveTask(taskOne);
        solveTask(taskTwo);
        System.out.println("------------------------------------------------");
        System.out.println();
    }
}

