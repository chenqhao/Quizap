package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAnswers {
    private Answers testAnswers;

    @BeforeEach
    void runBefore(){
        testAnswers  = new Answers("Answer");
    }

    @Test
    void TestConstructor(){
        assertEquals("Answer", testAnswers.getAnswer());
        assertFalse(testAnswers.isCorrect());
    }

    @Test
    void TestSetCorrect(){
        testAnswers.setCorrect();
        assertTrue(testAnswers.isCorrect());
    }
    @Test
    void TestToString(){
        String string = testAnswers.toString();
        assertTrue(string.contains("Answer"));
    
    }
}
