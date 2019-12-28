package Dictionary;

import BinaryTree.BinaryTree;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Dictionary {

    private BinaryTree<DictNode> _dictData;

    public Dictionary() {
        _dictData = new BinaryTree<DictNode>();
    }

    public String getTranslation(String word) {
        var searched = _dictData.searchItem( new DictNode(word, "") );
        return searched == null ? "" : searched.getTranslation();
    }

    public void addDictData(String absFilePath) throws Exception {

        Object obj = new JSONParser().parse( new FileReader(absFilePath) );

        JSONObject jo = (JSONObject) obj;

        JSONArray wordsArray = (JSONArray) jo.get("words");

        for (Object o : wordsArray) {
            JSONObject tmp = (JSONObject) o;
            _dictData.addItem( new DictNode( (String) tmp.get("word:"), (String) tmp.get("translation:") ) );
        }
    }

    public void addDictData(String word, String wordTranslation) {
        _dictData.addItem( new DictNode(word, wordTranslation) );
    }
}