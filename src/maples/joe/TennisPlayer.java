package maples.joe;

public class TennisPlayer {
    private String playerID;
    private String firstName;
    private String lastName;
    private String year;
    private String location;

    public TennisPlayer(String playerID, String firstName, String lastName, String year, String location) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.location = location;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWinLossRecord() {
        // needs other classes to be finished first
    }

    public String print() {
        // needs other classes to be finished first
    }
}
