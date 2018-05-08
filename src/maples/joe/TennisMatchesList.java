/*
 * Author: Joseph Maples
 * CS102 Assignment 1
 */
package maples.joe;

public class TennisMatchesList implements TennisMatchesListInterface {
    private TennisMatchNode head;
    private TennisMatchNode tail ;
    private int size;
    private String playerId;

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
    public void prepend(TennisMatchNode match)
    {
        match.setPrev(tail);
        tail.setNext(match);
        head.setPrev(match);
        match.setNext(head);
        head = match;
            
        // Update size to reflect new addition
        size++ ;
    }

    // Insert a new item at tail
    public void append(TennisMatchNode match)
    {
        match.setPrev(tail);
        tail.setNext(match);
        head.setPrev(match);
        match.setNext(head);
        tail = match;

        // Update size to reflect new addition
        size++;
    }

    // Insert item at the given position
    public void insertMatch(TennisMatch matchData)
    {
        TennisMatchNode match = new TennisMatchNode(matchData, null, null);
        
        if (head == null) {
            head = match;
            head.setNext(match);
            head.setPrev(match);
            tail = head;
            return;
        }
        // If position is 1, prepend value
        // else, check if pos is new tail
        if (head.compareTo(match) == -1)
        {
            prepend(match);
            return;
        } else if (tail.compareTo(match) == 1) {
            append(match);
            return;
        }

        // Iterate to position starting at head
        TennisMatchNode node = head;
        while (node.compareTo(match) == -1) {
            node = node.getNext();
        }

        TennisMatchNode tmp = node.getNext();
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
        TennisMatchNode node = head.getNext();
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                TennisMatchNode prev = node.getPrev();
                TennisMatchNode next = node.getNext();
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
    public TennisMatchNode get(int pos) {
        // Check if pos is impossible, throw an exception
        if (pos > ++size)
            throw new RuntimeException("Position out of range");

        // Start at HEAD, iterate to requested position
        TennisMatchNode node = head;
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

    public String getWinLoss(String playerId) {
        int win = 0;
        int loss = 0;
        if (head == null)
            return "(0/0)";
        TennisMatchNode node = head;
        if (node.getMatch().getWinner().equals(playerId))
            win++;
        else
            loss++;
            
        while (node.getNext() != head)
        {
            node = node.getNext();
            if (node.getMatch().getWinner().equals(playerId))
                win++;
            else
                loss++;
        }
        return "(" + win + "/" + loss + ")";
    }

    // Format the list as a string
    public void printMatches()
    {
        TennisMatchNode node = head;

        // Check if empty first
        if (size == 0 || head == null) {
            System.out.print("empty\n");
            return;
        }
        String linkedList = node.getMatch().toString() + "\n";

        // Iterate over all matches
        while (node.getNext() != head)
        {
            node = node.getNext();
            linkedList += node.getMatch().toString() + "\n";
        }

        System.out.print(linkedList + "\n");
    }
}
