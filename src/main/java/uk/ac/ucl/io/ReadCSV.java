package uk.ac.ucl.io;

import uk.ac.ucl.entities.*;
import uk.ac.ucl.enums.Gender;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReadCSV {

    public List<Patient> getPatients(File file) throws IOException {
        return Files.lines(Paths.get(file.toURI())).skip(1).map(line -> {
//            System.out.println(line);
            String[] data = line.split(",");

            UUID id = UUID.fromString(data[0]);
            String birthDateStr = data[1].trim();
            LocalDate birthdate = birthDateStr.isEmpty() ? null : LocalDate.parse(birthDateStr);
            String deathDateStr = data[2].trim();
            LocalDate deathdate = deathDateStr.isEmpty() ? null : LocalDate.parse(deathDateStr);
            String ssn = data[3];
            String drivers = data[4];
            String passport = data[5];
            String prefix = data[6];
            String first = data[7];
            String last = data[8];
            String suffix = data[9];
            String maiden = data[10];
            String marital = data[11];
            String race = data[12];
            String ethnicity = data[13];
            Gender gender = Gender.valueOf(data[14]);
            String birthplace = data[15];
            String addressStr = data[16];
            String city = data[17];
            String state = data[18];
            String zip = data.length > 19 ? data[19] : null;

            Address address = new Address(addressStr, city, state, zip);
            Identification identification = new Identification(ssn, drivers, passport);
            PatientDetails patientDetails = new PatientDetails(maiden, marital, race, ethnicity, birthplace);
            Name name = new Name(prefix, first, last, suffix);
            return new Patient(id, gender, birthdate, deathdate, name, identification, patientDetails, address);
        }).collect(Collectors.toList());
    }

}