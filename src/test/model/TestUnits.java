package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestUnits {
    private Units testUnits;
    private Questions question1;
    private Questions question2;

    @BeforeEach
    void runBefore(){
        testUnits = new Units("Basics");
        question1 = new Questions("what is this");
        question2 = new Questions("what is that");
    }

    @Test
    void TestConstructor(){
        assertEquals("Basics",testUnits.getUnit());
        assertEquals(0,testUnits.getQuestions().size());
        assertFalse(testUnits.isCompletion());
    }

    @Test
    void TestAddOneQuestion(){
        testUnits.addQuestions(question1);
        ArrayList<Questions> questions = testUnits.getQuestions();
        assertEquals(1, questions.size());
        assertEquals(question1, questions.get(0));
    }

    @Test
    void TestAddMultipleQuestion(){
        testUnits.addQuestions(question1);
        testUnits.addQuestions(question2);
        ArrayList<Questions> questions = testUnits.getQuestions();
        assertEquals(2, questions.size());
        assertEquals(question1, questions.get(0));
        assertEquals(question2, questions.get(1));
    }
    @Test
    void TestAddDupe(){
        testUnits.addQuestions(question1);
        testUnits.addQuestions(question1);
        ArrayList<Questions> questions = testUnits.getQuestions();
        assertEquals(1, questions.size());
    }

    @Test
    void TestCheckCompletion(){
        testUnits.Markcompleted();
        assertEquals(true, testUnits.isCompletion());
    }

    @Test
    void TestToString(){
        testUnits.addQuestions(question1);
        String string = testUnits.toString();
        assertTrue(string.contains("Basics"));
    }
}
