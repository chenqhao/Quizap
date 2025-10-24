package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistance.Writable;

// represents a course with a list of units and name 
public class Courses implements Writable {
    private String courseName;
    private ArrayList<Units> units;

    // REQUIRES: name of non zero length
    // MODIFIES: this
    // EFFECTS: Constructs a course with given name and a list of units
    public Courses(String name) {
        this.courseName = name; 
        this.units = new ArrayList<>();
    }

    // REQUIRES: unit is not null
    // MODIFIES: this 
    // EFFECTS: adds Units to a course list of units
    public void addUnits(Units unit) {
        if (!units.contains(unit)) {
            units.add(unit);
        }
    }

    // EFFECTS: returns a string representation of the courses
    public String toString() {
        return courseName;
    }
    
    public String getCourse() {
        return courseName;
    }

    public ArrayList<Units> getUnits() {
        return units; 
    }
    
    /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("course", courseName);
        json.put("unit", unitToJson());
        return json;
    }

    /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    // EFFECTS: returns things in the units as a JSON array
    private JSONArray unitToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Units u: units) {
            jsonArray.put(u.toJson());
        }

        return jsonArray;
    }

}
