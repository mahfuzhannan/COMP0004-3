package uk.ac.ucl;

import uk.ac.ucl.gui.LoadCsvPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.IOException;

public class View extends JFrame {
    private static final String TITLE = "Patient App";

    private Model model;

    private JButton openFileChooserButton;
    private JFileChooser fileChooser;

    public View(Model model) throws HeadlessException {
        super(TITLE);

        this.model = model;

        this.setSize(new Dimension(800, 500));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout(10, 10));
    }

    public void init() {
        JPanel csvPanel = new JPanel(new BorderLayout());
        csvPanel.setBorder(new TitledBorder("File Load Panel"));
        openFileChooserButton = new JButton("Load File");
        openFileChooserButton.addActionListener(e -> {
            int status = fileChooser.showDialog(this, "Approve");
            if (status == JFileChooser.APPROVE_OPTION) {
                try {
                    model.readFile(fileChooser.getSelectedFile());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Could not load file.");
                }
            }
        });
        csvPanel.add(openFileChooserButton, BorderLayout.CENTER);


        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select file to load");
        fileChooser.setMultiSelectionEnabled(false);

        this.add(csvPanel, BorderLayout.NORTH);
        this.setVisible(true);
    }
}
