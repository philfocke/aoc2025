import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        final String TESTINPUT = "/Users/philfocke/repos/aoc2025/day-1/testInput.txt";
        final String INPUT = "/Users/philfocke/repos/aoc2025/day-1/input.txt";

        safeOpener safeOpenerOne =  new safeOpener();
//        safeOpenerOne.openSafe(TESTINPUT);
//        safeOpenerOne.openSafe(INPUT);
        safeOpener safeOpenerTwo = new safeOpenerTwo();
        safeOpenerTwo.openSafe(TESTINPUT);

    }



}
class safeOpener {
    int zerosHit = 0;
    int value = 50;

    safeOpener() {
    }

    void openSafe(String path) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String direction = line.substring(0, 1);
                int movement = Integer.parseInt(line.substring(1));
                if (direction.equals("L")) {
                    subMod100(value, movement);
                } else {
                    addMod100(value, movement);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.printf("Zeros hit: %d \n", zerosHit);
    }


    void checkZero() {
        zerosHit += value == 0 ? 1 : 0;
    }

    void subMod100(int a, int b) {
        if (a < b) {
            value = 100 - (b - a);
        } else {
            value = (a - b);
        }
        checkZero();
    }

    void addMod100(int a, int b) {
        value = ((a + b) % 100);
        checkZero();
    }
}

class safeOpenerTwo extends safeOpener {



    @Override
    void subMod100(int a, int b) {
        while (b > 99) {
            ++zerosHit;
            b -= 100;
        }
        if (a < b) {
            ++zerosHit;
            value = 100 - (b - a);
        } else {
            value = (a - b);
        }
        super.checkZero();
    }

    @Override
    void addMod100(int a, int b) {
        System.out.printf("%d \n", b);
        while (b > 99) {
            ++zerosHit;
            b -= 100;
        }
        if (a + b > 100) {
            ++zerosHit;
            value = (a + b) % 100;
            super.checkZero();
        }
    }
}