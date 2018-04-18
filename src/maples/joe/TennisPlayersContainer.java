package maples.joe;

public class TennisPlayersContainer {
    private TennisPlayersNode head;
    private TennisPlayersNode tail ;
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
        TennisPlayersNode newTennisPlayersNode = new TennisPlayersNode(player, null, null);

        // If the list is empty, set to head and link circularly
        // else move to HEAD
        if (head == null)
        {
            newTennisPlayersNode.setNext(newTennisPlayersNode);
            newTennisPlayersNode.setPrev(newTennisPlayersNode);
            head = newTennisPlayersNode;
            tail = head;
        } else {
            newTennisPlayersNode.setPrev(tail);
            tail.setNext(newTennisPlayersNode);
            head.setPrev(newTennisPlayersNode);
            newTennisPlayersNode.setNext(head);
            head = newTennisPlayersNode;
        }

        // Update size to reflect new addition
        size++ ;
    }

    // Insert a new item at tail
    public void append(TennisPlayer player)
    {
        // Create a new node with the given data
        TennisPlayersNode newTennisPlayersNode = new TennisPlayersNode(player, null, null);

        // If the list is empty, set to head and link circularly
        // else move to tail
        if (head == null)
        {
            newTennisPlayersNode.setNext(newTennisPlayersNode);
            newTennisPlayersNode.setPrev(newTennisPlayersNode);
            head = newTennisPlayersNode;
            tail = head;
        } else {
            newTennisPlayersNode.setPrev(tail);
            tail.setNext(newTennisPlayersNode);
            head.setPrev(newTennisPlayersNode);
            newTennisPlayersNode.setNext(head);
            tail = newTennisPlayersNode;
        }

        // Update size to reflect new addition
        size++;
    }

    // Insert item at the given position
    public void insert(TennisPlayer player, int pos)
    {
        // Check if pos is impossible, throw an exception
        if (pos > ++size)
            throw new RuntimeException("Position out of range");

        // Create a new nude with the given data
        TennisPlayersNode newTennisPlayersNode = new TennisPlayersNode(player, null, null);

        // If position is 1, prepend value
        // else, check if pos is new tail
        if (pos == 1)
        {
            prepend(player);
            return;
        } else if (pos == ++size) {
            append(player);
            return;
        }

        // Iterate to position starting at head
        TennisPlayersNode node = head;
        for (int i = 2; i <= size; i++)
        {
            // Once we find the correct position, insert the node
            if (i == pos)
            {
                TennisPlayersNode tmp = node.getNext();
                node.setNext(newTennisPlayersNode);
                newTennisPlayersNode.setPrev(node);
                newTennisPlayersNode.setNext(tmp);
                tmp.setPrev(newTennisPlayersNode);
            }

            node = node.getNext();
        }

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
        TennisPlayersNode node = head.getNext();
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                TennisPlayersNode prev = node.getPrev();
                TennisPlayersNode next = node.getNext();
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

    // Return the player contained by a TennisPlayersNode
    public TennisPlayer getPlayer(int pos) {
        // Check if pos is impossible, throw an exception
        if (pos > ++size)
            throw new RuntimeException("Position out of range");

        // Start at HEAD, iterate to requested position
        TennisPlayersNode node = head;
        if (pos > size)
            return null;
        for (int i = 1; i <= size; i++)
        {
            if (i == pos)
                return node.getPlayer();

            node = node.getNext();
        }
        return null;
    }

    // Return the match list contained by a TennisPlayersNode
    public TennisMatchesList getMatches(int pos) {
        // Check if pos is impossible, throw an exception
        if (pos > ++size)
            throw new RuntimeException("Position out of range");

        // Start at HEAD, iterate to requested position
        TennisPlayersNode node = head;
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

    public void insertMatch(TennisMatch match, int pos) {
        // Check if pos is impossible, throw an exception
        if (pos > ++size)
            throw new RuntimeException("Position out of range");

        // Start at HEAD, iterate to requested position
        TennisPlayersNode node = head;
        if (pos > size)
            return;

        for (int i = 1; i <= size; i++)
        {
            if (i == pos)
                node.getMatches().append(match);

            node = node.getNext();
        }
        return;
    }

    // Format the list as a string
    public String toString()
    {
        TennisPlayersNode node = head;

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
}
