import sun.java2d.opengl.WGLSurfaceData;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

public class DictionaryManagement {

    /**
     *
     */
    public static void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int numWord = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numWord; i++) {
            String engWord = sc.nextLine();
            String vieWord = sc.nextLine();
            Word word = new Word(engWord, vieWord);
            Dictionary.words.add(word);
        }
//        sc.close();
    }

    /**
     *
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
     *
     */
    public static void dictionaryLookup() {
        System.out.print("Nhập từ tiếng anh:  ");
        Scanner sc = new Scanner(System.in);
        String wordToLookUp = sc.nextLine();
        wordToLookUp = wordToLookUp.toLowerCase();
        int length = Dictionary.words.size();

        for (int i = 0; i < length; i++) {
            if (Dictionary.words.get(i).getWord_target().toLowerCase().indexOf(wordToLookUp) == 0) {
                String s1 = Dictionary.words.get(i).getWord_target();
                String s2 = Dictionary.words.get(i).getWord_explain();
                System.out.println(String.format("%-16s", s1) + String.format("%-15s", s2));
            }
        }
    }

}
