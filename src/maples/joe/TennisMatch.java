package maples.joe;

public class TennisMatch implements TennisMatchInterface {
    private int dateYear, dateMonth, dateDay;
    private String player1Id, player2Id, tournament, scores, winner;
    private TennisMatch next, prev;

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

    public TennisMatch getNext() {
        return next;
    }

    public void setNext(TennisMatch next) {
        this.next = next;
    }

    public TennisMatch getPrev() {
        return prev;
    }

    public void setPrev(TennisMatch prev) {
        this.prev = prev;
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

    private int evalSets(String setScore, int first, int last) {
        if (last < first)
            return Integer.MAX_VALUE;

        if (isInteger(setScore)) {
            if (first == last)
                return Integer.parseInt(setScore);

            int score = Integer.parseInt(setScore);
            return score - evalSets(setScore, first + String.valueOf(score).length(), last) > 0 ? 1 : -1;
        }

        return evalSets(setScore, ++first, last);
    }

    private int parseGame(String[] scoreArray, int first, int last){
        if (last < first)
            return Integer.MAX_VALUE;

        if (first == last)
            return evalSets(scoreArray[first], 0, 2);

        return evalSets(scoreArray[first], 0, 2) + parseGame(scoreArray, ++first, last);
    }

    public String determineWinner() {
        String[] scoreArray = scores.split(",");
        int numMatches = scoreArray.length;
        int playerOneWins = parseGame(scoreArray, 0, numMatches);
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
      // Add code here
    }

    public void print() {
        System.out.print(toString());
    }

    public int compareTo(TennisMatch test) {
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
