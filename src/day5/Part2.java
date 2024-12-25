package day5;

import java.nio.file.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import utils.FileUtils;

public class Part2 {
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

                if (!isInRightOrder) {
                    // REORDER
                    System.out.println("pagesInLine before sort === " + Arrays.toString(pagesInLine));
                    Integer[] pagesBoxed = Arrays.stream(pagesInLine).boxed().toArray(Integer[]::new);
                     Arrays.sort(pagesBoxed, (a, b) -> {
                        if (a.equals(b)) return 0;

                        // Get the order list for 'a' and 'b'
                        List<Integer> afterA = orderMap.getOrDefault(a, Collections.emptyList());
                        List<Integer> afterB = orderMap.getOrDefault(b, Collections.emptyList());

                        // If 'b' should be after 'a', then 'a' comes first
                        if (afterA.contains(b)) return -1;

                        // If 'a' should be after 'b', then 'b' comes first
                        if (afterB.contains(a)) return 1;

                        // If no specific order is defined, maintain current order
                        return 0;
                    });
                    for (int i = 0; i < pagesInLine.length; i++) {
                        pagesInLine[i] = pagesBoxed[i];
                    }

                    int middleIndex = pagesInLine.length / 2;
                    sumMiddlePage += pagesInLine[middleIndex];
                }
            }

            System.out.println("TOTAL SUM: " + sumMiddlePage);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public static int[] insertAfter(int[] array, int target, int newElement) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IllegalArgumentException("Target element not found");
        }

        int[] newArray = new int[array.length + 1];
        for (int i = 0, j = 0; i < newArray.length; i++) {
            newArray[i] = (i == index + 1) ? newElement : array[j++];
        }
        return newArray;
    }

    public static int[] insertBefore(int[] array, int target, int newElement) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IllegalArgumentException("Target element not found");
        }

        int[] newArray = new int[array.length + 1];
        for (int i = 0, j = 0; i < newArray.length; i++) {
            if (i == index) {
                newArray[i] = newElement;
            } else {
                newArray[i] = array[j++];
            }
        }
        return newArray;
    }
}