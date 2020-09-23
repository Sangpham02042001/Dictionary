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

}
