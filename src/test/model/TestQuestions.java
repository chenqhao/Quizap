package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import org.junit.jupiter.api.BeforeEach;

@ExcludeFromJacocoGeneratedReport
public class TestQuestions {
    private Questions testQuestion;
    private Answers answer1;
    private Answers answer2;

    @BeforeEach
    void runBefore() {
        testQuestion = new Questions("what does OOP mean");
        answer1 = new Answers("Object Oriented Programming");
        answer2 = new Answers("Only On Paper");
    }

    @Test
    void testConstructos() {
        assertEquals("what does OOP mean", testQuestion.getQuestion());
        assertEquals(0, testQuestion.getAnswers().size());
        assertFalse(testQuestion.isCompletion());
    }

    @Test 
    void testAddSingleAnswer() {
        testQuestion.addAnswers(answer1);
        ArrayList<Answers> answers = testQuestion.getAnswers();
        assertEquals(1, answers.size());
        assertEquals(answer1, answers.get(0));
    }

    @Test
    void testAddMultipleAnswers() {
        testQuestion.addAnswers(answer1);
        testQuestion.addAnswers(answer2);
        ArrayList<Answers> answers = testQuestion.getAnswers();
        assertEquals(2, answers.size());
        assertEquals(answer1, answers.get(0));
        assertEquals(answer2, answers.get(1));
    }
    
    @Test
    void testAddDupe() {
        testQuestion.addAnswers(answer1);
        testQuestion.addAnswers(answer1);
        ArrayList<Answers> answers = testQuestion.getAnswers();
        assertEquals(1, answers.size());
    }

    @Test 
    void testCheckCompletion() {
        answer1.setCorrect();
        testQuestion.markcompleted(answer1);
        assertEquals(true, testQuestion.isCompletion());
    }

    @Test
    void testCheckNotCompletion() {
        testQuestion.markcompleted(answer1);
        assertFalse(testQuestion.isCompletion());
    }

    @Test
    void testToString() {
        String string = testQuestion.toString();
        assertTrue(string.contains("what does OOP mean"));
    }

    @Test
    void testIfWritten() {
        testQuestion.setWritten();
        assertTrue(testQuestion.getWritten());

    }
}
