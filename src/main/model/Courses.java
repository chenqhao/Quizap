package model;

import java.util.ArrayList;

// represents a course with a list of units
public class Courses {
    private String courseName;
    private ArrayList<Units> units;

    // REQUIRES: name of non zero length
    // MODIFIES: this
    // EFFECTS: Constructs a course with given name and a list of units
    public Courses(String name){
        // stub

    }

    // REQUIRES: unit is not null
    // MODIFIES: this 
    // EFFECTS: adds Units to a course list of units
    public void addUnits(Units unit){
        // stub
    }

    // EFFECTS: returns a string representation of the courses
    public String toString(){
        return null;
    }
    
    public String getCourse(){
        return null;
    }

    public ArrayList<Units> getUnits(){
        return null; 
    }
    

}
