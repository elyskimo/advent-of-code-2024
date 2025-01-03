package day3;

import java.nio.file.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Arrays;
import utils.FileUtils;

public class Part1 {
    public static void main(String[] args) {

        try {
            FileUtils fileUtils = new FileUtils();
            String filePath = "data/mul.txt";
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            String regex = "mul\\(\\d+,\\d+\\)";
            int multiplicationsResult = 0;
            Pattern pattern = Pattern.compile(regex);

            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    String mul = matcher.group();

                    String parametersSub = mul.substring(4, mul.length() - 1);

                    String[] parameters = parametersSub.split(",");
                    int multiplication = Integer.parseInt(parameters[0]) * Integer.parseInt(parameters[1]);

                    multiplicationsResult += multiplication;

                }

            }

            System.out.println("TOTAL MULTIPLICATION RESULT : " + multiplicationsResult);

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
