package transfer;

import transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransferLoggerService {

    private final List<Transaction> transactions = new ArrayList<>();

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
            System.out.println(transaction);
        }
    }
}