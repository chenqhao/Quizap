package persistance;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

  /* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
// Represents a reader that reads User from JSON data stored in file
public class JsonReader {

    // EFFECTS: constructs reader from source file
    public JsonReader(String source) {

    }

    // EFFECTS: reads User file and returns it
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as as string and returns it 
    private String readFile(String source) throws IOException {
        return null;
    }

    // EFFECTS: parses User from JSON object and adds it to workroom
    private User parseUser(JSONObject jsonObject) {
        return null;
    }
  
    // MODIFIES: user
    // EFFECTS: parses courses from JSON object and adds them to user
    private void addCourses(User user, JSONObject jsonObject) {

    }

    // MODIFIES: user
    // EFFECTS: parses course from JSON object and adds it to user
    private void addCourse(User user, JSONObject jsonObject) {

    }

    // MODIFIES: course
    // EFFECTS: parses units from JSON object and adds them to course
    private void addUnits(Courses course, JSONObject jsonObject) {

    }

    // MODIFIES: course
    // EFFECTS: parses unit from JSON object and adds it to course
    private void addUnit(Courses course, JSONObject jsonObject) {

    }

    // MODIFIES: unit
    // EFFECTS: parses questions from JSON object and adds them to unit
    private void addQuestions(Units unit, JSONObject jsonObject) {

    }

    // MODIFIES: unit
    // EFFECTS: parses question from JSON object and adds it to unit
    private void addQuestion(Units unit, JSONObject jsonObject) {

    }

    // MODIFIES: question
    // EFFECTS: parses answer from JSON object and adds them to question
    private void addAnswers(Questions question, JSONObject jsonObject) {

    }

    // MODIFIES: question
    // EFFECTS: parses answers from JSON object and adds it to question
    private void addAnswer(Questions question, JSONObject jsonObject) {

    }
}