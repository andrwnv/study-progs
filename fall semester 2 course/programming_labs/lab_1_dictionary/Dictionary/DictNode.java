package Dictionary;

public class DictNode implements Comparable<DictNode> {
    private String _word;
    private String _wordTranslation;

    DictNode(String word, String wordTranslation) {
        _word = word;
        _wordTranslation = wordTranslation;
    }

    public String getWord() {
        return _word;
    }

    String getTranslation() {
        return _wordTranslation;
    }

    @Override
    public int compareTo(DictNode dictNode) {
        if ( _word.compareTo(dictNode._word) > 0 ) {
            return 1;
        } else if ( _word.compareTo(dictNode._word) == 0 ) {
            return 0;
        }

        return -1;
    }

    @Override
    public String toString() {
        return String.format(new String("Word: %32s \tTranslation: %32s"), _word, _wordTranslation);
    }
}