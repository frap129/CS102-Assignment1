package maples.joe;

public class TennisMatchNode {
    private TennisMatch match;
    private TennisMatchNode next, prev;

    public TennisMatchNode(TennisMatch match, TennisMatchNode prev, TennisMatchNode next) {
        this.match = match;
        this.prev = prev;
        this.next = next;
    }

    public TennisMatch getMatch() {
        return match;
    }

    public void setMatch(TennisMatch match) {
        this.match = match;
    }

    public TennisMatchNode getNext() {
        return next;
    }

    public void setNext(TennisMatchNode next) {
        this.next = next;
    }

    public TennisMatchNode getPrev() {
        return prev;
    }

    public void setPrev(TennisMatchNode prev) {
        this.prev = prev;
    }

    public int compareTo(TennisMatchNode match) {
        return this.match.compareTo(match.match);
    }
}
