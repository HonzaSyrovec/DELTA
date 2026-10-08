package transfer;

import accounts.BankAccount;
import accounts.StudentAccount;
import transaction.Transaction;
import transaction.TransactionFactory;

public class DepositTransferService {

    private static final double STUDENT_ACCOUNT_DEPOSIT_BONUS = 0.005;

    private final TransactionFactory transactionFactory;

    private final TransferLoggerService transferLoggerService;

    public DepositTransferService(TransactionFactory transactionFactory, TransferLoggerService transferLoggerService) {
        if (transactionFactory == null || transferLoggerService == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }

        this.transactionFactory = transactionFactory;
        this.transferLoggerService = transferLoggerService;
    }

    public void deposit(BankAccount bankAccount, double amount) {
        if (bankAccount == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be a positive number");
        }

        double newBalance = bankAccount.getBalance() + amount;

        if (bankAccount instanceof StudentAccount) {
            double depositBonus = amount * STUDENT_ACCOUNT_DEPOSIT_BONUS;

            newBalance += depositBonus;
        }

        Transaction transaction = transactionFactory.createDeposit(bankAccount.getAccountNumber(), amount);

        bankAccount.setBalance(newBalance);

        transferLoggerService.log(transaction);
    }

}