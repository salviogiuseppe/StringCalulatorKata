package com.fincons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCalculatorTest {
    @Test
    void testAddEmptyString() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add(""));
    }

    @Test
    void testAddSingleNumber() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(1, calculator.add("1"));
    }
    @Test
    void testAddTwoNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("1,2"));
    }
    @Test
    void testAddNullString() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add(null));
    }

    @Test
    void testAddMultipleNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1,2,3"));
        assertEquals(10, calculator.add("1,2,3,4"));
        assertEquals(15, calculator.add("1,2,3,4,5"));
    }


    @Test
    void testAddWithNewLines() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1\n2,3"));
        assertEquals(10, calculator.add("1\n2\n3,4"));
    }


/*
Step 4: support different delimiters
Support different delimiters: to change a delimiter, the beginning of the string will contain a separate line that looks like this:

"//[delimiter]\n[numbers...]"

For example "//;\n1;2" should return 3 where the default delimiter is ';'.

The first line is optional. All existing scenarios should still be supported.
 */
    @Test
    void testAddWithCustomDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("//;\n1;2"));
        assertEquals(6, calculator.add("//|\n1|2|3"));
        assertEquals(10, calculator.add("//#\n1#2#3#4"));
    }


    /*
    Step 5: negative numbers
Calling add() with a negative number will throw an exception "negatives not allowed" - and the negative that was passed.

For example add("1,4,-1") should throw an exception with the message "negatives not allowed: -1".

If there are multiple negatives, show all of them in the exception message.
     */

    @Test
    void testAddWithNegativeNumbers() {
        StringCalculator calculator = new StringCalculator();
        try {
            calculator.add("1,-2,3");
        } catch (NumberFormatException e) {
            assertEquals("negatives not allowed: [-2]", e.getMessage());
        }
    }
    @Test
    void testAddWithMultipleNegativeNumbers() {
        StringCalculator calculator = new StringCalculator();
        try {
            calculator.add("1,-2,-3");
        } catch (NumberFormatException e) {
            assertEquals("negatives not allowed: [-2, -3]", e.getMessage());
        }
    }


    /*Step 6: ignore big numbers
Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2

     */

    @Test
    void testAddWithBigNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(2, calculator.add("2,1001"));
        assertEquals(0, calculator.add("1001"));
        assertEquals(1000, calculator.add("1000,1001"));
    }



    //aggiungi test per avere coverage al 100%
    @Test
    void testAddWithEmptyDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add("//;\n"));
    }

    @Test
    void testAddWithNullInput() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add(null));
    }

    @Test
    void testCalculateSumWithEmptyParts() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add(",\n"));
    }

    @Test
    void testCheckNumeriNegativiWithoutException() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("1,2"));
    }
}
