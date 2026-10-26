# My Personal Project
<strong>Quizap</strong>
## What will it do?
I want to build a multiple choice quiz app that will help with studying. Upon starting the app the user can manually type in each <strong><em>course code </strong></em>(eg. CPSC210, MATH200, etc) with <strong><em>subsections for each unit </strong></em>. The user can then <strong><em>make questions</strong></em> and can <strong><em>add multiple answers </strong></em>  or <strong><em> one</strong></em>  to these questions(eg. 2,3,4) to put into each subsection, selecting one or more that is correct with multiple correct answers giving you are <strong> percentage score</strong>. Additionally, they can also input a <strong><em> text</strong></em> like webwork. If they get a question correct, the question will be marked as <strong> complete</strong> so they can track what they have already done. additionally I would like to add a bookmark function to <strong><em> bookmark a question</strong></em>  the user would like to come back to. Furthermore, I would also like to implement a section where the user could <strong><em> provide descriptions/explanations</strong></em>  on why the answer is correct or wrong.


## Who will use it? 
My goal is to cater towards people who would like to practice courses that require memorization as well as just to have a site to practice questions to easily with multiple ways of implementation.

## Why is this project of interest to you?
During my first year of university, I had a course where it was all just memorization. At the time, I only had my notes and that is what I used to practice with. If I had a simliar app to this, I feel like it would have been a more efficient way to study rather than flipping throw my notes over and over again. I hope to build this app to resolve that problem and have a more efficient way of studying memorization as well as most courses in general. 

## User Story

- As a User, I want to add courses
- As a User, I want to add sections to the courses
- As a User, I want to add questions to the sections 
- As a User, I want to add answers(multiple choice, single answer, written) to the questions
- As a User, I want to add descriptions/explanation to each answer
- As a User, I want to be able to bookmark a question 
- As a User, I want to be able to see which questions I have completed 
- As a User, I want to be able to see my courses 
- As a User, I want to be able to see my units
- As a User, I want to be able to see my questions
- As a User, I want to be able to see my answers 
- As a User, I want to be able to save my courses and its contents 
- As a User, I want to be able to load my courses and its contents


## Instructions for End User

- click add user and insert user name
- you can add courses by clicking on the "Add Course" Button
- you can add a Unit to a select course, by click on a the course dropdown and clicking the desired Course and then clicking "Add Unit"
- you can add a Question to a select Unit, by click on a the Unit dropdown and clicking the desired Question and then clicking "Add Question"
- you can add a Answer to a select Question, by click on a the Question dropdown and clicking the desired Question and then clicking "Add Answer"
- you can save your data by clicking on the "save" button on the bottom 
- you can load your data by clicking on the "load" button on the bottom
- you can start the quiz by clicking on the "start quiz" button on the right


## Phase 4: Task 2

```
WindowListener method called: windowActivated.

WindowListener method called: windowOpened.

WindowListener method called: windowDeactivated.

WindowListener method called: windowActivated.

Thu Nov 27 05:54:32 PST 2025
added CPSC210 to Howie

Thu Nov 27 05:54:32 PST 2025
added Design 6 to CPSC210

Thu Nov 27 05:54:32 PST 2025
added What is a singleton  to Design 6

Thu Nov 27 05:54:32 PST 2025
set a singleton as correct

Thu Nov 27 05:54:32 PST 2025
added a singleton to What is a singleton 
```

## Phase 4: Task 3

- One major refactoring i would do in my project given more time would be to make most of the main model classes (Courses, Units, etc) 
extend a abstraction. These classes are store a collection of child classes that all have similar methods. implementing a abstraction class 
can support all the main methods that all these classes use such as adding, getting without the need to duplicate these methods for each individual class. 

- Additionally, I think my GUI code could have benefitted by making multiple classes so it does not violate the Single Responsibility Principle. Currently, the GUI handles everything, however, i think i could have made different classes such as a class  handling all the data logic such as adding all the data, a controller class, a class to handle persistence and left the current class to construct the main visuals for the GUI. 
