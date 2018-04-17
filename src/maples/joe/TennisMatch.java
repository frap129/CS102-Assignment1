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

    private int evalScore(String score) {
        int playerOneScore = Integer.parseInt(score.substring(0, 0));
        int playerTwoScore = Integer.parseInt(score.substring(2, 2));
        return playerOneScore > playerTwoScore ? 1 : 0;
    }

    private int parseWins(String[] scoreArray, int first, int last){
        if (last > first)
            return -1;

        if (first == last)
            return evalScore(scoreArray[first]);

        return evalScore(scoreArray[first]) + parseWins(scoreArray, ++first, last);
    }

    public String getWinner() {
        String[] scoreArray = scores.split(",");
        int numMatches = scoreArray.length;
        int playerOneWins = parseWins(scoreArray, 0, numMatches);
        if (playerOneWins > (numMatches / 2))
            return playerOne;
        else
            return playerTwo;
    }
}
