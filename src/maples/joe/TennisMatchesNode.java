package maples.joe;

public class TennisMatchesNode {
    private TennisMatch match;
    private TennisMatchesNode next, prev;

    public TennisMatchesNode(TennisMatch match, TennisMatchesNode next, TennisMatchesNode prev) {
        this.match = match;
        this.next = next;
        this.prev = prev;
    }

    public TennisMatch getMatch() {
        return match;
    }

    public void setMatch(TennisMatch match) {
        this.match = match;
    }

    public TennisMatchesNode getNext() {
        return next;
    }

    public void setNext(TennisMatchesNode next) {
        this.next = next;
    }

    public TennisMatchesNode getPrev() {
        return prev;
    }

    public void setPrev(TennisMatchesNode prev) {
        this.prev = prev;
    }
}