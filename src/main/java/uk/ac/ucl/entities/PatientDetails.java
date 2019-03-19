package uk.ac.ucl.entities;

import uk.ac.ucl.enums.Gender;

public class PatientDetails {

    private String maiden;
    private String marital;
    private String race;
    private String ethnicity;
    private String birthplace;

    public PatientDetails() {
    }

    public PatientDetails(String maiden, String marital, String race, String ethnicity, String birthplace) {
        this.maiden = maiden;
        this.marital = marital;
        this.race = race;
        this.ethnicity = ethnicity;
        this.birthplace = birthplace;
    }

    public String getMaiden() {
        return maiden;
    }

    public void setMaiden(String maiden) {
        this.maiden = maiden;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @Override
    public String toString() {
        return "PatientDetails{" +
                "maiden='" + maiden + '\'' +
                ", marital='" + marital + '\'' +
                ", race='" + race + '\'' +
                ", ethnicity='" + ethnicity + '\'' +
                ", birthplace='" + birthplace + '\'' +
                '}';
    }
}
