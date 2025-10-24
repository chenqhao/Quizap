package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestCourses {
    private Courses testCourses;
    private Units testUnits1;
    private Units testUnits2;

    @BeforeEach
    void runBefore() {
        testCourses = new Courses("CPSC210");
        testUnits1 = new Units("Basics");
        testUnits2 = new Units("Abstractions");

    }

    @Test
    void testConstructor() {
        assertEquals("CPSC210", testCourses.getCourse());
        assertEquals(0, testCourses.getUnits().size());
    }

    @Test
    void testSingleUnit() {
        testCourses.addUnits(testUnits1);
        ArrayList<Units> units = testCourses.getUnits();
        assertEquals(1, units.size());
        assertEquals(testUnits1, units.get(0));
    
    }

    @Test
    void testMultipleUnit() {
        testCourses.addUnits(testUnits1);
        testCourses.addUnits(testUnits2);
        ArrayList<Units> units = testCourses.getUnits();
        assertEquals(2, units.size());
        assertEquals(testUnits1, units.get(0));
        assertEquals(testUnits2, units.get(1));
    }

    @Test 
    void testAddDupe() {
        testCourses.addUnits(testUnits1);
        testCourses.addUnits(testUnits1);
        ArrayList<Units> units = testCourses.getUnits();
        assertEquals(1, units.size());
        assertEquals(testUnits1, units.get(0));
    }
    
    @Test
    void testToString() {
        String string = testCourses.toString();
        assertTrue(string.contains("CPSC210"));
    }
}   
