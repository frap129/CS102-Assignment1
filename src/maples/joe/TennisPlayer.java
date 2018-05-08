/*
 * Author: Joseph Maples
 * CS102 Assignment 1
 */
package maples.joe;

public class TennisPlayer implements TennisPlayerInterface {
    private String id, firstName, lastName, country;
    private int birthYear;

    public TennisPlayer(String id, String firstName, String lastName, int birthYear, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String toString(){
        String player = id + ": " + firstName + " " + lastName + ", " + birthYear;
        return player;
    }

    public void print() {
        System.out.print(toString());
    }

    public int compareTo(TennisPlayer player) {
        String player2 = player.getId();
        
        // Simply filter results from String's compareToIgnoreCase
        if ((this.id.compareToIgnoreCase(player2)) > 0) // ID comes after player
            return 1;
        else if ((this.id.compareToIgnoreCase(player2)) == 0) //ID == player
            return 0;
        else if ((this.id.compareToIgnoreCase(player2)) < 0 )// ID come before player
            return -1;
        return 1;
    }
}
