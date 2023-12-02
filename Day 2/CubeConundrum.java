import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CubeConundrum {
    public static void main(String[] args) {
        int result = calculateSum("Day 2/input.txt");
        System.out.println(result);
        int result2 = calculateSum2("Day 2/input.txt");
        System.out.println(result2);
    }

    private static int calculateSum(String inputFile) {
        int sum = 0;
        int maxRed = 12;
        int maxBlue = 14;
        int maxGreen = 13;

        try {
            File file = new File(inputFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (isPossibleGame(line, maxRed, maxBlue, maxGreen))
                    sum += getGameIdOf(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return sum;
    }

    private static boolean isPossibleGame(String line, int maxRed, int maxBlue, int maxGreen) {
        String game = line.split(":")[1].trim();
        String[] subsets = game.split(";");
        for (String subset : subsets) {
            String[] cubes = subset.trim().split(",");
            for (String cube : cubes) {
                String[] pick = cube.trim().split(" ");
                int number = Integer.valueOf(pick[0]);
                String colour = pick[1];
                if (colour.equals("red") && number > maxRed)
                    return false;
                else if (colour.equals("blue") && number > maxBlue)
                    return false;
                else if (colour.equals("green") && number > maxGreen)
                    return false;
            }
        }
        return true;
    }

    private static int getGameIdOf(String line) {
        String game = line.split(":")[0].trim();
        int id = Integer.valueOf(game.split(" ")[1]);
        return id;
    }

    private static int calculateSum2(String inputFile) {
        int sum = 0;

        try {
            File file = new File(inputFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sum += powerOfSet(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return sum;
    }

    private static int powerOfSet(String line) {
        String game = line.split(":")[1].trim();
        String[] subsets = game.split(";");
        int maxRed = 0, maxBlue = 0, maxGreen = 0;
        for (String subset : subsets) {
            String[] cubes = subset.trim().split(",");
            for (String cube : cubes) {
                String[] pick = cube.trim().split(" ");
                int number = Integer.valueOf(pick[0]);
                String colour = pick[1];
                if (colour.equals("red") && number > maxRed)
                    maxRed = number;
                else if (colour.equals("blue") && number > maxBlue)
                    maxBlue = number;
                else if (colour.equals("green") && number > maxGreen)
                    maxGreen = number;
            }
        }
        return maxRed * maxBlue * maxGreen;
    }
}
