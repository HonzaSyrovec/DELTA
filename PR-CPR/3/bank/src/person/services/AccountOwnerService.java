package person.services;

import person.AccountOwner;
import person.factories.AccountOwnerFactory;

import java.util.LinkedList;
import java.util.List;

public class AccountOwnerService {

    private final AccountOwnerFactory accountOwnerFactory = new AccountOwnerFactory();

    private final List<AccountOwner> accountOwners = new LinkedList<>();

    public AccountOwner createAccountOwner(String name, String lastName) {
        AccountOwner owner = accountOwnerFactory.createAccountOwner(name, lastName);

        accountOwners.add(owner);

        return owner;
    }

}
