package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestQuestions {
    private Questions testQuestion;
    private Answers Answer1;
    private Answers Answer2;

    @BeforeEach
    void runBefore(){
        testQuestion = new Questions("what does OOP mean");
        Answer1 = new Answers("Object Oriented Programming");
        Answer2 = new Answers("Only On Paper");
    }

    @Test
    void TestConstructos(){
        assertEquals("what does OOP mean", testQuestion.getQuestion());
        assertEquals(0, testQuestion.getAnswers().size());
        assertFalse(testQuestion.isCompletion());
    }

    @Test 
    void testAddSingleAnswer(){
        testQuestion.addAnswers(Answer1);
        ArrayList<Answers> answers = testQuestion.getAnswers();
        assertEquals(1, answers.size());
        assertEquals(Answer1, answers.get(0));
    }

    @Test
    void testAddMultipleAnswers(){
        testQuestion.addAnswers(Answer1);
        testQuestion.addAnswers(Answer2);
        ArrayList<Answers> answers = testQuestion.getAnswers();
        assertEquals(2, answers.size());
        assertEquals(Answer1, answers.get(0));
        assertEquals(Answer2, answers.get(1));
    }
    

    @Test
    void testAddDupe(){
        testQuestion.addAnswers(Answer1);
        testQuestion.addAnswers(Answer1);
        ArrayList<Answers> answers = testQuestion.getAnswers();
        assertEquals(1, answers.size());
    }

    @Test
    void TestCheckCompletion(){
        Answer1.setCorrect();
        testQuestion.Markcompleted(Answer1);
        assertEquals(true, testQuestion.isCompletion());
    }

    @Test
    void TestToString(){
        String string = testQuestion.toString();
        assertTrue(string.contains("what does OOP mean"));
    }
}
