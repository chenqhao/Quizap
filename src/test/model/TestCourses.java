package model;



import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void TestConstructor(){
        assertEquals("CPSC210", testCourses.getCourse());
        assertEquals(0, testCourses.getUnits().size());
    }

    @Test
    void TestSingleUnit(){
        testCourses.addUnits(testUnits1);
        ArrayList<Units> units = testCourses.getUnits();
        assertEquals(1, units.size());
        assertEquals(testUnits1, units.get(0));
    
    }

    @Test
    void TestMultipleUnit(){
        testCourses.addUnits(testUnits1);
        testCourses.addUnits(testUnits2);
        ArrayList<Units> units = testCourses.getUnits();
        assertEquals(2, units.size());
        assertEquals(testUnits1, units.get(0));
        assertEquals(testUnits2, units.get(1));
    }

    @Test 
    void TestAddDupe(){
        testCourses.addUnits(testUnits1);
        testCourses.addUnits(testUnits1);
        ArrayList<Units> units = testCourses.getUnits();
        assertEquals(1, units.size());
        assertEquals(testUnits1, units.get(0));
    }

    @Test
    void TestToString(){
        String string = testCourses.toString();
        assertTrue(string.contains("CPSC210"));
    }
}   
