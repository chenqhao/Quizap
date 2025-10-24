package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestAnswers {
    private Answers testAnswers;

    @BeforeEach
    void runBefore() {
        testAnswers  = new Answers("Answer");
    }

    @Test
    void testConstructor() {
        assertEquals("Answer", testAnswers.getAnswer());
        assertFalse(testAnswers.isCorrect());
    }

    @Test
    void testSetCorrect() {
        testAnswers.setCorrect();
        assertTrue(testAnswers.isCorrect());
    }

    @Test
    void testToString() {
        String string = testAnswers.toString();
        assertTrue(string.contains("Answer"));
    
    }


}
