package focke.day;

import focke.base.Day;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class PrintingDepartment extends Day {
    int[][] grid;
    int[][] solvedGrid;

    public PrintingDepartment() throws IOException {
        super(4);
    }


    @Override
    protected boolean runTestOne() throws IOException {
        createTestInput();
        int[][] testGrid = createTestGrid();
        //printGrid(testGrid);
        int[][] x = createNewGrid(testGrid);
        //System.out.println("------------------------------");
        //printSolvedBoard(x);
        //assert((checkAccessiblePaper(testGrid, 0, 0))) : "Should be false";
        //assert(!(checkAccessiblePaper(testGrid, 0, 1))) : "Should be false";
        //assert(checkAccessiblePaper(testGrid, 0, 2)) : "Should be true";
        //assert(checkAccessiblePaper(testGrid, 0, 3)) : "Should be true";
        //assert(!checkAccessiblePaper(testGrid, 0, 4)) : "Should be false";
        //assert(checkAccessiblePaper(testGrid, 0, 5)) : "Should be true";
        return true;
    }


    private int[][] createNewGrid(int[][] grid) {
        // 0 no Paper
        // 1 Paper
        // 2 Accessible Paperr
        solvedGrid = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1) {
                    solvedGrid[i][j] = checkAccessiblePaper(grid, i,j) ? 2 : 1;
                } else {
                    solvedGrid[i][j] = 0;
                }
            }
        }
        return solvedGrid;
    }

    private boolean checkAccessiblePaper(int[][] grid, int y, int x) {
        int counter = 0;
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                if (
                        y + i >= 0
                                && (y + i) < grid.length
                                && x + j >= 0
                                && x + j < grid[0].length ) {
                    //System.out.println("Post x: " + (j + x) + " Pos y: " + (i + y));
                    if (!(i == 0 && j == 0)) {
                        counter += grid[i + y][j + x] == 1 ? 1 : 0;
                    }
                }
            }
        }
        //System.out.println("Counter: " + counter);
        return counter < 4;
    }

    private void newGridGeneration(int[][] tmpGrid) {
        for (int i = 0; i < tmpGrid.length; ++i) {
            for (int j = 0; j < tmpGrid[0].length ; ++j) {
                if (tmpGrid[i][j] == 2 ) {
                    tmpGrid[i][j] = 0;
                }
            }
        }
    }

    private long countAccessibleRolls(int[][] grid) {
        long count = 0;
        for (int[] i : grid) {
            for (int j : i) {
                if (j == 2 ) {
                    ++count;
                }
            }
        }
        return count;
    }

    @Override
    protected void solveTwo() throws IOException {
        createInput();
        cleanInput();
        int[][] twoGrid = createNewGrid(grid);
        long counter = 0;
        long tmpCounter = countAccessibleRolls(twoGrid);
        while (tmpCounter != 0) {
            counter = counter + tmpCounter;
            newGridGeneration(twoGrid);
            twoGrid = createNewGrid(twoGrid);
            tmpCounter = countAccessibleRolls(twoGrid);
        }
        printResult(2, counter);
    }

    @Override
    protected void solveOne() throws IOException {
        createInput();
        cleanInput();
        createNewGrid(grid);
        printResult(1, countAccessibleRolls(solvedGrid));
    }

    private void cleanInput() {
        grid = new int[input.size()][input.getFirst().length()];
        for (int i = 0; i < input.size(); ++i) {
            for (int j = 0; j < input.getFirst().length(); ++j) {
                grid[i][j] = input.get(i).charAt(j) == '@' ? 1 : 0;
            }
        }
    }

    private int[][] createTestGrid() {
        int length = testInput.size();
        int height = testInput.getFirst().length();
        int[][] testGrid = new int[height][length];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < length; ++j) {
                testGrid[i][j] = testInput.get(i).charAt(j) == '@' ? 1 : 0;
            }
        }
        return testGrid;
    }

    private void printSolvedBoard(int[][] grid ) {
        for (int[] i: grid) {
            for (int j : i) {
                if (j == 0) {
                    System.out.print(".");
                } else if (j == 1) {
                    System.out.print("@");
                } else {
                    System.out.print("x");
                }
            }
            System.out.println();
        }

    }
    private void printGrid(boolean[][] grid) {
        for (boolean[] booleans : grid) {
            for (int j = 0; j < grid[0].length; ++j) {
                System.out.print(booleans[j] ? "@" : ".");
            }
            System.out.println();
        }
    }
}
