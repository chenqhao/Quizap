package persistance;

import java.io.*;

import org.json.JSONObject;

import model.*;

  /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
// Represents a writer that writes JSON representation of user of file
public class JsonWriter {

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        
    }

    // MODIFIES: this
    // EFFECTS: opens writer, throws FileNotFoundException if destination file cannot be opened for writing 
    public void open() throws FileNotFoundException {
        
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of user to file 
    public void write(User user) {
    
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
      
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
    
    }

}
