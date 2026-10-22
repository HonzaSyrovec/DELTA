package accounts.services;

import accounts.*;
import accounts.factories.BankAccountFactory;
import person.AccountOwner;

import java.util.ArrayList;
import java.util.List;

public class BankAccountService {

    private final BankAccountFactory bankAccountFactory;

    private final List<BankAccount> bankAccounts = new ArrayList<>();

    public BankAccountService(BankAccountFactory bankAccountFactory) {
        if (bankAccountFactory == null) {
            throw new IllegalArgumentException("Bank account factory cannot be null");
        }

        this.bankAccountFactory = bankAccountFactory;
    }

    public BankAccount createCurrentBankAccount(AccountOwner accountOwner, double balance) {
        BankAccount currentAccount = bankAccountFactory.createCurrentAccount(accountOwner, balance);
        this.bankAccounts.add(currentAccount);

        return currentAccount;
    }

    public BankAccount createSavingBankAccount(AccountOwner accountOwner, double balance) {
        BankAccount savingAccount = bankAccountFactory.createSavingAccount(accountOwner, balance);
        this.bankAccounts.add(savingAccount);

        return savingAccount;
    }

    public BankAccount createStudentBankAccount(AccountOwner accountOwner, double balance, String school) {
        BankAccount studentAccount = bankAccountFactory.createStudentAccount(accountOwner, balance, school);
        this.bankAccounts.add(studentAccount);

        return studentAccount;
    }

    public BankAccount createBusinessBankAccount(AccountOwner accountOwner, double balance) {
        BankAccount businessAccount = bankAccountFactory.createBusinessAccount(accountOwner, balance);
        this.bankAccounts.add(businessAccount);

        return businessAccount;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
