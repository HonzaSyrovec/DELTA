package transfer;

import accounts.BankAccount;
import accounts.BusinessAccount;
import accounts.StudentAccount;

public class WithdrawTransferService {

    private static final double BUSINESS_ACCOUNT_SERVICE_FEE = 0.01;

    public void withdraw(BankAccount account, double amount) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be a positive number");
        }

        double newBalance = account.getBalance() - amount;

        if (account instanceof BusinessAccount) {
            double serviceFee = amount * BUSINESS_ACCOUNT_SERVICE_FEE;

            newBalance -= serviceFee;
        }

        if (newBalance < getWithdrawLimit(account)) {
            throw new IllegalArgumentException("Not enough funds on account");
        }

        account.setBalance(newBalance);
    }

    private int getWithdrawLimit(BankAccount account) {
        if (account instanceof StudentAccount) {
            return -5000;
        }

        return 0;
    }

}