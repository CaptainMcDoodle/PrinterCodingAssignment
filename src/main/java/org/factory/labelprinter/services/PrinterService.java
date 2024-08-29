package org.factory.labelprinter.services;

import org.factory.labelprinter.exceptions.EmptyInputException;

import java.util.Scanner;
import java.util.regex.Pattern;

public class PrinterService {
    // Regular expression to match characters not between 'a' and 'm'. It was decided to report all non-valid
    // characters, including whitespaces and special characters as faulty.
    private static final String ERROR_REGEX = "[^a-m]";
    private static final Pattern REGEX_PATTERN = Pattern.compile(ERROR_REGEX);

    public static void main(String[] args) {
        /*
         * Usually, here would be some code that gives instructions to the printer.
         * Instead, you can run the program and insert your (faulty) control strings there.
         * You can exit the program by typing 'exit'.
         */
        final Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Please input a control string for which you want to know the error percentage, or type \"exit\" to quit: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                final String errorPercentage = printerError(input);
                System.out.println("The error percentage for " + "\"" + input + "\"" + " is: " + errorPercentage + System.lineSeparator());
            } catch (final Exception e) {
                System.out.println(e.getMessage() + System.lineSeparator());
            }
        }

        System.out.println(System.lineSeparator()+"Program terminated.");
        scanner.close();
}

// Method name printerError was chosen over printer_error to stay true to java naming conventions.
public static String printerError(final String printerResponse) throws EmptyInputException {
    if (printerResponse.isEmpty()) {
        throw new EmptyInputException("The input is empty. Only non-empty strings are allowed.");
    }

    long errorCount = REGEX_PATTERN.matcher(printerResponse)
            .results()
            .count();
    return errorCount + "/" + printerResponse.length();
}
}