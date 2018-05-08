/*
 * Author: Joseph Maples
 * CS102 Assignment 1
 */
package maples.joe;

public class TennisMatch implements TennisMatchInterface {
    private int dateYear, dateMonth, dateDay;
    private String player1Id, player2Id, tournament, scores, winner;


    public TennisMatch(int dateYear, int dateMonth, int dateDay, String player1Id, String player2Id, String tournament, String scores) {
        this.dateYear = dateYear;
        this.dateMonth = dateMonth;
        this.dateDay = dateDay;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.tournament = tournament;
        this.scores = scores;
        this.winner = determineWinner();
    }

    public int getDateYear() {
        return dateYear;
    }

    public int getDateMonth() {
        return dateMonth;
    }

    public int getDateDay() {
        return dateDay;
    }

    public String getPlayer1Id() {
        return player1Id;
    }

    public String getPlayer2Id() {
        return player2Id;
    }

    public String getTournament() {
        return tournament;
    }

    public String getScore() {
        return scores;
    }

    public String getWinner() {
        return winner;
    }

    private static boolean isInteger(String string) {
        boolean isValidInteger = false;
        try {
            // Exploit an exception to judge if a string is an int
            Integer.parseInt(string);
            isValidInteger = true;
        } catch (NumberFormatException ex) {
            // Not an integer
        }

        return isValidInteger;
    }

    private int evalSets(String setScore, int first, int last) {
        // last cannot be less than first
        // Return a big number to show somethng is wrong
        if (last < first)
            return Integer.MAX_VALUE;

        // Use isInteger(int) to ignore the "-" in the score
        if (isInteger(Character.toString(setScore.charAt(first)))) {
            // If first == last, we have reached the max depth of recursion
            if (first == last)
                return Integer.parseInt(Character.toString(setScore.charAt(first)));
            
            // Parse 1 char each time we recurse, branch to read next char
            int score = Integer.parseInt(Character.toString(setScore.charAt(first)));
            return score - evalSets(setScore, ++first, last);
        }
        
        // If the char at first is not an integer, skip it
        return evalSets(setScore, ++first, last);
    }

    private int parseGame(String[] scoreArray, int first, int last){
        // last cannot be less than first
        // Return a big number to show somethng is wrong
        if (last < first)
            return Integer.MAX_VALUE;

        // If first == last, we have reached the max depth of recursion
        if (first == last)
            return evalSets(scoreArray[first], 0, 2) > 0 ? 1 : -1;

        // Parse 1 set each time we recurse, and branch until all sets are parsed
        return (evalSets(scoreArray[first], 0, 2) > 0 ? 1 : -1) + parseGame(scoreArray, ++first, last);
    }

    public String determineWinner() {
        // Split scores into array
        String[] scoreArray = scores.split(",");
        
        int numMatches = scoreArray.length;
        int playerOneWins = parseGame(scoreArray, 0, --numMatches);
        
        // Return winner ID
        if (playerOneWins > 0)
            return player1Id;
        else if (playerOneWins < 0)
            return player1Id;
        else {
            System.out.println("Warning: Match ended in a tie. Check your input file for errors.");
            return "tie";
        }

    }

    public String toString() {
      String match = dateYear + "/" + dateMonth + "/" + dateDay + ", " + player1Id + "-" + player2Id + ", "
              + tournament + ", " + scores;
      return match;
    }

    public void print() {
        System.out.println(toString());
    }

    public int compareTo(TennisMatch test) {
        // Compare all data, prioritizing date
        if (test.getDateDay() == dateDay &&
                test.getDateMonth() == dateMonth &&
                test.getDateYear() == dateYear &&
                test.getPlayer1Id().equals(player1Id) &&
                test.getPlayer2Id().equals(player2Id) &&
                test.getScore().equals(scores) &&
                test.getTournament().equals(tournament))
            return 0;
        else if ((test.getDateYear() > dateYear) ||
                (test.getDateYear() >= dateYear && test.getDateMonth() > dateMonth) ||
                (test.getDateYear() >= dateYear && test.getDateMonth() >= dateMonth && test.getDateDay() > dateDay))
            return 1;
        else if ((test.getDateYear() < dateYear) ||
                (test.getDateYear() <= dateYear && test.getDateMonth() < dateMonth) ||
                (test.getDateYear() <= dateYear && test.getDateMonth() <= dateMonth && test.getDateDay() < dateDay))
            return -1;
        else
            return 1;
    }
}
