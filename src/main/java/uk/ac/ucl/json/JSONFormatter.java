package uk.ac.ucl.json;

import uk.ac.ucl.entities.*;
import uk.ac.ucl.enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

public class JSONFormatter {

    public String serializePatient(Patient patient) {
        if (patient == null) {
            return "{}";
        }
        return "{" +
                "\"id\": \"" + patient.getId() + "\"," +
                "\"gender\": \"" + patient.getGender() + "\"," +
                "\"birthDate\": \"" + patient.getBirthDate() + "\"," +
                "\"deathDate\": \"" + patient.getDeathDate() + "\"," +
                "\"name\":" + serializeName(patient.getName()) + "," +
                "\"identification\":" + serializeId(patient.getIdentification()) + "," +
                "\"details\":" + serializeDetails(patient.getPatientDetails()) + "," +
                "\"address\":" + serializeAddress(patient.getAddress()) +
                "}";
    }

    public String serializeName(Name name) {
        if (name == null) {
            return "{}";
        }
        return "{" +
                "\"prefix\": \"" + name.getPrefix() + "\"," +
                "\"first\": \"" + name.getFirst() + "\"," +
                "\"last\": \"" + name.getLast() + "\"," +
                "\"suffix\": \"" + name.getSuffix() + "\"" +
                "}";
    }

    public String serializeId(Identification id) {
        if (id == null) {
            return "{}";
        }
        return "{" +
                "\"ssn\": \"" + id.getSsn() + "\"," +
                "\"drivers\": \"" + id.getDrivers() + "\"," +
                "\"passport\": \"" + id.getPassport() + "\"" +
                "}";
    }

    public String serializeDetails(PatientDetails details) {
        if (details == null) {
            return "{}";
        }
        return "{" +
                "\"maiden\": \"" + details.getMaiden() + "\"," +
                "\"marital\": \"" + details.getMarital() + "\"," +
                "\"race\": \"" + details.getRace() + "\"," +
                "\"ethnicity\": \"" + details.getEthnicity() + "\"," +
                "\"birthplace\": \"" + details.getBirthplace() + "\"" +
                "}";
    }

    private String serializeAddress(Address address) {
        if (address == null) {
            return "{}";
        }
        return "{" +
                "\"address\": \"" + address.getAddress() + "\"," +
                "\"city\": \"" + address.getCity() + "\"," +
                "\"state\": \"" + address.getState() + "\"," +
                "\"zip\": \"" + address.getZip() + "\"" +
                "}";
    }

    public String serializePatients(List<Patient> patients) {
        StringBuilder builder = new StringBuilder().append("[");
        for (Patient patient : patients) {
            builder.append(serializePatient(patient)).append(",");
        }

        if (builder.toString().endsWith(",")) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.append("]").toString();
    }

    public List<Patient> deserializePatients(String data) {
        List<Patient> patients = new ArrayList<>();
        Patient patient = new Patient();

        // Tokenize by commas as this separates different elements in json
        StringTokenizer tokenizer = new StringTokenizer(data, ",");
        while (tokenizer.hasMoreElements()) {
            String element = tokenizer.nextElement().toString().trim();
            // split by : to get key value pair
            String[] keyValueSplit = element.split(":");
            String key;
            String value;
            // Check if length of split greater than 2, this means we have reached an embedded json ignore first part

//            System.out.println("el: " + element);

            if (keyValueSplit.length > 2) {
                key = keyValueSplit[1].trim();
                value = keyValueSplit[2].trim();
            } else {
                key = keyValueSplit[0].trim();
                value = keyValueSplit[1].trim();
            }

            // Replace []{}" characters
            key = key.replaceAll("[\\[\\]{},\"]", "").trim();
            value = value.replaceAll("[\\[\\]{},\"]", "").trim();


//            System.out.println("key: " + key + ", value: " + value);

            switch (key) {
                case "id":
                    patient.setId(UUID.fromString(value));
                    break;
                case "gender":
                    patient.setGender(Gender.valueOf(value));
                    break;
                case "birthDate":
                    patient.setBirthDate(LocalDate.parse(value));
                    break;
                case "deathDate":
                    if (!"null".equals(value)) {
                        patient.setDeathDate(LocalDate.parse(value));
                    }
                    break;
                case "prefix":
                    Name name = new Name();
                    name.setPrefix(value);
                    patient.setName(name);
                    break;
                case "first":
                    patient.getName().setFirst(value);
                    break;
                case "last":
                    patient.getName().setLast(value);
                    break;
                case "suffix":
                    patient.getName().setSuffix(value);
                    break;
                case "ssn":
                    Identification id = new Identification();
                    id.setSsn(value);
                    patient.setIdentification(id);
                    break;
                case "drivers":
                    patient.getIdentification().setDrivers(value);
                    break;
                case "passport":
                    patient.getIdentification().setPassport(value);
                    break;
                case "maiden":
                    PatientDetails details = new PatientDetails();
                    details.setMaiden(value);
                    patient.setPatientDetails(details);
                    break;
                case "marital":
                    patient.getPatientDetails().setMarital(value);
                    break;
                case "race":
                    patient.getPatientDetails().setRace(value);
                    break;
                case "ethnicity":
                    patient.getPatientDetails().setEthnicity(value);
                    break;
                case "birthplace":
                    patient.getPatientDetails().setBirthplace(value);
                    break;
                case "address":
                    Address address = new Address();
                    address.setAddress(value);
                    patient.setAddress(address);
                    break;
                case "city":
                    patient.getAddress().setCity(value);
                    break;
                case "state":
                    patient.getAddress().setState(value);
                    break;
                case "zip":
                    patient.getAddress().setZip(value);
                    patients.add(patient);
                    patient = new Patient();
                    break;
            }
        }

        return patients;
    }
}
