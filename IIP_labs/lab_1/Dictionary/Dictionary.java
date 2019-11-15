package Dictionary;

import BinaryTree.BinaryTree;
import Debugger.Debugger;

import java.io.FileReader;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Dictionary {

    private BinaryTree<DictNode> dict_data;

    public Dictionary(String s) {
        dict_data = new BinaryTree<DictNode>();
        Debugger.enableDebugger();
    }

    public String getTranslation(String word) {
        var searched = dict_data.searchItem(new DictNode(word, ""));

        return searched == null ? "" : searched.getTranslation();
    }

    public void addDictData(String file_path) throws Exception {

        Object obj = new JSONParser().parse(new FileReader(file_path)); // Object obj = new JSONParser().parse(new FileReader("JSONExample.json"));

        JSONObject jo = (JSONObject) obj;

        JSONArray words_array = (JSONArray) jo.get("words");

        for (Object o : words_array) {
            JSONObject tmp = (JSONObject) o;
            dict_data.addItem(new DictNode((String) tmp.get("word:"), (String) tmp.get("translation:")));
        }
    }

    public void addDictData(String word, String word_translation) {
        dict_data.addItem( new DictNode(word, word_translation) );
    }

}