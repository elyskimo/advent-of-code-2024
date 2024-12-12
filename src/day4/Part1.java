package day4;

import java.nio.file.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import utils.FileUtils;

public class Part1 {
    private static String[] xmasArray;

    public static void main(String[] args) {

        try {
            FileUtils fileUtils = new FileUtils();
            String[] xmas = fileUtils.getXmasArrays();
            setXmasArray(xmas);
            int xmasCounter = 0;

            System.out.println(Arrays.toString(xmasArray));

            for (int lineIndex = 0; lineIndex <= xmasArray.length - 1; lineIndex++) {
                String line = xmasArray[lineIndex];
//                 int xIndex = line.indexOf('X');
                int[] xIndexes = findAllIndexesOfX(line);

                if (xIndexes.length == -1) {
                    continue;
                }

                int occurrencesXmas = line.split("XMAS", -1).length - 1;
                int occurrencesXmasReversed = line.split("SAMX", -1).length - 1;
                System.out.println("ocu "+ occurrencesXmas);
                if (occurrencesXmas > 0) {
                    xmasCounter += occurrencesXmas;
                }
                if (occurrencesXmasReversed > 0) {
                    xmasCounter += occurrencesXmasReversed;
                }

                for (int xIndex : xIndexes) {

                    System.out.println(" current line ---------- " + line + " current INDEX ---------- " + xIndex);
                    // ------------------------------------------------------ UP ------------------------------------------------------
                    if (lineIndex > 3) {
                        String lineBefore = xmasArray[lineIndex - 1];
                        System.out.println(lineBefore.charAt(xIndex));
                        if (lineBefore.charAt(xIndex) == 'M') {
                            boolean foundUp = checkUp(lineIndex - 1, xIndex, 'A');
                            if (foundUp) {
                                xmasCounter++;
                            }
                        }
                        System.out.println(lineBefore.charAt(xIndex - 1));
                        if (xIndex - 1 > -1 && lineBefore.charAt(xIndex - 1) == 'M') {
                            checkNorthWest();
                        }
                        System.out.println(lineBefore.charAt(xIndex + 1));
                        if (xIndex + 1 < lineBefore.length() - 1 && lineBefore.charAt(xIndex + 1) == 'M') {
                            checkNorthEast();
                        }

                    }

                    // ------------------------------------------------------ DOWN ------------------------------------------------------
                    if (lineIndex < xmasArray.length - 4) {
                        String lineAfter = xmasArray[lineIndex + 1];
                        System.out.println(lineAfter.charAt(xIndex));
                        if (lineAfter.charAt(xIndex) == 'M') {
                            boolean foundDown = checkDown(lineIndex + 1, xIndex, 'A');
                            if (foundDown) {
                                xmasCounter++;
                            }
                        }
                        System.out.println(lineAfter.charAt(xIndex - 1));
                        if (xIndex - 1 > -1 && lineAfter.charAt(xIndex - 1) == 'M') {
                            checkSouthWest();
                        }
                        System.out.println(lineAfter.charAt(xIndex + 1));
                        if (xIndex + 1 < lineAfter.length() - 1 && lineAfter.charAt(xIndex + 1) == 'M') {
                            checkSouthEast();
                        }
                    }
                }
                break;

            }

            System.out.println("TOTAL XMAS : " + xmasCounter);


        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static boolean checkDown(int lineIndex, int charIndex, char lookForChar) {
        try {
            String lineAfter = xmasArray[lineIndex + 1];
            System.out.println("Check down " + lineAfter + " - char -> " + charIndex + " - lookForChar -> " + lookForChar);
            if (lineAfter.charAt(charIndex) == lookForChar) {
                if (lookForChar == 'S') {
                    return true;
                }
                return checkDown(lineIndex + 1, charIndex, 'S');
            }

            return false;
        } catch(Exception e) {
            return false;
        }

    }

    public static boolean checkUp(int lineIndex, int charIndex, char lookForChar) {
        System.out.println("Check up");
        try {
            String lineBefore = xmasArray[lineIndex - 1];
            if (lineBefore.charAt(charIndex) == lookForChar) {
                if (lookForChar == 'S') {
                    return true;
                }
                return checkUp(lineIndex - 1, charIndex, 'S');
            }

            return false;
        } catch(Exception e) {
            return false;
        }


    }

    public static void checkNorthEast() {

        System.out.println("checkNorthEast");
    }

    public static void checkNorthWest() {
        System.out.println("checkNorthWest");
    }
    public static void checkSouthEast() {
        System.out.println("checkSouthEast");
    }

    public static void checkSouthWest() {
        System.out.println("checkSouthWest");
    }

    public static int[] findAllIndexesOfX(String line) {
        List<Integer> indexes = new ArrayList<>();
        int index = line.indexOf('X');

        while (index != -1) {
            indexes.add(index); // Add the found index to the list
            index = line.indexOf('X', index + 1); // Find the next index
        }

        return indexes.stream().mapToInt(i -> i).toArray();
    }

    static void setXmasArray(String[] value) {
        xmasArray = value;
    }

    static String[] getXmasArray() {
        return xmasArray;
    }
}
