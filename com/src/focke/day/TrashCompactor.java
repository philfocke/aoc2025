package focke.day;

import focke.base.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrashCompactor extends Day {
    public TrashCompactor() throws IOException {
        super(6);
    }


    @Override
    protected boolean runTestOne() throws IOException {
        createTestInput();
        long result = calcMathHomework(testInput);
        assert (result == 4277556) : "Should be 4277556 is: " + result;
        return true;
    }

    @Override
    protected boolean runTestTwo() throws IOException {
        createTestInput();
        long result = calcReverseMathHomework(testInput);
        assert (result == 3263827) : "Should be 3263827 is: " + result;
        return true;
    }

    @Override
    protected void solveTwo() throws IOException {
        createInput();
        long result = calcReverseMathHomework(input);
        printResult(2, result);
    }


    @Override
    protected void solveOne() throws IOException {
        createInput();
        long result = calcMathHomework(input);
        printResult(1, result);
    }

    private long calcReverseMathHomework(List<String> input) {

        // i -> x, j -> y
        //find next Icon /mult or + 
        // next Icon position = start
        // next Icon position -1 = end
        // next Icon position = nextIconPosition
        // Math Logic
        String rotatedString = rotateString(input);
        String[] expressions = rotatedString.trim().replaceAll("\\s+", " ").split("\\s");
        long counter = 0;
        counter += calcValueOfStrings(expressions);

       return counter; 
    }
    private long calcValueOfStrings(String[] expressions) {
        List<Long> nums = new ArrayList<Long>();
        long res = 0;
        for (String expression : expressions) {
            if (expression.contains("*") || expression.contains("+")) {
                char operator;
                if (expression.equals("*") || expression.equals("+")) {
                    operator = expression.charAt(0);
                } else{
                    operator = expression.charAt(expression.length() - 1);
                    nums.add(Long.parseLong(expression.substring(0, expression.length() - 1)));
                }
                res += operator == '*' ? multiply(nums) : add(nums);
                nums = new ArrayList<>();
            } else {
                nums.add(Long.parseLong(expression));
            }
        }
        return res;
    }
    
    private long multiply(List<Long> nums) {
        long result = 0;
        if (nums.size() < 2) {
            return result;
        } else {
           result = nums.getFirst() * nums.get(1);
           for (int i = 2; i < nums.size(); ++i) {
               result *= nums.get(i);
           }
        }
        return result;
    }
    
    private long add(List<Long> nums) {
        long result = 0;
        for (Long num : nums) {
            result += num;
        }
        return result;
    }

    private String rotateString(List<String> input) {
        // UMándern des lesens, so dass wir aus 64 23 314 -> 4 431 6 2 3 + bauendann sind wir wieder ganz noraml ... dann postfix notation
        //Stringbuilder benutzen parsen to Integer.
        StringBuilder sb = new StringBuilder();
        for (int i = input.getFirst().length() - 1; i >= 0; --i) {
            for (String s : input) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }


    private long calcMathHomework(List<String> input) {
        // 1 = add, 2 = mult
        long[][] expressions = createExpressions(input);
        long counter = 0;
        for (int i = 0; i < expressions[0].length; ++i ) {
            long expressionCounter = expressions[0][i];
            boolean mult = expressions[expressions.length -1][i] == 2;
            for (int j = 1; j < expressions.length - 1; ++j) {
                if (mult) {
                    expressionCounter *= expressions[j][i];
                } else {
                    expressionCounter += expressions[j][i];
                }
            }
            counter += expressionCounter;
        }
        return counter;
    }

    long[][] createExpressions(List<String> input) {
        // Trim über komplett Rgex raus aus bash denkweise..
        int y = input.size();
        int x = input.getFirst().trim().split("\\s+").length;
        long[][] expressions = new long[y][x];
        for (int i = 0; i < y; ++i) {
            String[] tmpString = input.get(i).trim().split("\\s+");
            for (int j = 0; j < x; ++j) {
                if (tmpString[j].contains("*") || tmpString[j].contains("+")) {
                    expressions[i][j] = tmpString[j].equals("*") ? 2 : 1;
                } else {
                    expressions[i][j] = Long.parseLong(tmpString[j]);
                }
            }
        }
        return expressions;
    }
}
