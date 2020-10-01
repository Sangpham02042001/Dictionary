//import com.sun.prism.shader.Solid_ImagePattern_Loader;
import sun.java2d.opengl.WGLSurfaceData;

import java.io.*;
import java.util.*;

public class DictionaryManagement {
    //public static ArrayList<Integer> indexWordLookUp = new ArrayList<Integer>();

    /**
     * Insert from command line.
     */
    public static void insertFromCommandlineForCMD() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số từ cần thêm:  ");
        int numWord = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numWord; i++) {
            System.out.println("Nhập từ tiếng Anh thứ " + (i + 1) + ":  ");
            String engWord = sc.nextLine();
            System.out.println("Nhập từ tiếng Viết thứ " + (i+1) + ": ");
            String vieWord = sc.nextLine();
            Word word = new Word(engWord, vieWord);
            Dictionary.addWord(word);
        }
    }

    public static void alterFromCommandLineForCMD() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số từ cần sửa trong Từ điển: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int index = dictionaryLookupForCMD();
            if (index == 0) {
                //System.out.println("Not Found");
                continue;
            } else {
                System.out.print("Nhập index từ cần sửa:  ");
                int indexsString = sc.nextInt();
                String engAlter;
                String vieAlter;
                System.out.print("Nhập: 1 - sửa English | 2 - sửa VietNamese | Nhập 3 - sửa cả 2 :  ");
                int check = sc.nextInt();
                sc.nextLine();
                switch (check) {
                    case 1:
                        System.out.print("English:  ");
                        engAlter = sc.nextLine();
                        Dictionary.getWords().get(index - 1).setWord_target(engAlter);
                        System.out.println("Changed successfully!");
                        break;
                    case 2:
                        System.out.print("VietNamese:  ");
                        vieAlter = sc.nextLine();
                        Dictionary.getWords().get(index - 1).setWord_explain(vieAlter);
                        System.out.println("Changed successfully!");
                        break;
                    case 3:
                        System.out.println("English: ");
                        engAlter = sc.nextLine();
                        Dictionary.getWords().get(index - 1).setWord_target(engAlter);
                        System.out.println("Vietnamese: ");
                        vieAlter = sc.nextLine();
                        Dictionary.getWords().get(index - 1).setWord_explain(vieAlter);
                        break;
                    default:
                        System.out.println("Not '1' or '2' or '3'!!");
                }
            }
        }
    }

    public static void deleteFromCommandLine() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so tu can xoa: ");
        int leng = Dictionary.getWords().size();
        int n = sc.nextInt();
        int index;
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap index tu can xoa");
            index = sc.nextInt();
            if (index >= leng) {
                System.out.println("Out of index");
                continue;
            }
            Dictionary.getWords().remove(index - 1);
            System.out.println("Deleted successfully!");
        }
    }

    /**
     * Insert from file.
     */
    public static void insertFromFile() throws Exception{
        try {
            File myFile = new File("./src/dictionariesCMD.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] array = data.split("   ", 2);
                Word newFileWord = new Word(array[0], array[1]);
                Dictionary.addWord(newFileWord);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        dictionaryExporToFile();
    }

    public static void InsertFromFileGraphics() {
        try {
            File file = new File("src\\dictionaries.txt");
            FileInputStream fstream = new FileInputStream(file);
            byte[] data = new byte[(int)file.length()];
            fstream.read(data);
            String allLine = new String(data, "UTF-8");
            String[] lines = allLine.split("@");
            String wordTarget, wordExplain;
            for (int i = 1; i < lines.length; i++) {
                String[] arr1 = lines[i].split("\n", 2);
                String[] arr2 = arr1[0].split("/", 2);
                if (arr2.length >= 2) {
                    wordTarget = arr2[0];
                    //arr2[i].replace("/" , "");
                    wordExplain = "/" + arr2[1] + "\n" + arr1[1];
                    Word word = new Word(wordTarget, wordExplain);
                    Dictionary.addWord(word);
                }
            }
            fstream.close();
        } catch (Exception e) {
            System.out.println("cac");
        }
    }

    /**
     * Write to file.
     */
    public static void dictionaryExporToFile() {
        try {
            FileWriter myWriter = new FileWriter("./src/new.txt");
            int length = Dictionary.getWords().size();
            for (int i = 0; i < length; i++) {
                String engWord = Dictionary.getWords().get(i).getWord_target();
                String vieWord = Dictionary.getWords().get(i).getWord_explain();
                myWriter.write(engWord + "    " + vieWord + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the new.txt!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Look up word
     */
    public static int dictionaryLookupForCMD() {
        //indexWordLookUp.clear();
        System.out.print("Nhập từ tiếng anh:  ");
        Scanner sc = new Scanner(System.in);
        String wordToLookUp = sc.nextLine();
        wordToLookUp = wordToLookUp.toLowerCase();
        int length = Dictionary.getWords().size();

        int index = 0;
        for (int i = 0; i < length; i++) {
            wordToLookUp = wordToLookUp.strip();
            if (Dictionary.getWords().get(i).getWord_target().toLowerCase().indexOf(wordToLookUp) == 0) {
                //indexWordLookUp.add(i);
                index++;
                String s1 = Dictionary.getWords().get(i).getWord_target();
                String s2 = Dictionary.getWords().get(i).getWord_explain();
                System.out.println(String.format("%-8d", index) + String.format("%-16s", s1) + String.format("%-15s", s2));
                //System.out.println(s1 + "\n" + s2);
            }
        }
        if (index == 0) {
            System.out.println("Not Found");
        }
        return index;
    }

    public static int dictionaryLookupForGraphic() {
        System.out.print("Nhập từ tiếng anh:  ");
        Scanner sc = new Scanner(System.in);
        String wordToLookUp = sc.nextLine();
        wordToLookUp = wordToLookUp.toLowerCase();
        int length = Dictionary.getWords().size();

        int index = 0;
        for (int i = 0; i < length; i++) {
            wordToLookUp = wordToLookUp.strip();
            if (Dictionary.getWords().get(i).getWord_target().toLowerCase().indexOf(wordToLookUp) == 0) {
                //indexWordLookUp.add(i);
                index++;
                String s1 = Dictionary.getWords().get(i).getWord_target();
                String s2 = Dictionary.getWords().get(i).getWord_explain();
                //System.out.println(String.format("%-8d", index) + String.format("%-16s", s1) + String.format("%-15s", s2));
                System.out.println(s1 + "\n" + s2);
            }
        }
        return index;
    }

    public static int showList() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap do dai tu can tim: ");
        int n = sc.nextInt();
        sc.nextLine();
        String s1 = "";
        String s2;
        int index = 0;
        int length = Dictionary.getWords().size();
        for (int i = 0; i < n; i++) {
        //while (!sc.nextLine() != "/n") {
            s2 = sc.nextLine();
            s1 += s2;
            //int index = 0;
            for (int j = index; j < length; j++) {
                s1 = s1.trim();
                if(Dictionary.getWords().get(j).getWord_target().toLowerCase().indexOf(s1) != 0) {
                    index++;
                } else {
                    break;
                }
            }
            for (int j = index; j < (index + 5) && Dictionary.getWords().get(j).getWord_target().toLowerCase().indexOf(s1) == 0  ;j++) {
                System.out.println(Dictionary.getWords().get(j).getWord_target());
            }
        }
        sc.close();
        return index;
    }

    public static void showMeaning(int index) {
        System.out.println("\n" + Dictionary.getWords().get(index).getWord_target() + "\n" + Dictionary.getWords().get(index).getWord_explain());
    }
}
