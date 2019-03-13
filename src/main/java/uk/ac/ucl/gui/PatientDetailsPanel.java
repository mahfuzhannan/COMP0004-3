package uk.ac.ucl.gui;

import uk.ac.ucl.entities.Patient;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PatientDetailsPanel extends JPanel {
    private Map<String, PatientComponent> componentMap;

    private PatientComponent idComponent;
    private PatientComponent birthdateComponent;
    private PatientComponent deathdateComponent;
    private PatientComponent ssnComponent;
    private PatientComponent driversComponent;
    private PatientComponent passportComponent;
    private PatientComponent prefixComponent;
    private PatientComponent firstComponent;
    private PatientComponent lastComponent;
    private PatientComponent suffixComponent;
    private PatientComponent maidenComponent;
    private PatientComponent maritalComponent;
    private PatientComponent raceComponent;
    private PatientComponent ethnicityComponent;
    private PatientComponent genderComponent;
    private PatientComponent birthplaceComponent;
    private PatientComponent addressComponent;
    private PatientComponent cityComponent;
    private PatientComponent stateComponent;
    private PatientComponent zipComponent;

    public PatientDetailsPanel() {
        this.setBorder(new TitledBorder("Patient Detail"));
        componentMap = new HashMap<>();

        idComponent = new PatientComponent(new JLabel("id"), new JTextField());
        birthdateComponent = new PatientComponent(new JLabel("birthdate"), new JTextField());
        deathdateComponent = new PatientComponent(new JLabel("deathdate"), new JTextField());
        ssnComponent = new PatientComponent(new JLabel("ssn"), new JTextField());
        driversComponent = new PatientComponent(new JLabel("drivers"), new JTextField());
        passportComponent = new PatientComponent(new JLabel("passport"), new JTextField());
        prefixComponent = new PatientComponent(new JLabel("prefix"), new JTextField());
        firstComponent = new PatientComponent(new JLabel("first"), new JTextField());
        lastComponent = new PatientComponent(new JLabel("last"), new JTextField());
        suffixComponent = new PatientComponent(new JLabel("suffix"), new JTextField());
        maidenComponent = new PatientComponent(new JLabel("maiden"), new JTextField());
        maritalComponent = new PatientComponent(new JLabel("marital"), new JTextField());
        raceComponent = new PatientComponent(new JLabel("race"), new JTextField());
        ethnicityComponent = new PatientComponent(new JLabel("ethnicity"), new JTextField());
        genderComponent = new PatientComponent(new JLabel("gender"), new JTextField());
        birthplaceComponent = new PatientComponent(new JLabel("birthplace"), new JTextField());
        addressComponent = new PatientComponent(new JLabel("address"), new JTextField());
        cityComponent = new PatientComponent(new JLabel("city"), new JTextField());
        stateComponent = new PatientComponent(new JLabel("state"), new JTextField());
        zipComponent = new PatientComponent(new JLabel("zip"), new JTextField());

        this.setLayout(new GridLayout(20, 0));

        this.add(idComponent);
        this.add(birthdateComponent);
        this.add(deathdateComponent);
        this.add(ssnComponent);
        this.add(driversComponent);
        this.add(passportComponent);
        this.add(prefixComponent);
        this.add(firstComponent);
        this.add(lastComponent);
        this.add(suffixComponent);
        this.add(maidenComponent);
        this.add(maritalComponent);
        this.add(raceComponent);
        this.add(ethnicityComponent);
        this.add(genderComponent);
        this.add(birthplaceComponent);
        this.add(addressComponent);
        this.add(cityComponent);
        this.add(stateComponent);
        this.add(zipComponent);

    }

    public void setPatient(Patient patient) {
        idComponent.getTextField().setText(patient.getId().toString());
        birthdateComponent.getTextField().setText(patient.getBirthDate().toString());
        deathdateComponent.getTextField().setText(patient.getDeathDate() == null ? null : patient.getDeathDate().toString());
        ssnComponent.getTextField().setText(patient.getIdentification().getSsn().toString());
        driversComponent.getTextField().setText(patient.getIdentification().getDrivers().toString());
        passportComponent.getTextField().setText(patient.getIdentification().getPassport().toString());
        prefixComponent.getTextField().setText(patient.getName().getPrefix().toString());
        firstComponent.getTextField().setText(patient.getName().getFirst().toString());
        lastComponent.getTextField().setText(patient.getName().getLast().toString());
        suffixComponent.getTextField().setText(patient.getName().getSuffix().toString());
        maidenComponent.getTextField().setText(patient.getPatientDetails().getMaiden().toString());
        maritalComponent.getTextField().setText(patient.getPatientDetails().getMarital().toString());
        raceComponent.getTextField().setText(patient.getPatientDetails().getRace().toString());
        ethnicityComponent.getTextField().setText(patient.getPatientDetails().getEthnicity().toString());
        genderComponent.getTextField().setText(patient.getGender().toString());
        birthplaceComponent.getTextField().setText(patient.getPatientDetails().getBirthplace().toString());
        addressComponent.getTextField().setText(patient.getAddress().getAddress().toString());
        cityComponent.getTextField().setText(patient.getAddress().getCity().toString());
        stateComponent.getTextField().setText(patient.getAddress().getState().toString());
        zipComponent.getTextField().setText(patient.getAddress().getZip().toString());
    }


    public static void main(String[] args) {
        new PatientDetailsPanel();
    }

    private class PatientComponent extends JPanel {
        private JLabel label;
        private JTextField textField;

        public PatientComponent(JLabel label, JTextField textField) {
            this.label = label;
            this.textField = textField;
            this.textField.setColumns(20);

            GridBagLayout gridBagLayout = new GridBagLayout();
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagLayout.setConstraints(this, gridBagConstraints);
            this.setLayout(gridBagLayout);

            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.anchor = GridBagConstraints.EAST;

            gridBagConstraints.weightx = 0.1;
            this.add(this.label, gridBagConstraints);
            gridBagConstraints.gridx = 1;
            this.add(this.textField, gridBagConstraints);
        }

        public JLabel getLabel() {
            return label;
        }

        public void setLabel(JLabel label) {
            this.label = label;
        }

        public JTextField getTextField() {
            return textField;
        }

        public void setTextField(JTextField textField) {
            this.textField = textField;
        }
    }

}
