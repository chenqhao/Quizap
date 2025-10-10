package model;

import java.util.ArrayList;

// represents a unit that has a name, list of questions and a completion status
public class Units {
    private String unit; // unit name
    private ArrayList<Questions> questions; // unit questions
    private boolean completion; //unit status

    // REQUIRES: name of non zero length
    // MODIFIES: this 
    // EFFECTS: constructs a unit with given name with a list of questions and sets completion to false
    public Units(String unit){
        // stub
    }

    // REQUIRES: question that is not null
    // MODIFIES: this
    // EFFECTS: adds questions to a units list of questions
    public void addQuestions(Questions question){
        // stub
    }

    // MODIFIES: this 
    // EFFECTS: marks this unit as complete
    public void Markcompleted(){
        this.completion = true;
    }

    // EFFECTS: returns a string representation of the unit
    public String toString(){
        return null;
    }

    public String getUnit(){
        return null;
    }

    public ArrayList<Questions> getQuestions(){
        return null;
    }

    public boolean isCompletion(){
        return false;
    }

}