import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativeFirstElementPivot {

    public static void quickSort(int[] array) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(array.length - 1);

        while (!stack.isEmpty()) {
            int high = stack.pop();
            int low = stack.pop();

            if (low < high) {
                int pivotIndex = partitionFirstElement(array, low, high);

                if (pivotIndex - 1 > low) {
                    stack.push(low);
                    stack.push(pivotIndex - 1);
                }
                if (pivotIndex + 1 < high) {
                    stack.push(pivotIndex + 1);
                    stack.push(high);
                }
            }
        }
    }

    private static int partitionFirstElement(int[] array, int low, int high) {
        int pivot = array[low];
        int i = low + 1;
        for (int j = low + 1; j <= high; j++) {
            if (array[j] < pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, low, i - 1);
        return i - 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Method to print the array
    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // Method to read numbers from the file into an array
    private static int[] readNumbersFromFile(String filename, int size) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while (numbers.size() < size && (line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers.stream().mapToInt(i -> i).toArray();
    }

 // Main method to test the QuickSort implementation with file input
 public static void main(String[] args) {
    String filename = "randomNumbers.txt"; // Filename of the numbers file
    int[] sizes = {100, 1000, 10000, 100000, 1000000}; // Different input sizes for testing
    int timesToTest = 10; // Number of times to test each input size

    for (int size : sizes) {
        long totalTime = 0; // Total time for all tests of this size
        for (int test = 0; test < timesToTest; test++) {
            int[] array = readNumbersFromFile(filename, size); // Read numbers before each test

            long startTime = System.nanoTime();
            quickSort(array); // Sort the array using iterative QuickSort
            long endTime = System.nanoTime();

            totalTime += (endTime - startTime); // Add time for this test to total
        }
        long averageTime = totalTime / timesToTest; // Calculate average time
        System.out.println("Input: " + size + ", average time: " + averageTime + " nt");
    }
}

}
