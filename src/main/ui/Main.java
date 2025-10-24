package ui;

import java.io.FileNotFoundException;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;


/* Description: JsonSerializationDemo
     * Author: Paul Carter
     * Date: Oct 23, 2025
     * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
@ExcludeFromJacocoGeneratedReport
public class Main {
    public static void main(String[] args) {
        try { 
            System.out.println("welcome to the quiz app!");
            new StartApp();
        } catch (FileNotFoundException e) {
            System.out.println("unable to run: file not found");
        }
    }   
}
