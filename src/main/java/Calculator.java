import java.security.InvalidParameterException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Calculator {

    public static final String XML_SOURCE = "source.xml";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        System.out.println("available currencies: " );
        calculator.getCurrencyCodes();

        System.out.println("enter the amount in euro" );
        int amount = scanner.nextInt();
        scanner.nextLine();

        System.out.println("enter target currency code" );
        String target = scanner.nextLine();

        System.out.println(calculator.calc(amount, target) + " " + target);
    }

    public double calc(double amount, String target){
        if (amount < 0){
            throw new IllegalArgumentException("the amount must be greater than zero");
        }
        String upperCaseTarget =target.toUpperCase();
        final List<Rate> rates = getCurrencyList();
        final Optional<Rate> targetRate = rates.stream()
                .filter(rate -> rate.getCurrency().equals(upperCaseTarget))
                .findFirst();
        if(targetRate.isPresent()){
            return amount * targetRate.get().getValue();
        } else {
            throw new InvalidParameterException("unknown currency codes!");
        }
    }

    public void getCurrencyCodes(){
        List <String> codesList = getCurrencyList().stream()
                .map(Rate::getCurrency)
                .collect(Collectors.toList());
        if (codesList != null){
            String currencies = codesList.stream()
                    .collect(Collectors.joining(", "));
            System.out.println(currencies);
        } else {
            throw new NullPointerException("Currency Code list is empty");
        }

    }
    public List<Rate> getCurrencyList(){
        Parser parser = new Parser();
        List<Rate> currencyListFromSource = parser.getCurrencyListFromSource(XML_SOURCE);
        if (currencyListFromSource != null){
           return currencyListFromSource;
        } else {
            throw new NullPointerException("Currency list is empty");
        }
    }
}










