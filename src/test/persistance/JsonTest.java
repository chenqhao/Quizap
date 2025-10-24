package persistance;

import model.*;

import static org.junit.jupiter.api.Assertions.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Courses;


/* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
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
