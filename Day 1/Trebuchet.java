import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Trebuchet {
    public static void main(String[] args) {
        int result = calculateSum("Day 1/input.txt");
        System.out.println(result);
        int result2 = calculateSum2("Day 1/input.txt");
        System.out.println(result2);

    }

    private static int calculateSum(String inputFile) {
        int sum = 0;

        try {
            File input = new File(inputFile);
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sum += getCalibrationValue(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        return sum;
    }

    private static int getCalibrationValue(String line) {
        String numbersOnly = line.replaceAll("[^0-9]", "");
        int first = numbersOnly.charAt(0) - '0';
        int last = numbersOnly.charAt(numbersOnly.length() - 1) - '0';
        int calibrationValue = first * 10 + last;

        return calibrationValue;
    }

    private static int calculateSum2(String inputFile) {
        int sum = 0;
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);

        try {
            File input = new File(inputFile);
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sum += getCalibrationValue2(line, map);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        return sum;
    }

    private static int getCalibrationValue2(String line, Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i)))
                sb.append(line.charAt(i));
            else {
                for (String number : map.keySet()) {
                    if (line.substring(i).startsWith(number))
                        sb.append(map.get(number));
                }
            }
        }
        String numbersOnly = sb.toString();
        int first = numbersOnly.charAt(0) - '0';
        int last = numbersOnly.charAt(numbersOnly.length() - 1) - '0';
        int calibrationValue = first * 10 + last;

        return calibrationValue;
    }
}
