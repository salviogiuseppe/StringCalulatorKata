package com.fincons;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {

    public static final String DEFAULT_DELIMITER = ",|\n";
    public static final String CUSTOM_DELIMITER_PREFIX = "//";
    public static final String LINE_BREAK = "\n";
    public static final int MAX_NUMBER = 1000;

    public int add(String numbers) {
        if (isNullOrEmpty(numbers)) {
            return 0;
        }

        String delimiter = extractDelimiter(numbers);
        String sanitizedNumbers = sanitizeNumbers(numbers);

        return calculateSum(sanitizedNumbers.split(delimiter));
    }

    private boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    private String extractDelimiter(String numbers) {
        return numbers.startsWith(CUSTOM_DELIMITER_PREFIX) ? getDelimiter(numbers) : DEFAULT_DELIMITER;
    }

    private String sanitizeNumbers(String numbers) {
        return numbers.startsWith(CUSTOM_DELIMITER_PREFIX)
                ? numbers.split(LINE_BREAK, 2)[1]
                : numbers;
    }

    private String getDelimiter(String numbers) {
        if (numbers.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            String customDelimiter = numbers.split(LINE_BREAK, 2)[0].substring(2);
            return Pattern.quote(customDelimiter);
        }
        return DEFAULT_DELIMITER;
    }

    private int calculateSum(String[] parts) {
        int sum = 0;
        List<Integer> negativeNumbers = new ArrayList<>();

        for (String part : parts) {
            if (part.isEmpty()) {
                continue;
            }
            sum = getSum(part, negativeNumbers, sum);
        }
        checkNumeriNegativiThrowException(negativeNumbers);
        return sum;
    }

    private static void checkNumeriNegativiThrowException(List<Integer> negativeNumbers) {
        if (!negativeNumbers.isEmpty()) {
            throw new NumberFormatException("negatives not allowed: " + negativeNumbers);
        }
    }

    private static int getSum(String part, List<Integer> negativeNumbers, int sum) {
        int number = Integer.parseInt(part.trim());
        if (isNegativo(number)) {
            negativeNumbers.add(number);
        } else if (isNonSuperaIlMassimo(number)) {
            sum += number;
        }
        return sum;
    }

    private static boolean isNonSuperaIlMassimo(int number) {
        return number <= MAX_NUMBER;
    }

    private static boolean isNegativo(int number) {
        return number < 0;
    }


}