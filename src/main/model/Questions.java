package model;

import java.util.ArrayList;

// represents questions that has a question description, list of answers and completion status 
public class Questions {
    private String question;
    private ArrayList<Answers> answers; 
    private boolean completion; 

    // REQUIRES: description of non zero length
    // MODIFIES: this
    // EFFECTS: Constructs a question with a list of answers and sets completion to false
    public Questions(String question){
        // stub

    }

    // REQUIRES: answer is not null
    // MODIFIES: this
    // EFFECTS: adds answers to a questions list of answers 
    public void addAnswers(Answers answer){
        // stub
    }

    // MODIFIES: this 
    // EFFECTS: marks question as complete
    public void Markcompleted(){
        // stub
    }
    public String getQuestion(){
        return null;
    }

    // EFFECTS: returns a string representation of the question
    public String toString(){
        return null;
    }

    public ArrayList<Answers> getAnswers(){
        return null;
    }

    public boolean isCompletion(){
        return false; 
    }
}
