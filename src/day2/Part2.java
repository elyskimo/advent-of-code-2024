package day2;

import java.nio.file.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;
import utils.FileUtils;


public class Part2 {
    public static void main(String[] args) {

        try {
            FileUtils fileUtils = new FileUtils();

            int numberOfSafeLevels = getNumberOfSaveLevels();

            System.out.println("TOTAL SAFE LEVELS : " + numberOfSafeLevels);

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static int getNumberOfSaveLevels() throws IOException {
        String filePath = "data/levels.txt";

        int numberOfSafeLevels = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] levelValuesSplit = line.trim().split("\\s+");
                int[] levelValues = new int[levelValuesSplit.length];

                for (int i = 0; i < levelValuesSplit.length; i++) {
                    levelValues[i] = Integer.parseInt(levelValuesSplit[i]);
                }

                boolean isSafe = checkSafety(levelValues);

                if (isSafe) {
                    numberOfSafeLevels++;
                    continue;
                }

                isSafe = checkByRemovingOneLevel(levelValues);

                if (isSafe) {
                    numberOfSafeLevels++;
                    continue;
                }

            }
        }

        return numberOfSafeLevels;
    }

    private static boolean checkSafety(int[] levelValues) {
        if (levelValues.length < 2) {
            return false;
        }
        int lastKey = levelValues.length - 1;
        int firstValue = levelValues[0];
        int lastValue = levelValues[lastKey];

        if (firstValue == lastValue) {
            return false;
        }


        int[] differences = getDifferences(levelValues);

        return checkDifferences(differences, firstValue, lastValue);

    }


    private static int[] getDifferences(int[] levelValues) {
        int lastKey = levelValues.length - 1;
        int[] differences = new int[lastKey];


        for (int i = 0; i < lastKey; i++) {
            differences[i] = levelValues[i + 1] - levelValues[i];
        }

        return differences;
    }

    private static boolean checkDifferences(int[] differences, int firstValue, int lastValue) {

        if (firstValue < lastValue) {
            return Arrays.stream(differences).allMatch(val -> val > 0 && val < 4);
        }

        return Arrays.stream(differences).allMatch(val -> val < 0 && val > -4);
    }

    public static boolean checkByRemovingOneLevel(int[] levelValues) {
        for (int i = 0; i <= levelValues.length - 1; i++) {
                ArrayList<Integer> levelValuesList = new ArrayList<>(Arrays.asList(Arrays.stream(levelValues).boxed().toArray(Integer[]::new)));
                levelValuesList.remove(i);

                int[] levelValuesReduced = levelValuesList.stream().mapToInt(Integer::intValue).toArray();
                int[] differences = getDifferences(levelValuesReduced);

                int lastKey = levelValuesReduced.length - 1;
                boolean isSafe = checkDifferences(differences, levelValuesReduced[0], levelValuesReduced[lastKey]);

                if (isSafe) {
                    return true;
                }
        }

        return false;
    }


}
