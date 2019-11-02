package Dictionary;

public class DictNode implements Comparable<DictNode> {
    private String m_word;
    private String m_word_translation;

    DictNode(String word, String word_translation) {
        m_word = word;
        m_word_translation = word_translation;
    }

    public String getWord() {
        return m_word;
    }

    String getTranslation() {
        return m_word_translation;
    }

    @Override
    public int compareTo(DictNode dictNode) {
        if (m_word.compareTo(dictNode.m_word) > 0) {
            return 1;
        } else if (m_word.compareTo(dictNode.m_word) == 0) {
            return 0;
        }

        return -1;
    }

    @Override
    public String toString() {
        return String.format(new String("Word: %32s \tTranslation: %32s"), m_word, m_word_translation);
    }
}