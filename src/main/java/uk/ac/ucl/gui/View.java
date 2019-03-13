package uk.ac.ucl.gui;

import uk.ac.ucl.Model;
import uk.ac.ucl.entities.Patient;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class View extends JFrame {
    private static final String TITLE = "Patient App";

    private Model model;

    private DefaultListModel<Patient> listModel;
    private ListSelectionModel selectionModel;

    private PatientPanel patientPanel;


    public View(Model model) throws HeadlessException {
        super(TITLE);

        this.model = model;

        this.setSize(new Dimension(800, 500));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout(10, 10));
    }

    public void init() {
        createFileChooser();
        this.add(getFileChooserPanel(), BorderLayout.NORTH);
        this.add(getPatientsPanel(), BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void createFileChooser() {

    }

    private JPanel getFileChooserPanel() {
        JPanel csvPanel = new JPanel(new GridLayout(1, 3));
        csvPanel.setBorder(new TitledBorder("File I/O Panel"));
        JButton openFileChooserButton = new JButton("Load CSV");
        JFileChooser fileChooser = new JFileChooser(new File("src/main/resources"));
        fileChooser.setDialogTitle("Select file to load");
        fileChooser.setMultiSelectionEnabled(false);
        openFileChooserButton.addActionListener(e -> {
            int status = fileChooser.showDialog(this, "Approve");
            if (status == JFileChooser.APPROVE_OPTION) {
                try {
                    populatePatients(model.readFile(fileChooser.getSelectedFile()));
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Could not load file.");
                }
            }
        });

        JButton saveJsonButton = new JButton("Save JSON");
        saveJsonButton.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog("Enter a filename");
            try {
                String fileLocation = model.savePatientsJSON(filename + ".json");
                JOptionPane.showMessageDialog(this, "File has been saved to: " + fileLocation);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(this,"Failed to save patients to JSON.");
            }
        });

        csvPanel.add(openFileChooserButton);
        csvPanel.add(saveJsonButton);
        return csvPanel;
    }

    private JPanel getPatientsPanel() {
        JPanel patientsPanel = new JPanel(new BorderLayout());
        patientsPanel.setBorder(new TitledBorder("Patients"));

        listModel = new DefaultListModel<>();
        JList<Patient> patientJList = new JList<>(listModel);
        selectionModel = patientJList.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientJList.setSelectionModel(selectionModel);
        patientJList.setLayoutOrientation(JList.VERTICAL);


        patientJList.getSelectionModel().addListSelectionListener(e -> {
            int[] selected = selectionModel.getSelectedIndices();
            if(selected.length > 0) {
                populatePatient(listModel.get(selected[0]));
            }
        });

        patientPanel = new PatientPanel();

        JPanel searchPanel = new JPanel(new FlowLayout());

        JTextField searchByName = new JTextField();
        searchByName.setColumns(10);
        JTextField searchByAddress = new JTextField();
        searchByAddress.setColumns(10);

        JButton search = new JButton("Filter Patients");
        search.addActionListener(e -> {
            populatePatients(model.filterPatients(searchByName.getText(), searchByAddress.getText()));
        });

        searchPanel.add(new JLabel("Name:"));
        searchPanel.add(searchByName);
        searchPanel.add(new JLabel("Address:"));
        searchPanel.add(searchByAddress);
        searchPanel.add(search);
        patientsPanel.add(searchPanel, BorderLayout.NORTH);
        patientsPanel.add(new JScrollPane(patientJList), BorderLayout.WEST);
        patientsPanel.add(new JScrollPane(patientPanel), BorderLayout.CENTER);
        return patientsPanel;
    }

    private void populatePatients(List<Patient> patients) {
        selectionModel.clearSelection();
        listModel.clear();
        listModel.addAll(patients);
    }

    private void populatePatient(Patient patient) {
        patientPanel.setPatient(patient);
    }
}
