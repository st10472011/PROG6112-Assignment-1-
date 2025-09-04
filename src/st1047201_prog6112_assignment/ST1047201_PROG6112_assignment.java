/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package st1047201_prog6112_assignment;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Owner
 */
public class ST1047201_PROG6112_assignment {

    // Declaring Variables
    private static ArrayList<Series> seriesCollection;
    private static Scanner userInput;
    private static boolean applicationRunning;
    private static final String MENU_SEPARATOR = "******************************************";
    private static final String SERIES_SEPARATOR = "----------------------------------------";
    
    
    public static void main(String[] args) {
        initializeApplication();
        runMainApplicationLoop();
        cleanupResources();
    }
    
    //initialize 
    private static void initializeApplication() {
        seriesCollection = new ArrayList<Series>();
        userInput = new Scanner(System.in);
        applicationRunning = true;
        
        System.out.println(MENU_SEPARATOR);
        System.out.println("TV SERIES MANAGEMENT APPLICATION");
        System.out.println("Welcome to your TV series tracker!");
        System.out.println(MENU_SEPARATOR);
        System.out.println();
    }
    
    
    private static void runMainApplicationLoop() {
        while (applicationRunning) {
            displayMainMenu();
            int userChoice = getUserMenuChoice();
            processUserChoice(userChoice);
            
            // Add a small pause 
            if (applicationRunning) {
                pauseForUserInput();
            }
        }
    }
    
    // Display main menu
    private static void displayMainMenu() {
        System.out.println("LATEST SERIES - 2025");
        System.out.println(MENU_SEPARATOR);
        //System.out.println("Enter (1) to launch menu or any other key to exit");
        System.out.println();
        System.out.println("Please select one of the following menu items:");
        System.out.println("(1) Capture a new series.");
        System.out.println("(2) Search for a series.");
        System.out.println("(3) Update series age restriction");
        System.out.println("(4) Delete a series.");
        System.out.println("(5) Print series report - 2025");
        System.out.println("(6) Exit Application.");
        System.out.println();
    }
    
    // User menu choice
    private static int getUserMenuChoice() {
        System.out.print("Enter your choice: ");
        try {
            String input = userInput.nextLine().trim();
            
            // Handles empty input
            if (input.isEmpty()) {
                return 0; 
            }
            
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0; 
        }
    }
    
    // User choice
    private static void processUserChoice(int choice) {
        System.out.println();
        
        switch (choice) {
            case 1:
                captureNewSeries();
                break;
            case 2:
                searchForSeries();
                break;
            case 3:
                updateSeriesAgeRestriction();
                break;
            case 4:
                deleteSeries();
                break;
            case 5:
                printSeriesReport();
                break;
            case 6:
                exitApplication();
                break;
            default:
                System.out.println("Invalid choice or exiting application...");
                exitApplication();
                break;
        }
    }
    
    // Gets a new TV series
    private static void captureNewSeries() {
        System.out.println("CAPTURE A NEW SERIES");
        System.out.println(MENU_SEPARATOR);
        
        try {
            // Get series ID
            System.out.print("Enter the series id: ");
            int seriesId = Integer.parseInt(userInput.nextLine().trim());
            
            // Check if series ID already exists
            if (findSeriesById(seriesId) != null) {
                System.out.println("Error: A series with ID " + seriesId + " already exists!");
                //System.out.println("Enter (1) to launch menu or any other key to exit");
                return;
            }
            
            // Get series name
            System.out.print("Enter the series name: ");
            String seriesName = userInput.nextLine().trim();
            
            // Get and Validate age 
            int seriesAge = getValidAgeRestriction();
            if (seriesAge == -1) {
                //System.out.println("Enter (1) to launch menu or any other key to exit");
                return;
            }
            
            // Get number of episodes
            System.out.print("Enter the number of episodes: ");
            int numberOfEpisodes = Integer.parseInt(userInput.nextLine().trim());
            
            if (numberOfEpisodes <= 0) {
                System.out.println("Number of episodes must be positive!");
                //System.out.println("Enter (1) to launch menu or any other key to exit");
                return;
            }
            
            // Create and save new series
            Series newSeries = new Series(seriesId, seriesName, seriesAge, numberOfEpisodes);
            seriesCollection.add(newSeries);
            
            System.out.println("Series processed successfully!!!");
            //System.out.println("Enter (1) to launch menu or any other key to exit");
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numeric values!");
            //System.out.println("Enter (1) to launch menu or any other key to exit");
        }
    }
    
    // Valid age restriction 
    private static int getValidAgeRestriction() {
        while (true) {
            System.out.print("Enter the series age restriction: ");
            try {
                int age = Integer.parseInt(userInput.nextLine().trim());
                
                if (Series.isValidAge(age)) {
                    return age;
                } else {
                    
                    System.out.println("You have entered a incorrect series age!!!");
                    System.out.println("Please re-enter the series age >>>");
                }
            } catch (NumberFormatException e) {
                
                System.out.println("You have entered a incorrect series age!!!");
                System.out.println("Please re-enter the series age >>>");
            }
        }
    }
    
    // Search for series 
    private static void searchForSeries() {
        System.out.print("Enter the series id to search: ");
        try {
            int searchId = Integer.parseInt(userInput.nextLine().trim());
            
            Series foundSeries = findSeriesById(searchId);
            
            System.out.println(SERIES_SEPARATOR);
            
            if (foundSeries != null) {
                
                System.out.println("SERIES ID: " + foundSeries.getSeriesId());
                System.out.println("SERIES NAME: " + foundSeries.getSeriesName());
                System.out.println("SERIES AGE RESTRICTION: " + foundSeries.getSeriesAge());
                System.out.println("SERIES NUMBER OF EPISODES: " + foundSeries.getSeriesNumberOfEpisodes());
            } else {
                
                System.out.println("Series with Series Id: " + searchId + " was not found");
            }
            
            System.out.println(SERIES_SEPARATOR);
            //System.out.println("Enter (1) to launch menu or any other key to exit");
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid series ID number!");
            //System.out.println("Enter (1) to launch menu or any other key to exit");
        }
    }
    
    //Update age restriction 
    private static void updateSeriesAgeRestriction() {
        System.out.print("Enter the series id to be updated: ");
        try {
            int updateId = Integer.parseInt(userInput.nextLine().trim());
            
            Series seriesToUpdate = findSeriesById(updateId);
            
            if (seriesToUpdate != null) {
                System.out.println("Enter the series name: " + seriesToUpdate.getSeriesName());
                
                // Get new age
                int newAge = getValidAgeRestriction();
                if (newAge != -1) {
                    seriesToUpdate.setSeriesAge(newAge);
                    System.out.println("Enter the number of episodes: " + seriesToUpdate.getSeriesNumberOfEpisodes());
                    System.out.println("Series updated successfully!");
                }
                //System.out.println("Enter (1) to launch menu or any other key to exit");
            } else {
                System.out.println("Series with ID " + updateId + " was not found!");
                //System.out.println("Enter (1) to launch menu or any other key to exit");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid series ID number!");
            //System.out.println("Enter (1) to launch menu or any other key to exit");
        }
    }
    
    // Delete series
    private static void deleteSeries() {
        System.out.print("Enter the series id to delete: ");
        try {
            int deleteId = Integer.parseInt(userInput.nextLine().trim());
            
            Series seriesToDelete = findSeriesById(deleteId);
            
            if (seriesToDelete != null) {
                // Confirmation 
                System.out.print("Are you sure you want to delete series " + deleteId + " from the system? Yes (y) to delete: ");
                String confirmation = userInput.nextLine().trim().toLowerCase();
                
                if (confirmation.equals("y") || confirmation.equals("yes")) {
                    seriesCollection.remove(seriesToDelete);
                    
                    System.out.println(SERIES_SEPARATOR);
                    System.out.println("Series with Series Id: " + deleteId + " WAS deleted!");
                    System.out.println(SERIES_SEPARATOR);
                } else {
                    System.out.println("Deletion cancelled.");
                }
            } else {
                System.out.println("Series with Series Id: " + deleteId + " was not found!");
            }
            
            //System.out.println("Enter (1) to launch menu or any other key to exit");
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid series ID number!");
            //System.out.println("Enter (1) to launch menu or any other key to exit");
        }
    }
    
    // Print Report
    private static void printSeriesReport() {
        
        System.out.println("Please select one of the following menu items:");
        System.out.println("(1) Capture a new series.");
        System.out.println("(2) Search for a series.");
        System.out.println("(3) Update series age restriction");
        System.out.println("(4) Delete a series.");
        System.out.println("(5) Print series report - 2025");
        System.out.println("(6) Exit Application.");
        System.out.println();
        
        if (seriesCollection.isEmpty()) {
            System.out.println("No series have been captured yet.");
        } else {
            
            for (int i = 0; i < seriesCollection.size(); i++) {
                System.out.println("Series " + (i + 1));
                System.out.println(SERIES_SEPARATOR);
                Series currentSeries = seriesCollection.get(i);
                System.out.println("SERIES ID: " + currentSeries.getSeriesId());
                System.out.println("SERIES NAME: " + currentSeries.getSeriesName());
                System.out.println("SERIES AGE RESTRICTION: " + currentSeries.getSeriesAge());
                System.out.println("NUMBER OF EPISODES: " + currentSeries.getSeriesNumberOfEpisodes());
                System.out.println(SERIES_SEPARATOR);
            }
        }
        
        //System.out.println("Enter (1) to launch menu or any other key to exit");
    }
    
    // Find ID
    public static Series findSeriesById(int seriesId) {
        for (Series series : seriesCollection) {
            if (series.getSeriesId() == seriesId) {
                return series;
            }
        }
        return null;
    }
    
    
    private static void pauseForUserInput() {
        System.out.println();
        System.out.print("Press Enter to continue...");
        userInput.nextLine();
        System.out.println();
    }
    
    // Exit application 
    private static void exitApplication() {
        applicationRunning = false;
        System.out.println("Exiting TV Series Management Application...");
        System.out.println("Thank you for using our application!");
    }
    
    // Close scanner option 
    private static void cleanupResources() {
        if (userInput != null) {
            userInput.close();
        }
    }
    
    // public methods for unit testing
    
    public static Series searchSeries(int seriesId) {
        return findSeriesById(seriesId);
    }
    
    public static boolean updateSeries(int seriesId, String newName, int newAge, int newEpisodes) {
        Series seriesToUpdate = findSeriesById(seriesId);
        if (seriesToUpdate != null && Series.isValidAge(newAge)) {
            seriesToUpdate.setSeriesName(newName);
            seriesToUpdate.setSeriesAge(newAge);
            seriesToUpdate.setSeriesNumberOfEpisodes(newEpisodes);
            return true;
        }
        return false;
    }
    
    public static boolean deleteSeries(int seriesId) {
        Series seriesToDelete = findSeriesById(seriesId);
        if (seriesToDelete != null) {
            seriesCollection.remove(seriesToDelete);
            return true;
        }
        return false;
    }
    
    public static boolean addSeries(Series series) {
        if (series != null && findSeriesById(series.getSeriesId()) == null) {
            seriesCollection.add(series);
            return true;
        }
        return false;
    }
    
    public static ArrayList<Series> getSeriesCollection() {
        return new ArrayList<Series>(seriesCollection);
    }
    
    public static void clearSeriesCollection() {
        if (seriesCollection != null) {
            seriesCollection.clear();
        }
    }
    
    public static boolean validateSeriesAge(int age) {
        return Series.isValidAge(age);
    }
}
