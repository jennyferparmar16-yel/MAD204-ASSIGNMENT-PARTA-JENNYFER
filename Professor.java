/**
 Professor.java

 Description:
 - Represents a professor with a department, title, and number of
 - courses taught. Extends Person and implements Evaluable.
 - Performance is evaluated based on the number of courses taught.

    Jennyfer Parmar
 * Date: 06/10/2025
 */
public class Professor extends Person implements Evaluable {
    String department;
    String title;
    int coursesTaught; // used to evaluate performance

    public Professor(int id, String name, int age, String department, String title, int coursesTaught) {
        super(id, name, age);
        this.department = department;
        this.title = title;
        this.coursesTaught = coursesTaught;
    }

    @Override
    public String introduce() {
        return String.format("Hello, I'm %s, %s in %s department. I teach %d courses.", name, title, department, coursesTaught);
    }

    @Override
    public String toString() {
        return String.format("%s | Type: Professor | Dept: %s | Title: %s | CoursesTaught: %d", super.toString(), department, title, coursesTaught);
    }

    @Override
    public String evaluatePerformance() {
        String perf;
        if (coursesTaught >= 4) perf = "Excellent";
        else if (coursesTaught >= 2) perf = "Good";
        else if (coursesTaught == 1) perf = "Satisfactory";
        else perf = "Needs Improvement";
        return String.format("Professor %s -> Courses Taught: %d -> Performance: %s", name, coursesTaught, perf);
    }
}
