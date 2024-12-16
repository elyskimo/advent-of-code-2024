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
            int xmasCounterInLine = 0;
            int xmasCounterInLineReversed = 0;
            int xmasCounterUp = 0;
            int xmasCounterNE = 0;
            int xmasCounterNW = 0;
            int xmasCounterDown = 0;
            int xmasCounterSE = 0;
            int xmasCounterSW = 0;

            for (int lineIndex = 0; lineIndex <= xmasArray.length - 1; lineIndex++) {
                String line = xmasArray[lineIndex];
                int[] xIndexes = findAllIndexesOfX(line);

                if (xIndexes.length == -1) {
                    continue;
                }

                int occurrencesXmas = line.split("XMAS", -1).length - 1;
                int occurrencesXmasReversed = line.split("SAMX", -1).length - 1;

                if (occurrencesXmas > 0) {
                    xmasCounter += occurrencesXmas;
                    xmasCounterInLine += occurrencesXmas;
                }

                if (occurrencesXmasReversed > 0) {
                    xmasCounter += occurrencesXmasReversed;
                    xmasCounterInLineReversed += occurrencesXmasReversed;
                }

                for (int xIndex : xIndexes) {
                    // ------------------------------------------------------ UP ------------------------------------------------------
                    if (lineIndex > 2) {
                        String lineBefore = xmasArray[lineIndex - 1];

                        if (lineBefore.charAt(xIndex) == 'M') {
                            boolean foundUp = checkUp(lineIndex - 1, xIndex, 'A');
                            if (foundUp) {
                                xmasCounter++;
                                xmasCounterUp++;
                            }
                        }

                        if (xIndex - 1 > -1 && lineBefore.charAt(xIndex - 1) == 'M') {
                            boolean foundNorthWest = checkNorthWest(lineIndex - 1, xIndex - 2, 'A');
                            if (foundNorthWest) {
                                xmasCounter++;
                                xmasCounterNW++;
                            }
                        }

                        if (xIndex + 1 < lineBefore.length() - 1 && lineBefore.charAt(xIndex + 1) == 'M') {
                            boolean foundNorthEast = checkNorthEast(lineIndex - 1, xIndex + 2, 'A');
                            if (foundNorthEast) {
                                xmasCounter++;
                                xmasCounterNE++;
                            }
                        }

                    }

                    // ------------------------------------------------------ DOWN ------------------------------------------------------
                    if (lineIndex < xmasArray.length - 3) {
                        String lineAfter = xmasArray[lineIndex + 1];

                        if (lineAfter.charAt(xIndex) == 'M') {
                            boolean foundDown = checkDown(lineIndex + 1, xIndex, 'A');
                            if (foundDown) {
                                xmasCounter++;
                                xmasCounterDown++;
                            }
                        }

                        if (xIndex - 1 > -1 && lineAfter.charAt(xIndex - 1) == 'M') {
                            boolean foundSouthWest = checkSouthWest(lineIndex + 1, xIndex - 2, 'A');
                            if (foundSouthWest) {
                                xmasCounter++;
                                xmasCounterSW++;
                            }
                        }

                        if (xIndex + 1 < lineAfter.length() - 1 && lineAfter.charAt(xIndex + 1) == 'M') {
                            boolean foundSouthEast = checkSouthEast(lineIndex + 1, xIndex + 2, 'A');
                            if (foundSouthEast) {
                                xmasCounter++;
                                xmasCounterSE++;
                            }
                        }
                    }
                }
            }


//             System.out.println("TOTAL InLine : " + xmasCounterInLine);
//             System.out.println("TOTAL InLineReversed : " + xmasCounterInLineReversed);
//             System.out.println("TOTAL Up : " + xmasCounterUp);
//             System.out.println("TOTAL NE : " + xmasCounterNE);
//             System.out.println("TOTAL NW : " + xmasCounterNW);
//             System.out.println("TOTAL Down : " + xmasCounterDown);
//             System.out.println("TOTAL SE : " + xmasCounterSE);
//             System.out.println("TOTAL SW : " + xmasCounterSW);

//             System.out.println("------------------------------------------------------------------------");
            System.out.println("TOTAL XMAS : " + xmasCounter);


        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static boolean checkDown(int lineIndex, int charIndex, char lookForChar) {
        try {
            String lineAfter = xmasArray[lineIndex + 1];
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

    public static boolean checkNorthEast(int lineIndex, int charIndex, char lookForChar) {
        try {
            String lineBefore = xmasArray[lineIndex - 1];
            if (lineBefore.charAt(charIndex) == lookForChar) {
                if (lookForChar == 'S') {
                    return true;
                }
                return checkUp(lineIndex - 1, charIndex + 1, 'S');
            }

            return false;
        } catch(Exception e) {
            return false;
        }
    }

    public static boolean checkNorthWest(int lineIndex, int charIndex, char lookForChar) {
        try {
            String lineBefore = xmasArray[lineIndex - 1];
            if (lineBefore.charAt(charIndex) == lookForChar) {
                if (lookForChar == 'S') {
                    return true;
                }
                return checkUp(lineIndex - 1, charIndex - 1, 'S');
            }

            return false;
        } catch(Exception e) {
            return false;
        }
    }
    public static boolean checkSouthEast(int lineIndex, int charIndex, char lookForChar) {
        try {
            String lineAfter = xmasArray[lineIndex + 1];
            if (lineAfter.charAt(charIndex) == lookForChar) {
                if (lookForChar == 'S') {
                    return true;
                }
                return checkDown(lineIndex + 1, charIndex + 1, 'S');
            }

            return false;
        } catch(Exception e) {
            return false;
        }
    }

    public static boolean checkSouthWest(int lineIndex, int charIndex, char lookForChar) {
        try {
            String lineAfter = xmasArray[lineIndex + 1];
            if (lineAfter.charAt(charIndex) == lookForChar) {
                if (lookForChar == 'S') {
                    return true;
                }
                return checkDown(lineIndex + 1, charIndex - 1, 'S');
            }

            return false;
        } catch(Exception e) {
            return false;
        }
    }

    public static int[] findAllIndexesOfX(String line) {
        List<Integer> indexes = new ArrayList<>();
        int index = line.indexOf('X');

        while (index != -1) {
            indexes.add(index);
            index = line.indexOf('X', index + 1);
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
