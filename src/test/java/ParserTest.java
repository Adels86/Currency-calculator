import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    public void checkingNumberOffCurrencies(){
        //given
        final String source = "source.xml";
        final Parser parser = new Parser();
        final int amountOfCurrency = 31;
        //when
        List<Rate> currencyListFromSource = parser.getCurrencyListFromSource(source);
        //then
        Assertions.assertEquals(amountOfCurrency,currencyListFromSource.size());


    }


}