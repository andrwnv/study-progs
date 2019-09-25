package com.company;

import java.util.ArrayList;
import java.util.Collections;

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
        heap = new ArrayList<T>();
    }

    private T getParent(int child_index) {
        return heap.get( (child_index - 1) / 2 );
    }

    private T getLeftChild(int parent_index) {
        return heap.get(2 * parent_index + 1);
    }

    private T getRightChild(int parent_index) {
        return heap.get(2 * parent_index + 2);
    }

    private void siftUp() {

    }

    private void siftDown() {

    }

    private void restoreTree() {

    }

    public int getSize() {
        return 0;
    }

    public Boolean empty() {
        return true;
    }

    public void pushItem(T item) {

    }

    public void popBack() {

    }

    public void erase(int item_position) {

    }

    public void print() {

    }
}
