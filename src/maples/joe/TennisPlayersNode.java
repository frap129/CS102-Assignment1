package maples.joe;

public class TennisPlayersNode {
    private TennisPlayer player;
    private TennisPlayersNode next, prev;
    private TennisMatchesList matches;

    public TennisPlayersNode(TennisPlayer player, TennisMatchesList matches, TennisPlayersNode next, TennisPlayersNode prev) {
        this.player = player;
        this.matches = matches;
        this.next = next;
        this.prev = prev;
    }

    public TennisPlayersNode(TennisPlayer player, TennisPlayersNode next, TennisPlayersNode prev) {
        this.player = player;
        this.matches = null;
        this.next = next;
        this.prev = prev;
    }

    public TennisPlayersNode(TennisMatchesList matches, TennisPlayersNode next, TennisPlayersNode prev) {
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

    public TennisPlayersNode getNext() {
        return next;
    }

    public void setNext(TennisPlayersNode next) {
        this.next = next;
    }

    public TennisPlayersNode getPrev() {
        return prev;
    }

    public void setPrev(TennisPlayersNode prev) {
        this.prev = prev;
    }

    public TennisMatchesList getMatches() {
        return matches;
    }

    public void setMatches(TennisMatchesList matches) {
        this.matches = matches;
    }
}