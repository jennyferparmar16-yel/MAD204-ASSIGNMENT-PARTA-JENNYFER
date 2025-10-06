/**
 MAD204-01 Java Development for MA
 Assignment 1 - Part A (People Management System)
 Jennyfer Parmar - A00201240
 Date of Submission: 06/10/2025
 Description: Main class for managing a collection of Person objects including
 **/
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PeopleManagementSystem {
    private static final String DATA_FILE = "people.txt";
    private ArrayList<Person> people = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PeopleManagementSystem app = new PeopleManagementSystem();
        app.run();
    }

    /** Program run loop. */
    public void run() {
        loadFromFile(); // Load saved people if file exists
        // Demonstrate factorial recursion once as required
        System.out.println("Demo factorial(5) = " + factorial(5));
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter choice: ");
            String choiceStr = scanner.nextLine();
            try {
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1: addPerson();
                    break;
                    case 2: listPeople();
                    break;
                    case 3: searchByIdMenu();
                    break;
                    case 4: searchByNameMenu();
                    break;
                    case 5: removeByIdMenu();
                    break;
                    case 6: celebrateBirthdayMenu();
                    break;
                    case 7: showPerformanceMenu();
                    break;
                    case 8: runCountdownMenu();
                    break;
                    case 9: saveToFile();
                    running = false;
                    System.out.println("Saved. Exiting.");
                    break;
                    default:
                        System.out.println("Invalid choice. Please choose 1-9.");
                }
            }
            catch (NumberFormatException ex)
            {
                System.out.println("Please enter a valid number for menu choice.");
            }
            catch (Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== People Management System ===");
        System.out.println("1. Add Person");
        System.out.println("2. List People");
        System.out.println("3. Search Person by ID");
        System.out.println("4. Search Person by Name");
        System.out.println("5. Remove Person");
        System.out.println("6. Celebrate Birthday");
        System.out.println("7. Show Performance Evaluation");
        System.out.println("8. Run Countdown (Recursion Demo)");
        System.out.println("9. Save & Exit");
    }

    /** Add student, professor, or TA */
    private void addPerson() {
        System.out.println("Add Type: 1) Student  2) Professor  3) TeachingAssistant");
        System.out.print("Enter type: ");
        String t = scanner.nextLine();
        try {
            int type = Integer.parseInt(t);
            System.out.print("Enter ID (integer): ");
            int id = Integer.parseInt(scanner.nextLine());
            if (findById(id) != null) {
                System.out.println("ID already exists. Aborting add.");
                return;
            }
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine());
            if (age <= 0) {
                System.out.println("Age must be > 0. Aborting.");
                return;
            }

            switch (type) {
                case 1: // Student
                    System.out.print("Enter program: ");
                    String program = scanner.nextLine();
                    System.out.print("Enter year (int): ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter GPA (0.0 - 4.0): ");
                    double gpa = Double.parseDouble(scanner.nextLine());
                    if (gpa < 0.0 || gpa > 4.0) {
                        System.out.println("Invalid GPA range. Aborting.");
                        return;
                    }
                    people.add(new Student(id, name, age, program, year, gpa));
                    System.out.println("Student added.");
                    break;
                case 2: // Professor
                    System.out.print("Enter department: ");
                    String dept = scanner.nextLine();
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter number of courses taught (int): ");
                    int courses = Integer.parseInt(scanner.nextLine());
                    people.add(new Professor(id, name, age, dept, title, courses));
                    System.out.println("Professor added.");
                    break;
                case 3: // TA
                    System.out.print("Enter program: ");
                    String prog = scanner.nextLine();
                    System.out.print("Enter year (int): ");
                    int y = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter GPA (0.0 - 4.0): ");
                    double g = Double.parseDouble(scanner.nextLine());
                    if (g < 0.0 || g > 4.0) {
                        System.out.println("Invalid GPA range. Aborting.");
                        return;
                    }
                    System.out.print("Enter assigned course (can be empty): ");
                    String assigned = scanner.nextLine();
                    people.add(new TeachingAssistant(id, name, age, prog, y, g, assigned));
                    System.out.println("Teaching Assistant added.");
                    break;
                default:
                    System.out.println("Unknown type. Aborting.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Input format error: " + nfe.getMessage());
        }
    }

    private void listPeople() {
        if (people.isEmpty()) {
            System.out.println("No people to display.");
            return;
        }
        System.out.println("Listing all people:");
        for (Person p : people) {
            System.out.println(p.toString());
            System.out.println("Introduce: " + p.introduce());
            System.out.println("-----");
        }
    }

    /** Overloaded search by int id */
    public Person search(int id) {
        return findById(id);
    }

    /** Overloaded search by name */
    public ArrayList<Person> search(String name) {
        ArrayList<Person> found = new ArrayList<>();
        for (Person p : people) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                found.add(p);
            }
        }
        return found;
    }

    private void searchByIdMenu() {
        System.out.print("Enter ID to search: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Person p = search(id);
            if (p == null) System.out.println("No person with ID " + id);
            else {
                System.out.println(p.toString());
                System.out.println("Introduce: " + p.introduce());
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid ID format.");
        }
    }

    private void searchByNameMenu() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        ArrayList<Person> results = search(name);
        if (results.isEmpty()) System.out.println("No persons found with name containing: " + name);
        else {
            for (Person p : results) {
                System.out.println(p.toString());
            }
        }
    }

    private void removeByIdMenu() {
        System.out.print("Enter ID to remove: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Person p = findById(id);
            if (p == null) {
                System.out.println("No person with ID " + id);
            } else {
                people.remove(p);
                System.out.println("Removed person with ID " + id);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid ID format.");
        }
    }

    private void celebrateBirthdayMenu() {
        System.out.print("Enter ID to celebrate birthday: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Person p = findById(id);
            if (p == null) System.out.println("No person with ID " + id);
            else {
                p.celebrateBirthday();
                System.out.println("Birthday celebrated. New age: " + p.getAge());
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid ID format.");
        }
    }

    private void showPerformanceMenu() {
        System.out.print("Enter ID to evaluate (or press Enter to evaluate all): ");
        String line = scanner.nextLine();
        if (line.trim().isEmpty()) {
            for (Person p : people) {
                if (p instanceof Evaluable) {
                    System.out.println(((Evaluable) p).evaluatePerformance());
                } else {
                    System.out.println(p.getName() + " -> Not evaluable.");
                }
            }
        } else {
            try {
                int id = Integer.parseInt(line);
                Person p = findById(id);
                if (p == null) System.out.println("No person with ID " + id);
                else if (p instanceof Evaluable) System.out.println(((Evaluable) p).evaluatePerformance());
                else System.out.println("Person not evaluable.");
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid ID.");
            }
        }
    }

    private void runCountdownMenu() {
        System.out.print("Enter starting number for countdown (n >= 0): ");
        try {
            int n = Integer.parseInt(scanner.nextLine());
            if (n < 0) {
                System.out.println("n must be >= 0.");
                return;
            }
            System.out.println("Countdown:");
            countdown(n);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid number.");
        }
    }

    /** Recursive factorial */
    public int factorial(int a) {
        if (a <= 1) return 1;
        return a * factorial(a - 1);
    }

    /** Recursive countdown that prints numbers to console */
    public void countdown(int n) {
        System.out.println(n);
        if (n <= 0) return;
        countdown(n - 1);
    }

    /** Helper to find by id */
    private Person findById(int id) {
        for (Person p : people) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    /** Save all people to a simple text file */
    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Person p : people) {
                // Format: Type|id|name|age|extra...
                if (p instanceof TeachingAssistant) {
                    TeachingAssistant ta = (TeachingAssistant) p;
                    pw.printf("TA|%d|%s|%d|%s|%d|%.2f|%s%n",
                            ta.getId(), escape(ta.getName()), ta.getAge(),
                            escape(ta.getProgram()), ta.getYear(), ta.getGpa(), escape(ta.assignedCourse));
                } else if (p instanceof Student) {
                    Student s = (Student) p;
                    pw.printf("STUDENT|%d|%s|%d|%s|%d|%.2f%n",
                            s.getId(), escape(s.getName()), s.getAge(),
                            escape(s.getProgram()), s.getYear(), s.getGpa());
                } else if (p instanceof Professor) {
                    Professor prof = (Professor) p;
                    pw.printf("PROF|%d|%s|%d|%s|%s|%d%n",
                            prof.getId(), escape(prof.getName()), prof.getAge(),
                            escape(prof.department), escape(prof.title), prof.coursesTaught);
                } else {
                    // generic fallback
                    pw.printf("PERSON|%d|%s|%d%n", p.getId(), escape(p.getName()), p.getAge());
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error saving to file: " + ioe.getMessage());
        }
    }

    /** Load people from file if exists */
    private void loadFromFile() {
        File f = new File(DATA_FILE);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = splitLine(line);
                if (parts.length == 0) continue;
                String type = parts[0];
                if ("TA".equals(type) && parts.length >= 8) {
                    int id = Integer.parseInt(parts[1]);
                    String name = unescape(parts[2]);
                    int age = Integer.parseInt(parts[3]);
                    String program = unescape(parts[4]);
                    int year = Integer.parseInt(parts[5]);
                    double gpa = Double.parseDouble(parts[6]);
                    String assigned = unescape(parts[7]);
                    people.add(new TeachingAssistant(id, name, age, program, year, gpa, assigned));
                } else if ("STUDENT".equals(type) && parts.length >= 7) {
                    int id = Integer.parseInt(parts[1]);
                    String name = unescape(parts[2]);
                    int age = Integer.parseInt(parts[3]);
                    String program = unescape(parts[4]);
                    int year = Integer.parseInt(parts[5]);
                    double gpa = Double.parseDouble(parts[6]);
                    people.add(new Student(id, name, age, program, year, gpa));
                } else if ("PROF".equals(type) && parts.length >= 7) {
                    int id = Integer.parseInt(parts[1]);
                    String name = unescape(parts[2]);
                    int age = Integer.parseInt(parts[3]);
                    String dept = unescape(parts[4]);
                    String title = unescape(parts[5]);
                    int courses = Integer.parseInt(parts[6]);
                    people.add(new Professor(id, name, age, dept, title, courses));
                } else if ("PERSON".equals(type) && parts.length >= 4) {
                    int id = Integer.parseInt(parts[1]);
                    String name = unescape(parts[2]);
                    int age = Integer.parseInt(parts[3]);
                    // Generic person not normally created by UI
                    people.add(new Student(id, name, age, "Undeclared", 1, 0.0));
                }
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error loading file: " + ex.getMessage());
        }
    }

    /** Simple escaping for '|' and newline */
    private String escape(String s) {
        if (s == null) return "";
        return s.replace("|", "\\|").replace("\n", " ");
    }

    private String unescape(String s) {
        if (s == null) return "";
        return s.replace("\\|", "|");
    }

    /** Split a saved line by '|' but treat '\|' as escaped. */
    private String[] splitLine(String line) {
        ArrayList<String> parts = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean escape = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (escape) {
                sb.append(c);
                escape = false;
            } else {
                if (c == '\\') {
                    escape = true;
                } else if (c == '|') {
                    parts.add(sb.toString());
                    sb = new StringBuilder();
                } else {
                    sb.append(c);
                }
            }
        }
        parts.add(sb.toString());
        return parts.toArray(new String[0]);
    }
}