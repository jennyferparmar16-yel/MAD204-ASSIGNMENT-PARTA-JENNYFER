People Management System (Java OOP Console Application)
Name: Jennyfer Parmar
Student ID: A000201240
Course: MAD204 – Java Development for MAD
Submission Date: 06/10/2025

Project Overview

This project implements a console-based People Management System demonstrating advanced Object-Oriented Programming (OOP) principles in Java.
It allows managing Students, Professors, and Teaching Assistants using inheritance, polymorphism, interfaces, exception handling, recursion, and file I/O.

Class Design
1. Abstract Class – Person
Fields: id, name, age
Methods:
abstract void introduce()
void celebrateBirthday() → increases age by 1
toString() overridden for formatted output

2. Subclasses
Student:
Fields: program, year, gpa
Overloaded constructors
Overrides introduce()
Implements evaluatePerformance() → returns grade based on GPA

Professor:
Fields: department, title
Overrides introduce()
Implements evaluatePerformance() → based on number of courses taught
TeachingAssistant (extends Student):
Field: assignedCourse
Overrides introduce()
Implements evaluatePerformance() → based on GPA and TA duties

3. Interface – Evaluable
   Defines one method:
   String evaluatePerformance();
   
Data Management:
All Person objects are stored in an ArrayList<Person> inside the PeopleManagementSystem class, which manages:
Adding, listing, searching (by ID or name), and removing people.
Persistent storage through a text file (people.txt) for saving and loading data.

Recursion Features:
factorial(int a) → Demonstrates recursion for factorial computation.
countdown(int n) → Demonstrates recursive countdown (menu option).

Exception Handling & Validation:
All user input is validated using try-catch blocks.
Ensures:
age > 0
0.0 ≤ GPA ≤ 4.0
Handles invalid menu selections gracefully.

File I/O
Saves all person details to people.txt when the program exits.
Loads data from people.txt on startup.

Defines one method:

String evaluatePerformance();
