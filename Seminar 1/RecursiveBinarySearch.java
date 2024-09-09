import java.io.*;
import java.nio.file.*;
import java.util.*;

public class RecursiveBinarySearch {

    public static boolean binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return false;
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return true;
        } else if (arr[mid] > target) {
            return binarySearchRecursive(arr, target, left, mid - 1);
        } else {
            return binarySearchRecursive(arr, target, mid + 1, right);
        }
    }

    public static int[] readNumbersFromFile(String filePath, int size) throws IOException {
        return Files.lines(Paths.get(filePath))
                    .mapToInt(Integer::parseInt)
                    .limit(size)
                    .toArray();
    }

    public static void measurePerformance(int[] arr, int target, int inputSize, int iterations) {
        int[] sample = Arrays.copyOf(arr, inputSize);
        Arrays.sort(sample); // Ensure the array is sorted for binary search

        long totalTime = 0;
        for (int i = 0; i < iterations; i++) {
            // Measure recursive binary search
            long startTime = System.nanoTime();
            binarySearchRecursive(sample, target, 0, inputSize - 1);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }

        long averageTime = totalTime / iterations; // Calculate average time in nanoseconds
        System.out.println("Input size: " + inputSize + ", Average Recursive time: " + averageTime + " ns");
    }

    public static String findFileInCurrentDirectory() {
        File dir = new File(".");
        File[] files = dir.listFiles((d, name) -> name.endsWith("randomNumbers.txt"));
        if (files != null && files.length > 0) {
            return files[0].getPath();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            String filePath = findFileInCurrentDirectory();
            if (filePath == null) {
                System.out.println("No randomNumbers.txt file found in the current directory.");
                return;
            }
            int[] numbers = readNumbersFromFile(filePath, 1000000); // Read the maximum size once
            int target = numbers[numbers.length - 1]; // Use the last number as target for the worst-case scenario

            // The number of iterations for averaging the time
            int iterations = 10;

            // Run performance measure for different input sizes
            int[] inputSizes = {100, 1000, 10000, 100000, 1000000};
            for (int size : inputSizes) {
                measurePerformance(numbers, target, size, iterations);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
