package BinaryTree;

import Debugger.Debugger;

public class BinaryTree<T extends Comparable<T>> {

    private Node<T> m_head;
    private int     m_size;

    public BinaryTree() {
        Debugger.enableDebugger();

        m_head      = null;
        m_size = 0;
    }

    public BinaryTree(Node<T> tree_node) {
        m_head = tree_node;
        ++m_size;
    }

    public Node<T> getHead() {
        return m_head;
    }

    public void setHead(Node<T> new_head) {
        m_head = new_head;
    }

    public int getSize() {
        return m_size;
    }

    public boolean empty() {
        return m_head == null;
    }

    public void clear() {
        m_head = null;
    }

    private Node<T> addItem(Node<T> curr_node, T value) {
        if (curr_node == null)
            return new Node<T>(value);

        if (value.compareTo(curr_node.getData()) < 0)
            curr_node.setLeftChild(addItem(curr_node.getLeftChild(), value));
        else
            curr_node.setRightChild(addItem(curr_node.getRightChild(), value));

        return curr_node;
    }

    public void addItem(T value) {
        m_head = addItem(m_head, value);
        ++m_size;
    }

    private T findSmallestValue(Node<T> curr_item) {
        return curr_item.getLeftChild() == null ? curr_item.getData()
                                                : findSmallestValue(curr_item.getLeftChild());
    }

    private Node<T> deleteItem(Node<T> curr_item, T value) {
        if (curr_item == null)
            return null;

        // TODO somewhere BUG

        if (value.compareTo(curr_item.getData()) < 0)
            curr_item.setLeftChild( deleteItem(curr_item.getLeftChild(), value) );
        else if (value.compareTo(curr_item.getData()) > 0)
            curr_item.setRightChild( deleteItem(curr_item.getRightChild(), value) );
        else
        {
            if (curr_item.getLeftChild() == null)
                return curr_item.getRightChild();
            else if (curr_item.getRightChild() == null)
                return curr_item.getLeftChild();

            curr_item.setData(findSmallestValue(curr_item.getRightChild()));

            curr_item.setRightChild( deleteItem(curr_item.getRightChild(), curr_item.getData()) );
        }

        return curr_item;
    }

    public void deleteItem(T value) {
        m_head = deleteItem(m_head, value);
    }

    public T searchItem(T value) {
        return searchItem(m_head, value);
    }

    private T searchItem(Node<T> current, T value) {
        if (current == null)
            return null;

        if (value == current.getData())
            return current.getData();

        return (value.compareTo(current.getData()) < 0 ? searchItem(current.getLeftChild(), value)
                : searchItem(current.getRightChild(), value));
    }

    public void DFS_print(Node<T> node) {
        if (node == null)
            return;

        print(node.getData().toString());
        DFS_print(node.getLeftChild());
        DFS_print(node.getRightChild());
    }

    private void print(String str) {
        System.out.println(str);
    }
}