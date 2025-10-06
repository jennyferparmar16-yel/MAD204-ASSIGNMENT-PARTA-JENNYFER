/**
 Student.java
 Description:
 - Represents a student enrolled in a specific program and academic year.
 - Includes GPA for performance evaluation. Extends Person and
 - implements Evaluable.
 - Demonstrates inheritance and polymorphism.
    Jennyfer Parmar
   Date: 06/10/2025
 **/
public class Student extends Person implements Evaluable {
    private String program;
    private int year;
    private double gpa;

    /** Constructor with all fields. */
    public Student(int id, String name, int age, String program, int year, double gpa) {
        super(id, name, age);
        this.program = program;
        this.year = year;
        this.gpa = gpa;
    }

    /** Overloaded constructor with default year=1 and gpa=0.0 */
    public Student(int id, String name, int age, String program) {
        this(id, name, age, program, 1, 0.0);
    }

    @Override
    public String introduce() {
        return String.format("Hi, I'm %s (Student). Program: %s, Year: %d, GPA: %.2f", name, program, year, gpa);
    }

    @Override
    public String toString() {
        return String.format("%s | Type: Student | Program: %s | Year: %d | GPA: %.2f", super.toString(), program, year, gpa);
    }

    public double getGpa() {
        return gpa;
    }

    public String getProgram() {
        return program;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String evaluatePerformance() {
        // Convert GPA to letter grade
        char letter;
        if (gpa >= 3.7) letter = 'A';
        else if (gpa >= 3.0) letter = 'B';
        else if (gpa >= 2.0) letter = 'C';
        else if (gpa >= 1.0) letter = 'D';
        else letter = 'F';
        return String.format("Student %s -> GPA: %.2f -> Letter Grade: %c", name, gpa, letter);
    }
}
