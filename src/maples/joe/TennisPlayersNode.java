package maples.joe;

public class TennisPlayersNode {
  private TennisPlayer player;
  private TennisPlayersNode next, prev;

    public TennisPlayersNode(TennisPlayer player, TennisPlayersNode next, TennisPlayersNode prev) {
        this.player = player;
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
}