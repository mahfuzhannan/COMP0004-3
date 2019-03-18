package uk.ac.ucl.daos;

import uk.ac.ucl.db.DatabaseConnector;
import uk.ac.ucl.entities.*;
import uk.ac.ucl.enums.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PatientDao {


    public PatientDao() {
    }

    public void savePatients(List<Patient> patients) throws SQLException, ClassNotFoundException {
        for (Patient patient : patients) {
            if(savePatient(patient) > 0) {
                System.out.println("Saved patient " + patient.getId().toString());
            }
            if(saveAddress(patient.getAddress(), patient.getId()) > 0 ) {
                System.out.println("Saved Address for patient " + patient.getId().toString());
            } else {
                System.out.println("Failed to save Address " + patient.getId().toString());
            }
            if(saveIdentification(patient.getIdentification(), patient.getId()) > 0 ) {
                System.out.println("Saved Identification for patient " + patient.getId().toString());
            } else {
                System.out.println("Failed to save Identification " + patient.getId().toString());
            }
            if(savePatientName(patient.getPatientName(), patient.getId()) > 0 ) {
                System.out.println("Saved PatientName for patient " + patient.getId().toString());
            } else {
                System.out.println("Failed to save PatientName " + patient.getId().toString());
            }
            if(savePatientDetails(patient.getPatientDetails(), patient.getId()) > 0 ) {
                System.out.println("Saved PatientDetails for patient " + patient.getId().toString());
            } else {
                System.out.println("Failed to save PatientDetails " + patient.getId().toString());
            }
        }
    }

    public int savePatient(Patient patient) throws SQLException, ClassNotFoundException {
        String builder = "INSERT INTO PATIENT VALUES (" +
                "\"" + patient.getId() + "\"," +
                "\"" + patient.getGender().name() + "\"," +
                "\"" + patient.getBirthDate().toString() + "\"," +
                "\"" + (patient.getDeathDate() == null ? null : patient.getDeathDate().toString()) + "\");";
        return DatabaseConnector.getInstance().getStatement().executeUpdate(builder);
    }

    public int saveAddress(Address address, UUID patientUuid) throws SQLException, ClassNotFoundException {
        String builder = "INSERT INTO ADDRESS VALUES (" +
                "\"" + address.getAddress() + "\"," +
                "\"" + address.getCity() + "\"," +
                "\"" + address.getState() + "\"," +
                "\"" + address.getZip() + "\"," +
                "\"" + patientUuid + "\");";
        return DatabaseConnector.getInstance().getStatement().executeUpdate(builder);
    }

    public int saveIdentification(Identification identification, UUID patientUuid) throws SQLException, ClassNotFoundException {
        String builder = "INSERT INTO IDENTIFICATION VALUES (" +
                "\"" + identification.getSsn() + "\"," +
                "\"" + identification.getDrivers() + "\"," +
                "\"" + identification.getPassport() + "\"," +
                "\"" + patientUuid + "\");";
        return DatabaseConnector.getInstance().getStatement().executeUpdate(builder);
    }

    public int savePatientName(PatientName patientName, UUID patientUuid) throws SQLException, ClassNotFoundException {
        String builder = "INSERT INTO PATIENTNAME VALUES (" +
                "\"" + patientName.getPrefix() + "\"," +
                "\"" + patientName.getFirst() + "\"," +
                "\"" + patientName.getLast() + "\"," +
                "\"" + patientName.getSuffix() + "\"," +
                "\"" + patientUuid + "\");";
        return DatabaseConnector.getInstance().getStatement().executeUpdate(builder);
    }

    public int savePatientDetails(PatientDetails patientDetails, UUID patientUuid) throws SQLException, ClassNotFoundException {
        String builder = "INSERT INTO PATIENTDETAILS VALUES (" +
                "\"" + patientDetails.getMaiden() + "\"," +
                "\"" + patientDetails.getMarital() + "\"," +
                "\"" + patientDetails.getRace() + "\"," +
                "\"" + patientDetails.getEthnicity() + "\"," +
                "\"" + patientDetails.getBirthplace() + "\"," +
                "\"" + patientUuid + "\");";
        return DatabaseConnector.getInstance().getStatement().executeUpdate(builder);
    }

    public List<Patient> getPatients() throws SQLException, ClassNotFoundException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM PATIENT " +
                "INNER JOIN ADDRESS ON ADDRESS.PATIENT = PATIENT.ID " +
                "INNER JOIN PATIENTNAME ON PATIENTNAME.PATIENT = PATIENT.ID " +
                "INNER JOIN IDENTIFICATION ON IDENTIFICATION.PATIENT = PATIENT.ID " +
                "INNER JOIN PATIENTDETAILS ON PATIENTDETAILS.PATIENT = PATIENT.ID ";

        Statement statement = DatabaseConnector.getInstance().getStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(UUID.fromString(rs.getString("ID")));
            patient.setGender(Gender.valueOf(rs.getString("GENDER")));
            patient.setBirthDate(LocalDate.parse(rs.getString("BIRTHDATE")));
            patient.setAddress(new Address(rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP")));
            patient.setIdentification(new Identification(rs.getString("SSN"), rs.getString("DRIVERS"), rs.getString("PASSPORT")));
            patient.setPatientDetails(new PatientDetails(rs.getString("MAIDEN"), rs.getString("MARITAL"), rs.getString("RACE"), rs.getString("ETHNICITY"), rs.getString("BIRTHPLACE")));
            patient.setPatientName(new PatientName(rs.getString("PREFIX"), rs.getString("FIRST"), rs.getString("LAST"), rs.getString("SUFFIX")));
            patients.add(patient);
        }
        return patients;
    }

    public Patient getPatient(UUID patientId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM PATIENT WHERE ID=\"" + patientId.toString() + "\";";
        ResultSet rs = DatabaseConnector.getInstance().getStatement().executeQuery(query);
        Patient patient = new Patient();
        patient.setId(UUID.fromString(rs.getString("ID")));
        patient.setGender(Gender.valueOf(rs.getString("GENDER")));
        patient.setBirthDate(LocalDate.parse(rs.getString("BIRTHDATE")));
        String deathDate = rs.getString("DEATHDATE");
        if (deathDate != null && !"null".equalsIgnoreCase(deathDate)) {
            patient.setDeathDate(LocalDate.parse(deathDate));
        }

        patient.setAddress(getAddress(patientId));
        patient.setIdentification(getIdentification(patientId));
        patient.setPatientDetails(getPatientDetails(patientId));
        patient.setPatientName(getPatientName(patientId));

        return patient;
    }

    public Address getAddress(UUID patientId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM ADDRESS WHERE PATIENT=\"" + patientId.toString() + "\";";
        ResultSet rs = DatabaseConnector.getInstance().getStatement().executeQuery(query);
        return new Address(rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP"));
    }

    public Identification getIdentification(UUID patientId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM IDENTIFICATION WHERE PATIENT=\"" + patientId.toString() + "\";";
        ResultSet rs = DatabaseConnector.getInstance().getStatement().executeQuery(query);
        return new Identification(rs.getString("SSN"), rs.getString("DRIVERS"), rs.getString("PASSPORT"));
    }

    public PatientDetails getPatientDetails(UUID patientId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM PATIENTDETAILS WHERE PATIENT=\"" + patientId.toString() + "\";";
        ResultSet rs = DatabaseConnector.getInstance().getStatement().executeQuery(query);
        return new PatientDetails(rs.getString("MAIDEN"), rs.getString("MARITAL"), rs.getString("RACE"), rs.getString("ETHNICITY"), rs.getString("BIRTHPLACE"));
    }

    public PatientName getPatientName(UUID patientId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM PATIENTNAME WHERE PATIENT=\"" + patientId.toString() + "\";";
        ResultSet rs = DatabaseConnector.getInstance().getStatement().executeQuery(query);
        return new PatientName(rs.getString("PREFIX"), rs.getString("FIRST"), rs.getString("LAST"), rs.getString("SUFFIX"));
    }

}
