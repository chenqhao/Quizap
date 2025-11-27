package model;

import org.json.JSONObject;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
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
        EventLog.getInstance().logEvent(new Event("set " + answer + " as correct"));
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return correct; 
    }

    // returns a string representation of the answers
    @Override
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

    @ExcludeFromJacocoGeneratedReport
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((answer == null) ? 0 : answer.hashCode());
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
        Answers other = (Answers) obj;
        if (answer == null) {
            if (other.answer != null) {
                return false;
            }
        } else if (!answer.equals(other.answer)) {
            return false;
        }
        return true;
    }

    
    
}
