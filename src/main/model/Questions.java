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
    public Questions(String question) {
        this.question = question;
        completion = false; 
        answers = new ArrayList<>();

    }

    // REQUIRES: answer is not null
    // MODIFIES: this
    // EFFECTS: adds answers to a questions list of answers 
    public void addAnswers(Answers answer) {
        if (!answers.contains(answer)) {
            answers.add(answer);
        }
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
}
