import accounts.*;
import person.AccountOwner;
import transfer.DepositTransferService;
import transfer.WithdrawTransferService;
import transfer.TransferService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AccountOwner accountOwner = new AccountOwner("jozef", "svoboda");
        accountOwner.setLastName("novak");

        BankAccount bankAccount = new CurrentAccount(accountOwner, "1234", 500);
        BankAccount studentAccount = new StudentAccount(accountOwner, "1234", 500, "Delta");
        BankAccount savingAccount = new SavingAccount(accountOwner, "1234");


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

        DepositTransferService depositTransferService = new DepositTransferService();
        depositTransferService.deposit(bankAccount, 400);
        depositTransferService.deposit(bankAccount, 100);
        depositTransferService.deposit(bankAccount, 200);
        depositTransferService.deposit(bankAccount, 600);

        printBalance(bankAccount);

        WithdrawTransferService withdrawTransferService = new WithdrawTransferService();

        withdrawTransferService.withdraw(bankAccount, 300);
        withdrawTransferService.withdraw(bankAccount, 300);

        withdrawTransferService.withdraw(bankAccount, 100);
        withdrawTransferService.withdraw(bankAccount, 50);
        withdrawTransferService.withdraw(bankAccount, 400);

        printBalance(bankAccount);

        BankAccount businessAccount = new BusinessAccount(accountOwner, "456");
        businessAccount.setBalance(5000);

        TransferService transferService = new TransferService();

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
    }

    private static void printBalance(BankAccount bankAccount) {
        System.out.println("balance: " + bankAccount.getBalance());
    }
}