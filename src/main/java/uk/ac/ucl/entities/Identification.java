package uk.ac.ucl.entities;

public class Identification {

    private String ssn;
    private String drivers;
    private String passport;

    public Identification() {
    }

    public Identification(String ssn, String drivers, String passport) {
        this.ssn = ssn;
        this.drivers = drivers;
        this.passport = passport;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getDrivers() {
        return drivers;
    }

    public void setDrivers(String drivers) {
        this.drivers = drivers;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
