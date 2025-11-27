package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import persistance.Writable;

// represents questions that has a question description, list of answers and completion status 
public class Questions implements Writable {
    private String question;
    private ArrayList<Answers> answers; 
    private boolean completion; 
    private boolean isWritten;

    // REQUIRES: description of non zero length
    // MODIFIES: this
    // EFFECTS: Constructs a question with a list of answers and sets completion to false
    public Questions(String question) {
        this.question = question;
        completion = false; 
        answers = new ArrayList<>();
        this.isWritten = false; 

    }

    // REQUIRES: answer is not null
    // MODIFIES: this
    // EFFECTS: adds answers to a questions list of answers 
    public boolean addAnswers(Answers answer) {
        for (Answers a : answers) { 
            if (a.getAnswer().equalsIgnoreCase(answer.getAnswer())) {
                return false; 
            }
        }
        answers.add(answer);
        EventLog.getInstance().logEvent(new Event("added " + answer + " to " + question));
        return true;
    }

    // MODIFIES: this 
    // EFFECTS: marks question as complete
    public void markcompleted(Answers answer) {
        if (answer.isCorrect() == true) {
            this.completion = true;
        }
    }
    
    public String getQuestion() {
        return question;
    }

    // EFFECTS: returns a string representation of the question
    public String toString() {
        return question;
    }

    public ArrayList<Answers> getAnswers() {
        return answers;
    }

    public boolean isCompletion() {
        return completion; 
    }

    public void setWritten() {
        this.isWritten = true;
    }

    public boolean getWritten() {
        return isWritten;
    }

    /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", question);
        json.put("answer", answersToJson());
        json.put("isWritten", isWritten);
        return json;
    }

    /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    // EFFECTS: returns things in the answers as a JSON array
    private JSONArray answersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Answers a: answers) {
            jsonArray.put(a.toJson());
        }
        return jsonArray;
    }

    @ExcludeFromJacocoGeneratedReport
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((question == null) ? 0 : question.hashCode());
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
        Questions other = (Questions) obj;
        if (question == null) {
            if (other.question != null) {
                return false;
            }
        } else if (!question.equals(other.question)) {
            return false;
        }
        return true;
    }

    

}
