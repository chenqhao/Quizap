package model;

// represents a answer with a answer description and correct status
public class Answers {
    String answer;
    boolean correct;

    // REQUIRES: description of non zero length
    // MODIFIES: this
    // EFFECTS: Constructs a answer with a given description and sets default correct to false 
    public Answers(String answer){
        this.answer = answer;
        this.correct = false;
    }


    public void setCorrect(){
        this.correct = true; 
    }

    public String getAnswer(){
        return answer;
    }

    public boolean isCorrect(){
        return correct;
    }

    // returns a string representation of the answers
    public String toString(){
        return answer;
    }
    
}
