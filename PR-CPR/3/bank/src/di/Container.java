package di;

import accounts.factories.BankAccountFactory;
import accounts.serialization.BankAccountGsonSerializer;
import accounts.serialization.BankAccountSerializeFactory;
import accounts.serialization.BankAccountSerializer;
import accounts.serialization.BankAccountXmlSerializer;
import accounts.services.AccountNumberGenerator;
import accounts.services.BankAccountService;
import accounts.services.IBankAccountService;
import accounts.services.decorators.BankAccountWithLoggerService;
import accounts.services.decorators.BankAccountWithLoggerWithTimeService;
import currency.Currency;
import currency.CurrencyConverter;
import currency.MoneyFormatter;
import person.factories.AccountOwnerFactory;
import person.services.AccountOwnerService;
import transaction.TransactionFactory;
import transfer.DepositTransferService;
import transfer.TransferLoggerService;
import transfer.TransferService;
import transfer.WithdrawTransferService;

public class Container {

    public AccountNumberGenerator accountNumberGenerator = new AccountNumberGenerator("6969");

    AccountOwnerFactory accountOwnerFactory = new AccountOwnerFactory();
    public AccountOwnerService accountOwnerService
            = new AccountOwnerService(accountOwnerFactory);

    public BankAccountFactory bankAccountFactory = new BankAccountFactory(accountNumberGenerator);

    public IBankAccountService bankAccountService
            = new BankAccountService(bankAccountFactory);
    public IBankAccountService bankAccountWithLoggerService
            = new BankAccountWithLoggerService(bankAccountService);
    public IBankAccountService bankAccountWithTimeLoggerService
            = new BankAccountWithLoggerWithTimeService(bankAccountService);
    public IBankAccountService bankAccountWithLoggerAndTimeLoggerService
            = new BankAccountWithLoggerService(bankAccountWithTimeLoggerService);

    public BankAccountSerializeFactory bankAccountSerializeFactory = new BankAccountSerializeFactory();

    public BankAccountSerializer bankAccountJsonSerializer = new BankAccountGsonSerializer(bankAccountSerializeFactory);
    public BankAccountSerializer bankAccountXmlSerializer = new BankAccountXmlSerializer(bankAccountSerializeFactory);

    public CurrencyConverter currencyConverter = new CurrencyConverter();
    // ve vypisech prepocitavame na EUR (u EUR uctu zpet na CZK)
    public MoneyFormatter moneyFormatter = new MoneyFormatter(currencyConverter, Currency.EUR, Currency.CZK);

    public TransactionFactory transactionFactory = new TransactionFactory();
    public TransferLoggerService transferLoggerService = new TransferLoggerService(moneyFormatter);

    public DepositTransferService depositTransferService
            = new DepositTransferService(transactionFactory, transferLoggerService);
    public WithdrawTransferService withdrawTransferService
            = new WithdrawTransferService(transactionFactory, transferLoggerService);
    public TransferService transferService
            = new TransferService(transactionFactory, transferLoggerService, currencyConverter);

}
