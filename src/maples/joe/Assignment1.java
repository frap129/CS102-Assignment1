/*
 * Author: Joseph Maples
 * CS102 Assignment 1
 */
package maples.joe;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;


public class Assignment1 {

    private static final Assignment1 assignment = new Assignment1();

    public static void main(String[] args) {
        TennisDatabase database = new TennisDatabase();
        loadFile(args, database);
        assignment.displayUserOptions(database);
    }
    
    public static void loadFile(String [] args, TennisDatabase database) {
        // Make sure a file was provided. If not, report error.
        if (args.length > 0) {
            String inputFileName= args[0];

            // Warn if extra arguments were provided
            if (args.length >= 2) {
                System.out.println("Warning: More than one argument provided.\nWarning: Using first arg as file name");
            }

            // Read the file
            try {
                Scanner fileScanner = new Scanner(new File(inputFileName));
                readFile(fileScanner, database);
            } catch (IOException ex) {
                System.out.println("Error: " + inputFileName + " missing or unreadable.");
            }
        } else {
            System.out.println("Error: Missing file name argument.");
        }
    }

    private static void readFile(Scanner fileScanner, TennisDatabase database) {
        String[] fileData;
        while (fileScanner.hasNextLine()) {
            // Make line upper case, split at "/" into an array
            fileData = fileScanner.nextLine().toUpperCase().split("/");

            if (fileData.length > 0) {
                database.handleData(fileData);
            }
        }
    }
    
    private static boolean isInteger(String string) {
        boolean isValidInteger = false;
        try {
            Integer.parseInt(string);
            isValidInteger = true;
        } catch (NumberFormatException ex) {
            // Not an integer
        }

        return isValidInteger;
    }

    private void displayUserOptions(TennisDatabase database) {
        System.out.println("Welcome to the CS-102 Tennis Manager");
        int selection = 0;
        String input;
        do {
            System.out.println("Available Commands:");
            System.out.println("1 --> Print all tennis players");
            System.out.println("2 --> Print all tennis matches of a player");
            System.out.println("3 -- Print all tennis matches.");
            System.out.println("4 -- Insert a new player.");
            System.out.println("5 -- Insert a new match.");
            System.out.println("9 --> Exit");
            System.out.print("Your choice? ");
            Scanner userInput = new Scanner(System.in);
            input = userInput.next();
            if (isInteger(input))
                selection = Integer.parseInt(input);

            switch (selection) {
                case 1:
                    printPlayers(database);
                    break;
                case 2:
                    printPlayerMatches(userInput, database);
                    break;
                case 3:
                    printAllMatches(database);
                    break;
                case 4: 
                    userAddPlayer(userInput, database);
                    break;
                case 5:
                    userAddMatch(userInput, database);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (selection != 9);

    }
    
    private void userAddPlayer(Scanner userInput, TennisDatabase database) {
        System.out.print("Enter the Player ID: ");
        String id = userInput.next();
        System.out.print("Enter the Player's First Name: ");
        String firstName = userInput.next();
        System.out.print("Enter the Player's Last Name: ");
        String lastName = userInput.next();
        System.out.print("Enter the Player's Birth Year: ");
        String yearString = userInput.next();
        int year = 0;
        if(isInteger(yearString))
            year = Integer.parseInt(yearString);
        else {
            System.out.println("Error: Year not an integer.");
            return;
        }
        System.out.print("Enter the Player's Country: ");
        String country = userInput.next();
        TennisPlayer newPlayer = new TennisPlayer(id.toUpperCase(), firstName.toUpperCase(),
                                            lastName.toUpperCase(), year, country.toUpperCase());
        database.userCreatePlayer(newPlayer);
    }
    
    private void userAddMatch(Scanner userInput, TennisDatabase database) {
        System.out.print("Enter the First Player's ID: ");
        String player1Id = userInput.next();
        System.out.print("Enter the Second Player's ID: ");
        String player2Id = userInput.next();
        System.out.print("Enter the Day of the Match: ");
        String dayInput = userInput.next();
        int day = 0;
        if(isInteger(dayInput))
            day = Integer.parseInt(dayInput);
        else {
            System.out.println("Error: Day not an integer.");
            return;
        }
        System.out.print("Enter the Month of the Match: ");
        String monthInput = userInput.next();
        int month = 0;
        if(isInteger(monthInput))
            month = Integer.parseInt(monthInput);
        else {
            System.out.println("Error: Month not an integer.");
            return;
        }
        System.out.print("Enter the Year of the Match: ");
        String yearInput = userInput.next();
        int year = 0;
        if(isInteger(yearInput))
            year = Integer.parseInt(yearInput);
        else {
            System.out.println("Error: Day not an integer:");
            return;
        }
        System.out.print("Enter the Name of the Tournament: ");
        String tournament = userInput.next();
        System.out.print("Enter the Set Scores (Example: 3-6,2-7): ");
        String scores = userInput.next();
        TennisMatch newMatch = new TennisMatch(year, month, day, player1Id.toUpperCase(),
                                            player2Id.toUpperCase(), tournament.toUpperCase9), scores);
        database.userCreateMatch(newMatch);
    }

    private void printPlayers(TennisDatabase database) {
        database.printAllTennisPlayers();
    }

    private void printPlayerMatches(Scanner userInput, TennisDatabase database) {
        System.out.print("Enter Player ID: ");
        String playerId = userInput.next();
        database.printTennisMatchesOfPlayer(playerId);

    }

    private void printAllMatches(TennisDatabase database) {
        database.printAllMatches();
    }
}