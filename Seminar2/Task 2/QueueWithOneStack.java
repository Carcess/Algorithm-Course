// Import the Stack class
import java.util.Stack;

// Class to implement a queue using a single stack
class QueueWithOneStack<E> {
    private Stack<E> stack = new Stack<>();

    // Static final variable to set a limit for the queue size
    private static final int MAX_SIZE = 100; // Example size limit

    // Method to add an item to the queue
    public void enqueue(E item) {
        // Check if adding this item will exceed the queue size
        if (size() >= MAX_SIZE) {
            System.out.println("Warning: Queue overflow!");
        } else {
            // Add the item to the stack
            stack.push(item);
        }
    }

    // Method to return the current size of the queue
    public int size() {
        return stack.size();
    }

    // Method to remove an item from the queue
    public E dequeue() {
        // Check if the stack is empty
        if (stack.isEmpty()) {
            System.out.println("Warning: Queue underflow!");
            return null;
        }
        // Pop the top element from the stack
        E front = stack.pop();
        // If the stack is now empty, return the popped element
        if (stack.isEmpty()) {
            return front;
        } else {
            // Recursively dequeue the next element
            E item = dequeue();
            // Push the previously popped element back onto the stack
            stack.push(front);
            // Return the dequeued item
            return item;
        }
    }

    // Main method - entry point of the application
    public static void main(String[] args) {
        QueueWithOneStack<Integer> queue = new QueueWithOneStack<>();
    
        // Enqueue items up to the max size limit
        for (int i = 1; i <= MAX_SIZE; i++) {
            queue.enqueue(i);
        }
    
        // Uncomment the next line to trigger the overflow warning
        //queue.enqueue(MAX_SIZE + 1); // This should trigger overflow warning
    
        // Dequeue all enqueued items
        for (int i = 1; i <= MAX_SIZE; i++) {
            System.out.println(queue.dequeue());
        }
    
        // Uncomment the next line to trigger the underflow warning
        //System.out.println(queue.dequeue()); // Should trigger underflow warning
    }    
}
