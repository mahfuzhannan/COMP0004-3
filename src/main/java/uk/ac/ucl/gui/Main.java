package uk.ac.ucl.gui;

import uk.ac.ucl.model.Model;

public class Main {

    public static void main(String[] args) {
        new View(new Model()).init();
    }
}
