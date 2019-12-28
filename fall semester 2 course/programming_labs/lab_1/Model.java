import BinaryTree.BinaryTree;
import Dictionary.DictNode;
import Dictionary.Dictionary;

class Model {
    private Dictionary _dict;

    Model() {
        _dict = new Dictionary("");
        try {
            _dict.addDictData("/home/andrew/Desktop/StudyProgs/IIP_labs/lab_1/Dictionary/dictionary_list.json");
        } catch (Exception e) {
            System.out.println("File doesnt exist!");
        }
    }

    String getTranslation(String word) {
        return _dict.getTranslation(word);
    }

    void addDictPair(String word, String translation) {
        _dict.addDictData(word, translation);
    }

}
