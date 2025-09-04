/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st1047201_prog6112_assignment;

/**
 *
 * @author Owner
 */
class UndergraduateStudent extends Student {
    private int year; // 1st, 2nd, 3rd, 4th year
    
    
    //Default constructor
    
    public UndergraduateStudent() {
        super();
        this.year = 1;
    }
    
    
    //Constructor with parameters
     
    public UndergraduateStudent(int studentId, String studentName, String course, int year) {
        super(studentId, studentName, course);
        this.year = year;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    
    // Override to string method
    @Override
    public String toString() {
        return super.toString() + ", Year: " + year;
    }
    
    
    //Check if student is on honor roll (average >= 85)
    
    public boolean isOnHonorRoll() {
        return calculateAverage() >= 85.0;
    }
}
