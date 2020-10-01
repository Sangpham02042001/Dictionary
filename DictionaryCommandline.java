import sun.java2d.windows.GDIWindowSurfaceData;

import javax.swing.tree.DefaultTreeCellEditor;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.lang.String;
import java.util.*;
import javax.swing.*;
import java.awt.*;
public class DictionaryCommandline {

    /**
     *
     */
    public static void showAllWordsInCMDFile() {
        int length = Dictionary.getWords().size();
        System.out.println(String.format("%-10s|", "No") +  String.format("%-15s|", "English") + String.format("%-15s", "VietNam"));
        for (int i = 0; i < length; i++) {
            String s2 = Dictionary.getWords().get(i).getWord_explain();
            String s1 = Dictionary.getWords().get(i).getWord_target();
            System.out.println(String.format("%-12s", i + 1) +  String.format("%-16s", s1) + String.format("%-15s", s2));
            //System.out.println(s1);
        }
    }

    /**
     *
     */
    public static void dictionaryBasic() throws Exception {
        DictionaryManagement.insertFromFile();
        //DictionaryManagement.insertFromCommandlineForCMD();
        DictionaryManagement.alterFromCommandLineForCMD();
        //DictionaryManagement.deleteFromCommandLine();
        //DictionaryManagement.dictionaryLookupForCMD();
        showAllWordsInCMDFile();
    }

    /**
     *
     */
    public static void dictionaryAdvanced() throws Exception {
        DictionaryManagement.InsertFromFileGraphics();
//        showAllWords();
        //DictionaryManagement.dictionaryLookupForGraphic();
//        DictionaryManagement.alterFromCommandLine();
        int index = DictionaryManagement.showList();
        DictionaryManagement.showMeaning(index);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Dictionary dictionary = new Dictionary();
        //dictionaryAdvanced();
        dictionaryBasic();

    }

}
