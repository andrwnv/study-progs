package com.company;

import Debugger.Debugger;

import java.util.ArrayList;

/*
    Описать класс, реализующий бинарное дерево, обладающее возможностью добавления новых элементов,
    удаления существующих, поиска элемента по ключу,
    а также последовательного доступа ко всем элементам.

    Написать программу, использующую этот класс для представления англо-русского словаря.
    Программа должна содержать меню, позволяющее осуществить
    проверку всех методов класса. Предусмотреть возможность формирования словаря из файла и с клавиатуры.
*/

class BinaryTree<T> {

    private ArrayList<T> heap;

    BinaryTree() {
        Debugger.enableDebugger();
        heap = new ArrayList<T>();
    }

    private T getParent(int child_index) { return heap.get( (child_index - 1) / 2 ); }

    private T getLeftChild(int parent_index) { return heap.get( 2 * parent_index + 1 ); }

    private T getRightChild(int parent_index) { return heap.get( 2 * parent_index + 2 ); }

    private void siftUp(int item_index) {

    }

    private void siftDown(int item_index) {

    }

    private void restoreTree() {

    }

    public int getSize() { return 0; }

    public boolean empty() { return true; }

    public void pushBack(T item) {
        heap.add(item);
        siftUp( heap.size() - 1 );

        Debugger.log("[pushBack: INFO] called.");
    }

    public void popBack() {
        try {
            heap.remove( heap.remove(heap.size()) );
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
