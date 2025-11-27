package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
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
    public boolean addUnits(Units unit) {
        for (Units u : units) {
            if (u.getUnit().equalsIgnoreCase(unit.getUnit())) {
                return false; 
            }
        }
        units.add(unit);
        EventLog.getInstance().logEvent(new Event("added " + unit + " to " + courseName));
        return true;

    }

    // EFFECTS: returns a string representation of the courses
    @Override
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

    @ExcludeFromJacocoGeneratedReport
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
        return result;
    }

    @ExcludeFromJacocoGeneratedReport
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Courses other = (Courses) obj;
        if (courseName == null) {
            if (other.courseName != null) {
                return false;
            }
        } else if (!courseName.equals(other.courseName)) {
            return false;
        }
        return true;
    }

    

}
