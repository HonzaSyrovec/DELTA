import accounts.*;
import person.AccountOwner;
import person.AccountOwnerFactory;
import transfer.DepositTransferService;
import transfer.WithdrawTransferService;
import transfer.TransferService;
import generator.AccountNumberGenerator;
import generator.SequentialAccountNumberGenerator;
import transaction.TransactionFactory;
import transfer.TransferLoggerService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AccountOwnerFactory accountOwnerFactory = new AccountOwnerFactory();

        AccountOwner accountOwner = accountOwnerFactory.createAccountOwner("jozef", "novak");
        AccountNumberGenerator accountNumberGenerator = new SequentialAccountNumberGenerator();
        BankAccountFactory bankAccountFactory = new BankAccountFactory(accountNumberGenerator);

        BankAccount bankAccount = bankAccountFactory.createCurrentAccount(accountOwner, 500);
        BankAccount studentAccount = bankAccountFactory.createStudentAccount(accountOwner, 500, "Delta");
        BankAccount savingAccount = bankAccountFactory.createSavingAccount(accountOwner);


        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(bankAccount);
        bankAccounts.add(studentAccount);


        for (BankAccount account: bankAccounts){
            if (account instanceof InterestPoint) {
                ((InterestPoint)account).calculateInterest();
            }
        }

        for (BankAccount account: bankAccounts){

            if (account instanceof StudentAccount) {
                StudentAccount stdAccount = (StudentAccount) account;
                System.out.println("school: " + stdAccount.getSchoolName());
            }

            System.out.println("balance: " + account.getBalance());

        }


        printBalance(bankAccount);

        TransactionFactory transactionFactory = new TransactionFactory();
        TransferLoggerService transferLoggerService = new TransferLoggerService();

        DepositTransferService depositTransferService = new DepositTransferService(transactionFactory, transferLoggerService);
        depositTransferService.deposit(bankAccount, 400);
        depositTransferService.deposit(bankAccount, 100);
        depositTransferService.deposit(bankAccount, 200);
        depositTransferService.deposit(bankAccount, 600);

        printBalance(bankAccount);

        WithdrawTransferService withdrawTransferService = new WithdrawTransferService(transactionFactory, transferLoggerService);

        withdrawTransferService.withdraw(bankAccount, 300);
        withdrawTransferService.withdraw(bankAccount, 300);

        withdrawTransferService.withdraw(bankAccount, 100);
        withdrawTransferService.withdraw(bankAccount, 50);
        withdrawTransferService.withdraw(bankAccount, 400);

        printBalance(bankAccount);

        BankAccount businessAccount = bankAccountFactory.createBusinessAccount(accountOwner, 5000);

        TransferService transferService = new TransferService(transactionFactory, transferLoggerService);

        // business -> current, fee 0.3 %
        transferService.transfer(businessAccount, bankAccount, 1000);
        printBalance(businessAccount);
        printBalance(bankAccount);

        // current -> business, no fee
        transferService.transfer(bankAccount, businessAccount, 200);
        printBalance(businessAccount);
        printBalance(bankAccount);

        // invalid inputs
        try {
            transferService.transfer(bankAccount, businessAccount, -10);
        } catch (IllegalArgumentException e) {
            System.out.println("error: " + e.getMessage());
        }

        try {
            transferService.transfer(bankAccount, bankAccount, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("error: " + e.getMessage());
        }

        try {
            transferService.transfer(bankAccount, businessAccount, 999999);
        } catch (IllegalArgumentException e) {
            System.out.println("error: " + e.getMessage());
        }

        System.out.println("--- history ---");
        transferLoggerService.printHistory();
    }

    private static void printBalance(BankAccount bankAccount) {
        System.out.println("balance: " + bankAccount.getBalance());
    }
}