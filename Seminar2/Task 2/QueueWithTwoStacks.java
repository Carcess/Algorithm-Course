// import the Stack class
import java.util.Stack;

// Class to implement a queue using two stacks
class QueueWithTwoStacks<E> {
    private Stack<E> inbox = new Stack<>();
    private Stack<E> outbox = new Stack<>();

    // Method to return the total size of the queue
    public int size() {
        return inbox.size() + outbox.size();
    }

    // Static final variable to set a limit for the queue size
    private static final int MAX_SIZE = 100; // Example size limit

    // Method to add an item to the queue
    public void enqueue(E item) {
        // If outbox is empty, transfer all items from inbox to outbox
        if (size() >= MAX_SIZE) {
            System.out.println("Warning: Queue overflow!");
            // Add the item to the inbox stack
        } else {
            inbox.push(item);
        }
    }
    
    // Method to remove an item from the queue
    public E dequeue() {
        // If outbox is empty, transfer all items from inbox to outbox
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) {
                outbox.push(inbox.pop());
            }
        }
        // Check if the queue is empty
        if (outbox.isEmpty()) {
            System.out.println("Warning: Queue underflow!");
            return null; // Or throw an exception
        }
        // Remove and return the top element from the outbox stack
        return outbox.pop();
    }

    // Main method - entry point of the application
    public static void main(String[] args) {
        QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<>();

        // Enqueue up to the max size
        for (int i = 1; i <= MAX_SIZE; i++) {
            queue.enqueue(i);
        }

        // Attempt to enqueue one more item to trigger overflow warning
        //queue.enqueue(MAX_SIZE + 1); // This should trigger overflow warning

        // Dequeue all items
        for (int i = 1; i <= MAX_SIZE; i++) {
            System.out.println(queue.dequeue());
        }

        // Uncomment the line below to trigger the underflow warning
        //System.out.println(queue.dequeue()); // Should print "Warning: Queue underflow!"
    }
}
