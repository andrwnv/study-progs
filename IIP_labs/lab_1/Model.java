import BinaryTree.BinaryTree;
import Dictionary.DictNode;
import Dictionary.Dictionary;

class Model {
    private Dictionary test;

    Model() {
        test = new Dictionary();

        test.addDictData("translate", "перевод");
        test.addDictData("cat", "кот");
        test.addDictData("hello", "привет");
    }

    String getTranslation(String word) {
        return test.getTranslation(word);
    }

    void addDictPair(String word, String translation) {
        test.addDictData(word, translation);
    }

}
