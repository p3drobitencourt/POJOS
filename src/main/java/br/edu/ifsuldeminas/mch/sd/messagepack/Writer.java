package br.edu.ifsuldeminas.mch.sd.messagepack;

import br.edu.ifsuldeminas.mch.sd.pojos.Address;
import br.edu.ifsuldeminas.mch.sd.pojos.Person;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessagePacker;

import java.io.FileOutputStream;
import java.io.IOException;

public class Writer {
    public void write(Person person, String fileName) throws IOException {
        Address address = person.getAddress();

        try (MessagePacker packer = MessagePack.newDefaultPacker(new FileOutputStream(fileName))) {
            packer.packMapHeader(4);
            packer.packString("name");
            packer.packString(person.getName());
            packer.packString("cpf");
            packer.packString(person.getCpf());
            packer.packString("birthDay");
            packer.packLong(person.getBirthDay().getTime());
            packer.packString("address");

            packer.packMapHeader(6);
            packer.packString("patio");
            packer.packString(address.getPatio());
            packer.packString("number");
            packer.packInt(address.getNumber());
            packer.packString("neighborhood");
            packer.packString(address.getNeighborhood());
            packer.packString("zipCode");
            packer.packString(address.getZipCode());
            packer.packString("city");
            packer.packString(address.getCity());
            packer.packString("state");
            packer.packString(address.getState());
        }
    }
}
