package model;

import java.util.ArrayList;
// represents a user having a name and list of courses
public class User{
    private String name; // user name 
    private ArrayList<Courses> courses; // user courses 

    
    // MODIFIES: this
    // EFFECTS: Constructs user with given name and a new list of courses   
    public User(String name){
    //   stub
    }

    // REQUIRES: course is not null
    // MODIFIES: this 
    // EFFECTS: adds course to a users list of courses 
    public void addCourses(Courses course){
    //    stub  
    }
    
    public ArrayList<Courses> getCourses(){
        return null;
    }

    public String getUser(){
        return null;
    }

}