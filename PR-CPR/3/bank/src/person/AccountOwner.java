package person;

public class AccountOwner {

    private String uuid;

    private String name;

    private String lastName;

    public AccountOwner(String uuid, String name, String lastName) {
        this.uuid = uuid;
        this.name = name;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }
}
