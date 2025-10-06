/**
 TeachingAssistant.java

 Description:
 - Represents a Teaching Assistant (TA) who is also a student.
 - Adds an assignedCourse field and evaluates performance based on
 - GPA and assigned duties. Extends Student and implements Evaluable.

 - Demonstrates multilevel inheritance.
  Jennyfer Parmar
 06/10/2025
 */


public class TeachingAssistant extends Student implements Evaluable {
    String assignedCourse;

    public TeachingAssistant(int id, String name, int age, String program, int year, double gpa, String assignedCourse) {
        super(id, name, age, program, year, gpa);
        this.assignedCourse = assignedCourse;
    }

    public TeachingAssistant(int id, String name, int age, String program, String assignedCourse) {
        super(id, name, age, program);
        this.assignedCourse = assignedCourse;
    }

    @Override
    public String introduce() {
        return String.format("Hi, I'm %s (TA). Program: %s, Year: %d, GPA: %.2f. Assigned to: %s",
                getName(), getProgram(), getYear(), getGpa(), assignedCourse);
    }

    @Override
    public String toString() {
        return String.format("%s | Type: TA | AssignedCourse: %s", super.toString(), assignedCourse);
    }

    @Override
    public String evaluatePerformance() {
        // Simple combination of GPA and TA duties (assigned course existence)
        String duty = (assignedCourse != null && !assignedCourse.isEmpty()) ? "Active TA duties" : "No duties assigned";
        double score = getGpa();
        String grade;
        if (score >= 3.5) grade = "High";
        else if (score >= 2.5) grade = "Medium";
        else grade = "Low";
        return String.format("TA %s -> GPA: %.2f -> Duty: %s -> Performance Level: %s", getName(), getGpa(), duty, grade);
    }
}