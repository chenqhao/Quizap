package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
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
    public boolean addQuestions(Questions question) {
        for (Questions q : questions) {
            if (q.getQuestion().equalsIgnoreCase(question.getQuestion())) {
                return false; 
            }
        }
        questions.add(question);
        EventLog.getInstance().logEvent(new Event("added " + question + " to " + unit));
        return true;
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
        JSONObject json = new JSONObject();
        json.put("unit", unit);
        json.put("question", questionsToJson());
        return json;
    }

    /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    // EFFECTS: returns things in the questions as a JSON array
    private JSONArray questionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Questions q: questions) {
            jsonArray.put(q.toJson());
        }

        return jsonArray;
    }

    @ExcludeFromJacocoGeneratedReport
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
        Units other = (Units) obj;
        if (unit == null) {
            if (other.unit != null) {
                return false;
            }
        } else if (!unit.equals(other.unit)) {
            return false;
        }
        return true;
    }

    

}