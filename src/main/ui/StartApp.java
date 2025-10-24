package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.*;
import persistance.JsonReader;
import persistance.JsonWriter;

@ExcludeFromJacocoGeneratedReport
// Quiz Appplication 
public class StartApp {
    private static final String JSON_STORE = "./data/user.json";
    private User user;
    private Courses courses;
    private Units units;
    private Questions questions;
    private Answers answers;
    private Scanner input;
    private boolean condition;
    private boolean chooserConditon; 
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: initalizes fields, creates scanner, sets coniditon and count, starts
    // makeUser
    public StartApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        this.condition = false;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        makeUser();

    }

    // REQUIRES: name length > 0
    // MODIFIES: this
    // EFFECTS: allows user make a username, input courses and at the very end
    // choose the course they wish to access
    public void makeUser() {
        System.out.println("Enter User Name");
        String userName = input.nextLine();
        user = new User(userName);
        displayMenu();
        String access = input.nextLine().toUpperCase();
        processCommand(access);
        while (!chooserConditon) {
            displayMenu();
            String newAccess = input.nextLine().toUpperCase();
            processCommand(newAccess);
        }
    }

    // REQUIRES: name length > 0
    // MODIFIES: this, validInput, user, courses
    // EFFECTS: allows user to input courses, creates course objects, adds to user,
    // runs inputUnit to add units
    public void inputCourses() {
        this.condition = false;
        while (condition == false) {
            System.out.println("Enter Course Name");
            String courseName = input.nextLine();
            courses = new Courses(courseName);
            inputUnits(courses);
            user.addCourses(courses);
            boolean validInput = false;
            while (!validInput) {
                System.out.println("would you like to add another course? Y/N");
                String add = input.nextLine().toUpperCase();
                if (add.equals("Y")) {
                    validInput = true;
                    this.condition = false;
                } else if (add.equals("N")) {
                    validInput = true;
                    this.condition = true;
                } else {
                    System.out.println("Please input a Valid Prompt");
                }
            }
        }
    }

    // REQUIRES: name length > 0
    // MODIFIES: this, courses, units
    // EFFECTS: allows user to input units, makes unit objects, adds to courses,
    // runs inputQuestion to add questions
    public void inputUnits(Courses courses) {
        this.condition = false;
        while (condition == false) {
            System.out.println("Enter Unit For Course");
            String unit = input.nextLine();
            units = new Units(unit);
            inputQuestions(units);
            courses.addUnits(units);
            boolean validInput = false;
            while (!validInput) {
                System.out.println("would you like to add another Unit? Y/N");
                String add = input.nextLine().toUpperCase();
                if (add.equals("Y")) {
                    validInput = true;
                    this.condition = false;
                } else if (add.equals("N")) {
                    validInput = true;
                    this.condition = true;
                } else {
                    System.out.println("Please input a Valid Prompt");
                }
            }
        }
    }

    // REQUIRES: name length > 0
    // MODIFIES: this, units questions
    // EFFECTS: lets user add questions to units, creates question objects, adds
    // them, and runs inputAnswers
    public void inputQuestions(Units units) {
        this.condition = false;
        while (condition == false) {
            System.out.println("Enter Question");
            String question = input.nextLine();
            questions = new Questions(question);
            inputAnswers(questions);
            units.addQuestions(questions);
            boolean validInput = false;
            while (!validInput) {
                System.out.println("would you like to add another Question? Y/N");
                String add = input.nextLine().toUpperCase();
                if (add.equals("Y")) {
                    validInput = true;
                    this.condition = false;
                } else if (add.equals("N")) {
                    validInput = true;
                    this.condition = true;
                } else {
                    System.out.println("Please input a Valid Prompt");
                }
            }
        }
    }

    // MODIFIES: this, validInput
    // EFFECTS: allows users to either add a written or multiple choice question
    public void inputAnswers(Questions questions) {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("what kind of answer would you like to input?: Multiple/Written");
            String questionType = input.nextLine().toLowerCase();
            if (questionType.equals("multiple")) {
                validInput = true;
                multipleChoice(questions);
            } else if (questionType.equals("written")) {
                validInput = true;
                questions.setWritten();
                writtenChoice(questions);   
            } else {
                System.out.println("please choose one of the options");
            }
        }
    }

    // REQUIRES: name length > 0
    // MODIFIES: this, units, questions 
    // EFFECTS: lets user add two answers, creates answer objects, and allows adding
    // more or ending input
    public void multipleChoice(Questions questions) {
        int count = 0;
        while (count < 2) {
            System.out.println("Enter answer");
            String answer1 = input.nextLine();
            answers = new Answers(answer1);
            questions.addAnswers(answers);
            giveValidCorrectness(answers);
            count += 1;
        }
        addMoreChoice(questions);
    }

    // MODIFIES: this, validInput
    // EFFECTS: lets user to set answer as correct or false
    public void giveValidCorrectness(Answers answers) {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("do you want to set this as the correct answer? Y/N");
            String correct1 = input.nextLine().toUpperCase();
            if (correct1.equals("Y")) {
                answers.setCorrect();
                validInput = true;
            } else if (correct1.equals("N")) {
                validInput = true;
            } else {
                System.out.println("please input a valid input");
            }
        }
    }

    // MODIFIES: this, validInput
    // EFFECTS: allows use to add more answers 
    public void addMoreChoice(Questions questions) {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("would you like to add another answer? Y/N");
            String add = input.nextLine().toUpperCase();
            if (add.equals("Y")) {
                validInput = true;
                addMoreMultipleChoice(questions);
            } else if (add.equals("N")) {
                validInput = true;
            } else {
                System.out.println("please input a valid prompt");
            }
        }
    }


    // REQUIRES: name length > 0
    // MODIFIES: this
    // EFFECTS: allows user to add infinte multiple choice answers, creates answer
    // object to put into questions
    public void addMoreMultipleChoice(Questions questions) {
        this.condition = false;
        while (condition == false) {
            System.out.println("Enter new answer");
            String answerMore = input.nextLine();
            answers = new Answers(answerMore);
            questions.addAnswers(answers);
            giveValidCorrectness(answers);
            addEvenMoreMultipleChoice();
        }
    }

    // MODIFIES: this, validInput
    // EFFECTS: allows users to add more multiple choice questions 
    public void addEvenMoreMultipleChoice() {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("add one more answer? Y/N");
            String newAnswer = input.nextLine().toUpperCase();
            if (newAnswer.equals("Y")) {
                validInput = true;
                this.condition = false;
            } else if (newAnswer.equals("N")) {
                validInput = true;
                this.condition = true;
            } else {
                System.out.println("please input a valid input");
            }
        }
    }

    // REQUIRES: name length > 0
    // MODIFIES: this, questions, answers
    // EFFECTS: allows user to input a written question, creates answer object to
    // add into question
    public void writtenChoice(Questions questions) {
        System.out.println("please input the answer to the question eg. x^2");
        String answer = input.nextLine();
        answers = new Answers(answer);
        answers.setCorrect();
        questions.addAnswers(answers);
    }

    // REQUIRES: user to have at least one course added 
    // EFFECTS: lets user pick a course, shows units, runs ChooseUnits, returns
    // selected or null if not found
    public Courses chooseCourse() {
        System.out.println(user.getCourses());
        System.out.println("choose course you would like to access");
        String choose = input.nextLine();
        for (Courses c: user.getCourses()) {
            if (choose.equals(c.getCourse())) {
                System.out.println(c.getUnits());
                chooseUnits(c);
                accessCourse();
                return c;
            }
        }
        System.out.println("there is not a course in your list");
        accessCourse();
        return null;
    }

    // REQUIRES: user to have at least one unit added 
    // EFFECTS: lets user pick a unit, shows questions, runs ChooseQuestions,
    // returns selected or null if not found
    public Units chooseUnits(Courses selectedCourses) {
        System.out.println("Choose unit you would like to access");
        String choose = input.nextLine();
        for (Units u : selectedCourses.getUnits()) {
            if (choose.equals(u.getUnit())) {
                System.out.println(u.getQuestions());
                chooseQuestions(u);
                return u;   
            }
        }
        System.out.println("there is not a Unit in your list");
        return null;
    }


    // REQUIRES: user to have at least one question added 
    // EFFECTS: lets user pick a question, shows available answers, returns selected
    // or null if not found
    public Questions chooseQuestions(Units selectedUnits) {
        System.out.println("choose which question you would like to solve");
        String choose = input.nextLine();
        for (Questions q : selectedUnits.getQuestions()) {
            if (choose.equals(q.getQuestion())) {
                chooseAnswer(q);
                return q;
            }
        }
        System.out.println("there is not a Question in your list");
        return null;
    }

    // REQUIRES: user to have at least one answer added
    // EFFECTS: lets user choose or type out a question
    private void chooseAnswer(Questions selectedQuestions) { 
        if (selectedQuestions.getWritten() == true) {
            System.out.println("please input your answer");
            String chooseWritten = input.nextLine();
            if (chooseWritten.equals(selectedQuestions.getAnswers().get(0).getAnswer())) {
                System.out.println("correct");
            } else {
                System.out.println("incorrect");
            }
        } else if (selectedQuestions.getWritten() == false) {
            System.out.println(selectedQuestions.getAnswers());
            System.out.println("please select a answer");
            String choose = input.nextLine();
            for (Answers a: selectedQuestions.getAnswers()) {
                if (choose.equals(a.getAnswer())) {
                    if (a.isCorrect() == true) {
                        System.out.println("correct");
                    } else {
                        System.out.println("incorrect");
                    }
                }
            }
        } 
        System.out.println("there is not a answer in your list");
    }

    // REQUIRES: user to have at least one course added 
    // MODIFIES: this 
    // EFFECTS: lets users pick a new course to access
    private void accessCourse() {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("would you like to access another courses? Y/N");
            String access = input.nextLine().toUpperCase();
            if (access.equals("Y")) {
                validInput = true;
                chooseCourse();
            } else if (access.equals("N")) {
                validInput = true; 
            } else {
                System.out.println("please input a valid input");
            }

        }
    }

    // EFFECTS: displays options for user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tA -> add course");
        System.out.println("\tC -> choose courses");
        System.out.println("\tS -> save course to user");
        System.out.println("\tL -> load course from user");
        System.out.println("\tQ -> quit");
    }

    // EFFECTS: things
    // EFFECTS: proccesses user command 
    private void processCommand(String command) {
        if (command.equals("A")) {
            inputCourses();
        } else if (command.equals("C")) {
            chooseCourse();
        } else if (command.equals("S")) {
            // saveUser();
        } else if (command.equals("L")) {
            loadUser();
        } else if (command.equals("Q")) {
            this.chooserConditon = true; 
        } else {
            System.out.println("Selection not valid...");
        }
    }
    

    /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    // MODIFIES: this 
    // EFFECTS: loads user from file 
    private void loadUser() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded " + user.getUser() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
