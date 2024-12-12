package day3;

import java.nio.file.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Arrays;
import utils.FileUtils;

public class Part2 {
    public static void main(String[] args) {

        try {
            FileUtils fileUtils = new FileUtils();
            String filePath = "data/mul.txt";
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            String regex = "mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)";
            int multiplicationsResult = 0;
            Pattern pattern = Pattern.compile(regex);
            Boolean isInstructionEnabled = true;


            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    String mul = matcher.group();

                    if (!isInstructionEnabled && !mul.equals("do()")) {
                        continue;
                    }

                    if (isInstructionEnabled && mul.equals("don't()")) {
                        isInstructionEnabled = false;
                        continue;
                    }

                    if (mul.equals("do()")) {
                        if (isInstructionEnabled) {
                            continue;
                        }

                        isInstructionEnabled = true;
                        continue;
                    }

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
