import java.util.ArrayList;

public class Dictionary {

    public static ArrayList<Word> words;

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
    public ArrayList<Word> getWords() {
        return words;
    }

    /**
     *
     * @param words
     */
    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

}
