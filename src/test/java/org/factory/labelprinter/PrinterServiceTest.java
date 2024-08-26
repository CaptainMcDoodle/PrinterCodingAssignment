package org.factory.labelprinter;

import org.factory.labelprinter.exceptions.EmptyInputException;
import org.factory.labelprinter.services.PrinterService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrinterServiceTest {

    @Test
    public void printerErrorTest_noErrors() {
        assertEquals(PrinterService.printerError("aaabbbbhaijjjm"), "0/14");
    }

    @Test
    public void printerErrorTest_errors() {
        assertEquals(PrinterService.printerError("aaaxbbbbyyhwawiwjjjwwm"), "8/22");
        assertEquals(PrinterService.printerError("www"), "3/3");
    }

    @Test
    public void printerErrorTest_emptyString() {
        assertThrows(EmptyInputException.class, () -> PrinterService.printerError(""), "0/0");

    }

    @Test
    public void printerErrorTest_specialCharacters() {
        assertEquals(PrinterService.printerError(" "), "1/1");
        assertEquals(PrinterService.printerError("!@#$%^&*()_+"), "12/12");
    }
}
