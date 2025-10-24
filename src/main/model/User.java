package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistance.Writable;

// represents a user having a name and list of courses

public class User implements Writable {
    private String name; // user name 
    private ArrayList<Courses> courses; // user courses 

    // REQUIRES: name of non zero length
    // MODIFIES: this
    // EFFECTS: Constructs user with given name and a new list of courses   
    public User(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    // REQUIRES: course is not null
    // MODIFIES: this 
    // EFFECTS: adds course to a users list of courses 
    public void addCourses(Courses course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }
    
    public ArrayList<Courses> getCourses() {
        return courses;
    }

    public String getUser() {
        return name;
    }

    /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    @Override
    public JSONObject toJson() {
        return null;
    }

    /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    // EFFECTS: returns things in the User as a JSON array
    private JSONArray coursesToJson() {
        return null;

    }
}