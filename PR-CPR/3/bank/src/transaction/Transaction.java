package transaction;

import java.time.LocalDateTime;

public class Transaction {

    private final String id;

    private final TransactionType type;

    private final LocalDateTime createdAt;

    private final double amount;

    private final double fee;

    private final String sourceAccountNumber;

    private final String targetAccountNumber;

    public Transaction(
            String id,
            TransactionType type,
            LocalDateTime createdAt,
            double amount,
            double fee,
            String sourceAccountNumber,
            String targetAccountNumber
    ) {
        this.id = id;
        this.type = type;
        this.createdAt = createdAt;
        this.amount = amount;
        this.fee = fee;
        this.sourceAccountNumber = sourceAccountNumber;
        this.targetAccountNumber = targetAccountNumber;
    }

    public String getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double getAmount() {
        return amount;
    }

    public double getFee() {
        return fee;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    @Override
    public String toString() {
        return type
                + " | amount: " + amount
                + " | fee: " + fee
                + " | from: " + (sourceAccountNumber == null ? "-" : sourceAccountNumber)
                + " | to: " + (targetAccountNumber == null ? "-" : targetAccountNumber)
                + " | at: " + createdAt;
    }
}