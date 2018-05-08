/*
 * Author: Joseph Maples
 * CS102 Assignment 1
 */
package maples.joe;

public class TennisMatchesContainer implements TennisMatchesContainerInterface{

    private TennisMatch[] matches;
    private int numMatches;
    private int size;
    
    public TennisMatchesContainer() {
        size = 2;
        numMatches = 0;
        matches = new TennisMatch[size];
    }
    
    public void insertMatch(TennisMatch match) {
        // Check if array is full before insertion
        if (numMatches == size) {
            // Resize if full
            TennisMatch[] newMatchContainer = new TennisMatch[matches.length + 1];
            
            // Copy data to new array
            for (int index = 0; index < matches.length; index++) {
                newMatchContainer[index] = matches[index];
            }
            
            matches = newMatchContainer;
            size++;
        }
        
        
        int i;
        for (i = 0; (i < numMatches) && (match.compareTo(matches[i]) < 0); i++) {
            // This is just to fild the correct position
        }
        
        // Check if position is tail
        int position = i;
        if (position == numMatches) {
            matches[numMatches] = match;
            numMatches++;
        } else {
            // Insertion in the middle (or at front), right shift required.
            for (int j = numMatches - 1; j >= position; j--) {
                matches[j + 1] = matches[j];
            }
            matches[position] = match;
            numMatches++;
        }
    }

    public void printAllMatches() {
        for(int index = 0; index < numMatches; index++) {
            matches[index].print();
        }
    }
}