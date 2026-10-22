package accounts.serialization;

import accounts.BankAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.LinkedList;
import java.util.List;

public class BankAccountXMLSerializer implements BankAccountSerializer {

    XmlMapper xmlMapper = new XmlMapper();

    BankAccountSerializeFactory bankAccountSerializeFactory = new BankAccountSerializeFactory();

    public String serializeAll(List<BankAccount> bankAccounts) {
        List<BankAccountSerialize> bankAccountSerialize = new LinkedList<>();

        for (BankAccount bankAccount : bankAccounts) {
            bankAccountSerialize.add(
                    bankAccountSerializeFactory.createBankAccountSerialize(bankAccount)
            );
        }

        try {
            return xmlMapper.writer().withRootName("root").writeValueAsString(bankAccountSerialize);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Cannot serialize bank accounts to XML", e);
        }
    }

    public String serialize(BankAccount bankAccount) {
        BankAccountSerialize bankAccountSerialize =
                bankAccountSerializeFactory.createBankAccountSerialize(bankAccount);

        try {
            return xmlMapper.writeValueAsString(bankAccountSerialize);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Cannot serialize bank account to XML", e);
        }
    }
}