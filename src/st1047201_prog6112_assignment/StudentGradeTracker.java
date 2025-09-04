/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st1047201_prog6112_assignment;

/**
 *
 * @author Owner
 */
import java.util.*;

// main - Section B
public class StudentGradeTracker {
    
    // Variables showing good variable declaration
    private static ArrayList<Student> students;
    private static Scanner input;
    private static boolean running;
    private static String[] subjectNames;
    private static int[][] yearStats; // 2D array for statistics
    
    
    //Main method
     
    public static void main(String[] args) {
        initializeSystem();
        loadSampleData();
        runApplication();
        cleanup();
    }
    
    
    //Initialize the system
    
    private static void initializeSystem() {
        students = new ArrayList<Student>();
        input = new Scanner(System.in);
        running = true;
        
        // Array of subject names
        subjectNames = new String[]{"Module 1", "Module 2", "Module 3", "Module 4", "Module 5"};
        
        // 2D array for year statistics [year][pass/fail]
        yearStats = new int[4][2];
        
        System.out.println("====================================");
        System.out.println("  STUDENT GRADE TRACKER SYSTEM");
        System.out.println("====================================");
        System.out.println("System started successfully!");
        System.out.println();
    }
    
    
    //Load some sample data
     
    private static void loadSampleData() {
        // Create sample students using different constructors
        UndergraduateStudent student1 = new UndergraduateStudent(101, "Alejandro Garnacho", "Computer Science", 1);
        student1.setGrade(0, 85.5); // Module 1
        student1.setGrade(1, 78.0); // Module 2
        student1.setGrade(2, 92.0); // Module 3
        student1.setGrade(3, 68.0); // Module 4
        student1.setGrade(4, 71.0); // Module 5
        
        UndergraduateStudent student2 = new UndergraduateStudent(102, "Marcus Rashford", "Engineering", 2);
        student2.setGrade(0, 76.5); // Module 1
        student2.setGrade(1, 82.0); // Module 2
        student2.setGrade(2, 88.5); // Module 3
        student2.setGrade(3, 59.0); // Module 4
        student2.setGrade(4, 79.0); // Module 5
        
        students.add(student1);
        students.add(student2);
        
        System.out.println("Sample data loaded: " + students.size() + " students");
        System.out.println();
    }
    
    
    //Main application loop
     
    private static void runApplication() {
        while (running) {
            showMenu();
            int choice = getChoice();
            processChoice(choice);
            
            if (running) {
                System.out.println("Press Enter to continue...");
                input.nextLine();
                System.out.println();
            }
        }
    }
    
    
    //Show main menu
     
    private static void showMenu() {
        System.out.println("====================================");
        System.out.println("         MAIN MENU");
        System.out.println("====================================");
        System.out.println("(1) Add New Student");
        System.out.println("(2) View All Students");
        System.out.println("(3) Search Student");
        System.out.println("(4) Add Grades");
        System.out.println("(5) View Statistics");
        System.out.println("(6) Honor Roll Report");
        System.out.println("(7) Exit");
        System.out.println("====================================");
    }
    
    
     //Get user choice
    private static int getChoice() {
        System.out.print("Enter your choice: ");
        try {
            String line = input.nextLine();
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    
     //Process user choice using control structures
    private static void processChoice(int choice) {
        System.out.println();
        
        switch (choice) {
            case 1:
                addNewStudent();
                break;
            case 2:
                viewAllStudents();
                break;
            case 3:
                searchStudent();
                break;
            case 4:
                addGrades();
                break;
            case 5:
                viewStatistics();
                break;
            case 6:
                honorRollReport();
                break;
            case 7:
                exitSystem();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
    
    
    // Add new student
    private static void addNewStudent() {
        System.out.println("=== ADD NEW STUDENT ===");
        
        try {
            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(input.nextLine());
            
            // Check if ID already exists
            if (findStudentById(id) != null) {
                System.out.println("Student ID already exists!");
                return;
            }
            
            System.out.print("Enter student name: ");
            String name = input.nextLine();
            
            System.out.print("Enter course: ");
            String course = input.nextLine();
            
            System.out.print("Enter year (1-4): ");
            int year = Integer.parseInt(input.nextLine());
            
            if (year < 1 || year > 4) {
                System.out.println("Year must be between 1 and 4!");
                return;
            }
            
            // Create new student using constructor
            UndergraduateStudent newStudent = new UndergraduateStudent(id, name, course, year);
            students.add(newStudent);
            
            System.out.println("Student added successfully!");
            System.out.println(newStudent.toString());
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numbers!");
        }
    }
    
    
    //View all students using loops
     
    private static void viewAllStudents() {
        System.out.println("=== ALL STUDENTS ===");
        
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }
        
        // Use enhanced for loop
        for (Student student : students) {
            System.out.println(student.toString());
        }
        
        System.out.println("Total students: " + students.size());
    }
    
    
    //Search for student
    
    private static void searchStudent() {
        System.out.println("=== SEARCH STUDENT ===");
        System.out.print("Enter student ID: ");
        
        try {
            int searchId = Integer.parseInt(input.nextLine());
            Student found = findStudentById(searchId);
            
            if (found != null) {
                System.out.println("Student found:");
                System.out.println(found.toString());
                
                // Show individual grades
                showStudentGrades(found);
            } else {
                System.out.println("Student with ID " + searchId + " not found.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid student ID!");
        }
    }
    
    
    //Add grades to student
    
    private static void addGrades() {
        System.out.println("=== ADD GRADES ===");
        System.out.print("Enter student ID: ");
        
        try {
            int studentId = Integer.parseInt(input.nextLine());
            Student student = findStudentById(studentId);
            
            if (student == null) {
                System.out.println("Student not found!");
                return;
            }
            
            System.out.println("Adding grades for: " + student.getStudentName());
            System.out.println("Enter grades for each subject (0 to skip):");
            
            // Loop through subjects using regular for loop
            for (int i = 0; i < subjectNames.length; i++) {
                System.out.print(subjectNames[i] + ": ");
                double grade = Double.parseDouble(input.nextLine());
                
                if (grade > 0 && grade <= 100) {
                    student.setGrade(i, grade);
                } else if (grade != 0) {
                    System.out.println("Invalid grade. Skipping " + subjectNames[i]);
                }
            }
            
            System.out.println("Grades updated successfully!");
            System.out.println("New average: " + String.format("%.1f", student.calculateAverage()));
            System.out.println("Grade: " + student.getGradeLetter());
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numbers!");
        }
    }
    
    
    //View statistics using 2D array
    private static void viewStatistics() {
        System.out.println("=== SYSTEM STATISTICS ===");
        
        // Reset statistics array
        for (int year = 0; year < 4; year++) {
            for (int status = 0; status < 2; status++) {
                yearStats[year][status] = 0;
            }
        }
        
        // Calculate statistics using nested loops
        for (Student student : students) {
            if (student instanceof UndergraduateStudent) {
                UndergraduateStudent ugStudent = (UndergraduateStudent) student;
                int year = ugStudent.getYear() - 1; // Convert to 0-based index
                
                if (year >= 0 && year < 4) {
                    double average = student.calculateAverage();
                    if (average >= 50.0) {
                        yearStats[year][0]++; // Pass
                    } else {
                        yearStats[year][1]++; // Fail
                    }
                }
            }
        }
        
        System.out.println("Total Students: " + students.size());
        System.out.println("\nStatistics by Year:");
        System.out.println("Year | Pass | Fail | Total");
        System.out.println("----|------|------|------");
        
        // Display statistics using 2D array
        for (int year = 0; year < 4; year++) {
            int pass = yearStats[year][0];
            int fail = yearStats[year][1];
            int total = pass + fail;
            System.out.printf("  %d  |  %2d  |  %2d  |  %2d%n", (year + 1), pass, fail, total);
        }
        
        // Calculate overall statistics
        double totalAverage = 0;
        int count = 0;
        for (Student student : students) {
            double avg = student.calculateAverage();
            if (avg > 0) {
                totalAverage += avg;
                count++;
            }
        }
        
        if (count > 0) {
            //System.out.println("\nOverall Class Average: " + String.format("%.1f", totalAverage / count));
        }
    }
    
    
     //Honor roll report using inheritance
     
    private static void honorRollReport() {
        System.out.println("=== HONOR ROLL REPORT ===");
        System.out.println("Students with average >= 85.0:");
        
        int honorCount = 0;
        
        // Use loops and inheritance concepts
        for (Student student : students) {
            if (student instanceof UndergraduateStudent) {
                UndergraduateStudent ugStudent = (UndergraduateStudent) student;
                
                if (ugStudent.isOnHonorRoll()) {
                    System.out.println("* " + ugStudent.toString());
                    honorCount++;
                }
            }
        }
        
        System.out.println("\nTotal students on honor roll: " + honorCount);
        
        if (honorCount == 0) {
            System.out.println("No students currently qualify for honor roll.");
        }
    }
    
    
    //Find student by ID
     
    private static Student findStudentById(int id) {
        // Simple loop to search
        for (Student student : students) {
            if (student.getStudentId() == id) {
                return student;
            }
        }
        return null;
    }
    
    
     // grades for a student
     
    private static void showStudentGrades(Student student) {
        System.out.println("\nSubject Grades:");
        double[] grades = student.getGrades();
        
        for (int i = 0; i < subjectNames.length; i++) {
            if (grades[i] > 0) {
                System.out.println("  " + subjectNames[i] + ": " + grades[i]);
            } else {
                System.out.println("  " + subjectNames[i] + ": Not graded");
            }
        }
    }
    
    /**
     * Exit system
     */
    private static void exitSystem() {
        System.out.println("Thank you for using Student Grade Tracker!");
        running = false;
    }
    
    /**
     * Cleanup resources
     */
    private static void cleanup() {
        if (input != null) {
            input.close();
        }
    }
    
    // public methods for unit testing 
    
    public static Student searchStudentById(int id) {
        return findStudentById(id);
    }
    
    public static boolean addStudent(Student student) {
        if (student != null && findStudentById(student.getStudentId()) == null) {
            students.add(student);
            return true;
        }
        return false;
    }
    
    public static ArrayList<Student> getStudents() {
        return new ArrayList<Student>(students);
    }
    
    public static void clearStudents() {
        if (students != null) {
            students.clear();
        }
    }
    
    public static void initializeForTesting() {
        if (students == null) {
            students = new ArrayList<Student>();
            subjectNames = new String[]{"Module 1", "Module 2", "Module 3", "Module 4", "Module 5"};
            yearStats = new int[4][2];
        }
    }
}
