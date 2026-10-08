package transaction;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionFactory {

    public Transaction createDeposit(String targetAccountNumber, double amount) {
        validateAccountNumber(targetAccountNumber);
        validateAmount(amount);

        return new Transaction(
                generateId(),
                TransactionType.DEPOSIT,
                LocalDateTime.now(),
                amount,
                0,
                null,
                targetAccountNumber
        );
    }

    public Transaction createWithdraw(String sourceAccountNumber, double amount, double fee) {
        validateAccountNumber(sourceAccountNumber);
        validateAmount(amount);
        validateFee(fee);

        return new Transaction(
                generateId(),
                TransactionType.WITHDRAW,
                LocalDateTime.now(),
                amount,
                fee,
                sourceAccountNumber,
                null
        );
    }

    public Transaction createTransfer(String sourceAccountNumber, String targetAccountNumber, double amount, double fee) {
        validateAccountNumber(sourceAccountNumber);
        validateAccountNumber(targetAccountNumber);
        validateAmount(amount);
        validateFee(fee);

        return new Transaction(
                generateId(),
                TransactionType.TRANSFER,
                LocalDateTime.now(),
                amount,
                fee,
                sourceAccountNumber,
                targetAccountNumber
        );
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    private void validateAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be empty");
        }
    }

    private void validateAmount(double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be a positive number");
        }
    }

    private void validateFee(double fee) {
        if (Double.isNaN(fee) || Double.isInfinite(fee) || fee < 0) {
            throw new IllegalArgumentException("Fee cannot be negative");
        }
    }
}