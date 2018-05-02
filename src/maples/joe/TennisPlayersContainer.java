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

        // If the list is empty, set to head and link circularly
        // else move to HEAD
        if (head == null)
        {
            newTennisPlayerNode.setNext(newTennisPlayerNode);
            newTennisPlayerNode.setPrev(newTennisPlayerNode);
            head = newTennisPlayerNode;
            tail = head;
        } else {
            newTennisPlayerNode.setPrev(tail);
            tail.setNext(newTennisPlayerNode);
            head.setPrev(newTennisPlayerNode);
            newTennisPlayerNode.setNext(head);
            head = newTennisPlayerNode;
        }

        // Update size to reflect new addition
        size++ ;
    }

    // Insert a new item at tail
    public void append(TennisPlayer player)
    {
        // Create a new node with the given data
        TennisPlayerNode newTennisPlayerNode = new TennisPlayerNode(player, null, null);

        // If the list is empty, set to head and link circularly
        // else move to tail
        if (head == null)
        {
            newTennisPlayerNode.setNext(newTennisPlayerNode);
            newTennisPlayerNode.setPrev(newTennisPlayerNode);
            head = newTennisPlayerNode;
            tail = head;
        } else {
            newTennisPlayerNode.setPrev(tail);
            tail.setNext(newTennisPlayerNode);
            head.setPrev(newTennisPlayerNode);
            newTennisPlayerNode.setNext(head);
            tail = newTennisPlayerNode;
        }

        // Update size to reflect new addition
        size++;
    }

    // Insert item at the given position
    public void insertPlayer(TennisPlayer player)
    {
        // Create a new node with the given data
        TennisPlayerNode newTennisPlayerNode = new TennisPlayerNode(player, null, null);

        // If position is 1, prepend value
        // else, check if pos is new tail
        if (head.getPlayer().compareTo(player) > 0)
        {
            prepend(player);
            return;
        } else if (tail.getPlayer().compareTo(player) < 0) {
            append(player);
            return;
        }

        // Iterate to position starting at head
        TennisPlayerNode node = head;
        do {
            node = node.getNext();
        }
        while (node.getPlayer().compareTo(player) < 0);

        TennisPlayerNode tmp = node.getNext();
        node.setNext(newTennisPlayerNode);
        newTennisPlayerNode.setPrev(node);
        newTennisPlayerNode.setNext(tmp);
        tmp.setPrev(newTennisPlayerNode);

        // Update size to reflect new addition
        size++ ;
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
        for (int i = 1; i <= size; i++)
        {
            if (id.equals(node.getPlayer().getId()))
                return node;

            node = node.getNext();
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

    public void insertMatch(TennisMatch match) {
        // I'll do this later I promise
    }

    // Format the list as a string
    public String toString()
    {
        TennisPlayerNode node = head;

        // Check if empty first
        if (size == 0)
            return "empty\n";

        String linkedList = head.getPlayer() + "\n";
        node = head.getNext();

        // Iterate over all players
        while (node.getNext() != head)
        {
            linkedList += node.getPlayer() + "\n";
            node = node.getNext();
        }

        return linkedList + "\n" + node.getNext() + "\n";
    }

    public void printAllPlayers(){
        System.out.print(toString());
    }

    public void printMatchesOfPlayer(String id) {
        getPlayer(id).printMatches();
    }
}
