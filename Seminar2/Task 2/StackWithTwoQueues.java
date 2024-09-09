// Import necessary classes for LinkedList and Queue
import java.util.LinkedList;
import java.util.Queue;

// Class to implement a stack using two queues
class StackWithTwoQueues<E> {
    private Queue<E> queue1 = new LinkedList<>();
    private Queue<E> queue2 = new LinkedList<>();
    // Method to return the total size of the stack
    public int size() {
        // Only one queue contains all the elements at any time.
        return queue1.size() + queue2.size();
    }
    // Static final variable to set a limit for the stack size
    private static final int MAX_SIZE = 100; // Example size limit

    // Method to push an item onto the stack
    public void push(E item) {
        // check if adding this item will exceed the stack size
        if (size() >= MAX_SIZE) {
            System.out.println("Warning: Stack overflow!");
            return;
        }
        // Place the item in queue2 and then transfer all items from queue1 to queue2
        queue2.offer(item);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        // Swap the names of the two queues
        Queue<E> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    // Method to pop an item from the stack
    public E pop() {
        // Check if the stack is empty
        if (queue1.isEmpty()) {
            System.out.println("Warning: Stack underflow!");
            return null; // Or throw an exception
        }
        // Remove and return the front element from queue1
        return queue1.remove();
    }
    
    // Main method - entry point of the application
    public static void main(String[] args) {
        StackWithTwoQueues<Integer> stack = new StackWithTwoQueues<>();

        // Push items up to the max size limit
        for (int i = 1; i <= MAX_SIZE; i++) {
            stack.push(i);
        }

        // Uncomment the next line to trigger the overflow warning
        stack.push(MAX_SIZE + 1); // This should trigger overflow warning

        // Pop all items from the stack
        for (int i = 1; i <= MAX_SIZE; i++) {
            System.out.println(stack.pop());
        }

        // Uncomment the next line to trigger the underflow warning
        //System.out.println(stack.pop()); // Should trigger underflow warning
    }
}
