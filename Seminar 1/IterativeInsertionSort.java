import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IterativeInsertionSort {

    public static <AnyType extends Comparable<? super AnyType>> void iterativeInsertionSort(AnyType[] a) {
        for (int p = 1; p < a.length; p++) {
            AnyType tmp = a[p];
            int j = p;

            // Move elements greater than tmp up one position to make space for insertion
            for (; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }

            // Insert tmp into its correct position
            a[j] = tmp;
        }
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

     // Main method to test the IterativeInsertionSort with file input
     public static void main(String[] args) {
        String filename = "randomNumbers.txt"; // Filename of the numbers file
        int[] sizes = {100, 1000, 10000, 100000, 1000000}; // Adjusted input sizes for testing
        int timesToTest = 10; // Number of times to test each input size

        for (int size : sizes) {
            long totalTime = 0; // Total time for all tests of this size

            // Testing the sorting multiple times for each input size
            for (int test = 0; test < timesToTest; test++) {
                Integer[] array = readNumbersFromFile(filename, size); // Read numbers before each test

                long startTime = System.nanoTime();
                iterativeInsertionSort(array); // Sort the array using iterative insertion sort
                long endTime = System.nanoTime();

                totalTime += (endTime - startTime); // Add time for this test to total
            }
            long averageTime = totalTime / timesToTest; // Calculate average time
            System.out.println("Input: " + size + ", average time: " + averageTime + " nt");
        }
    }


}
