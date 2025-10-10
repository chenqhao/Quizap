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

    // REQUIERS: answer is not null
    // MODIFIES: this
    // EFFECTS: adds answers to a questions list of answers 
    public void addAnswers(Answers answer){
        // stub
    }

    // MODIFIES: this 
    // EFFECTS: marks question as complete
    public void Markcompleted(Answers answer){
        // stub
    }
    public String getQuestion(){
        return null;
    }

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
