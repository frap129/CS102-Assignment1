package maples.joe;

public class TennisPlayer implements TennisPlayerInterface {
    private String Id, firstName, lastName, country;
    private int birthYear;

    public TennisPlayer(String Id, String firstName, String lastName, int birthYear, String country) {
        this.Id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.country = getCountry();
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
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

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWinLossRecord() {
        // needs other classes to be finished first
    }

    public String toString(){
        // Add code here
    }

    public void print() {
        System.out.print(toString());
    }

    public int compareTo(TennisPlayer player) {
        return Id.compareToIgnoreCase(player.getId());
    }
}
