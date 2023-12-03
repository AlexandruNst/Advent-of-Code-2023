import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GearRatios {
    public static void main(String[] args) {
        int result = calculateSum("Day 3/input.txt");
        System.out.println(result);
        int result2 = calculateRatio("Day 3/input.txt");
        System.out.println(result2);
    }

    private static int calculateSum(String inputFile) {
        List<char[]> matrix = new ArrayList<>();

        try {
            File input = new File(inputFile);
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                matrix.add(line.toCharArray());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        return sumMatrix(matrix);
    }

    private static int sumMatrix(List<char[]> matrix) {
        int sum = 0;

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).length; j++) {
                if (!Character.isDigit(matrix.get(i)[j]) && matrix.get(i)[j] != '.') {
                    // symbol at i, j
                    for (int r = -1; r <= 1; r++) {
                        for (int c = -1; c <= 1; c++) {
                            if (i + r >= 0 && i + r < matrix.size() && j + c >= 0 && j + c < matrix.get(0).length) {
                                if (Character.isDigit(matrix.get(i + r)[j + c])) {
                                    sum += getNumberAtPos(matrix, i + r, j + c);
                                }
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }

    private static int getNumberAtPos(List<char[]> matrix, int i, int j) {
        while (j >= 0 && Character.isDigit(matrix.get(i)[j]))
            j--;
        j++;
        int num = 0;
        while (j < matrix.get(i).length && Character.isDigit(matrix.get(i)[j])) {
            num *= 10;
            num += matrix.get(i)[j] - '0';
            matrix.get(i)[j] = '.';
            j++;
        }
        return num;
    }

    private static int calculateRatio(String inputFile) {
        List<char[]> matrix = new ArrayList<>();

        try {
            File input = new File(inputFile);
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                matrix.add(line.toCharArray());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        return ratioMatrix(matrix);
    }

    private static int ratioMatrix(List<char[]> matrix) {

        int sum = 0;

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).length; j++) {
                if (matrix.get(i)[j] == '*') {
                    // * symbol at i, j
                    List<Integer> numbersAroundGear = new ArrayList<>();
                    for (int r = -1; r <= 1; r++) {
                        for (int c = -1; c <= 1; c++) {
                            if (i + r >= 0 && i + r < matrix.size() && j + c >= 0 && j + c < matrix.get(0).length) {
                                if (Character.isDigit(matrix.get(i + r)[j + c])) {
                                    numbersAroundGear.add(getNumberAroundGear(matrix, i + r, j + c));
                                }
                            }
                        }
                    }
                    if (numbersAroundGear.size() == 2) {
                        sum += numbersAroundGear.get(0) * numbersAroundGear.get(1);
                    }
                }
            }
        }
        return sum;
    }

    private static int getNumberAroundGear(List<char[]> matrix, int i, int j) {
        while (j >= 0 && Character.isDigit(matrix.get(i)[j]))
            j--;
        j++;
        int num = 0;
        while (j < matrix.get(i).length && Character.isDigit(matrix.get(i)[j])) {
            num *= 10;
            num += matrix.get(i)[j] - '0';
            matrix.get(i)[j] = '.';
            j++;
        }
        return num;
    }
}