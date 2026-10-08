package transfer;

import accounts.BankAccount;
import accounts.BusinessAccount;
import accounts.StudentAccount;
import transaction.Transaction;
import transaction.TransactionFactory;

public class WithdrawTransferService {

    private static final double BUSINESS_ACCOUNT_SERVICE_FEE = 0.01;

    private final TransactionFactory transactionFactory;

    private final TransferLoggerService transferLoggerService;

    public WithdrawTransferService(TransactionFactory transactionFactory, TransferLoggerService transferLoggerService) {
        if (transactionFactory == null || transferLoggerService == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }

        this.transactionFactory = transactionFactory;
        this.transferLoggerService = transferLoggerService;
    }

    public void withdraw(BankAccount account, double amount) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be a positive number");
        }

        double newBalance = account.getBalance() - amount;
        double serviceFee = 0;

        if (account instanceof BusinessAccount) {
            serviceFee = amount * BUSINESS_ACCOUNT_SERVICE_FEE;

            newBalance -= serviceFee;
        }

        if (newBalance < getWithdrawLimit(account)) {
            throw new IllegalArgumentException("Not enough funds on account");
        }

        Transaction transaction = transactionFactory.createWithdraw(account.getAccountNumber(), amount, serviceFee);

        account.setBalance(newBalance);

        transferLoggerService.log(transaction);
    }

    private int getWithdrawLimit(BankAccount account) {
        if (account instanceof StudentAccount) {
            return -5000;
        }

        return 0;
    }

}