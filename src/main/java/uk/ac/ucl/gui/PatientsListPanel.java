package uk.ac.ucl.gui;

import uk.ac.ucl.entities.Patient;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class PatientsListPanel extends JPanel {

    private DefaultListModel<Patient> listModel;
    private ListSelectionModel selectionModel;
    private JList<Patient> patientJList;

    public PatientsListPanel() {
        this.setLayout(new GridLayout());
        listModel = new DefaultListModel<>();
        patientJList = new JList<>(listModel);
        selectionModel = patientJList.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientJList.setSelectionModel(selectionModel);
        patientJList.setLayoutOrientation(JList.VERTICAL);

        this.add(new JScrollPane(patientJList));
    }

    public void addListSelectionListener(ListSelectionListener listSelectionListener) {
        patientJList.getSelectionModel().addListSelectionListener(listSelectionListener);
    }

    public DefaultListModel<Patient> getListModel() {
        return listModel;
    }

    public ListSelectionModel getSelectionModel() {
        return selectionModel;
    }

    public JList<Patient> getPatientJList() {
        return patientJList;
    }

    public void clearSelection() {
        selectionModel.clearSelection();
        listModel.clear();
    }

    public void setPatients(List<Patient> patients) {
        listModel.addAll(patients);
    }
}
