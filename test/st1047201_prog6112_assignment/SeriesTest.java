/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st1047201_prog6112_assignment;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;

/**
 *
 * @author Owner
 */

public class SeriesTest {

    private ArrayList<Series> list() {
        return ST1047201_PROG6112_assignment.getSeriesCollection();
    }

    private Series findById(int id) {
        for (Series s : list()) if (s.getSeriesId() == id) return s;
        return null;
    }

    private boolean removeById(int id) {
        for (int i = 0; i < list().size(); i++) {
            if (list().get(i).getSeriesId() == id) {
                list().remove(i);
                return true;
            }
        }
        return false;
    }

    @BeforeEach
    void reset() {
        ST1047201_PROG6112_assignment.clearSeriesCollection();
        // Seed one record 
        list().add(new Series(101, "Extreme Sports", 12, 10));
    }

    // --------- Required rubric tests ---------

    @Test
    void TestSearchSeries() { 
        Series found = findById(101); 
        assertNotNull(found);
        assertEquals("Extreme Sports", found.getSeriesName());
        assertEquals(12, found.getSeriesAge());
        assertEquals(10, found.getSeriesNumberOfEpisodes());
    }

    @Test
    void TestSearchSeries_SeriesNotFound() { 
        Series found = findById(999);
        assertNull(found);
    }

    @Test
    void TestUpdateSeries() { 
        Series s = findById(101);
        assertNotNull(s);

        
        s.setSeriesName("Extreme Sports 2025");
        s.setSeriesAge(13);
        s.setSeriesNumberOfEpisodes(12);

        Series after = findById(101);
        assertEquals("Extreme Sports 2025", after.getSeriesName());
        assertEquals(13, after.getSeriesAge());
        assertEquals(12, after.getSeriesNumberOfEpisodes());
    }

    @Test
    void TestDeleteSeries() { 
        boolean removed = removeById(101);
        assertTrue(removed);
        assertNull(findById(101));
    }

    @Test
    void TestDeleteSeries_SeriesNotFound() { // 3 marks
        boolean removed = removeById(888);
        assertFalse(removed);
    }

    @Test
    void TestSeriesAgeRestriction_AgeValid() { 
        assertTrue(ST1047201_PROG6112_assignment.validateSeriesAge(2));
        assertTrue(ST1047201_PROG6112_assignment.validateSeriesAge(12));
        assertTrue(ST1047201_PROG6112_assignment.validateSeriesAge(18));
    }

    @Test
    void TestSeriesAgeRestriction_SeriesAgeInValid() {
        assertFalse(ST1047201_PROG6112_assignment.validateSeriesAge(1));
        assertFalse(ST1047201_PROG6112_assignment.validateSeriesAge(0));
        assertFalse(ST1047201_PROG6112_assignment.validateSeriesAge(19));
        assertFalse(ST1047201_PROG6112_assignment.validateSeriesAge(-5));
    }
}

