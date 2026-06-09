package br.edu.ifsuldeminas.mch.sd.protobuf;

import br.edu.ifsuldeminas.mch.sd.pojos.Address;
import br.edu.ifsuldeminas.mch.sd.pojos.Person;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class Reader {
    public Person read(String fileName) throws IOException {
        PersonProto.PersonMessage personMessage;

        try (FileInputStream input = new FileInputStream(fileName)) {
            personMessage = PersonProto.PersonMessage.parseFrom(input);
        }

        PersonProto.AddressMessage addressMessage = personMessage.getAddress();
        Address address = new Address(
                addressMessage.getPatio(),
                addressMessage.getNumber(),
                addressMessage.getNeighborhood(),
                addressMessage.getZipCode(),
                addressMessage.getCity(),
                addressMessage.getState());

        return new Person(
                personMessage.getName(),
                personMessage.getCpf(),
                new Date(personMessage.getBirthDay()),
                address);
    }
}
