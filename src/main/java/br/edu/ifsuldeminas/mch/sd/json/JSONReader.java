package br.edu.ifsuldeminas.mch.sd.json;

import br.edu.ifsuldeminas.mch.sd.pojos.Person;
import com.cedarsoftware.util.io.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class JSONReader {
    public static void main(String[] args) {
        Person emerson = null;
        File jsonFile = new File("person.json");
        JsonReader jsonReader;

        try {
            jsonReader = new JsonReader(new FileInputStream(jsonFile));
            emerson = (Person) jsonReader.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (emerson != null) {
            System.out.println(emerson);
            System.out.println(emerson.getAddress());
        }
    }
}
