    package ui;


    import java.util.*;

    import model.*;

    // Quiz Appplication 
    public class StartApp { 
        private User user;
        private Courses courses; 
        private Units units;
        private Questions questions;
        private Answers answers;
        private Scanner input;
        private boolean condition;      
        private int count; 

        // MODIFIES: this
        // EFFECTS: initalizes fields, creates scanner and sets coniditon and count and then starts the application with creating a user
        public StartApp(){
            input = new Scanner(System.in);
            this.condition = false;
            this.count = 0; 
            MakeUser();

        }

        // REQUIRES: name length > 0
        // MODIFIES: this 
        // EFFECTS: allows user to give themselves a username, allows them to input courses and at the very end choose the course they wish to access
        public void MakeUser(){
            System.out.println("Enter User Name");
            String userName = input.nextLine();
            user = new User(userName);
            InputCourses();
            ChooseCourse();
        }

        // REQUIRES: name length > 0
        // MODIFIES: this
        // EFFECTS: allows user to input courses, creating course objects and adding them to user, runs InputUnit to add units
        public void InputCourses(){ 
            this.condition = false;
            while(condition == false){
            System.out.println("Enter Course Name");
            String courseName = input.nextLine();
            courses = new Courses(courseName);
            user.addCourses(courses);
            InputUnits();
            boolean validInput = false; 
            while(!validInput){
            System.out.println("would you like to add another course? Y/N");
            String add = input.nextLine();
            if(add.equals("Y")){
                validInput = true; 
                this.condition = false;
            }
            else if(add.equals("N")){
                validInput = true;
                this.condition = true;
            }
            else{
                System.out.println("Please input a Valid Prompt");
            }
            }
            }
        }

        // REQUIRES: name length > 0
        // MODIFIES: this 
        // EFFECTS: allows user to input units, creating unit objects and adding them to courses, runs InputQuestion to add questions
        public void InputUnits(){
            this.condition = false;
            while(condition == false){
            System.out.println("Enter Unit For Course");
            String unit = input.nextLine();
            units = new Units(unit);
            courses.addUnits(units);
            InputQuestions();
            boolean validInput = false;
            while(!validInput){
            System.out.println("would you like to add another Unit? Y/N");
            String add = input.nextLine();
            if(add.equals("Y")){
                validInput = true;
                this.condition = false;
            }
            else if(add.equals("N")){
                validInput = true; 
                this.condition = true;
            }
            else{
                System.out.println("Please input a Valid Prompt");
            }
        }
    }
    }

        // REQUIRES: name length > 0
        // MODIFIES: this
        // EFFECTS: allows user to add questions into units, makes question objects to add into unit, runs InputAnswers to add answers
        public void InputQuestions(){
            this.condition = false; 
            while(condition == false){
            System.out.println("Enter Question");
            String question = input.nextLine();
            questions = new Questions(question);
            units.addQuestions(questions);
            InputAnswers();
            boolean validInput = false;
            while(!validInput) {
            System.out.println("would you like to add another Question? Y/N");
            String add = input.nextLine();
            if(add.equals("Y")){
                validInput = true;
                this.condition = false;
            }
            else if(add.equals("N")){
                validInput = true;
                this.condition = true;
            }
            else{
                System.out.println("Please input a Valid Prompt");
            }
        }
        }
    }

        // MODIFIES: this
        // EFFECTS: allows users to either add a written or multiple choice question
        public void InputAnswers(){
            boolean validInput = false;
            while(!validInput){
            System.out.println("what kind of answer would you like to input?: Multiple/Written");
            String questionType = input.nextLine();
            if(questionType.equals("multiple")){
                validInput = true;
                MultipleChoice();
            }
            else if (questionType.equals("written")){
                validInput = true;
                WrittenChoice();
            }
            else{
                System.out.println("please chooes one of the options");
            }
        }
        }
    // REQUIRES: name length > 0
    // MODIFIES: this
    // EFFECTS: allows user to add two answers to their question, makes answer objects to add into questions, allows user to either add more questions or end method
    @SuppressWarnings("methodlength")
    public void MultipleChoice(){
            while(count < 2){
            System.out.println("Enter answer");
            String answer1 = input.nextLine();
            answers = new Answers(answer1);
            questions.addAnswers(answers);
            System.out.println("do you want to set this as the correct answer? Y/N");
            String correct1 = input.nextLine();
            if(correct1.equals("Y")){
                answers.setCorrect();
            }
            this.count += 1;
        }
            boolean validInput = false;
            while(!validInput){
            System.out.println("would you like to add another answer? Y/N");
            String add = input.nextLine();
            if(add.equals("Y")){
                validInput = true;
                AddMoreMultipleChoice();
            }
            else if(add.equals("N")){
                validInput = true;
                // ChooseCourse();
            }
            else{
                System.out.println("please input a valid prompt");
            }
        }
    }

    // REQUIRES: name length > 0
    // MODIFIES: this
    // EFFECTS: allows user to add as many multiple choice answers as they want, creates answer object to put into questions 
    @SuppressWarnings("methodlength")
    public void AddMoreMultipleChoice(){
            this.condition = false;
            while(condition == false){
                System.out.println("Enter new answer");
                String answerMore = input.nextLine();
                answers = new Answers(answerMore);
                questions.addAnswers(answers);
                System.out.println("do you want to set this as the correct answer? Y/N");
                String correct3 = input.nextLine();
                if(correct3.equals("Y")){
                    answers.setCorrect();
                }
            boolean validInput = false;
            while(!validInput){
            System.out.println("add one more answer? Y/N");
            String newAnswer = input.nextLine();
            if(newAnswer.equals("Y")){
                validInput = true;
                this.condition = false;
            }
            else if(newAnswer.equals("N")){
                validInput = true;
                this.condition = true;
            }
            else{
                System.out.println("please input a valid input");
            }
            }
        }
    }
    

    // REQUIRES: name length > 0
    // MODIFIES: this
    // EFFECTS: allows user to input a written question, creates answer object to add into question
    public void WrittenChoice(){
        System.out.println("please input the answer to the question eg. x^2");
        String answer = input.nextLine();
        answers = new Answers(answer);
        questions.addAnswers(answers);
    }



    // MODIFIES: this
    // EFFECTS: allows user to access a course of their choice, displays units, runs ChooseUnits, returns selected unit or null if nothing found 
    public Courses ChooseCourse(){
        System.out.println(user.getCourses());
        System.out.println("choose course you would like to access");
        String choose = input.nextLine();
        for(Courses c: user.getCourses()){
            if(choose.equals(c.getCourse())){
                System.out.println(c.getUnits());
                ChooseUnits(c);
                return c;
            }
        }
        System.out.println("there is not a course in your list");
        return null;
    }

    // MODIFIES: this
    // EFFECTS: allows user to access a unit or their choice, displays questions, runs ChooseQuestion, returns selected question or null if nothing found 
    public Units ChooseUnits(Courses selectedCourses){
        System.out.println("Choose unit you would like to access");
        String choose = input.nextLine();
        for (Units u : selectedCourses.getUnits()){
            if(choose.equals(u.getUnit())){
                System.out.println(u.getQuestions());
                ChooseQuestions(u);
                return u;
            }
        }
        System.out.println("there is not a Unit in your list");
        return null;
    }


    // MODIFIES: this
    // EFFECTS: allows user to access a question of their choice, displays avaliable answers, returns selected question or null if nothing found 
    public Questions ChooseQuestions(Units selectedUnits){
        System.out.println("choose which question you would like to solve");
        String choose = input.nextLine();
        for (Questions q: selectedUnits.getQuestions()){
            if(choose.equals(q.getQuestion())){
                System.out.println(q.getAnswers());
                return q;
            }
        }
        System.out.println("there is not a Question in your list");
        return null;
    }

}
