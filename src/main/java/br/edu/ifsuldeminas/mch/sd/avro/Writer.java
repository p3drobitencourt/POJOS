package br.edu.ifsuldeminas.mch.sd.avro;

import br.edu.ifsuldeminas.mch.sd.pojos.Address;
import br.edu.ifsuldeminas.mch.sd.pojos.Person;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Writer {
    public void write(Person person, String fileName) throws IOException {
        Schema schema = loadSchema();
        GenericRecord record = toRecord(person, schema);

        GenericDatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(schema, new File(fileName));
            dataFileWriter.append(record);
        }
    }

    static Schema loadSchema() throws IOException {
        try (InputStream input = Writer.class.getResourceAsStream("/avro/person.avsc")) {
            if (input == null) {
                throw new IOException("Schema Avro nao encontrado em /avro/person.avsc.");
            }
            return new Schema.Parser().parse(input);
        }
    }

    private GenericRecord toRecord(Person person, Schema schema) {
        Address address = person.getAddress();
        Schema addressSchema = schema.getField("address").schema();

        GenericRecord addressRecord = new GenericData.Record(addressSchema);
        addressRecord.put("patio", address.getPatio());
        addressRecord.put("number", address.getNumber());
        addressRecord.put("neighborhood", address.getNeighborhood());
        addressRecord.put("zipCode", address.getZipCode());
        addressRecord.put("city", address.getCity());
        addressRecord.put("state", address.getState());

        GenericRecord personRecord = new GenericData.Record(schema);
        personRecord.put("name", person.getName());
        personRecord.put("cpf", person.getCpf());
        personRecord.put("birthDay", person.getBirthDay().getTime());
        personRecord.put("address", addressRecord);

        return personRecord;
    }
}
