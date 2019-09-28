package BinaryTree;

import Debugger.Debugger;

/*
    Описать класс, реализующий бинарное дерево, обладающее возможностью добавления новых элементов,
    удаления существующих, поиска элемента по ключу,
    а также последовательного доступа ко всем элементам.

    Написать программу, использующую этот класс для представления англо-русского словаря.
    Программа должна содержать меню, позволяющее осуществить
    проверку всех методов класса. Предусмотреть возможность формирования словаря из файла и с клавиатуры.
*/


public class BinaryTree<T> {

    private Node head;
    private int  tree_size;

    public BinaryTree() {
        Debugger.enableDebugger();

        head      = null;
        tree_size = 0;
    }

    public BinaryTree(Node tree_node) {
        head = tree_node;
        ++tree_size;
    }

    public int getSize() {
        return tree_size;
    }

    public boolean empty() {
        return tree_size == 0;
    }

    public void pushBack(T item) {
        Debugger.log("[pushBack: INFO] called.");


    }

    public void pushBack(Node<T> node) {
        if (head == null){
            head = node;
            return;
        }

        if (node.getData() > head.getData()) {

        }
    }

    public void popBack() {
        try {
            // TODO: delete past elem
        } catch (IndexOutOfBoundsException ex) {
            Debugger.log("[popBack: CRITICAL] Trying delete elem from empty contain!");
            System.exit(2);
        }

        Debugger.log("[pushBack: INFO] popBack func called.");
    }

    public void erase(int item_position) {

    }

    public void print() {

    }
}
