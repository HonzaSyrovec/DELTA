package transfer;

import accounts.BankAccount;
import accounts.BusinessAccount;

public class TransferService {

    private static final double BUSINESS_ACCOUNT_TRANSFER_FEE = 0.003;

    public void transfer(BankAccount from, BankAccount to, double amount) {
        validate(from, to, amount);

        double totalAmount = amount;

        if (from instanceof BusinessAccount) {
            double transferFee = amount * BUSINESS_ACCOUNT_TRANSFER_FEE;

            totalAmount += transferFee;
        }

        if (from.getBalance() < totalAmount) {
            throw new IllegalArgumentException("Not enough funds on source account");
        }

        from.setBalance(from.getBalance() - totalAmount);
        to.setBalance(to.getBalance() + amount);
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