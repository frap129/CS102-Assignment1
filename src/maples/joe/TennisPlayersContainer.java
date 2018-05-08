package maples.joe;

public class TennisPlayersContainer implements TennisPlayersContainerInterface{
    private TennisPlayerNode head;
    private TennisPlayerNode tail ;
    private int size;

    // Constructor for the list, requires no args
    public TennisPlayersContainer()
    {
        head = null;
        tail = null;
        size = 0;
    }

    // Return if the list is empty or not
    public boolean isEmpty()
    {
        return head == null;
    }

    // Return size of the list
    public int getSize()
    {
        return size;
    }

    // Insert a new item at HEAD
    public void prepend(TennisPlayer player)
    {
        // Create a new nude with the given data
        TennisPlayerNode newTennisPlayerNode = new TennisPlayerNode(player, null, null);

        newTennisPlayerNode.setPrev(tail);
        tail.setNext(newTennisPlayerNode);
        head.setPrev(newTennisPlayerNode);
        newTennisPlayerNode.setNext(head);
        head = newTennisPlayerNode;

        // Update size to reflect new addition
        size++ ;
    }

    // Insert a new item at tail
    public void append(TennisPlayer player)
    {
        // Create a new node with the given data
        TennisPlayerNode newTennisPlayerNode = new TennisPlayerNode(player, null, null);

        newTennisPlayerNode.setPrev(tail);
        tail.setNext(newTennisPlayerNode);
        head.setPrev(newTennisPlayerNode);
        newTennisPlayerNode.setNext(head);
        tail = newTennisPlayerNode;

        // Update size to reflect new addition
        size++;
    }

    // Insert item at the given position
    public void insertPlayer(TennisPlayer player)
    {
        TennisPlayerNode newNode = new TennisPlayerNode(player, null, null);
        if (this.size == 0) {
            this.head = newNode;
            this.head.setPrev(this.head);
            this.head.setNext(this.head);
            this.size++;
        } else {
            TennisPlayerNode curr = this.head;
            int i = 0;
            while ((i < this.size) && (player.compareTo(curr.getPlayer()) > 0)) {
                curr = curr.getNext();
                i++;
            }
            if (player.compareTo(curr.getPlayer()) == 0){
               throw new TennisDatabaseRuntimeException("You cannot enter a duplicate player.");
            }
            else{
            if (i == 0){
                this.head = newNode;
            }
            newNode.setNext(curr);
            newNode.setPrev(curr.getPrev());
            curr.getPrev().setNext(newNode);
            curr.setPrev(newNode);
            this.size++;
            }
        }
    }

    // Remove item at the given position
    public void remove(int pos)
    {
        // Check if pos is impossible, throw an exception
        if (pos > size)
            throw new RuntimeException("Position out of range");

        // If removing HEAD, check if that's the only node
        if (pos == 1)
        {
            if (size == 1)
            {
                head = null;
                tail = null;
                size = 0;
                return;
            }

            head = head.getNext();
            head.setPrev(tail);
            tail.setNext(head);
            size--;
            return;
        }

        // If removing tail, relink from prev to HEAD and reduce size
        if (pos == size)
        {
            tail = tail.getPrev();
            tail.setNext(head);
            head.setPrev(tail);
            size--;
        }

        // Otherwise, find position and remove
        TennisPlayerNode node = head.getNext();
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                TennisPlayerNode prev = node.getPrev();
                TennisPlayerNode next = node.getNext();
                prev.setNext(next);
                next.setPrev(prev);
                size--;
                return;
            }

            node = node.getNext();
        }
    }

    // Empty the list
    public void removeAll() {
        head = null;
        tail = null;
        size = 0;
    }

    // Return the player contained by a TennisPlayerNode
    public TennisPlayerNode getPlayer(String id) {

        // Start at HEAD, iterate to requested position
        TennisPlayerNode node = head;
        if (id.toUpperCase().equals(node.getPlayer().getId().toUpperCase()))
            return node;
        
        while (node.getNext() != head)
        {
            node = node.getNext();
            
            if (id.toUpperCase().equals(node.getPlayer().getId().toUpperCase()))
                return node;
        }
        return null;
    }

    // Return the match list contained by a TennisPlayerNode
    public TennisMatchesList getMatches(int pos) {
        // Check if pos is impossible, throw an exception
        if (pos > ++size)
            throw new RuntimeException("Position out of range");

        // Start at HEAD, iterate to requested position
        TennisPlayerNode node = head;
        if (pos > size)
            return null;
        for (int i = 1; i <= size; i++)
        {
            if (i == pos)
                return node.getMatches();

            node = node.getNext();
        }
        return null;
    }

    public void insertMatch(TennisMatch match) throws NullPointerException {
        try {
            TennisPlayerNode player1 = getPlayer(match.getPlayer1Id());
            TennisPlayerNode player2 = getPlayer(match.getPlayer2Id());
            player1.getMatches().insertMatch(match);
            player2.getMatches().insertMatch(match);
        } catch(NullPointerException e) {
            System.out.println("Warning: One or more of the specified players do not exist. Discarding match.");
        }
    }

    // Format the list as a string
    public String toString()
    {
        TennisPlayerNode node = head;

        // Check if empty first
        if (size == 0)
            return "empty\n";

        String linkedList = node.getPlayer().toString() + ", " 
                    + node.getWinLoss() + "\n";

        // Iterate over all players
        while (node.getNext() != head)
        {
            node = node.getNext();
            linkedList += node.getPlayer().toString() + ", " 
                    + node.getWinLoss() + "\n";
        }

        return linkedList + "\n";
    }

    public void printAllPlayers(){
        System.out.print(toString());
    }

    public void printMatchesOfPlayer(String id) {
        TennisPlayerNode player = getPlayer(id);
        if (player != null)
            player.printMatches();
        else
            System.out.println("Error: No such player");
    }
}
