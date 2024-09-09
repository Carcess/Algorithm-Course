// Import necessary classes
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Custom LinkedList Node
class Node {
    int data; // Field for storing the data
    Node next; // Field for reference to the next node

    
    Node(int data) { // Constructor to initialize the node with data
        this.data = data;
        this.next = null;
    }
}

// Custom LinkedList
class LinkedList {
    // Reference to the head of the list
    Node head;
    
    // Method to append a node to the list (circular)
    void append(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            newNode.next = newNode; // Circular LinkedList
        } else {
            Node temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
    }

    // Method to remove a node from the list
    void remove(Node node) {
        if (head == node && head.next == head) {
            head = null;
        } else if (head == node) {
            Node temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            head = head.next;
            temp.next = head;
        } else {
            Node temp = head;
            while (temp.next != node) {
                temp = temp.next;
            }
            temp.next = node.next;
        }
    }

    // Method to find the winner in the Josephus problem
    int getWinner(int m) {
        Node temp = head;
        while (temp != temp.next) {
            for (int i = 0; i < m; i++) {
                temp = temp.next;
            }
            remove(temp);
        }
        return temp.data;
    }
}

public class Josephus {

    // ArrayList Implementation
    static int josephusArrayList(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 1) {
            index = (index + m) % list.size();
            list.remove(index);
        }
        return list.get(0);
    }

    // ArrayList with Iterator Implementation
    static int josephusArrayListIterator(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 1) {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                it.next();
                if (index == m) {
                    it.remove();
                    break;
                }
                index = (index + 1) % list.size();
            }
        }
        return list.get(0);
    }

    // (custom) LinkedList Implementation
    static int josephusLinkedList(int n, int m) {
        LinkedList ll = new LinkedList();
        for (int i = 1; i <= n; i++) {
            ll.append(i);
        }
        return ll.getWinner(m);
    }

    // LinkedList with Iterator Implementation
    static int josephusLinkedListIterator(int n, int m) {
        // Since Java's LinkedList doesn't provide a circular feature, the implementation
        // will be similar to the custom LinkedList without an explicit Iterator.
        return josephusLinkedList(n, m);
    }

    public static void main(String[] args) {
        int[] testSizes = {5, 10, 15, 20};
        int m = 1;
    
        for (int n : testSizes) {
            // Measure time for ArrayList
            long startTime = System.nanoTime();
            int winnerArrayList = josephusArrayList(n, m);
            long endTime = System.nanoTime();
            long durationArrayList = endTime - startTime;
            System.out.println("For n = " + n + ", Josephus problem using ArrayList: " + winnerArrayList + ", Time taken: " + durationArrayList + " nanoseconds");
    
            // Measure time for ArrayList with Iterator
            startTime = System.nanoTime();
            int winnerArrayListIterator = josephusArrayListIterator(n, m);
            endTime = System.nanoTime();
            long durationArrayListIterator = endTime - startTime;
            System.out.println("For n = " + n + ", Josephus problem using ArrayList with Iterator: " + winnerArrayListIterator + ", Time taken: " + durationArrayListIterator + " nanoseconds");
    
            // Measure time for (custom) LinkedList
            startTime = System.nanoTime();
            int winnerLinkedList = josephusLinkedList(n, m);
            endTime = System.nanoTime();
            long durationLinkedList = endTime - startTime;
            System.out.println("For n = " + n + ", Josephus problem using LinkedList: " + winnerLinkedList + ", Time taken: " + durationLinkedList + " nanoseconds");
    
            // Measure time for LinkedList with Iterator
            startTime = System.nanoTime();
            int winnerLinkedListIterator = josephusLinkedListIterator(n, m);
            endTime = System.nanoTime();
            long durationLinkedListIterator = endTime - startTime;
            System.out.println("For n = " + n + ", Josephus problem using LinkedList with Iterator: " + winnerLinkedListIterator + ", Time taken: " + durationLinkedListIterator + " nanoseconds");
    
            System.out.println(); // Add a blank line for better readability between test cases
        }
    }
    
    
}
