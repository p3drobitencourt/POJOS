package br.edu.ifsuldeminas.mch.sd.avro;

import br.edu.ifsuldeminas.mch.sd.pojos.Address;
import br.edu.ifsuldeminas.mch.sd.pojos.Person;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Reader {
    public Person read(String fileName) throws IOException {
        Schema schema = Writer.loadSchema();
        GenericDatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);

        try (DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(new File(fileName), datumReader)) {
            if (!dataFileReader.hasNext()) {
                throw new IOException("Arquivo Avro nao contem registros.");
            }

            GenericRecord personRecord = dataFileReader.next();
            GenericRecord addressRecord = (GenericRecord) personRecord.get("address");

            Address address = new Address(
                    addressRecord.get("patio").toString(),
                    (Integer) addressRecord.get("number"),
                    addressRecord.get("neighborhood").toString(),
                    addressRecord.get("zipCode").toString(),
                    addressRecord.get("city").toString(),
                    addressRecord.get("state").toString());

            return new Person(
                    personRecord.get("name").toString(),
                    personRecord.get("cpf").toString(),
                    new Date((Long) personRecord.get("birthDay")),
                    address);
        }
    }
}
