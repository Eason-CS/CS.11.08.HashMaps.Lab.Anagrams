import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AnagramSolver {

    private AnagramSolver() {};

    /**
     * Input: name of text file (containing English words).
     * Output: a hashmap of lists of words that are anagrams.
     * @param filename
     * @return
     */
    public static HashMap<String, ArrayList<String>> anagrams(String filename) {
        HashMap<String, ArrayList<String>> anagramMap = new HashMap<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();
                String sortedWord = sortString(word);

                if (!anagramMap.containsKey(sortedWord)) {
                    anagramMap.put(sortedWord, new ArrayList<>());
                }
                anagramMap.get(sortedWord).add(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }

        return anagramMap;
    }

    /**
     * Input: hashmap of lists of words that are anagrams.
     * Output: largest list of words in hashmap that are anagrams.
     * @param anagrams
     * @return
     */
    public static ArrayList<String> mostFrequentAnagram(HashMap<String, ArrayList<String>> anagrams) {
        ArrayList<String> largestAnagramGroup = new ArrayList<>();

        for (ArrayList<String> group : anagrams.values()) {
            if (group.size() > largestAnagramGroup.size()) {
                largestAnagramGroup = group;
            }
        }

        return largestAnagramGroup;
    }

    /**
     * Input: hashmap of lists of words that are anagrams.
     * Output: prints all key value pairs in the hashmap.
     * @param anagrams
     */
    public static void printKeyValuePairs(HashMap<String, ArrayList<String>> anagrams) {
        for (String key : anagrams.keySet()) {
            System.out.println(key + ": " + anagrams.get(key));
        }
    }

    /**
     * Helper method to sort the characters of a string.
     * @param word
     * @return
     */
    private static String sortString(String word) {
        char[] charArray = word.toCharArray();
        java.util.Arrays.sort(charArray);
        return new String(charArray);
    }

    public static void main(String[] args) {
        String filename = "words.txt"; // Replace with your file path
        HashMap<String, ArrayList<String>> anagramMap = anagrams(filename);

        System.out.println("All anagram groups:");
        printKeyValuePairs(anagramMap);

        ArrayList<String> largestAnagramGroup = mostFrequentAnagram(anagramMap);
        System.out.println("\nLargest anagram group:");
        System.out.println(largestAnagramGroup);
    }
}