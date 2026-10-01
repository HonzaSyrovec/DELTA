package transfer;

import accounts.BankAccount;
import accounts.StudentAccount;

public class DepositTransferService {

    private static final double STUDENT_ACCOUNT_DEPOSIT_BONUS = 0.005;

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

        bankAccount.setBalance(newBalance);
    }

}
