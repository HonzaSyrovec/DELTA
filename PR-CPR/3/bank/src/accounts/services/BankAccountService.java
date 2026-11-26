package accounts.services;

import accounts.*;
import accounts.factories.BankAccountFactory;
import currency.Currency;
import person.AccountOwner;

import java.util.ArrayList;
import java.util.List;

public class BankAccountService implements IBankAccountService {

    List<BankAccount> bankAccounts = new ArrayList<>();

    BankAccountFactory bankAccountFactory;

    public BankAccountService(BankAccountFactory bankAccountFactory) {
        this.bankAccountFactory = bankAccountFactory;
    }

    public BankAccount createCurrentBankAccount(AccountOwner accountOwner, double balance, Currency currency) {
        BankAccount currentAccount = bankAccountFactory.createCurrentBankAccount(accountOwner, balance, currency);
        this.bankAccounts.add(currentAccount);

        return currentAccount;
    }

    public BankAccount createSavingBankAccount(AccountOwner accountOwner, double balance, Currency currency) {
        BankAccount savingAccount = bankAccountFactory.createSavingBankAccount(accountOwner, balance, currency);
        this.bankAccounts.add(savingAccount);

        return savingAccount;
    }

    public BankAccount createStudentBankAccount(AccountOwner accountOwner, double balance, String school, Currency currency) {
        BankAccount studentAccount = bankAccountFactory.createStudentBankAccount(accountOwner, balance, school, currency);
        this.bankAccounts.add(studentAccount);

        return studentAccount;
    }

    public BankAccount createBusinessBankAccount(AccountOwner accountOwner, double balance, Currency currency) {
        BankAccount businessAccount = bankAccountFactory.createBusinessBankAccount(accountOwner, balance, currency);
        this.bankAccounts.add(businessAccount);

        return businessAccount;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
