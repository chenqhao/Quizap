package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
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
        EventLog.getInstance().logEvent(new Event("User created: " + name));
    }

    // REQUIRES: course is not null
    // MODIFIES: this 
    // EFFECTS: adds course to a users list of courses 
    public boolean addCourses(Courses course) {
        for (Courses c : courses) {
            if (c.getCourse().equalsIgnoreCase(course.getCourse())) {
                return false;
            }
        }
        courses.add(course);
        EventLog.getInstance().logEvent(new Event("added " + course + " to " + name));
        return true;
    }
    
    public ArrayList<Courses> getCourses() {
        return courses;
    }

    public String getUser() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: changes the user name given newName input
    public void changeName(String newName) {
        if (newName != null) {
            EventLog.getInstance().logEvent(new Event("changed  " + this.name + " to " + newName));
            this.name = newName;
        }

    }


    /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("course", coursesToJson());
        return json;
    }

    /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    // EFFECTS: returns things in the User as a JSON array
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Courses c: courses) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    @ExcludeFromJacocoGeneratedReport
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        User other = (User) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }


}