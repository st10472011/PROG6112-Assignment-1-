/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st1047201_prog6112_assignment;

/**
 *
 * @author Owner
 */


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.ArrayList;

public class StudentGradeTrackerTests {

    @Test
    public void resetStudents() {
        StudentGradeTracker.clearStudents();
    }

    private UndergraduateStudent makeStudentWithAvg(int id, String name, String course, int year, double avg) {
        // Create and set five subjects to reach roughly the desired average
        UndergraduateStudent s = new UndergraduateStudent(id, name, course, year);
        for (int i = 0; i < 5; i++) {
            s.setGrade(i, avg);
        }
        return s;
    }

    @Test
    public void CalculatesAverageCorrectly() {
        UndergraduateStudent s = makeStudentWithAvg(1, "Alejandro", "CS", 1, 85.0); // avg ~85
        assertTrue(StudentGradeTracker.addStudent(s));

        Student found = StudentGradeTracker.searchStudentById(1);
        assertNotNull(found);

        double avg = found.calculateAverage();
        assertEquals(85.0, avg, 0.0001);
    }

    @Test
    public void GradeLetterBoundaries() {
        UndergraduateStudent sAplus = makeStudentWithAvg(2, "Bruno", "IT", 2, 90.0);
        UndergraduateStudent sB     = makeStudentWithAvg(3, "Andre", "IT", 2, 70.0);

        assertTrue(StudentGradeTracker.addStudent(sAplus));
        assertTrue(StudentGradeTracker.addStudent(sB));

        assertEquals("A+", sAplus.getGradeLetter());
        assertEquals("B",  sB.getGradeLetter());
    }

    @Test
    public void AddDuplicateStudentNotAllowed() {
        UndergraduateStudent s = makeStudentWithAvg(4, "Leny", "SE", 3, 80.0);
        assertTrue(StudentGradeTracker.addStudent(s));
        assertFalse(StudentGradeTracker.addStudent(new UndergraduateStudent(4, "Duplicate", "SE", 3)));
    }

    @Test
    public void SearchStudentByIdWorks() {
        UndergraduateStudent s = makeStudentWithAvg(5, "Eze", "CS", 1, 75.0);
        StudentGradeTracker.addStudent(s);

        Student found = StudentGradeTracker.searchStudentById(5);
        assertNotNull(found);
        assertEquals(5, found.getStudentId());
        assertEquals("Eze", found.getStudentName());
    }

    @Test
    public void HonorRollFlag() {
        UndergraduateStudent honor = makeStudentWithAvg(6, "Kylian", "CS", 1, 85.0); // avg 85 => honor true
        UndergraduateStudent notHonor = makeStudentWithAvg(7, "Jude", "CS", 1, 84.0); // <85 => false
        assertTrue(StudentGradeTracker.addStudent(honor));
        assertTrue(StudentGradeTracker.addStudent(notHonor));

        assertTrue(honor.isOnHonorRoll());
        assertFalse(notHonor.isOnHonorRoll());
    }

    @Test
    public void GetStudentsReturnsListCopy() {
        UndergraduateStudent s1 = makeStudentWithAvg(8, "Harry", "IT", 2, 72.0);
        UndergraduateStudent s2 = makeStudentWithAvg(9, "Thomas", "IT", 2, 68.0);
        StudentGradeTracker.addStudent(s1);
        StudentGradeTracker.addStudent(s2);

        ArrayList<Student> list = StudentGradeTracker.getStudents();
        assertTrue(list.size() >= 2);
        // ensure it contains both IDs
        boolean has8 = list.stream().anyMatch(st -> st.getStudentId() == 8);
        boolean has9 = list.stream().anyMatch(st -> st.getStudentId() == 9);
        assertTrue(has8 && has9);
    }
}