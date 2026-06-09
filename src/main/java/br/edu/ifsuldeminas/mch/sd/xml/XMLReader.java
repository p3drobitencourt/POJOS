package br.edu.ifsuldeminas.mch.sd.xml;

import br.edu.ifsuldeminas.mch.sd.pojos.Address;
import br.edu.ifsuldeminas.mch.sd.pojos.Person;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.util.Date;

public class XMLReader {
    public static void main(String[] args) {
        XStream xstream = new XStream(new DomDriver());
        Class<?>[] classes = new Class[] {Person.class, Address.class, Date.class};
        xstream.allowTypes(classes);

        Person emerson = null;
        File xmlFile = new File("person.xml");
        emerson = (Person) xstream.fromXML(xmlFile);

        if (emerson != null) {
            System.out.println(emerson);
            System.out.println(emerson.getAddress());
        }
    }
}
