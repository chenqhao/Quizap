package ui.gui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

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
    private Courses selectedQuestion;
    private Questions questions;
    private Answers answers;
    
    private JButton userButton;
    private JButton courseButton;
    private JButton unitsButton;
    private JButton questionsButton;
    private JButton answerButton;

    private JButton startQuiz;

    private JButton saveButton;
    private JButton loadButton;

    private JsonWriter jsonWriter; 
    private JsonReader jsonReader;

    private JComboBox<String> courseDropdown;
    private JComboBox<String> unitDropdown;
    private JComboBox<String> questionDropdown;
    private JComboBox<String> answerDropdown;

    private JTextArea displayArea;


    @SuppressWarnings("methodlength")
    public QuizAppGUI() { 

        // adding frame
        super("Quizzard app");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,500);
        setLocationRelativeTo(null);

        courseDropdown = new JComboBox<>();
        unitDropdown = new JComboBox<>();
        questionDropdown = new JComboBox<>();
        answerDropdown = new JComboBox<>();


        // adding save functions
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        // adding buttons
        userButton = new JButton("Add User");
        courseButton = new JButton("Add Course");
        unitsButton = new JButton("Add Unit");
        questionsButton = new JButton("Add Question");
        answerButton = new JButton("Add Answer");

        startQuiz = new JButton("Start Quiz");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        startQuiz = new JButton("Start Quiz");

        // panel stuff
        JPanel buttonPanel = new JPanel();
        JPanel dataPanel = new JPanel();
        JPanel quizPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(1, 5, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        courseButton.setEnabled(false);
        unitsButton.setEnabled(false);
        questionsButton.setEnabled(false);
        answerButton.setEnabled(false);
        courseDropdown.setEnabled(false);
        unitDropdown.setEnabled(false);
        questionDropdown.setEnabled(false);
        answerDropdown.setEnabled(false);

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

    
        setVisible(true);

    }  

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

    public void addCourse() {
        String courseName = JOptionPane.showInputDialog(this, "Enter course name:");
        if (courseName != null && !courseName.trim().isEmpty()) {  
            selectedQuestion = new Courses(courseName.trim());
            user.getCourses().add(selectedQuestion);
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

    private Courses getSelectedCourse() {
        int index = courseDropdown.getSelectedIndex();
        if (index >= 0 && index < user.getCourses().size()) {
            return user.getCourses().get(index);
        }
        return null;
    }

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


    public void addAnswerToQuestion() {
        Questions selectedQuestions = getSelectedQuestion();
        if (selectedQuestions == null) {
            displayArea.append("No question selected.\n");
            return;
        }

        String answer = JOptionPane.showInputDialog(this, "Enter answer for " + selectedQuestions.getQuestion() + ":");
        if (answer != null && !answer.trim().isEmpty()) {
            answers = new Answers(answer);
            selectedQuestions.addAnswers(answers);
            answerDropdown.addItem(answer.trim());
            displayArea.append("Answer '" + answer + "' added to " + selectedQuestions.getQuestion() + ".\n");
            updateAnswersDropdown();
            answerDropdown.setEnabled(true);
        } else  {
            displayArea.append("No Answer has been added" + "\n");
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "No answer input added");
        }
    }


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

    private void loadData() {
        try {
            user = jsonReader.read();
            for (Courses c : user.getCourses()) {
                courseDropdown.addItem(c.getCourse());
            }
            enableAll(true);
            displayArea.append("Loaded " + user.getUser() + " from: " + JSON_STORE + "\n");
        } catch (IOException e) {
            displayArea.append("Unable to read from file: " + JSON_STORE + "\n");
        }
    }

    private void enableAll(boolean enable) {
        courseButton.setEnabled(enable);
        unitsButton.setEnabled(enable);
        questionsButton.setEnabled(enable);
        answerButton.setEnabled(enable);
        saveButton.setEnabled(enable);
        loadButton.setEnabled(enable);
        courseDropdown.setEnabled(enable);
    }

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
        informationPanel.add(answerDropdown);
        quizWindow.setVisible(true);
    }   


    private void selectingQuestion() { 
        // will add a multiple choice like question popup using JOptionPane
    }



    public static void main(String[] args) { 
        new QuizAppGUI();
    }
}