package generator;

public class SequentialAccountNumberGenerator implements AccountNumberGenerator {

    private static final String BANK_CODE = "6969";

    private long counter = 123456788L;

    @Override
    public String generate() {
        counter++;

        return String.format("%010d", counter) + "/" + BANK_CODE;
    }
}