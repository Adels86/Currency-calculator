public class Rate {
    private String Currency;
    private double value;

    public Rate(String currency, double value) {
        Currency = currency;
        this.value = value;
    }

    public String getCurrency() {
        return Currency;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rate rate = (Rate) o;

        if (Double.compare(rate.value, value) != 0) return false;
        return Currency != null ? Currency.equals(rate.Currency) : rate.Currency == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = Currency != null ? Currency.hashCode() : 0;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "Currency='" + Currency + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
