package persistance;

import org.json.JSONObject;

/* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
