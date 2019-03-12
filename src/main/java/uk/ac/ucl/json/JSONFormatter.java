package uk.ac.ucl.json;

import uk.ac.ucl.entities.*;

import java.util.List;

public class JSONFormatter {

    public String formatPatient(Patient patient) {
        if (patient == null) {
            return "{}";
        }
        return "{" +
                "id: \""+patient.getId()+"\"," +
                "gender: \""+patient.getGender()+"\"," +
                "birthDate: \""+patient.getBirthDate()+"\"," +
                "deathDate: \""+patient.getDeathDate()+"\"," +
                "name:" + formatName(patient.getName()) + "," +
                "identification:" + formatId(patient.getIdentification()) + "," +
                "details:" + formatDetails(patient.getPatientDetails()) + "," +
                "address:" + formatAddress(patient.getAddress()) +
                "}";
    }

    public String formatName(Name name) {
        if (name == null) {
            return "{}";
        }
        return "{" +
                "prefix: \"" + name.getPrefix() + "\"," +
                "first: \"" + name.getFirst() + "\"," +
                "last: \"" + name.getLast() + "\"," +
                "suffix: \"" + name.getSuffix() + "\"" +
                "}";
    }

    public String formatId(Identification id) {
        if (id == null) {
            return "{}";
        }
        return "{" +
                "ssn: \""+id.getSsn()+"\"," +
                "drivers: \""+id.getDrivers()+"\"," +
                "passport: \""+id.getPassport()+"\"" +
                "}";
    }

    public String formatDetails(PatientDetails details) {
        if (details == null) {
            return "{}";
        }
        return "{" +
                "maiden: \""+details.getMaiden()+"\"," +
                "marital: \""+details.getMarital()+"\"," +
                "race: \""+details.getRace()+"\"," +
                "ethnicity: \""+details.getEthnicity()+"\"," +
                "birthplace: \""+details.getBirthplace()+"\"" +
                "}";
    }

    private String formatAddress(Address address) {
        if (address == null) {
            return "{}";
        }
        return "{" +
                "address: \"" + address.getAddress() + "\"," +
                "city: \"" + address.getCity() + "\"," +
                "state: \"" + address.getState() + "\"," +
                "zip: \"" + address.getZip() + "\"" +
                "}";
    }

    public String formatPatients(List<Patient> patients) {
        StringBuilder builder = new StringBuilder().append("[");
        for (Patient patient : patients) {
            builder.append(formatPatient(patient)).append(",");
        }

        if (builder.toString().endsWith(",")) {
            builder.deleteCharAt(builder.length()-1);
        }

        return builder.append("]").toString();
    }
}
