package uk.ac.ucl.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {

    private JTextField searchByName;
    private JTextField searchByAddress;
    private JButton searchButton;

    public SearchPanel() {
        this.setLayout(new FlowLayout());

        searchByName = new JTextField();
        searchByName.setColumns(10);
        searchByAddress = new JTextField();
        searchByAddress.setColumns(10);

        searchButton = new JButton("Filter Patients");

        this.add(new JLabel("Name:"));
        this.add(searchByName);
        this.add(new JLabel("Address:"));
        this.add(searchByAddress);
        this.add(searchButton);
    }

    public void setSearchActionListener(ActionListener actionListener) {
        searchButton.addActionListener(actionListener);
    }

    public String getSearchByNameText() {
        return searchByName.getText();
    }

    public String getSearchByAddressText() {
        return searchByAddress.getText();
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
