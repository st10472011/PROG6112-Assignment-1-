/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st1047201_prog6112_assignment;

/**
 *
 * @author Owner
 */
public class Series {
    // variables showing information hiding 
    private int seriesId;
    private String seriesName;
    private int seriesAge;
    private int seriesNumberOfEpisodes;
    
    // Constructor
    public Series() {
        this.seriesId = 0;
        this.seriesName = "";
        this.seriesAge = 12; 
        this.seriesNumberOfEpisodes = 0;
    }
    
    
    public Series(int seriesId, String seriesName, int seriesAge, int seriesNumberOfEpisodes) {
        this.seriesId = seriesId;
        this.seriesName = seriesName;
        setSeriesAge(seriesAge); // Use setter for validation
        this.seriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }
    
    
    // New Constructor
    // Creating new series from already made one
    public Series(Series other) {
        this.seriesId = other.seriesId;
        this.seriesName = other.seriesName;
        this.seriesAge = other.seriesAge;
        this.seriesNumberOfEpisodes = other.seriesNumberOfEpisodes;
    }
    
    // Getter methods
    public int getSeriesId() {
        return seriesId;
    }
    
    public String getSeriesName() {
        return seriesName;
    }
    
    public int getSeriesAge() {
        return seriesAge;
    }
    
    public int getSeriesNumberOfEpisodes() {
        return seriesNumberOfEpisodes;
    }
    
    // Setter methods 
    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }
    
    public void setSeriesName(String seriesName) {
        // Series is not null
        if (seriesName != null && !seriesName.trim().isEmpty()) {
            this.seriesName = seriesName.trim();
        } else {
            this.seriesName = "Unknown Series";
        }
    }
    
    //Age validation
    public boolean setSeriesAge(int seriesAge) {
        if (isValidAge(seriesAge)) {
            this.seriesAge = seriesAge;
            return true;
        }
        return false;
    }
    
    public void setSeriesNumberOfEpisodes(int seriesNumberOfEpisodes) {
        // Check episode count is not negative
        if (seriesNumberOfEpisodes >= 0) {
            this.seriesNumberOfEpisodes = seriesNumberOfEpisodes;
        }
    }
    
    // Age validation
    public static boolean isValidAge(int age) {
        return age >= 2 && age <= 18;
    }
    
    // Returns string for display 
    @Override
    public String toString() {
        return String.format("SERIES ID: %d%nSERIES NAME: %s%nSERIES AGE RESTRICTION: %d%nNUMBER OF EPISODES: %d",
                seriesId, seriesName, seriesAge, seriesNumberOfEpisodes);
    }
    
    
    // Check if 2 Series are equal by ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Series series = (Series) obj;
        return seriesId == series.seriesId;
    }
    
    
    // Hash code
    @Override
    public int hashCode() {
        return Integer.hashCode(seriesId);
    }
}