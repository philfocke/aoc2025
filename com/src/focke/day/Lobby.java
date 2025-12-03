package focke.day;

import focke.base.Day;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Lobby extends Day {

    public Lobby() throws IOException {
        super(3);
    }


    @Override
    protected void solveTwo() throws IOException {
        BigInteger res = BigInteger.ZERO;
        for (String line: input) {
            res = res.add(findLargestBigJolt(line, 12)) ;
        }
        printBigResult(2, res);
    }
    @Override
    protected void solveOne() throws IOException {
        long res = input.stream().mapToLong((l) -> findLargestJolt(l, 2)).sum();
        printResult(1, res);
    }

    public boolean runTestOne() {
        assert (98 == findLargestJolt("987654321111111", 2)) : "Should be 98";
        assert (89 == findLargestJolt("811111111111119", 2)) : "Should be 89";
        assert (78 == findLargestJolt("234234234234278", 2)) : "Should be 78";
        assert (92 == findLargestJolt("818181911112111", 2)) : "Should be 92";
        return true;
    }

    public boolean runTestTwo() {
        BigInteger one = new BigInteger("987654321111");
        BigInteger two = new BigInteger("811111111119");
        BigInteger three = new BigInteger("434234234278");
        BigInteger four = new BigInteger("888911112111");

        assert (one.equals(findLargestBigJolt("987654321111111", 12))) : "Should be 987654321111";
        assert (two.equals(findLargestBigJolt("811111111111119", 12))) : "Should be 811111111119";
        assert (three.equals(findLargestBigJolt("234234234234278", 12))) : "Should be 434234234278";
        assert (four.equals(findLargestBigJolt("818181911112111", 12))) : "Should be 888911112111";
        return true;
    }


    private BigInteger findLargestBigJolt(String line, int size) {
        BigInteger res = BigInteger.ZERO;
        int start = 0;

        for (int i = 0; i < size; i++) {
            int remaining = size - (i + 1);

            int end = line.length() - remaining;

            int curJolt = -1;
            int bestPos = -1;

            for (int j = start; j < end; j++) {
                int curDigit = line.charAt(j) - '0';
                if (curDigit > curJolt) {
                    curJolt = curDigit;
                    bestPos = j;
                }
            }

            //prettier please..
            res = res.multiply(BigInteger.TEN).add(new BigInteger(String.valueOf(curJolt)));
            start = bestPos + 1;
        }
        return res;
    }


    private long findLargestJolt(String line, int size) {
        long res = 0;
        int start = 0;

        for (int i = 0; i < size; i++) {
            int remaining = size - (i + 1);

            int end = line.length() - remaining;

            int curJolt = -1;
            int bestPos = -1;

            for (int j = start; j < end; j++) {
                int curDigit = line.charAt(j) - '0';
                if (curDigit > curJolt) {
                    curJolt = curDigit;
                    bestPos = j;
                }
            }

            res = res * 10 + curJolt;
            start = bestPos + 1;
        }
        return res;
    }
}

