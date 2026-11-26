package transfer;

import currency.MoneyFormatter;
import transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransferLoggerService {

    private final List<Transaction> transactions = new ArrayList<>();

    private final MoneyFormatter moneyFormatter;

    public TransferLoggerService(MoneyFormatter moneyFormatter) {
        this.moneyFormatter = moneyFormatter;
    }

    public void log(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }

        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void printHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(
                    transaction.getType()
                            + " | amount: " + moneyFormatter.format(transaction.getAmount(), transaction.getCurrency())
                            + " | fee: " + moneyFormatter.format(transaction.getFee(), transaction.getCurrency())
                            + " | from: " + (transaction.getSourceAccountNumber() == null ? "-" : transaction.getSourceAccountNumber())
                            + " | to: " + (transaction.getTargetAccountNumber() == null ? "-" : transaction.getTargetAccountNumber())
            );
        }
    }
}
