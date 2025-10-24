package ui;

import java.io.FileNotFoundException;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class Main {
    public static void main(String[] args) {
        try { 
            new StartApp();
        } catch (FileNotFoundException e) {
            System.out.println("unable to run: file not found");
        }
    }   
}
