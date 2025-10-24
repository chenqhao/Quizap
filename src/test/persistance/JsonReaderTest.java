package persistance;

import model.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;


  /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
@ExcludeFromJacocoGeneratedReport
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistantFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReaderEmptyUser() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyUser.json");
        try {
            User user = reader.read();
            assertEquals("name", user.getUser());
            assertEquals(0, user.getCourses().size());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Couldnt read from file");
        }
    }

    @SuppressWarnings("methodlength")
    @Test
    void testReaderGeneralUser() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUser.json");
        try {
            User user = reader.read();
            assertEquals("howie", user.getUser());
            assertEquals(1, user.getCourses().size());
            
            Courses course = user.getCourses().get(0);
            assertEquals("cpsc210", course.getCourse());
            assertEquals(1, course.getUnits().size());

            Units unit = course.getUnits().get(0);
            assertEquals("basics", unit.getUnit());
            assertEquals(3, unit.getQuestions().size());

            Questions question1 = unit.getQuestions().get(0);
            Questions question2 = unit.getQuestions().get(1);
            Questions question3 = unit.getQuestions().get(2);
            assertEquals("oop", question1.getQuestion());
            assertEquals(3, question1.getAnswers().size());
            assertFalse(question1.getWritten());
            
            assertEquals("what", question3.getQuestion());
            assertEquals(1, question3.getAnswers().size());
            assertFalse(question3.getWritten());


            Answers answer1 = question1.getAnswers().get(0);
            Answers answer2 = question2.getAnswers().get(0);
            Answers answer3 = question3.getAnswers().get(0);
            assertEquals("oop", answer1.getAnswer());
            assertTrue(answer1.isCorrect());

            assertEquals("bye", answer2.getAnswer());
            assertTrue(answer2.isCorrect());

            assertEquals("yay", answer3.getAnswer());
            assertFalse(answer3.isCorrect());

        } catch (IOException e) {
            fail("Couldnt read from file");
        }
    }

}
