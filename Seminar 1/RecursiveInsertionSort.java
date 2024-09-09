import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecursiveInsertionSort {

    public static <AnyType extends Comparable<? super AnyType>> void recursiveInsertionSort(AnyType[] a) {
        recursiveInsertionSort(a, a.length);
    }

    // Helper method that sorts the first n elements of the array
    private static <AnyType extends Comparable<? super AnyType>> void recursiveInsertionSort(AnyType[] a, int n) {
        if (n <= 1) {
            return; // Base case: a single element is always sorted
        }
        
        // Sort first n-1 elements
        recursiveInsertionSort(a, n - 1);

        // Insert last element at its correct position in sorted array
        AnyType last = a[n - 1];
        int j = n - 2;

        // Move elements of a[0..i-1], that are greater than key, to one position ahead
        // of their current position
        while (j >= 0 && a[j].compareTo(last) > 0) {
            a[j + 1] = a[j];
            j--;
        }
        a[j + 1] = last;
    }

     // Method to read numbers from the file into an Integer array
     private static Integer[] readNumbersFromFile(String filename, int size) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while (numbers.size() < size && (line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers.toArray(new Integer[0]);
    }

    // Main method to test the RecursiveInsertionSort with file input
    public static void main(String[] args) {
        String filename = "randomNumbers.txt"; // Filename of the numbers file
        int[] sizes = {100, 1000, 10000, 100000, 1000000}; // Reduced input sizes for testing due to recursion limits
        int timesToTest = 10; // Number of times to test each input size

        for (int size : sizes) {
            long totalTime = 0; // Total time for all tests of this size

            // Testing the sorting multiple times for each input size
            for (int test = 0; test < timesToTest; test++) {
                Integer[] array = readNumbersFromFile(filename, size); // Read numbers before each test

                long startTime = System.nanoTime();
                recursiveInsertionSort(array); // Sort the array using recursive insertion sort
                long endTime = System.nanoTime();

                totalTime += (endTime - startTime); // Add time for this test to total
            }
            long averageTime = totalTime / timesToTest; // Calculate average time
            System.out.println("Input: " + size + ", average time: " + averageTime + " nt");
        }
    }
   
}
