package accounts;

import generator.AccountNumberGenerator;
import person.AccountOwner;

public class BankAccountFactory {

    private final AccountNumberGenerator accountNumberGenerator;

    public BankAccountFactory(AccountNumberGenerator accountNumberGenerator) {
        if (accountNumberGenerator == null) {
            throw new IllegalArgumentException("Account number generator cannot be null");
        }

        this.accountNumberGenerator = accountNumberGenerator;
    }

    public CurrentAccount createCurrentAccount(AccountOwner accountOwner) {
        return createCurrentAccount(accountOwner, 0);
    }

    public CurrentAccount createCurrentAccount(AccountOwner accountOwner, double balance) {
        validate(accountOwner, balance);

        return new CurrentAccount(accountOwner, accountNumberGenerator.generate(), balance);
    }

    public SavingAccount createSavingAccount(AccountOwner accountOwner) {
        return createSavingAccount(accountOwner, 0);
    }

    public SavingAccount createSavingAccount(AccountOwner accountOwner, double balance) {
        validate(accountOwner, balance);

        SavingAccount account = new SavingAccount(accountOwner, accountNumberGenerator.generate());
        account.setBalance(balance);

        return account;
    }

    public StudentAccount createStudentAccount(AccountOwner accountOwner, String schoolName) {
        return createStudentAccount(accountOwner, 0, schoolName);
    }

    public StudentAccount createStudentAccount(AccountOwner accountOwner, double balance, String schoolName) {
        validate(accountOwner, balance);

        if (schoolName == null || schoolName.isBlank()) {
            throw new IllegalArgumentException("School name cannot be empty");
        }

        return new StudentAccount(accountOwner, accountNumberGenerator.generate(), balance, schoolName);
    }

    public BusinessAccount createBusinessAccount(AccountOwner accountOwner) {
        return createBusinessAccount(accountOwner, 0);
    }

    public BusinessAccount createBusinessAccount(AccountOwner accountOwner, double balance) {
        validate(accountOwner, balance);

        BusinessAccount account = new BusinessAccount(accountOwner, accountNumberGenerator.generate());
        account.setBalance(balance);

        return account;
    }

    private void validate(AccountOwner accountOwner, double balance) {
        if (accountOwner == null) {
            throw new IllegalArgumentException("Account owner cannot be null");
        }

        if (Double.isNaN(balance) || Double.isInfinite(balance) || balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
    }
}