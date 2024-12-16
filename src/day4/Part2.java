package day4;

import java.nio.file.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import utils.FileUtils;

public class Part2 {
    private static String[] xmasArray;

    public static void main(String[] args) {

        try {
            FileUtils fileUtils = new FileUtils();
            String[] xmasArrays = fileUtils.getXmasArrays();
            setXmasArray(xmasArrays);
            int xmasCounter = 0;

            char[] caseMMSS = {'M','M','S','S'};
            char[] caseMSMS = {'M','S','M','S'};
            char[] caseSSMM = {'S','S','M','M'};
            char[] caseSMSM = {'S','M','S','M'};


            for (int lineIndex = 0; lineIndex < xmasArray.length; lineIndex++) {
                String lineString = xmasArray[lineIndex];
                char[] lineChars = lineString.toCharArray();

                if (lineIndex < xmasArray.length - 2) {
                    for (int currentIndex = 0; currentIndex < lineChars.length; currentIndex++) {

                        try {
                            char currentChar = lineChars[currentIndex];

                            if (currentChar != 'M' && currentChar != 'S') {
                                continue;
                            }
                            String oneLineAfter = xmasArray[lineIndex + 1];
                            char[] oneLineAfterChars = oneLineAfter.toCharArray();

                            char letterA = oneLineAfterChars[currentIndex + 1];

                            if (letterA != 'A') {
                                continue;
                            }
                            char NW = currentChar;
                            char NE = lineChars[currentIndex + 2];
                            char[] twoLinesAfterChars = xmasArray[lineIndex+2].toCharArray();
                            char SW = twoLinesAfterChars[currentIndex];
                            char SE = twoLinesAfterChars[currentIndex+2];
                            char[] xmas = { NW, NE, SW, SE };


                            if (Arrays.equals(xmas, caseMMSS) || Arrays.equals(xmas, caseMSMS) || Arrays.equals(xmas, caseSSMM) || Arrays.equals(xmas, caseSMSM)) {
                                 xmasCounter++;
                            }
                        } catch(Exception e) {
                            continue;
                        }
                    }
                }
            }

            System.out.println("TOTAL XMAS : " + xmasCounter);


        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    static void setXmasArray(String[] value) {
        xmasArray = value;
    }

    static String[] getXmasArray() {
        return xmasArray;
    }
}
