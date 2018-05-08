/*
 * Author: Joseph Maples
 * CS102 Assignment 1
 */
package maples.joe;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.rotateLeft;

public class TennisDatabase {

    private TennisPlayersContainer playersContainer = new TennisPlayersContainer();

    private TennisMatchesContainer matchesContainer = new TennisMatchesContainer();

    public void handleData(String[] recordInformation) {
        // Call correct method based on line identifier
        if(recordInformation[0].equals("PLAYER"))
            createPlayer(recordInformation);
        else if (recordInformation[0].equals("MATCH"))
            createMatch(recordInformation);
        else
            System.out.println("Error: " + recordInformation[0] + " not valid identifier.");
    }

    public void createPlayer(String[] playerInformation) {
        try {
            // Make sure player information is the correct length before instantiation
            if (playerInformation.length == 6) {
                final TennisPlayer player = new TennisPlayer(playerInformation[1], playerInformation[2]
                        , playerInformation[3], parseInt(playerInformation[4]), playerInformation[5]);
                playersContainer.insertPlayer(player);
            } else {
                throw new TennisDatabaseException("Do not have sufficient information for a TennisPlayer");
            }
        } catch (TennisDatabaseException ex) {
            System.out.println("Issue creating the match, invalid input for a field");
            System.out.println("Given Information");
            for (int index = 0; index < playerInformation.length; index++) {
                System.out.println("Data " + index + ": " + playerInformation[index]);
            }
        }
    }

    public void userCreatePlayer(TennisPlayer player) {
        playersContainer.insertPlayer(player);
    }

    public void createMatch(String[] matchData) {
        try {
            // Make sure player information is the correct length before instantiation
            if (matchData.length == 6) {
                final TennisMatch match = new TennisMatch(
                        Integer.parseInt(matchData[3].substring(0,4)),
                        Integer.parseInt(matchData[3].substring(4, 6)),
                        Integer.parseInt(matchData[3].substring(6)),
                        matchData[1], matchData[2], matchData[4], matchData[5]);
                // Only add matches for players that exist
                if ((playersContainer.getPlayer(match.getPlayer1Id()) != null)
                        && (playersContainer.getPlayer(match.getPlayer2Id()) != null)){
                    matchesContainer.insertMatch(match);
                    playersContainer.insertMatch(match);
                } else 
                    System.out.println("Warning: Unknown player referenced. Discarding match");
            } else {
                throw new TennisDatabaseException("Do not have sufficient information for a TennisMatch");
            }
        } catch (TennisDatabaseException ex) {
            System.out.println("Issue creating the match, invalid input for a field");
            System.out.println("Given Information");
            for(int index = 0; index < matchData.length; index++) {
                System.out.println("Data " + index + ": " + matchData[index]);
            }
        }
    }

    public void userCreateMatch(TennisMatch match) {
        matchesContainer.insertMatch(match);
    }

    protected void printAllTennisPlayers() {
        playersContainer.printAllPlayers();
    }

    protected void printTennisMatchesOfPlayer(String playerId) {
        playersContainer.printMatchesOfPlayer(playerId);
    }
    protected void printAllMatches() {
        matchesContainer.printAllMatches();
    }

}