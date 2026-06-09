package br.edu.ifsuldeminas.mch.sd.messagepack;

import br.edu.ifsuldeminas.mch.sd.pojos.Address;
import br.edu.ifsuldeminas.mch.sd.pojos.Person;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class Reader {
    public Person read(String fileName) throws IOException {
        String name = null;
        String cpf = null;
        long birthDay = 0L;
        Address address = null;

        try (MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(new FileInputStream(fileName))) {
            int mapSize = unpacker.unpackMapHeader();
            for (int i = 0; i < mapSize; i++) {
                String fieldName = unpacker.unpackString();
                switch (fieldName) {
                    case "name" -> name = unpacker.unpackString();
                    case "cpf" -> cpf = unpacker.unpackString();
                    case "birthDay" -> birthDay = unpacker.unpackLong();
                    case "address" -> address = unpackAddress(unpacker);
                    default -> unpacker.skipValue();
                }
            }
        }

        if (name == null || cpf == null || address == null) {
            throw new IOException("Arquivo MessagePack nao contem todos os campos de Person.");
        }

        return new Person(name, cpf, new Date(birthDay), address);
    }

    private Address unpackAddress(MessageUnpacker unpacker) throws IOException {
        String patio = null;
        int number = 0;
        String neighborhood = null;
        String zipCode = null;
        String city = null;
        String state = null;

        int mapSize = unpacker.unpackMapHeader();
        for (int i = 0; i < mapSize; i++) {
            String fieldName = unpacker.unpackString();
            switch (fieldName) {
                case "patio" -> patio = unpacker.unpackString();
                case "number" -> number = unpacker.unpackInt();
                case "neighborhood" -> neighborhood = unpacker.unpackString();
                case "zipCode" -> zipCode = unpacker.unpackString();
                case "city" -> city = unpacker.unpackString();
                case "state" -> state = unpacker.unpackString();
                default -> unpacker.skipValue();
            }
        }

        return new Address(patio, number, neighborhood, zipCode, city, state);
    }
}
