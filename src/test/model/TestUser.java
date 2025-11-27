package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestUser {
    private User testUser;
    private Courses course1;
    private Courses course2;

    @BeforeEach
    void runBefore() {
        testUser = new User("Howie");
        course1 = new Courses("CPSC210");
        course2 = new Courses("MATH200");
    }

    @Test
    void testConstructor() {
        assertEquals("Howie", testUser.getUser());
        assertEquals(0, testUser.getCourses().size());
    }

    @Test
    void testAddSingleCourse() {
        testUser.addCourses(course1);
        ArrayList<Courses> courses = testUser.getCourses();
        assertEquals(1,courses.size());
        assertEquals(course1, courses.get(0));
    }

    @Test
    void testAddMultipleCourse() {
        testUser.addCourses(course1);
        testUser.addCourses(course2);
        ArrayList<Courses> courses = testUser.getCourses();
        assertEquals(2,courses.size());
        assertEquals(course1, courses.get(0));
        assertEquals(course2, courses.get(1));
    }

    @Test
    void testAddDupe() {
        testUser.addCourses(course1);
        testUser.addCourses(course1);
        ArrayList<Courses> courses = testUser.getCourses();
        assertEquals(1, courses.size());
    }

    @Test
    void testChangeName() {
        testUser.changeName("Jaden");
        assertTrue(testUser.getUser().equals("Jaden"));
    }

    @Test
    void testChangeNameNull() {
        testUser.changeName(null);
        assertTrue(testUser.getUser().equals("Howie"));
    }

}
