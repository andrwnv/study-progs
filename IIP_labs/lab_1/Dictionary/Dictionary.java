package Dictionary;

import BinaryTree.BinaryTree;
import Debugger.Debugger;

import java.io.FileReader;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.FileNotFoundException;

public class Dictionary {

    private BinaryTree<DictNode> dict_data;

    public Dictionary() {
        dict_data = new BinaryTree<DictNode>();
        Debugger.enableDebugger();
    }

    public String getTranslation(String word) {
        var searched = dict_data.searchItem(new DictNode(word, ""));

        return searched == null ? "" : searched.getTranslation();
    }

    public void addDictData(String file_path) {

        String buff_line = "";
        try {
            FileReader file_reader     = new FileReader(file_path);
            BufferedReader buff_reader = new BufferedReader(file_reader);

            while ( (buff_line = buff_reader.readLine()) != null ) {
                System.out.println(buff_line);
            }

        } catch (FileNotFoundException ex) {
            Debugger.log(String.format("[addDictData: FAIL] file %s cant be open.", file_path));
        } catch (IOException ex) {
            Debugger.log("[addDictData: FAIL] reading file error.");
        }
    }

    public void addDictData(String word, String word_translation) {
        dict_data.addItem( new DictNode(word, word_translation) );
    }

}