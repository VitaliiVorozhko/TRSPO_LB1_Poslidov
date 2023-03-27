import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Задаємо розмір масиву та межі для генерації випадкових чисел
        int size = 1000;
        int min = 1;
        int max = 100;

        // Генеруємо масив випадкових чисел
        int[] array = generateArray(size, min, max);

        // Зберігаємо вхідні дані в файл
        saveArrayToFile(array, "input.txt");

        // Сортуємо масив
        long startTime = System.nanoTime();
        bubbleSort(array);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        // Виводимо відсортований масив та час сортування
        System.out.println("Sorted array:");
        printArray(array);
        System.out.println("Sorting time: " + elapsedTime + " nanoseconds");

        // Зберігаємо результати в файл
        saveArrayToFile(array, "output.txt");

        // Зчитуємо масив з файлу та виводимо його на екран
        int[] arrayFromFile = readArrayFromFile("output.txt");
        System.out.println("Array read from file:");
        printArray(arrayFromFile);

    }

    // Метод для генерації масиву випадкових чисел
    public static int[] generateArray(int size, int min, int max) {
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(max - min + 1) + min;
        }
        return array;
    }

    // Метод для збереження масиву в файл
    public static void saveArrayToFile(int[] array, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (int i = 0; i < array.length; i++) {
                writer.write(Integer.toString(array[i]));
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    // Метод для зчитування масиву з файлу
    public static int[] readArrayFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            int[] array = new int[countLines(filename)];
            int i = 0;
            while (scanner.hasNextInt()) {
                array[i++] = scanner.nextInt();
            }
            scanner.close();
            return array;
        } catch (IOException e) {
            System.out.println("Error reading file.");
            return new int[0];
        }
    }

    // Метод для отримання кількості рядків у файлі
    public static int countLines(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        int count = 0;
        while (scanner.hasNextLine()) {
            count++;
            scanner.nextLine();
        }
        scanner.close();
        return count;
    }

    // Метод для виведення масиву на екран
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // Метод для сортування масиву методом обміну
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Обмін значень
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
