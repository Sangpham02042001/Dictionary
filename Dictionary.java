import java.util.ArrayList;

public class Dictionary {

    private static ArrayList<Word> words;

    /**
     *
     */
    public Dictionary() {
        words = new ArrayList<Word>();
    }

    /**
     *
     * @return
     */
    public static ArrayList<Word> getWords() {
        return words;
    }

    /**
     *
     * @param words
     */
    public static void setWords(ArrayList<Word> words) {
        words = words;
    }

//    public static ArrayList<Word> addWord(Word newWord) {
//        words.add(newWord);
//        return words;
//    }

    public static void addWord(Word newWord) {
        words.add(newWord);
    }

}
