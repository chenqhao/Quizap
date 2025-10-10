package model;

import java.util.ArrayList;

// represents a course with a list of units and name 
public class Courses {
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
    

}
