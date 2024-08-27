package org.factory.labelprinter.services;

import org.factory.labelprinter.exceptions.EmptyInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrinterServiceTest {

    @Test
    public void printerErrorTest_noErrors() throws EmptyInputException {
        assertEquals(PrinterService.printerError("aaabbbbhaijjjm"), "0/14");
    }

    @Test
    public void printerErrorTest_errors() throws EmptyInputException {
        assertEquals(PrinterService.printerError("aaaxbbbbyyhwawiwjjjwwm"), "8/22");
        assertEquals(PrinterService.printerError("www"), "3/3");
    }

    @Test
    public void printerErrorTest_exceptionString() {
        assertThrows(EmptyInputException.class, () -> PrinterService.printerError(""));
    }

    @Test
    public void printerErrorTest_specialCharacters() throws EmptyInputException {
        assertEquals(PrinterService.printerError(" "), "1/1");
        assertEquals(PrinterService.printerError("!@#$%^&*()_+"), "12/12");
    }
}
