package ui.gui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.*;
import persistance.JsonReader;
import persistance.JsonWriter;

@ExcludeFromJacocoGeneratedReport
public class QuizAppGUI extends JFrame {
    
    private static final String JSON_STORE = "./data/user.json";

    private User user;
    private Units units;
    private Courses course;
    private Questions questions;
    
    private JButton userButton;
    private JButton courseButton;
    private JButton unitsButton;
    private JButton questionsButton;
    private JButton answerButton;

    private JButton startQuiz;
    private JButton answerQuestion;

    private JButton saveButton;
    private JButton loadButton;

    private JsonWriter jsonWriter; 
    private JsonReader jsonReader;

    private JComboBox<String> courseDropdown;
    private JComboBox<String> unitDropdown;
    private JComboBox<String> questionDropdown;
    private JComboBox<String> answerDropdown;

    private JTextArea displayArea;

    private JPanel buttonPanel;
    private JPanel dataPanel;
    private JPanel quizPanel;


    // represents the GUI of the quizApp
    public QuizAppGUI() { 

        super("Quizzard app");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,500);
        setLocationRelativeTo(null);

        constructComponents();

        addAllComponentsAndSet();

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setListeners();

        setVisible(true);

    }  

    // MODIFIES: this
    // EFFECTS: Constructs components of the GUI
    public void constructComponents() {
        
        courseDropdown = new JComboBox<>();
        unitDropdown = new JComboBox<>();
        questionDropdown = new JComboBox<>();
        answerDropdown = new JComboBox<>();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        userButton = new JButton("Add User");
        courseButton = new JButton("Add Course");
        unitsButton = new JButton("Add Unit");
        questionsButton = new JButton("Add Question");
        answerButton = new JButton("Add Answer");

        startQuiz = new JButton("Start Quiz");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        startQuiz = new JButton("Start Quiz");
        answerQuestion = new JButton("answer question");

        setStateOfComponents();
    }

    // MODIFIES: this
    // EFFECTS: sets state to false for all at first to help user navigate 
    public void setStateOfComponents() {

        courseButton.setEnabled(false);
        unitsButton.setEnabled(false);
        questionsButton.setEnabled(false);
        answerButton.setEnabled(false);
        courseDropdown.setEnabled(false);
        unitDropdown.setEnabled(false);
        questionDropdown.setEnabled(false);
        answerDropdown.setEnabled(false);
    }

    // MODIFIES: this 
    // EFFECTS: gives buttons actions when clicked 
    public void setListeners() {

        userButton.addActionListener(e -> addUser());
        courseButton.addActionListener(e -> addCourse());
        unitsButton.addActionListener(e -> addUnitToCourse());
        questionsButton.addActionListener(e -> addQuestionToUnit());
        answerButton.addActionListener(e -> addAnswerToQuestion());

        courseDropdown.addActionListener(e -> updateUnitsDropdown());
        unitDropdown.addActionListener(e -> updateQuestionsDropdown());
        questionDropdown.addActionListener(e -> updateAnswersDropdown());
        
        saveButton.addActionListener(e -> saveData());
        loadButton.addActionListener(e -> loadData());

        startQuiz.addActionListener(e -> startingQuiz());
        answerQuestion.addActionListener(e -> selectingQuestion());
    }

    // MODIFIES: this 
    // EFFECTS: adds buttons to panels in the GUI 
    public void addAllComponentsAndSet() { 

        buttonPanel = new JPanel();
        dataPanel = new JPanel();
        quizPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(1, 5, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        buttonPanel.add(userButton);
        buttonPanel.add(courseButton);
        buttonPanel.add(unitsButton);
        buttonPanel.add(questionsButton);
        buttonPanel.add(answerButton);
        buttonPanel.add(courseDropdown);
        buttonPanel.add(unitDropdown);
        buttonPanel.add(questionDropdown);
        buttonPanel.add(answerDropdown);


        dataPanel.add(saveButton);
        dataPanel.add(loadButton);

        quizPanel.add(startQuiz);

        add(dataPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.NORTH);
        add(quizPanel, BorderLayout.EAST);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

    }

    // MODIFIES: this 
    // EFFECTS: allows the user to add their own user with given name 
    public void addUser() { 
        String name = JOptionPane.showInputDialog(this, "Enter your name:");
        if (name != null && !name.trim().isEmpty()) {  
            user = new User(name.trim());
            displayArea.append("New user created: " + user.getUser() + "\n");
            userButton.setText("Change Name");
            courseButton.setEnabled(true);
        } else { 
            displayArea.append("No name has been added" + "\n");
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "No name input added");
        }
    }

    // MODIFIES: this, user
    // EFFECTS: adds Course to given User and dropdown
    public void addCourse() {
        String courseName = JOptionPane.showInputDialog(this, "Enter course name:");
        if (courseName != null && !courseName.trim().isEmpty()) {  
            course = new Courses(courseName.trim());
            user.getCourses().add(course);
            courseDropdown.addItem(courseName.trim());
            displayArea.append("New course added: " + courseName + "\n");
            unitsButton.setEnabled(true);
            courseDropdown.setEnabled(true);
        } else { 
            displayArea.append("No course has been added" + "\n");
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "No Course input added");
        }
    }

    // EFFECTS: Gets selected course in the course dropdown
    private Courses getSelectedCourse() {
        int index = courseDropdown.getSelectedIndex();
        if (index >= 0 && index < user.getCourses().size()) {
            return user.getCourses().get(index);
        }
        return null;
    }

    // MODIFIES: this, course
    // EFFECTS: adds unit to selcted course and dropdown
    public void addUnitToCourse() {
        Courses selectedCourse = getSelectedCourse();
        if (selectedCourse == null) {
            displayArea.append("No course selected.\n");
            return;
        }

        String unitName = JOptionPane.showInputDialog(this, "Enter unit name for " + selectedCourse.getCourse() + ":");
        if (unitName != null && !unitName.trim().isEmpty()) {
            units = new Units(unitName);
            selectedCourse.addUnits(units);
            unitDropdown.addItem(unitName.trim());
            displayArea.append("Unit '" + unitName + "' added to " + selectedCourse.getCourse() + ".\n");
            updateUnitsDropdown();
            questionsButton.setEnabled(true);
            unitDropdown.setEnabled(true);
        } else  {
            displayArea.append("No units has been added" + "\n");
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "No unit input added");
        }
    }  

    // EFFECTS: gets selected unit from dropdown
    private Units getSelectedUnit() {
        Courses selectedCourse = getSelectedCourse();
        if (selectedCourse == null) {
            return null;
        }

        int index = unitDropdown.getSelectedIndex();
        if (index >= 0 && index < selectedCourse.getUnits().size()) {
            return selectedCourse.getUnits().get(index);
        }
        return null;
    }

    // MODIFIES: this, unit 
    // EFFECTS: adds question to selected unit and dropdown
    public void addQuestionToUnit() {
        Units selectedUnit = getSelectedUnit();
        if (selectedUnit == null) {
            displayArea.append("No course selected.\n");
            return;
        }

        String questionName = JOptionPane.showInputDialog(this, "Enter question for " + selectedUnit.getUnit() + ":");
        if (questionName != null && !questionName.trim().isEmpty()) {
            questions = new Questions(questionName);
            selectedUnit.addQuestions(questions);
            questionDropdown.addItem(questionName.trim());
            displayArea.append("Question '" + questionName + "' added to " + selectedUnit.getUnit() + ".\n");
            updateQuestionsDropdown();
            answerButton.setEnabled(true);
            questionDropdown.setEnabled(true);
        } else  {
            displayArea.append("No questions has been added" + "\n");
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "No question input added");
        }

    }

    // EFFECTS: gets selected question from dropdown
    private Questions getSelectedQuestion() {
        Units selectedUnit = getSelectedUnit();
        if (selectedUnit == null) {
            return null;
        }
        int index = questionDropdown.getSelectedIndex();
        if (index >= 0 && index < selectedUnit.getQuestions().size()) {
            return selectedUnit.getQuestions().get(index);
        }
        return null;
    }


 
    // MODIFIES: this, question
    // EFFECTS: no questions selected if no question is selected,
    // otherwise:
    // display prompt for answer text and if correct
    // if user clicks yes and no empty creates answer and adds to selected question and dropdown
    // if user clicks yes and is empty a error pops up and plays a sound to let the user know 
    // if user clicks cancer does nothing 
    public void addAnswerToQuestion() {
        Questions selectedQuestion = getSelectedQuestion();
        if (selectedQuestion == null) {
            displayArea.append("No question selected.\n");
            return;
        }

        JTextField answerField = new JTextField();
        JCheckBox correctCheck = new JCheckBox("This is a correct answer");
        JPanel panel = createAnswerPanel(answerField, correctCheck);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Answer", 
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            validateAndAddAnswer(selectedQuestion, answerField.getText().trim(), correctCheck.isSelected());
        }
    }

    // EFFECTS: creates a panel for user to enter answer name 
    private JPanel createAnswerPanel(JTextField field, JCheckBox check) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Enter answer:"));
        panel.add(field);
        panel.add(check);
        return panel;
    }

    // MODIFIES: this, question
    // EFFECTS: if text.size() == 0, show message saying no answer added
    // else creates new answer with name and addes it to selected question, adds to dropdown 
    private void validateAndAddAnswer(Questions q, String text, boolean isCorrect) {
        if (text.isEmpty()) {
            displayArea.append("No answer added.\n");
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "No answer input added");
            return;
        }

        Answers newAnswer = new Answers(text);
        if (isCorrect) {
            newAnswer.setCorrect();
        }
        q.addAnswers(newAnswer);

        displayArea.append("Answer '" + text + "' added to " + q.getQuestion() 
                            + (isCorrect ? " (correct)\n" : "\n"));
        
        updateAnswersDropdown();
        answerDropdown.setEnabled(true);
    }


    // MODIFIES: this
    // EFFECTS: gives correct dropdown for each coures selected
    private void updateUnitsDropdown() {
        unitDropdown.removeAllItems();
        Courses selectedCourse = getSelectedCourse();
        if (selectedCourse != null) {
            for (Units u : selectedCourse.getUnits()) {
                unitDropdown.addItem(u.getUnit());
            }
            unitDropdown.setEnabled(true);
        } else {
            unitDropdown.setEnabled(false);
        }
    }

    // MODIFIES: this 
    // EFFECTS: gives correct dropdown for each question selected
    private void updateQuestionsDropdown() {
        questionDropdown.removeAllItems();
        Units selectedUnit = getSelectedUnit();
        if (selectedUnit != null) {
            for (Questions q : selectedUnit.getQuestions()) {
                questionDropdown.addItem(q.getQuestion());
            }
            questionDropdown.setEnabled(true);
        } else {
            questionDropdown.setEnabled(false);
        }
    }

    // MODIFIES: this 
    // EFFECTS: gives correct dropdown for each answer selected
    private void updateAnswersDropdown() {
        answerDropdown.removeAllItems();
        Questions selectedQuestion = getSelectedQuestion();
        if (selectedQuestion != null) {
            for (Answers a : selectedQuestion.getAnswers()) {
                answerDropdown.addItem(a.getAnswer());
            }
            answerDropdown.setEnabled(true);
        } else {
            answerDropdown.setEnabled(false);
        }
    }


    // MODIFIES: this
    // EFFECTS: save user to selected file
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            displayArea.append("Saved " + user.getUser() + " to: " + JSON_STORE + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            displayArea.append("could not write file: " + JSON_STORE + "\n");
        }
    }

    // MODIFIES: this, user 
    // EFFECTS: loads user file 
    private void loadData() {
        try {
            user = jsonReader.read();
            for (Courses c : user.getCourses()) {
                courseDropdown.addItem(c.getCourse());
            }
            enableAll(true);
            userButton.setText("Change name");
            displayArea.append("Loaded " + user.getUser() + " from: " + JSON_STORE + "\n");
        } catch (IOException e) {
            displayArea.append("Unable to read from file: " + JSON_STORE + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: either enables all as true or false depending on enable
    private void enableAll(boolean enable) {
        courseButton.setEnabled(enable);
        unitsButton.setEnabled(enable);
        questionsButton.setEnabled(enable);
        answerButton.setEnabled(enable);
        saveButton.setEnabled(enable);
        loadButton.setEnabled(enable);
        courseDropdown.setEnabled(enable);
    }

    // MODIFIES: this
    // EFFECTS: makes new frame for quiz
    private void startingQuiz() { 
        JFrame quizWindow = new JFrame(); 
        JPanel informationPanel = new JPanel();
        quizWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        quizWindow.setTitle("Quiz");
        quizWindow.add(informationPanel, BorderLayout.NORTH);
        quizWindow.setSize(1000,500);
        quizWindow.setLocationRelativeTo(null);
        informationPanel.setLayout(new GridLayout(1, 5, 10, 10));
        informationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        informationPanel.add(courseDropdown);
        informationPanel.add(unitDropdown);
        informationPanel.add(questionDropdown);
        informationPanel.add(answerQuestion);
        quizWindow.setVisible(true);
    }   

    // MODIFIES: this
    // EFFECTS: handles selecting question and goes into either written if only a single answer 
    // or multiple if there are more than one
    private void selectingQuestion() { 
        Questions selectedQuestion = getSelectedQuestion();
        if (selectedQuestion == null) {
            JOptionPane.showMessageDialog(this, "No question selected.");
            return;
        }

        ArrayList<Answers> answersList = selectedQuestion.getAnswers();
        if (answersList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No answers available for this question.");
            return;
        }

        if (answersList.size() == 1) {
            showWrittenQuestion(selectedQuestion);
        } else { 
            showMultipleChoiceQuestion(selectedQuestion, answersList);
        }
    }

    // MODIFIES: this 
    // EFFECT: gives user a written answer prompt 
    private void showWrittenQuestion(Questions selectedQuestion) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("<html><b>" + selectedQuestion.getQuestion() + "</b></html>"));
        JTextField answerField = new JTextField(20);
        panel.add(answerField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Question",  
                                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String answer = answerField.getText().trim();
            if (answer.isEmpty()) {
                JOptionPane.showInputDialog(this, "Please enter an answer!");
            } else {
                displayArea.append("Answered \"" + answer + "\" for: "  + selectedQuestion.getQuestion() + "\n");
                String selectedQuestionAnswer = selectedQuestion.getAnswers().get(0).getAnswer();
                if (answer.equalsIgnoreCase(selectedQuestionAnswer)) {
                    displayArea.append("Correct");
                } else { 
                    displayArea.append("Incorrect");
                }
            }
        }
    }

    // MODIFIES: this 
    // EFFECTS: gives user a multiple choice prompt with check boxes 
    private void showMultipleChoiceQuestion(Questions selectedQuestion, List<Answers> answersList) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("<html><b>" + selectedQuestion.getQuestion() + "</b></html>"));

        List<JCheckBox> checkboxes = new ArrayList<>();

        for (Answers a : answersList) {
            JCheckBox cb = new JCheckBox(a.getAnswer());
            panel.add(cb);
            checkboxes.add(cb);
        }

        ImageIcon icon = new ImageIcon("./data/images.png");

        Object[] dialogContent = { icon, panel };

        int result = JOptionPane.showConfirmDialog(this, dialogContent, 
                "Choose Answers", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            gradeSelections(selectedQuestion, answersList, checkboxes);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles checking if checked box is correct or not and returns 
    // Corresponding message correct or incorrec through comparsion of boolean
    // through is model is correct and that model box is checked given the same index
    private void gradeSelections(Questions q, List<Answers> answersList, List<JCheckBox> boxes) {

        List<String> selected = new ArrayList<>();
        List<String> correctAnswers = new ArrayList<>();

        boolean allCorrect = true;

        for (int i = 0; i < answersList.size(); i++) {
            Answers model = answersList.get(i);
            JCheckBox box = boxes.get(i);

            if (box.isSelected()) {
                selected.add(model.getAnswer());
            }

            if (model.isCorrect()) {
                correctAnswers.add(model.getAnswer());
            }

            if (box.isSelected() != model.isCorrect()) {
                allCorrect = false;
            }
        }

        displayArea.append("Selected: " + selected + "\n");

        if (allCorrect) {
            displayArea.append("Correct \n");
        } else {
            displayArea.append("Incorrect \n");
            displayArea.append("Correct answers: " + correctAnswers + "\n");
        }
    }


    // EFFECTS: runs the entire GUI
    public static void main(String[] args) { 
        new QuizAppGUI();
    }
}