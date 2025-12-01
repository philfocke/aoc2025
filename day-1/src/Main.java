import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO Two
        /*
        Add b -> for loop that iterates while b is > 99 and adds Zeros hit for ever 100 ...
         */
        final String TESTINPUT = "/Users/philfocke/repos/aoc2025/day-1/testInput.txt";
        final String INPUT = "/Users/philfocke/repos/aoc2025/day-1/input.txt";
        int value = 50;
        int zerosHit = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT)))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String direction = line.substring(0, 1);
                int movement = Integer.parseInt(line.substring(1));
                if (direction.equals("L")) {
                    value = subMod100(value, movement);
                } else {
                    value = addMod100(value, movement);
                }
                if (value == 0) {
                    zerosHit++;
                }
            }
            System.out.println(zerosHit + " " + value);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static int addMod100(int a, int b) {
        System.out.printf("%d + %d mod 100 = %d \n", a, b, (a + b) % 100);
        return ((a + b) % 100);
    }

    public static int subMod100(int a, int b) {
        if (a < b) {
            return 100 - (b - a);
        } else {
            return (a - b);
        }
    }

}
class safeOpener {
    int zerosHit = 0;
    int value = 50;

    safeOpener() {
    };





}