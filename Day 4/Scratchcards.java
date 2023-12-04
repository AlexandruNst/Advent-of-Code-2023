import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Scratchcards {
    public static void main(String[] args) {
        int result = calculateSum("Day 4/input.txt");
        System.out.println(result);
        long result2 = calculateSum2("Day 4/input.txt");
        System.out.println(result2);
    }

    private static int calculateSum(String inputFile) {
        int sum = 0;

        try {
            File input = new File(inputFile);
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sum += calculateWinnings(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        return sum;
    }

    private static int calculateWinnings(String line) {
        int winnings = 0;

        String[] winningNumbers = line.split("\\|")[0].trim().split(":")[1].trim().split(" ");
        String[] numbers = line.split("\\|")[1].trim().split(" ");

        Set<Integer> setWinning = new HashSet<>();
        for (String winningNumber : winningNumbers)
            if (!winningNumber.equals(""))
                setWinning.add(Integer.valueOf(winningNumber));
        for (String num : numbers) {
            if (!num.equals("")) {
                if (setWinning.contains(Integer.valueOf(num))) {
                    if (winnings == 0)
                        winnings = 1;
                    else
                        winnings = winnings * 2;
                }
            }
        }

        return winnings;
    }

    private static long calculateSum2(String inputFile) {
        long sum = 0;
        int[] scratchcards = new int[199];
        for (int i = 0; i < scratchcards.length; i++) {
            scratchcards[i] = 1;
        }

        try {
            File input = new File(inputFile);
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int[] res = calculateWinnings2(line);
                int winnings = res[1];
                int game = res[0];
                for (int i = 1; i <= winnings; i++)
                    scratchcards[game - 1 + i] += scratchcards[game - 1];
            }
            for (int scratchcard : scratchcards) {
                sum += scratchcard;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        return sum;
    }

    private static int[] calculateWinnings2(String line) {
        int winnings = 0;

        String[] winningNumbers = line.split("\\|")[0].trim().split(":")[1].trim().split(" ");
        String[] numbers = line.split("\\|")[1].trim().split(" ");
        String game = line.split("\\|")[0].trim().split(":")[0].split("Card")[1].trim();

        Set<Integer> setWinning = new HashSet<>();
        for (String winningNumber : winningNumbers)
            if (!winningNumber.equals(""))
                setWinning.add(Integer.valueOf(winningNumber));
        for (String num : numbers) {
            if (!num.equals("")) {
                if (setWinning.contains(Integer.valueOf(num))) {
                    winnings++;
                }
            }
        }

        return new int[] { Integer.valueOf(game), winnings };

    }

}
