package utils;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class FileUtils {

    public int[][] getLocationArrays() throws IOException {
        String filePath = "data/locations-list.txt";
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        int rowCount = lines.size();

        int[] locations1 = new int[rowCount];
        int[] locations2 = new int[rowCount];

        for (int i = 0; i < rowCount; i++) {
            String[] parts = lines.get(i).trim().split("\\s+");
            locations1[i] = Integer.parseInt(parts[0]);
            locations2[i] = Integer.parseInt(parts[1]);
        }

        return new int[][]{locations1, locations2};
    }

    public int[][] getLevelArrays() throws IOException {
        String filePath = "data/levels.txt";
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        int rowCount = lines.size();

        List<int[]> levelsList = new ArrayList<>();

        for (String line : lines) {
            String[] levelValuesSplit = line.trim().split("\\s+");
            System.out.println(levelValuesSplit);
            int[] levelValues = Arrays.stream(levelValuesSplit).mapToInt(Integer::parseInt).toArray();

            levelsList.add(levelValues);
        }

        return levelsList.toArray(new int[levelsList.size()][]);
    }

    public String[] getXmasArrays() throws IOException {
        String filePath = "data/xmas.txt";
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        int rowCount = lines.size();

        return lines.toArray(new String[rowCount]);
    }

    public Map<Integer, List<Integer>> extractOrderMap() throws IOException {
        String filePath = "data/ordering-pages.txt";
        Map<Integer, List<Integer>> orderMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains(",") || line.length() < 1) {
                    break;
                }

                String[] parts = line.split("\\|");
                int key = Integer.parseInt(parts[0].trim());
                int value = Integer.parseInt(parts[1].trim());
                orderMap.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
            }
        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }
        return orderMap;
    }

    public List<int[]> extractPagesList() throws IOException {
        String filePath = "data/ordering-pages.txt";
        List<int[]> pagesList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("|")) {
                    continue;
                }

                if (line.length() < 1) {
                    continue;
                }

                String[] numbers = line.split(",");
                int[] array = Arrays.stream(numbers)
                                    .map(String::trim)
                                    .mapToInt(Integer::parseInt)
                                    .toArray();
                pagesList.add(array);
            }
        }  catch (Exception e) {
          System.out.println("error " + e.toString());
        }

        return pagesList;
    }
//
//     public String[][] getLabPatrolArrays() throws IOException {
//         String filePath = "data/lab-patrol.txt";
//         List<String> lines = Files.readAllLines(Paths.get(filePath));
//         int rowCount = lines.size();
//
//         char[][] result =
//
//         return lines.toArray(new String[rowCount]);
//     }

    public Character[][] getLabPatrolArrays() throws IOException {
        String filePath = "data/lab-patrol.txt";
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        int rowCount = lines.size();

        // Create a 2D Character array
        Character[][] charArray = new Character[rowCount][];

        // Populate the Character array
        for (int i = 0; i < rowCount; i++) {
            String line = lines.get(i);
            charArray[i] = line.chars()  // Convert to IntStream
                               .mapToObj(c -> (char) c)  // Convert int to char
                               .toArray(Character[]::new);  // Collect into Character[]
        }

        return charArray;
    }

}
