package accounts;

import currency.Currency;
import person.AccountOwner;

public abstract class BankAccount {

    private String uuid;

    private AccountOwner accountOwner;

    private String accountNumber;

    private double balance;

    private Currency currency = Currency.CZK;

    public BankAccount(AccountOwner accountOwner) {
        this.accountOwner = accountOwner;
        this.balance = 0;
    }

    public BankAccount(AccountOwner accountOwner, double balance) {
        this(accountOwner);

        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
