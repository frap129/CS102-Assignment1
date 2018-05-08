/*
 * Author: Joseph Maples
 * CS102 Assignment 1
 */
package maples.joe;

public class TennisPlayerNode implements TennisPlayerNodeInterface {
    private TennisPlayer player;
    private TennisPlayerNode next, prev;
    private TennisMatchesList matches;

    public TennisPlayerNode(TennisPlayer player, TennisMatchesList matches, TennisPlayerNode next, TennisPlayerNode prev) {
        this.player = player;
        this.matches = matches;
        this.next = next;
        this.prev = prev;
    }

    public TennisPlayerNode(TennisPlayer player, TennisPlayerNode next, TennisPlayerNode prev) {
        this.player = player;
        this.matches = new TennisMatchesList();
        this.next = next;
        this.prev = prev;
    }

    public TennisPlayerNode(TennisMatchesList matches, TennisPlayerNode next, TennisPlayerNode prev) {
        this.player = null;
        this.matches = matches;
        this.next = next;
        this.prev = prev;
    }

    public TennisPlayer getPlayer() {
        return player;
    }

    public void setPlayer(TennisPlayer player) {
        this.player = player;
    }

    public TennisPlayerNode getNext() {
        return next;
    }

    public void setNext(TennisPlayerNode next) {
        this.next = next;
    }

    public TennisPlayerNode getPrev() {
        return prev;
    }

    public void setPrev(TennisPlayerNode prev) {
        this.prev = prev;
    }

    public TennisMatchesList getMatches() {
        return matches;
    }

    public void setMatches(TennisMatchesList matches) {
        this.matches = matches;
    }

    public void insertMatch(TennisMatch match) {
        matches.insertMatch(match);
    }
    
    public String getWinLoss() {
        return matches.getWinLoss(player.getId());
    }

    public void printMatches(){
        if (matches != null)
        matches.printMatches();
    }
    
    public void getWinLoss(String playerId) {
        matches.getWinLoss(playerId);
    }
}