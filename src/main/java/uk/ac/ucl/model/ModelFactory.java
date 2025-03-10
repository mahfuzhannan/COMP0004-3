package uk.ac.ucl.model;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

// This class gives access to the model to any other class that needs it.
// Calling the static method getModel (i.e., ModelFactory.getModel()) returns
// an initialised Model object. This version limits the program to one model object,
// which is returned whenever getModel is called.
// The factory also illustrates how a data file name can be passed to the model.

public class ModelFactory
{
  private static Model model;

  public static Model getModel() throws IOException {
    if (model == null)
    {
      model = new Model();
      // Note where the .csv file is in the data directory, and the pathname to locate it.
      model.readFile(new File("./data/patients1000.csv"));
    }
    return model;
  }
}
