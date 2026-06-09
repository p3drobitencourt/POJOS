package br.edu.ifsuldeminas.mch.sd.messagepack;

import br.edu.ifsuldeminas.mch.sd.pojos.Address;
import br.edu.ifsuldeminas.mch.sd.pojos.Person;

import java.io.IOException;
import java.util.Date;

public class Main {
    private static final String FILE_NAME = "person.msgpack";

    public static void main(String[] args) {
        Address address = new Address("Rua Jose", 20,
                "Por do Sol", "37.130-000", "Alfenas", "MG");
        Person emerson = new Person("Emerson Carvalho",
                "060.793.477-11", new Date(), address);

        try {
            new Writer().write(emerson, FILE_NAME);
            System.out.println("Arquivo MessagePack gerado: " + FILE_NAME);

            Person recovered = new Reader().read(FILE_NAME);
            System.out.println("Objeto recuperado via MessagePack:");
            System.out.println(recovered);
            System.out.println(recovered.getAddress());
        } catch (IOException e) {
            System.out.println("Erro ao processar arquivo MessagePack.");
            e.printStackTrace();
        }
    }
}
