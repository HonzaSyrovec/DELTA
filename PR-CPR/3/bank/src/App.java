import accounts.BankAccount;
import accounts.InterestPoint;
import accounts.StudentAccount;
import accounts.services.IBankAccountService;
import currency.Currency;
import currency.MoneyFormatter;
import di.Container;
import person.AccountOwner;

public class App {

    private MoneyFormatter moneyFormatter;

    public void run(Container container) {

        this.moneyFormatter = container.moneyFormatter;

        IBankAccountService bankAccountService = container.bankAccountService;
        IBankAccountService bankAccountWithLoggerService
                = container.bankAccountWithLoggerService;
        IBankAccountService bankAccountWithTimeLoggerService
                = container.bankAccountWithTimeLoggerService;
        IBankAccountService bankAccountWithLoggerAndTimeLoggerService
                = container.bankAccountWithLoggerAndTimeLoggerService;

        AccountOwner accountOwner = container.accountOwnerService.createAccountOwner("Jan", "Syrovec");

        System.out.println("ORIGINAL");
        BankAccount bankAccount = bankAccountService.createCurrentBankAccount(accountOwner, 500, Currency.CZK);
        System.out.println("========");

        System.out.println("WITH LOGGER");
        bankAccountWithLoggerService.createStudentBankAccount(accountOwner, 500, "Delta", Currency.CZK);
        System.out.println("========");

        System.out.println("WITH TIME LOGGER");
        BankAccount savingAccount = bankAccountWithTimeLoggerService.createSavingBankAccount(accountOwner, 500, Currency.EUR);
        System.out.println("========");

        System.out.println("WITH LOGGER AND TIME LOGGER");
        bankAccountWithLoggerAndTimeLoggerService.createBusinessBankAccount(accountOwner, 500, Currency.USD);
        System.out.println("========");

        String bankAccountXml = container.bankAccountXmlSerializer.serialize(bankAccount);
        String bankAccountXmlAll = container.bankAccountXmlSerializer.serializeAll(bankAccountService.getBankAccounts());

        String bankAccountJson = container.bankAccountJsonSerializer.serialize(bankAccount);
        String bankAccountJsonAll = container.bankAccountJsonSerializer.serializeAll(bankAccountService.getBankAccounts());

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

            printBalance(account);

        }

        printBalance(bankAccount);

        container.depositTransferService.deposit(bankAccount, 400);
        container.depositTransferService.deposit(bankAccount, 100);
        container.depositTransferService.deposit(bankAccount, 200);
        container.depositTransferService.deposit(bankAccount, 600);

        printBalance(bankAccount);

        container.withdrawTransferService.withdraw(bankAccount, 300);
        container.withdrawTransferService.withdraw(bankAccount, 300);

        container.withdrawTransferService.withdraw(bankAccount, 100);
        container.withdrawTransferService.withdraw(bankAccount, 50);
        container.withdrawTransferService.withdraw(bankAccount, 400);

        printBalance(bankAccount);

        System.out.println("TRANSFER 100 EUR -> CZK account");
        container.transferService.transfer(savingAccount, bankAccount, 100);

        printBalance(savingAccount);
        printBalance(bankAccount);

        System.out.println("HISTORY");
        container.transferLoggerService.printHistory();
    }

    private void printBalance(BankAccount bankAccount) {
        System.out.println(
                bankAccount.getAccountNumber() + " balance: "
                        + moneyFormatter.format(bankAccount.getBalance(), bankAccount.getCurrency())
        );
    }

}
