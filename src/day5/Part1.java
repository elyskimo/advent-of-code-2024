package day5;

import java.nio.file.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import utils.FileUtils;

public class Part1 {
    public static void main(String[] args) {
        try {
            FileUtils fileUtils = new FileUtils();

            Map<Integer, List<Integer>> orderMap = fileUtils.extractOrderMap();
            List<int[]> pagesList = fileUtils.extractPagesList();

            int sumMiddlePage = 0;

            for (int[] pagesInLine : pagesList) {
                boolean isInRightOrder = true;
                for (int index = 0; index < pagesInLine.length; index++) {
                    int page = pagesInLine[index];

                    if (orderMap.containsKey(page)) {
                        List<Integer> mustBeAfterPages = orderMap.get(page);

                        for (int i = 0; i < pagesInLine.length; i++) {
                            if (i == index) {
                                continue;
                            }
                            int pageToCheck = pagesInLine[i];

                            if (mustBeAfterPages != null && mustBeAfterPages.contains(pageToCheck)) {
                                isInRightOrder = index < i;
                                if (!isInRightOrder) {
                                    break;
                                }
                            }
                        }

                        if (!isInRightOrder) {
                            break;
                        }

                    }
                }

                if (isInRightOrder) {
                    int middleIndex = pagesInLine.length / 2;
                    sumMiddlePage += pagesInLine[middleIndex];
                }

            }

            System.out.println("TOTAL SUM: " + sumMiddlePage);



        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
        }
    }
}