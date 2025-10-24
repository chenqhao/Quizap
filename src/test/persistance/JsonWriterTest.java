package persistance;

import model.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


/* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
@ExcludeFromJacocoGeneratedReport
class JsonWriterTest extends JsonTest {
    
    @Test
    void testUserInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUser() {
        try {
            User user = new User("name");
            writerFunction("./data/testWriterEmptyUser.json", user);

            JsonReader reader = new JsonReader("./data/testWriterEmptyUser.json");
            user = reader.read();
            assertEquals("name", user.getUser());
            assertEquals(0, user.getCourses().size());
        } catch (IOException e) {
            fail("Exception should have been thrown");
            e.printStackTrace();
        }

    }

    @Test
    void testWriterGeneralUser() {
        try {
            User user = new User("Howie");
            user.addCourses(new Courses("cpsc210"));
            Courses course = user.getCourses().get(0);
            course.addUnits(new Units("basics"));
            Units unit = course.getUnits().get(0);
            unit.addQuestions(new Questions("oop"));
            Questions question = unit.getQuestions().get(0);
            question.addAnswers(new Answers("object oriented programming"));


            writerFunction("./data/testWriterGeneralUser.json", user);

            JsonReader reader = new JsonReader("./data/testWriterGeneralUser.json");
            user = reader.read();
            assertEquals("Howie", user.getUser());
            List<Courses> courses = user.getCourses();
            assertEquals(1, courses.size());
            assertEquals("cpsc210", courses.get(0).getCourse());
            assertEquals("basics", course.getUnits().get(0).getUnit());
            assertEquals("oop", unit.getQuestions().get(0).getQuestion());
            assertEquals("object oriented programming", question.getAnswers().get(0).getAnswer());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
            e.printStackTrace();
        }
    }


    private void writerFunction(String source, User user) {
        JsonWriter writer = new JsonWriter(source);
        try {
            writer.open();
            writer.write(user);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
