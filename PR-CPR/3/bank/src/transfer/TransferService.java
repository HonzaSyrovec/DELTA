package transfer;

import accounts.BankAccount;
import accounts.BusinessAccount;
import transaction.Transaction;
import transaction.TransactionFactory;

public class TransferService {

    private static final double BUSINESS_ACCOUNT_TRANSFER_FEE = 0.003;

    private final TransactionFactory transactionFactory;

    private final TransferLoggerService transferLoggerService;

    public TransferService(TransactionFactory transactionFactory, TransferLoggerService transferLoggerService) {
        if (transactionFactory == null || transferLoggerService == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }

        this.transactionFactory = transactionFactory;
        this.transferLoggerService = transferLoggerService;
    }

    public void transfer(BankAccount from, BankAccount to, double amount) {
        validate(from, to, amount);

        double totalAmount = amount;
        double transferFee = 0;

        if (from instanceof BusinessAccount) {
            transferFee = amount * BUSINESS_ACCOUNT_TRANSFER_FEE;

            totalAmount += transferFee;
        }

        if (from.getBalance() < totalAmount) {
            throw new IllegalArgumentException("Not enough funds on source account");
        }

        Transaction transaction = transactionFactory.createTransfer(
                from.getAccountNumber(),
                to.getAccountNumber(),
                amount,
                transferFee
        );

        from.setBalance(from.getBalance() - totalAmount);
        to.setBalance(to.getBalance() + amount);

        transferLoggerService.log(transaction);
    }

    private void validate(BankAccount from, BankAccount to, double amount) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        if (from == to) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be a positive number");
        }
    }
}