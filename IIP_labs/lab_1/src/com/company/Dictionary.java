package com.company;

import Debugger.Debugger;

import java.io.*;

public class Dictionary {

    private static class DictNode {
        public String m_word = "";
        public String m_word_translation = "";

        DictNode(String word, String word_translation) {
            m_word = word;
            m_word_translation = word_translation;
        }
    }

    private BinaryTree<DictNode> dict_data;

    Dictionary() {
        dict_data = new BinaryTree<DictNode>();
        Debugger.enableDebugger();
    }

    public String getTranslation(String word) {
        return "";
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
        dict_data.pushBack( new DictNode(word, word_translation) );
    }

}