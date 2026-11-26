package accounts.factories;

import accounts.*;
import accounts.services.AccountNumberGenerator;
import currency.Currency;
import person.AccountOwner;

import java.util.UUID;

public class BankAccountFactory {

    AccountNumberGenerator accountNumberGenerator;

    public BankAccountFactory(AccountNumberGenerator accountNumberGenerator) {
        this.accountNumberGenerator = accountNumberGenerator;
    }

    public BankAccount createCurrentBankAccount(AccountOwner accountOwner, double balance, Currency currency) {
        BankAccount currentAccount = new CurrentAccount(accountOwner, balance);
        this.initBankAccount(currentAccount, currency);

        return currentAccount;
    }

    public BankAccount createSavingBankAccount(AccountOwner accountOwner, double balance, Currency currency) {
        BankAccount savingAccount = new SavingAccount(accountOwner, balance);
        this.initBankAccount(savingAccount, currency);

        return savingAccount;
    }

    public BankAccount createStudentBankAccount(AccountOwner accountOwner, double balance, String school, Currency currency) {
        BankAccount studentAccount = new StudentAccount(accountOwner, balance, school);
        this.initBankAccount(studentAccount, currency);

        return studentAccount;
    }

    public BankAccount createBusinessBankAccount(AccountOwner accountOwner, double balance, Currency currency) {
        BankAccount businessAccount = new BusinessAccount(accountOwner, balance);
        this.initBankAccount(businessAccount, currency);

        return businessAccount;
    }

    private void initBankAccount(BankAccount bankAccount, Currency currency) {
        bankAccount.setUuid(UUID.randomUUID().toString());
        bankAccount.setAccountNumber(accountNumberGenerator.generate());
        bankAccount.setCurrency(currency);
    }

}
