import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -123})
    public void shouldReturnExceptionWhenAmountIsLessThanZero(double input) {
        //given
        String target = "PLN";
        //when & then
        assertThrows(IllegalArgumentException.class, () -> calculator.calc(input, target));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABC", "abc"})
    public void shouldReturnExceptionWhenProvidedCodeIsIncorrect(String input) {
        //given
        double amount = 100;
        //when & then
        assertThrows(InvalidParameterException.class, () -> calculator.calc(amount, input));

    }

    @ParameterizedTest
    @MethodSource("provideData")
    public void shouldReturnCorrectValueOfTheExchangedCurrency(double amount, String target, double expectedResult) {
        //given
        //when
        double result = calculator.calc(amount, target);
        //then
        assertEquals(expectedResult, result);

    }

    private static Stream<Arguments> provideData() {
        return Stream.of(
                Arguments.of(100, "pLN", 473.75),
                Arguments.of(1000, "GBP", 839.9),
                Arguments.of(1, "DKK", 7.4438),
                Arguments.of(0, "HUF", 0),
                Arguments.of(100, "usd", 101.98),
                Arguments.of(100, "JPy", 13641.999999999998),
                Arguments.of(100, "BgN", 195.57999999999998),
                Arguments.of(100, "CZK", 2461.0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCurrency")
    public void shouldReturnCurrencyList(Rate rate) {
        List<Rate> currencyList = calculator.getCurrencyList();
        assertTrue(currencyList.contains(rate));
        assertTrue(currencyList.size() == 31);
    }

    private static Stream<Arguments> provideCurrency() {
        return Stream.of(
                Arguments.of(new Rate("USD", 1.0198)),
                Arguments.of(new Rate("JPY", 136.42)),
                Arguments.of(new Rate("BGN", 1.9558)),
                Arguments.of(new Rate("CZK", 24.610)),
                Arguments.of(new Rate("DKK", 7.4438)),
                Arguments.of(new Rate("GBP", 0.83990)),
                Arguments.of(new Rate("HUF", 404.80)),
                Arguments.of(new Rate("PLN", 4.7375)),
                Arguments.of(new Rate("RON", 4.9343)),
                Arguments.of(new Rate("SEK", 10.3875)),
                Arguments.of(new Rate("CHF", 0.9744)),
                Arguments.of(new Rate("ISK", 138.30)),
                Arguments.of(new Rate("NOK", 9.8773)),
                Arguments.of(new Rate("HRK", 7.5180))
        );
    }
}