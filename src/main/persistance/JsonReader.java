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
    private String source; 

    // EFFECTS: constructs reader from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads User file and returns it
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as as string and returns it 
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses User from JSON object and adds it to workroom
    private User parseUser(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        User user = new User(name);
        addCourses(user, jsonObject);
        return user;
    }
  
    // MODIFIES: user
    // EFFECTS: parses courses from JSON object and adds them to user
    private void addCourses(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("course");
        for (Object json:  jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(user, nextCourse);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses course from JSON object and adds it to user
    private void addCourse(User user, JSONObject jsonObject) {
        String name = jsonObject.getString("course");
        Courses course = new Courses(name);
        user.addCourses(course);

        addUnits(course, jsonObject);
    }

    // MODIFIES: course
    // EFFECTS: parses units from JSON object and adds them to course
    private void addUnits(Courses course, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("unit");
        for (Object json:  jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addUnit(course, nextCourse);
        }
    }

    // MODIFIES: course
    // EFFECTS: parses unit from JSON object and adds it to course
    private void addUnit(Courses course, JSONObject jsonObject) {
        String name = jsonObject.getString("unit");
        Units unit = new Units(name);
        course.addUnits(unit);

        addQuestions(unit, jsonObject);
    }

    // MODIFIES: unit
    // EFFECTS: parses questions from JSON object and adds them to unit
    private void addQuestions(Units unit, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("question");
        for (Object json:  jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addQuestion(unit, nextCourse);
        }
    }

    // MODIFIES: unit
    // EFFECTS: parses question from JSON object and adds it to unit
    private void addQuestion(Units unit, JSONObject jsonObject) {
        String name = jsonObject.getString("question");
        Questions question = new Questions(name);
        if (jsonObject.has("isWritten") && jsonObject.getBoolean("isWritten")) {
            question.setWritten();
        }

        unit.addQuestions(question);
        addAnswers(question, jsonObject);
    }

    // MODIFIES: question
    // EFFECTS: parses answer from JSON object and adds them to question
    private void addAnswers(Questions question, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("answer");
        for (Object json:  jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addAnswer(question, nextCourse);
        }
    }

    // MODIFIES: question
    // EFFECTS: parses answers from JSON object and adds it to question
    private void addAnswer(Questions question, JSONObject jsonObject) {
        String name = jsonObject.getString("answer");
        Answers answer = new Answers(name);
        if (jsonObject.has("correct") && jsonObject.getBoolean("correct")) {
            answer.setCorrect();
        }
        question.addAnswers(answer);        
    }
}