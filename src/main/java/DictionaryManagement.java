import sun.java2d.opengl.WGLSurfaceData;

import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

public class DictionaryManagement {
    public static ArrayList<Integer> indexWordLookUp = new ArrayList<Integer>();
    /**
     * Insert from command line.
     */
    public static void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số từ cần thêm:  ");
        int numWord = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numWord; i++) {
            System.out.println("Nhập từ thứ " + (i + 1) + ":  ");
            String engWord = sc.nextLine();
            String vieWord = sc.nextLine();
            Word word = new Word(engWord, vieWord);
            Dictionary.words.add(word);
        }
    }

    public static void deleteFromCommandLine() {
        Scanner sc = new Scanner(System.in);
        dictionaryLookup();

        System.out.print("Nhập index các từ cần xóa:  ");
        String indexsString = sc.nextLine();

        String[] array = indexsString.split(" ");

        for (int i = 0; i < array.length; i++) {
            int indexToDelete = Integer.parseInt(String.valueOf(array[i]));
//            System.out.println(indexToDelete + " " + indexWordLookUp.get(indexToDelete - 1));
            Dictionary.words.remove(indexWordLookUp.get(indexToDelete - 1));
        }
        dictionaryExporToFile();
    }

    /**
     * Insert from file.
     */
    public static void insertFromFile() {
        try {
            File myFile = new File("src/main/java/dictionaries.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] array = data.split(" ");
                Word newFileWord = new Word(array[0], array[1]);
                Dictionary.words.add(newFileWord);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Write to file.
     */
    public static void dictionaryExporToFile() {
        try {
            FileWriter myWriter = new FileWriter("src/main/java/dictionaries.txt");
            int length = Dictionary.words.size();
            for (int i = 0; i < length; i++) {
                String engWord = Dictionary.words.get(i).getWord_target();
                String vieWord = Dictionary.words.get(i).getWord_explain();
                myWriter.write(engWord + " " + vieWord + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the dictionaries.txt!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Look up word
     */
    public static void dictionaryLookup() {
        indexWordLookUp.clear();
        System.out.print("Nhập từ tiếng anh:  ");
        Scanner sc = new Scanner(System.in);
        String wordToLookUp = sc.nextLine();
        wordToLookUp = wordToLookUp.toLowerCase();
        int length = Dictionary.words.size();

        int index = 0;
        for (int i = 0; i < length; i++) {
            if (Dictionary.words.get(i).getWord_target().toLowerCase().indexOf(wordToLookUp) == 0) {
                indexWordLookUp.add(i);
                index++;
                String s1 = Dictionary.words.get(i).getWord_target();
                String s2 = Dictionary.words.get(i).getWord_explain();
                System.out.println(String.format("%-8d", index) + String.format("%-16s", s1) + String.format("%-15s", s2));
            }
        }
    }

}
