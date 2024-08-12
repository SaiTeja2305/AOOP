import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {

    // Method to count word occurrences in a given text
    public Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCount = new HashMap<>();

        if (text == null || text.isEmpty()) {
            return wordCount; // Return empty map if the text is null or empty
        }

        // Split the text into words, using non-word characters as delimiters
        String[] words = text.toLowerCase().split("\\W+");

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        return wordCount;
    }

    // Main method for user interaction
    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a text to count word occurrences:");
        String text = scanner.nextLine();

        Map<String, Integer> result = wordCounter.countWords(text);

        if (result.isEmpty()) {
            System.out.println("No words found in the input.");
        } else {
            System.out.println("Word Occurrences:");
            for (Map.Entry<String, Integer> entry : result.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        scanner.close();
    }
}
