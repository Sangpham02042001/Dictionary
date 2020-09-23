import java.util.ArrayList;

public class Dictionary {

    public static ArrayList<Word> words;

    /**
     *
     */
    public Dictionary() {
        words = new ArrayList<Word>();
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }


}
