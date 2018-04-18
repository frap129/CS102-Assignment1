package maples.joe;

public class TennisMatch {
    private int date;
    private String playerOne;
    private String playerTwo;
    private String location;
    private String scores;

    public TennisMatch(int date, String playerOne, String playerTwo, String location, String scores) {
        this.date = date;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.location = location;
        this.scores = scores;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public static boolean isInteger(String string) {
        boolean isValidInteger = false;
        try {
            Integer.parseInt(string);
            isValidInteger = true;
        } catch (NumberFormatException ex) {
            // Not an integer
        }

        return isValidInteger;
    }

    private int evalScore(String setScore, int first, int last) {
        if (last < first)
            return Integer.MAX_VALUE;

        if (isInteger(setScore)) {
            if (first == last)
                return Integer.parseInt(setScore);

            int score = Integer.parseInt(setScore);
            return score - evalScore(setScore, first + String.valueOf(score).length(), last) > 0 ? 1 : -1;
        }

        return evalScore(setScore, ++first, last);
    }

    private int parseWins(String[] scoreArray, int first, int last){
        if (last < first)
            return Integer.MAX_VALUE;

        if (first == last)
            return evalScore(scoreArray[first], 0, 2);

        return evalScore(scoreArray[first], 0, 2) + parseWins(scoreArray, ++first, last);
    }

    public String getWinner() {
        String[] scoreArray = scores.split(",");
        int numMatches = scoreArray.length;
        int playerOneWins = parseWins(scoreArray, 0, numMatches);
        if (playerOneWins > 0)
            return playerOne;
        else if (playerOneWins < 0)
            return playerTwo;
        else {
            System.out.println("Warning: Match ended in a tie. Check your input file for errors.");
            return "tie";
        }

    }
}
