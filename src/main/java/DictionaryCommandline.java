import sun.java2d.windows.GDIWindowSurfaceData;

import javax.swing.tree.DefaultTreeCellEditor;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.lang.String;
import java.util.*;

public class DictionaryCommandline {

    /**
     *
     */
    public static void showAllWords() {
        int length = Dictionary.words.size();
        System.out.println(String.format("%-10s|", "No") +  String.format("%-15s|", "English") + String.format("%-15s", "VietNam"));
        for (int i = 0; i < length; i++) {
            String s1 = Dictionary.words.get(i).getWord_explain();
            String s2 = Dictionary.words.get(i).getWord_target();
            System.out.println(String.format("%-12s", i + 1) +  String.format("%-16s", s2) + String.format("%-15s", s1));
        }

    }

    /**
     *
     */
    public void dictionaryBasic(Dictionary dictionary) {
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
//        DictionaryManagement.insertFromCommandline();
        DictionaryManagement.insertFromFile();
        showAllWords();

    }

}
