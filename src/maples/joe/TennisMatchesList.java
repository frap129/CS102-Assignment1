package maples.joe;

public class TennisMatchesList {
    private TennisMatchesNode head;
    private TennisMatchesNode tail ;
    private int size;

    // Constructor for the list, requires no args
    public TennisMatchesList()
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
    public void prepend(TennisMatch match)
    {
        // Create a new nude with the given data
        TennisMatchesNode newTennisMatchesNode = new TennisMatchesNode(match, null, null);

        // If the list is empty, set to head and link circularly
        // else move to HEAD
        if (head == null)
        {
            newTennisMatchesNode.setNext(newTennisMatchesNode);
            newTennisMatchesNode.setPrev(newTennisMatchesNode);
            head = newTennisMatchesNode;
            tail = head;
        } else {
            newTennisMatchesNode.setPrev(tail);
            tail.setNext(newTennisMatchesNode);
            head.setPrev(newTennisMatchesNode);
            newTennisMatchesNode.setNext(head);
            head = newTennisMatchesNode;
        }

        // Update size to reflect new addition
        size++ ;
    }

    // Insert a new item at tail
    public void append(TennisMatch match)
    {
        // Create a new node with the given data
        TennisMatchesNode newTennisMatchesNode = new TennisMatchesNode(match, null, null);

        // If the list is empty, set to head and link circularly
        // else move to tail
        if (head == null)
        {
            newTennisMatchesNode.setNext(newTennisMatchesNode);
            newTennisMatchesNode.setPrev(newTennisMatchesNode);
            head = newTennisMatchesNode;
            tail = head;
        } else {
            newTennisMatchesNode.setPrev(tail);
            tail.setNext(newTennisMatchesNode);
            head.setPrev(newTennisMatchesNode);
            newTennisMatchesNode.setNext(head);
            tail = newTennisMatchesNode;
        }

        // Update size to reflect new addition
        size++;
    }

    // Insert item at the given position
    public void insert(TennisMatch match, int pos)
    {
        // Check if pos is impossible, throw an exception
        if (pos > ++size)
            throw new RuntimeException("Position out of range");

        // Create a new nude with the given data
        TennisMatchesNode newTennisMatchesNode = new TennisMatchesNode(match, null, null);

        // If position is 1, prepend value
        // else, check if pos is new tail
        if (pos == 1)
        {
            prepend(match);
            return;
        } else if (pos == ++size) {
            append(match);
            return;
        }

        // Iterate to position starting at head
        TennisMatchesNode node = head;
        for (int i = 2; i <= size; i++)
        {
            // Once we find the correct position, insert the node
            if (i == pos)
            {
                TennisMatchesNode tmp = node.getNext();
                node.setNext(newTennisMatchesNode);
                newTennisMatchesNode.setPrev(node);
                newTennisMatchesNode.setNext(tmp);
                tmp.setPrev(newTennisMatchesNode);
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
        TennisMatchesNode node = head.getNext();
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                TennisMatchesNode prev = node.getPrev();
                TennisMatchesNode next = node.getNext();
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

    // Return the data contained by a TennisMatchesNode
    public TennisMatch get(int pos) {
        // Check if pos is impossible, throw an exception
        if (pos > ++size)
            throw new RuntimeException("Position out of range");

        // Start at HEAD, iterate to requested position
        TennisMatchesNode node = head;
        if (pos > size)
            return null;
        for (int i = 1; i <= size; i++)
        {
            if (i == pos)
                return node.getMatch();

            node = node.getNext();
        }
        return null;
    }

    // Format the list as a string
    public String toString()
    {
        TennisMatchesNode node = head;

        // Check if empty first
        if (size == 0)
            return "empty\n";

        String linkedList = head.getMatch() + "\n";
        node = head.getNext();

        // Iterate over all matches
        while (node.getNext() != head)
        {
            linkedList += node.getMatch() + "\n";
            node = node.getNext();
        }

        return linkedList + "\n" + node.getNext() + "\n";
    }
}
