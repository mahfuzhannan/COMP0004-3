package uk.ac.ucl.entities;

import uk.ac.ucl.enums.Gender;

import java.time.LocalDate;
import java.util.UUID;

public class Patient {

    private UUID id;

    private Gender gender;

    private String prefix;

    private String first;

    private String last;

    private String suffix;

    private LocalDate birthDate;

    private LocalDate deathDate;

    private Identification identification;

    private PatientDetails patientDetails;

    private Address address;

    public Patient() {
    }

    public Patient(UUID id, Gender gender, String prefix, String first, String last, String suffix, LocalDate birthDate, LocalDate deathDate, Identification identification, PatientDetails patientDetails, Address address) {
        this.id = id;
        this.gender = gender;
        this.prefix = prefix;
        this.first = first;
        this.last = last;
        this.suffix = suffix;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.identification = identification;
        this.patientDetails = patientDetails;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public PatientDetails getPatientDetails() {
        return patientDetails;
    }

    public void setPatientDetails(PatientDetails patientDetails) {
        this.patientDetails = patientDetails;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
