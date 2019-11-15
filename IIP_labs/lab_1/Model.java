import BinaryTree.BinaryTree;
import Dictionary.DictNode;
import Dictionary.Dictionary;

class Model {
    private Dictionary test;

    Model() {
        test = new Dictionary("");
        try {
            test.addDictData("/home/andrew/Desktop/StudyProgs/IIP_labs/lab_1/Dictionary/dictionary_list.json");
        } catch (Exception e) {
            System.out.println("File doesnt exist!");
        }
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
