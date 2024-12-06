import java.nio.file.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicInteger;
import utils.FileUtils;


public class Exercise2 {
    public static void main(String[] args) {

        try {
            FileUtils fileUtils = new FileUtils();
            int[][] locations = fileUtils.getLocationArrays();

            int[] locations1 = locations[0];
            int[] locations2 = locations[1];
            int rowCount = locations1.length;


            Arrays.sort(locations1);
            Arrays.sort(locations2);


            AtomicInteger totalSimilarity = new AtomicInteger(0);

             Arrays.stream(locations2)
                  .boxed()
                  .collect(Collectors.groupingBy(s -> s))
                  .forEach((location, occurrences) -> {
                     if (Arrays.stream(locations1).anyMatch(x -> x == location)) {
                         totalSimilarity.addAndGet(location * occurrences.size());
                     }
                  });

             System.out.println("TOTAL SIMILARITY : " + totalSimilarity);

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
