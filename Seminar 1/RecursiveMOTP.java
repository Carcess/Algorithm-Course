import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecursiveMOTP {

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = medianOfThreePivot(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int medianOfThreePivot(int[] array, int low, int high) {
        int middle = (low + high) / 2;
        // Order the first, middle, and last elements
        if (array[middle] < array[low]) {
            swap(array, low, middle);
        }
        if (array[high] < array[low]) {
            swap(array, low, high);
        }
        if (array[high] < array[middle]) {
            swap(array, middle, high);
        }
        // Place pivot at position high - 1
        swap(array, middle, high - 1);
        return partition(array, low, high - 1);
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Pivot is now at high - 1
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
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
        int[] sizes = {100, 1000, 10000, 100000, 1000000}; // Adjusted input sizes for testing
        int timesToTest = 10; // Number of times to test each input size
    
        for (int size : sizes) {
            long totalTime = 0; // Total time for all tests of this size
    
            // Testing the sorting multiple times for each input size
            for (int test = 0; test < timesToTest; test++) {
                int[] array = readNumbersFromFile(filename, size); // Read numbers before each test
    
                long startTime = System.nanoTime();
                quickSort(array); // Sort the array using iterative QuickSort with median-of-three pivot
                long endTime = System.nanoTime();
    
                totalTime += (endTime - startTime); // Add time for this test to total
            }
            long averageTime = totalTime / timesToTest; // Calculate average time
            System.out.println("Input: " + size + ", average time: " + averageTime + " nt");
        }
    }
}
