package day1;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import utils.FileUtils;

public class Exercise1 {
    public static void main(String[] args) {

        try {
            FileUtils fileUtils = new FileUtils();
            int[][] locations = fileUtils.getLocationArrays();

            int[] locations1 = locations[0];
            int[] locations2 = locations[1];
            int rowCount = locations1.length;

            Arrays.sort(locations1);
            Arrays.sort(locations2);

            int totalDistance = 0;

            for (int i = 0; i < rowCount; i++) {
                int location1 = locations1[i];
                int location2 = locations2[i];

                 totalDistance += Math.abs(location1 - location2);
            }
             System.out.println("TOTAL DISTANCE : " + totalDistance);

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
