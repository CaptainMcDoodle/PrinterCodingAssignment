package org.factory.labelprinter.services;

import org.factory.labelprinter.exceptions.EmptyInputException;

import java.util.Scanner;
import java.util.regex.Pattern;

public class PrinterService {

    public static void main(String[] args) {
        /*
         * Usually, here would be some code that gives instructions to the printer.
         * Instead, you can run the program and insert your (faulty) control strings there.
         * You can exit the program by typing 'exit'.
         */

        final Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.print("Please input a control string for which you want to know the error percentage, or type \"exit\" to quit: ");
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("exit")) {
                String errorPercentage;
                try {
                     errorPercentage = printerError(input);
                } catch (final Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                System.out.println("The error percentage for " + "\"" + input + "\"" + " is: " + errorPercentage + System.lineSeparator());
            }
        } while (!input.equalsIgnoreCase("exit"));

        System.out.println(System.lineSeparator() + "Program terminated.");
        scanner.close();
    }

    // Method name printerError was chosen over printer_error to stay true to java naming conventions.
    public static String printerError(final String printerResponse) throws EmptyInputException{
        if (printerResponse.isEmpty()) {
            throw new EmptyInputException("The input is empty. Please input a non-empty string or type \"exit\" to quit te program:" + System.lineSeparator());
        }

        // Regular expression to match characters not between 'a' and 'm'. It was decided to report all non-valid
        // characters, including whitespaces and special characters as faulty.
        final String errorRegex = "[^a-m]";
        final long errorCount = Pattern.compile(errorRegex)
                .matcher(printerResponse)
                .results()
                .count();

        /* Depending on how critical the code is, how often it would run and if it would block other processes while
         * running, you could decide not to implement a regex pattern. The following code could be a bit more efficient,
         * but the Regex pattern implemented in the Stream API is more concise and, depending on preference, more readable.
         */
            //        final String supportedColors = "abcdefghijklm";
            //        int errorCount = 0;
            //        for (int i = 0; i < printerResponse.length(); i++) {
            //            if (supportedColors.indexOf(printerResponse.charAt(i)) == -1) {
            //                errorCount++;
            //            }
            //        }

        return errorCount + "/" + printerResponse.length();
    }
}