package person.services;

import person.AccountOwner;
import person.factories.AccountOwnerFactory;

public class AccountOwnerService {

    private final AccountOwnerFactory accountOwnerFactory;

    public AccountOwnerService(AccountOwnerFactory accountOwnerFactory) {
        this.accountOwnerFactory = accountOwnerFactory;
    }

    public AccountOwner createAccountOwner(String name, String lastName) {
        return accountOwnerFactory.createAccountOwner(name, lastName);
    }

}
