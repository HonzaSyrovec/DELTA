import accounts.*;
import accounts.factories.BankAccountFactory;
import accounts.serialization.BankAccountGsonSerializer;
import accounts.serialization.BankAccountSerializer;
import accounts.serialization.BankAccountXMLSerializer;
import accounts.services.BankAccountService;
import generator.AccountNumberGenerator;
import generator.SequentialAccountNumberGenerator;
import person.AccountOwner;
import person.services.AccountOwnerService;
import transaction.TransactionFactory;
import transfer.DepositTransferService;
import transfer.TransferLoggerService;
import transfer.TransferService;
import transfer.WithdrawTransferService;

public class Main {

    public static void main(String[] args) {

        AccountOwnerService accountOwnerService = new AccountOwnerService();

        AccountNumberGenerator accountNumberGenerator = new SequentialAccountNumberGenerator();
        BankAccountFactory bankAccountFactory = new BankAccountFactory(accountNumberGenerator);
        BankAccountService bankAccountService = new BankAccountService(bankAccountFactory);

        AccountOwner accountOwner = accountOwnerService.createAccountOwner("jozef", "novak");

        BankAccount bankAccount = bankAccountService.createCurrentBankAccount(accountOwner, 500);
        BankAccount studentAccount = bankAccountService.createStudentBankAccount(accountOwner, 500, "Delta");
        BankAccount savingAccount = bankAccountService.createSavingBankAccount(accountOwner, 500);
        BankAccount businessAccount = bankAccountService.createBusinessBankAccount(accountOwner, 5000);

        BankAccountSerializer bankAccountJsonSerializer = new BankAccountGsonSerializer();
        BankAccountSerializer bankAccountXmlSerializer = new BankAccountXMLSerializer();



        String bankAccountXml = bankAccountXmlSerializer.serialize(bankAccount);
        String bankAccountXmlAll = bankAccountXmlSerializer.serializeAll(bankAccountService.getBankAccounts());

        String bankAccountJson = bankAccountJsonSerializer.serialize(bankAccount);
        String bankAccountJsonAll = bankAccountJsonSerializer.serializeAll(bankAccountService.getBankAccounts());

        System.out.println(bankAccountXml);
        System.out.println(bankAccountXmlAll);

        System.out.println(bankAccountJson);
        System.out.println(bankAccountJsonAll);


        for (BankAccount account : bankAccountService.getBankAccounts()) {
            if (account instanceof InterestPoint) {
                ((InterestPoint) account).calculateInterest();
            }
        }

        for (BankAccount account : bankAccountService.getBankAccounts()) {

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