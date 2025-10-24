package model;

import org.json.JSONObject;

import persistance.Writable;

// represents a answer with a answer description and correct status
public class Answers implements Writable {
    private String answer;
    private boolean correct;

    // REQUIRES: description of non zero length
    // MODIFIES: this
    // EFFECTS: Constructs a answer with a given description and sets default correct to false 
    public Answers(String answer) {
        this.answer = answer;
        this.correct = false;
    }

    public void setCorrect() {
        this.correct = true; 
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return correct; 
    }

    

    // returns a string representation of the answers
    public String toString() {
        return answer;
    }

    /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("answer", answer);
        json.put("correct", correct);
        return json;
    }
    
}
