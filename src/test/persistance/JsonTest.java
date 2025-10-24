package persistance;

import model.*;

import static org.junit.jupiter.api.Assertions.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Courses;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkThingy(String name, User user, Courses course, Units unit, Questions question, Answers answer) {
        assertEquals(name, user.getUser());
        assertEquals(course, user.getCourses());
        assertEquals(unit, course.getUnits());
        assertEquals(question, unit.getQuestions());
        assertEquals(answer, question.getAnswers());
    }
}
