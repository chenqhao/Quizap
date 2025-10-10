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
    // EFFECTS: initalizes fields, creates scanner, sets coniditon and count, starts
    // makeUser
    public StartApp() {
        input = new Scanner(System.in);
        this.condition = false;
        this.count = 0;
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
        inputCourses();
        chooseCourse();
    }

    // REQUIRES: name length > 0
    // MODIFIES: this
    // EFFECTS: allows user to input courses, creates course objects, adds to user,
    // runs inputUnit to add units
    public void inputCourses() {
        this.condition = false;
        while (condition == false) {
            System.out.println("Enter Course Name");
            String courseName = input.nextLine();
            courses = new Courses(courseName);
            user.addCourses(courses);
            inputUnits();
            boolean validInput = false;
            while (!validInput) {
                System.out.println("would you like to add another course? Y/N");
                String add = input.nextLine();
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
    // MODIFIES: this
    // EFFECTS: allows user to input units, makes unit objects, adds to courses,
    // runs inputQuestion to add questions
    public void inputUnits() {
        this.condition = false;
        while (condition == false) {
            System.out.println("Enter Unit For Course");
            String unit = input.nextLine();
            units = new Units(unit);
            courses.addUnits(units);
            inputQuestions();
            boolean validInput = false;
            while (!validInput) {
                System.out.println("would you like to add another Unit? Y/N");
                String add = input.nextLine();
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
    // MODIFIES: this
    // EFFECTS: lets user add questions to units, creates question objects, adds
    // them, and runs inputAnswers
    public void inputQuestions() {
        this.condition = false;
        while (condition == false) {
            System.out.println("Enter Question");
            String question = input.nextLine();
            questions = new Questions(question);
            units.addQuestions(questions);
            inputAnswers();
            boolean validInput = false;
            while (!validInput) {
                System.out.println("would you like to add another Question? Y/N");
                String add = input.nextLine();
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

    // MODIFIES: this
    // EFFECTS: allows users to either add a written or multiple choice question
    public void inputAnswers() {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("what kind of answer would you like to input?: Multiple/Written");
            String questionType = input.nextLine();
            if (questionType.equals("multiple")) {
                validInput = true;
                multipleChoice();
            } else if (questionType.equals("written")) {
                validInput = true;
                writtenChoice();
            } else {
                System.out.println("please chooes one of the options");
            }
        }
    }

    // REQUIRES: name length > 0
    // MODIFIES: this
    // EFFECTS: lets user add two answers, creates answer objects, and allows adding
    // more or ending input
    @SuppressWarnings("methodlength")
    public void multipleChoice() {
        while (count < 2) {
            System.out.println("Enter answer");
            String answer1 = input.nextLine();
            answers = new Answers(answer1);
            questions.addAnswers(answers);
            System.out.println("do you want to set this as the correct answer? Y/N");
            String correct1 = input.nextLine();
            if (correct1.equals("Y")) {
                answers.setCorrect();
            }
            this.count += 1;
        }
        boolean validInput = false;
        while (!validInput) {
            System.out.println("would you like to add another answer? Y/N");
            String add = input.nextLine();
            if (add.equals("Y")) {
                validInput = true;
                addMoreMultipleChoice();
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
    @SuppressWarnings("methodlength")
    public void addMoreMultipleChoice() {
        this.condition = false;
        while (condition == false) {
            System.out.println("Enter new answer");
            String answerMore = input.nextLine();
            answers = new Answers(answerMore);
            questions.addAnswers(answers);
            System.out.println("do you want to set this as the correct answer? Y/N");
            String correct3 = input.nextLine();
            if (correct3.equals("Y")) {
                answers.setCorrect();
            }
            boolean validInput = false;
            while (!validInput) {
                System.out.println("add one more answer? Y/N");
                String newAnswer = input.nextLine();
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
    }

    // REQUIRES: name length > 0
    // MODIFIES: this
    // EFFECTS: allows user to input a written question, creates answer object to
    // add into question
    public void writtenChoice() {
        System.out.println("please input the answer to the question eg. x^2");
        String answer = input.nextLine();
        answers = new Answers(answer);
        questions.addAnswers(answers);
    }

    // MODIFIES: this
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
                accessSomething();
                return c;
            }
        }
        System.out.println("there is not a course in your list");
        return null;
    }

    // MODIFIES: this
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


    // MODIFIES: this
    // EFFECTS: lets user pick a question, shows available answers, returns selected
    // or null if not found

    public Questions chooseQuestions(Units selectedUnits) {
        System.out.println("choose which question you would like to solve");
        String choose = input.nextLine();
        for (Questions q : selectedUnits.getQuestions()) {
            if (choose.equals(q.getQuestion())) {
                System.out.println(q.getAnswers());
                return q;
            }
        }
        System.out.println("there is not a Question in your list");
        return null;
    }

    private void accessSomething() {
        boolean validInput = false;
        while (!validInput) {
        System.out.println("would you like to access another courses? Y/N");
        String access = input.nextLine();
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
}