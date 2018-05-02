package maples.joe;

public class TennisMatchesList implements TennisMatchesListInterface {
    private TennisMatch head;
    private TennisMatch tail ;
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
        // If the list is empty, set to head and link circularly
        // else move to HEAD
        if (head == null)
        {
            match.setNext(match);
            match.setPrev(match);
            head = match;
            tail = head;
        } else {
            match.setPrev(tail);
            tail.setNext(match);
            head.setPrev(match);
            match.setNext(head);
            head = match;
        }

        // Update size to reflect new addition
        size++ ;
    }

    // Insert a new item at tail
    public void append(TennisMatch match)
    {
        // If the list is empty, set to head and link circularly
        // else move to tail
        if (head == null)
        {
            match.setNext(match);
            match.setPrev(match);
            head = match;
            tail = head;
        } else {
            match.setPrev(tail);
            tail.setNext(match);
            head.setPrev(match);
            match.setNext(head);
            tail = match;
        }

        // Update size to reflect new addition
        size++;
    }

    // Insert item at the given position
    public void insertMatch(TennisMatch match)
    {
        // If position is 1, prepend value
        // else, check if pos is new tail
        if (head.compareTo(match) == 1)
        {
            prepend(match);
            return;
        } else if (tail.compareTo(match) == -1) {
            append(match);
            return;
        }

        // Iterate to position starting at head
        TennisMatch node = head;
        do {
            node = node.getNext();
        }
        while (node.compareTo(match) == -1);

        TennisMatch tmp = node.getNext();
        node.setNext(match);
        match.setPrev(node);
        match.setNext(tmp);
        tmp.setPrev(match);

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
        TennisMatch node = head.getNext();
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                TennisMatch prev = node.getPrev();
                TennisMatch next = node.getNext();
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

    // Return the data contained by a TennisMatch
    public TennisMatch get(int pos) {
        // Check if pos is impossible, throw an exception
        if (pos > ++size)
            throw new RuntimeException("Position out of range");

        // Start at HEAD, iterate to requested position
        TennisMatch node = head;
        if (pos > size)
            return null;
        for (int i = 1; i <= size; i++)
        {
            if (i == pos)
                return node;

            node = node.getNext();
        }
        return null;
    }

    // Format the list as a string
    public void printMatches()
    {
        TennisMatch node;

        // Check if empty first
        if (size == 0)
            System.out.print("empty\n");

        String linkedList = head.toString() + "\n";
        node = head.getNext();

        // Iterate over all matches
        while (node.getNext() != head)
        {
            linkedList += node.toString() + "\n";
            node = node.getNext();
        }

        System.out.print(linkedList + "\n" + node.getNext().toString() + "\n");
    }
}
