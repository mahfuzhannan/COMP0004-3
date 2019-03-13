package uk.ac.ucl.gui;

import uk.ac.ucl.Model;
import uk.ac.ucl.entities.Patient;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class View extends JFrame {
    private static final String TITLE = "Patient App";

    private Model model;

    private PatientsListPanel patientsListPanel;
    private PatientDetailsPanel patientDetailsPanel;
    private SearchPanel searchPanel;

    private IOPanel ioPanel;


    public View(Model model) throws HeadlessException {
        super(TITLE);

        this.model = model;

        this.setSize(new Dimension(800, 500));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout(10, 10));
    }

    public void init() {
        ioPanel = new IOPanel();
        ioPanel.setFileChooserActionListener(e -> {
            JFileChooser fileChooser = ioPanel.getFileChooser();
            int status = fileChooser.showDialog(this, "Approve");
            if (status == JFileChooser.APPROVE_OPTION) {
                try {
                    populatePatients(model.readFile(fileChooser.getSelectedFile()));
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Could not load file.");
                }
            }
        });

        ioPanel.setSaveJsonActionListener(e -> {
            String filename = JOptionPane.showInputDialog("Enter a filename");
            try {
                String fileLocation = model.savePatientsJSON(filename + ".json");
                JOptionPane.showMessageDialog(this, "File has been saved to: " + fileLocation);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(this, "Failed to save patients to JSON.");
            }
        });

        patientsListPanel = new PatientsListPanel();
        patientsListPanel.addListSelectionListener(e -> {
            int[] selected = patientsListPanel.getSelectionModel().getSelectedIndices();
            if (selected.length > 0) {
                populatePatient(patientsListPanel.getListModel().get(selected[0]));
            }
        });

        patientDetailsPanel = new PatientDetailsPanel();

        searchPanel = new SearchPanel();
        searchPanel.setSearchActionListener(e -> {
            populatePatients(model.filterPatients(searchPanel.getSearchByNameText(), searchPanel.getSearchByAddressText()));
        });


        JPanel patientsPanel = new JPanel(new BorderLayout());
        patientsPanel.setBorder(new TitledBorder("Patients"));
        patientsPanel.add(searchPanel, BorderLayout.NORTH);
        patientsPanel.add(new JScrollPane(patientsListPanel), BorderLayout.WEST);
        patientsPanel.add(new JScrollPane(patientDetailsPanel), BorderLayout.CENTER);

        this.add(ioPanel, BorderLayout.NORTH);
        this.add(patientsPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void populatePatients(List<Patient> patients) {
        patientsListPanel.clearSelection();
        patientsListPanel.setPatients(patients);
    }

    private void populatePatient(Patient patient) {
        patientDetailsPanel.setPatient(patient);
    }
}
