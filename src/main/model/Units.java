package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistance.Writable;

// represents a unit that has a name, list of questions and a completion status
public class Units implements Writable {
    private String unit; // unit name
    private ArrayList<Questions> questions; // unit questions
    private boolean completion; //unit status

    // REQUIRES: name of non zero length
    // MODIFIES: this 
    // EFFECTS: constructs a unit with given name with a list of questions and sets completion to false
    public Units(String unit) {
        this.unit = unit;
        this.questions = new ArrayList<>();
        this.completion = false;
    }

    // REQUIRES: question that is not null
    // MODIFIES: this
    // EFFECTS: adds questions to a units list of questions
    public void addQuestions(Questions question) {
        if (!questions.contains(question)) {
            questions.add(question);
        }
    }

    // MODIFIES: this 
    // EFFECTS: marks this unit as complete
    public void markcompleted() { 
        this.completion = true;
    }

    // EFFECTS: returns a string representation of the unit
    public String toString() {
        return unit;
    }

    public String getUnit() {
        return unit;
    }

    public ArrayList<Questions> getQuestions() {
        return questions;
    }

    public boolean isCompletion() {
        return completion;
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
    // EFFECTS: returns things in the questions as a JSON array
    private JSONArray questionsToJson() {
        return null;

    }
}