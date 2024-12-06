package utils;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

//         int[][] levels = new int[rowCount][];
        List<int[]> levelsList = new ArrayList<>();

        for (String line : lines) {
//             String[] levelValuesSplit = lines.get(i).trim().split("\\s+");
            String[] levelValuesSplit = line.trim().split("\\s+");
            System.out.println(levelValuesSplit);
            int[] levelValues = Arrays.stream(levelValuesSplit).mapToInt(Integer::parseInt).toArray();

//             levels[i] = levelValues;
            levelsList.add(levelValues);
        }

        return levelsList.toArray(new int[levelsList.size()][]);
    }


}
