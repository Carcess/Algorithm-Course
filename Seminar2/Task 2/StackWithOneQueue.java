// Import necessary classes for LinkedList and Queue
import java.util.LinkedList;
import java.util.Queue;

// Class to implement a stack using a single queue
class StackWithOneQueue<E> {
    private Queue<E> queue = new LinkedList<>();

    // Method to return the current size of the stack
    public int size() {
        return queue.size();
    }
    
    // Static final variable to set a limit for the stack size
    private static final int MAX_SIZE = 100; // Example size limit
    
    // Method to push an item onto the stack
    public void push(E item) {
         // Check if adding this item will exceed the stack size
        if (size() >= MAX_SIZE) {
            System.out.println("Warning: Stack overflow!");
            return;
        }
        // Add the item to the queue
        int size = queue.size();
        queue.offer(item);
        // Rotate the queue to place the new item at the front
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
    }
    
    // Method to pop an item from the stack
    public E pop() {
        // Check if the stack is empty
        if (queue.isEmpty()) {
            System.out.println("Warning: Stack underflow!");
            return null; // Or throw an exception
        }
        return queue.remove();
    }
   
    // Main method - entry point of the application
    public static void main(String[] args) {
        StackWithOneQueue<Integer> stack = new StackWithOneQueue<>();

        // Enqueue items up to the max size limit
        for (int i = 1; i <= MAX_SIZE; i++) {
            stack.push(i);
        }

        // Uncomment the next line to trigger the overflow warning
        //stack.push(MAX_SIZE + 1); // This should trigger overflow warning

        // Pop all items from the stack
        for (int i = 1; i <= MAX_SIZE; i++) {
            System.out.println(stack.pop());
        }

        // Uncomment the next line to trigger the underflow warning
        //System.out.println(stack.pop()); // Should trigger underflow warning
    }        
}
