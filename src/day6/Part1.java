package day6;

import java.nio.file.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import utils.FileUtils;

public class Part1 {
    private static Character[][] labPatrolArrays;

    public static void main(String[] args) {
        try {
            FileUtils fileUtils = new FileUtils();

            labPatrolArrays = fileUtils.getLabPatrolArrays();
            int[] currentPosition = new int[2];
            boolean isOut = false;
            String currentDirection = "up";
            Map<String, String> nextDirections = Map.of(
                "up", "right",
                "right", "down",
                "down", "left",
                "left", "up"
            );

            for (int lineIndex = 0; lineIndex < labPatrolArrays.length; lineIndex++) {
                Character[] labPatrolLine = labPatrolArrays[lineIndex];
//                 System.out.println(Arrays.toString(labPatrolLine) + "     " + Arrays.asList(labPatrolLine).contains('^'));
                for (int positionIndex = 0; positionIndex < labPatrolLine.length; positionIndex++) {
                    Character position = labPatrolLine[positionIndex];
                    if (position != '^') {
                        continue;
                    }
                    currentPosition[0] = lineIndex;
                    currentPosition[1] = positionIndex;

                }

            }

            while (!isOut) {
                if (labPatrolArrays[currentPosition[0]][currentPosition[1]] != 'X' && labPatrolArrays[currentPosition[0]][currentPosition[1]] != '#') {
                    labPatrolArrays[currentPosition[0]][currentPosition[1]] = 'X';
                }

                try {
                    boolean foundWay = false;
                    while (!foundWay) {
                        int[] nextPosition = getNextPosition(currentDirection, currentPosition);

                        Character newPosition = labPatrolArrays[nextPosition[0]][nextPosition[1]];
//                         System.out.println("OBSTACLE : " + newPosition + "   ==== DIRECTION : " + currentDirection + "    ==== NEXT POSITION COORDINATES :  " + Arrays.toString(nextPosition));
                        if (newPosition == '#') {
                            currentDirection = nextDirections.get(currentDirection);
                            continue;
                        }

                        currentPosition = nextPosition;
                        foundWay = true;
                    }

                } catch (Exception e) {
                    System.out.println("ERROR: " + e.toString());
                    isOut = true;
                }

            }
            int sumDistinctPositions = 0;
            for (int lineIndex = 0; lineIndex < labPatrolArrays.length; lineIndex++) {
                Character[] labPatrolLine = labPatrolArrays[lineIndex];
//                 System.out.println(Arrays.toString(labPatrolLine));
                int countXInLine = (int) Arrays.stream(labPatrolLine)
                                              .filter(c -> c == 'X')
                                              .count();
                sumDistinctPositions += countXInLine;
            }


            System.out.println("TOTAL DISTINCT POSITIONS: " + sumDistinctPositions);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    private static int[] getNextPosition(String currentDirection, int[] currentPosition) {
        int[] nextPosition = currentPosition.clone();
        switch (currentDirection) {
            case "up":
                nextPosition[0] = currentPosition[0] - 1;
                break;
            case "down":
                nextPosition[0] = currentPosition[0] + 1;
                break;
            case "right":
                nextPosition[1] = currentPosition[1] + 1;
                break;
            case "left":
                nextPosition[1] = currentPosition[1] - 1;
                break;
            default:
                break;
        }

        return nextPosition;
    }
}