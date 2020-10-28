package converter;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RomanNumeralConversionTest {

    RomanNumeralConverter c;

    @BeforeAll
    public void setup(){
        c = new RomanNumeralConverter();
    }

    @Test
    public void mustCreateConverter(){
        assertNotNull(c);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,I", "2,II", "3,III", "4,IV", "5,V", "6,VI", "7,VII", "8,VIII", "9,IX", "10,X"})
    public void oneToTenTest(String inputAndExpected) throws RomanNumeralException {
        // Arrange
        String[] pair = inputAndExpected.split(",");
        String input = pair[0];
        String expected = pair[1];

        // Act
        var result = c.toRoman(input);

        // Assert
        assertEquals(expected, result, String.format("%s was expected to be %s, but was %s", input, expected, result));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,I", "5,V", "10,X", "50,L", "100,C", "500,D", "1000,M"})
    public void basesTest(String inputAndExpected) throws RomanNumeralException {
        // Arrange
        String[] pair = inputAndExpected.split(",");
        String input = pair[0];
        String expected = pair[1];

        // Act
        var result = c.toRoman(input);

        // Assert
        assertEquals(expected, result, String.format("%s was expected to be %s, but was %s", input, expected, result));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "4,IV",
            "9,IX",
            "40,XL",
            "90,XC",
            "400,CD",
            "900,CM"
    })
    public void subtractiveForms(String inputAndExpected) throws RomanNumeralException {
        // Arrange
        String[] pair = inputAndExpected.split(",");
        String input = pair[0];
        String expected = pair[1];

        // Act
        var result = c.toRoman(input);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"3,III","8,VIII","39,XXXIX","89,LXXXIX","399,CCCXCIX", "899,DCCCXCIX"})
    public void oneBelowSubtractiveForms(String inputAndExpected) throws RomanNumeralException {
        // Arrange
        String[] pair = inputAndExpected.split(",");
        String input = pair[0];
        String expected = pair[1];

        // Act
        var result = c.toRoman(input);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"4,IV","9,IX", "49,XLIX", "99,XCIX", "499,CDXCIX", "999,CMXCIX"})
    public void oneBelowBaseTest(String inputAndExpected) throws RomanNumeralException {
        // Arrange
        String[] pair = inputAndExpected.split(",");
        String input = pair[0];
        String expected = pair[1];

        // Act
        var result = c.toRoman(input);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "39,XXXIX",
            "246,CCXLVI",
            "789,DCCLXXXIX",
            "2421,MMCDXXI",
            "160,CLX",
            "207,CCVII",
            "1009,MIX",
            "1066,MLXVI",
            "1776,MDCCLXXVI",
            "1918,MCMXVIII",
            "1954,MCMLIV",
            "2014,MMXIV",
            "3999,MMMCMXCIX",
            "2020,MMXX"
    })
    public void numbersFromWikipedia(String inputAndExpected) throws RomanNumeralException {
        // Arrange
        String[] pair = inputAndExpected.split(",");
        String input = pair[0];
        String expected = pair[1];

        // Act
        var result = c.toRoman(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustThrowExceptionWhenTryingToConvertZero() {
        // Act
        try {
            c.toRoman("0");
            fail("Expected exception when converting 0 to roman numeral");
        } catch (RomanNumeralException e){
            // pass
        }
    }

    @Test
    public void mustThrowExceptionWhenTryingToConvertNegative() {
        // Act
        try {
            c.toRoman("-1");
            fail("Expected exception when converting 0 to roman numeral");
        } catch (RomanNumeralException e){
            // pass
        }
    }

    @Test
    public void mustThrowExceptionWhenTryingToConvertNonNumber() {
        // Act
        try {
            c.toRoman("abc");
            fail("Expected exception when converting 0 to roman numeral");
        } catch (RomanNumeralException e){
            // pass
        }
    }

    @Test
    @Disabled("feature is not yet ready")
    public void mustDoStuffWhenBored() {
        // Arrange
        var expected = "bleh";

        // Act
        var result = "blah";

        // Assert
        assertEquals(expected, result);
    }
}
