package focke.base;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Task {
    private List<String> input;
    private final int taskNumber;
    private final int day;

    public Task(int day, int taskNumber) throws IOException {
        this.taskNumber = taskNumber;
        this.day = day;
    }

    public boolean createInput() throws IOException, FileNotFoundException{
        final String PATH = "";
        List<String> input = Files.readAllLines(Path.of(PATH));
        return false;
    }

    public void run() {
        System.out.println("Result not yet implemented");
    };

    public void runTest(){};


    public int getTaskNumber() {
        return taskNumber;
    }

    public List<String> getInput() {
        return  input;
    }

    public int getDay() {
        return day;
    }
}
