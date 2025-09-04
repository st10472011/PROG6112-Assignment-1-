/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st1047201_prog6112_assignment;

/**
 *
 * @author Owner
 */
class Student {
    // variables for info hiding 
    private int studentId;
    private String studentName;
    private String course;
    private double[] grades; // Array for storing grades
    
    
      //Default constructor
     
    public Student() {
        this.studentId = 0;
        this.studentName = "Unknown";
        this.course = "General";
        this.grades = new double[5]; // Array for 5 subjects
    }
    
    
     // Constructors
     
    public Student(int studentId, String studentName, String course) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.course = course;
        this.grades = new double[5];
    }
    
    // Getter methods
    public int getStudentId() {
        return studentId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public String getCourse() {
        return course;
    }
    
    public double[] getGrades() {
        return grades;
    }
    
    // Setter methods
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public void setGrade(int subject, double grade) {
        if (subject >= 0 && subject < 5) {
            this.grades[subject] = grade;
        }
    }
    
    
    //Calculate average grade using loops
    
    public double calculateAverage() {
        double total = 0;
        int count = 0;
        
        // Loop through grades array
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] > 0) {
                total += grades[i];
                count++;
            }
        }
        
        if (count > 0) {
            return total / count;
        }
        return 0;
    }
    
    
    //Get grade based on average
    
    public String getGradeLetter() {
        double avg = calculateAverage();
        
        if (avg >= 90) {
            return "A+";
        } else if (avg >= 85) {
            return "A";
        } else if (avg >= 80) {
            return "A-";
        } else if (avg >= 75) {
            return "B+";
        } else if (avg >= 70) {
            return "B";
        } else if (avg >= 65) {
            return "B-";
        } else if (avg >= 60) {
            return "C+";
        } else if (avg >= 55) {
            return "C";
        } else if (avg >= 50) {
            return "C-";
        } else {
            return "F";
        }
    }
    
    public String toString() {
        return "Student ID: " + studentId + 
               ", Name: " + studentName + 
               ", Course: " + course + 
               ", Average: " + String.format("%.1f", calculateAverage()) +
               ", Grade: " + getGradeLetter();
    }
}
