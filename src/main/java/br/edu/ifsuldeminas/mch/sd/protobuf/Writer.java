package br.edu.ifsuldeminas.mch.sd.protobuf;

import br.edu.ifsuldeminas.mch.sd.pojos.Address;
import br.edu.ifsuldeminas.mch.sd.pojos.Person;

import java.io.FileOutputStream;
import java.io.IOException;

public class Writer {
    public void write(Person person, String fileName) throws IOException {
        Address address = person.getAddress();

        PersonProto.AddressMessage addressMessage = PersonProto.AddressMessage.newBuilder()
                .setPatio(address.getPatio())
                .setNumber(address.getNumber())
                .setNeighborhood(address.getNeighborhood())
                .setZipCode(address.getZipCode())
                .setCity(address.getCity())
                .setState(address.getState())
                .build();

        PersonProto.PersonMessage personMessage = PersonProto.PersonMessage.newBuilder()
                .setName(person.getName())
                .setCpf(person.getCpf())
                .setBirthDay(person.getBirthDay().getTime())
                .setAddress(addressMessage)
                .build();

        try (FileOutputStream output = new FileOutputStream(fileName)) {
            personMessage.writeTo(output);
        }
    }
}
