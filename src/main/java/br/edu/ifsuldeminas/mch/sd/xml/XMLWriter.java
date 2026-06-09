package br.edu.ifsuldeminas.mch.sd.xml;

import br.edu.ifsuldeminas.mch.sd.pojos.Address;
import br.edu.ifsuldeminas.mch.sd.pojos.Person;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class XMLWriter {
    public static void main(String[] args) {
        PrintWriter writer = null;
        XStream xstream = new XStream(new DomDriver());
        Address address = new Address("Rua Jose", 20, "Por do Sol",
                "37.130-000", "Alfenas", "MG");
        Person emerson = new Person("Emerson Carvalho", "060.793.477-11",
                new Date(), address);

        try {
            String xml = xstream.toXML(emerson);
            writer = new PrintWriter("person.xml");
            writer.println(xml);
            System.out.println("Objeto parseado com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println("Objeto nao pode ser parseado.");
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
