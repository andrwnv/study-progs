package BinaryTree;

import Debugger.Debugger;

public class BinaryTree< T extends Comparable<T> > {

    private Node<T> m_head;
    private int     m_size;

    public BinaryTree() {
        Debugger.enableDebugger();

        m_head = null;
        m_size = 0;
    }

    public BinaryTree(Node<T> tree_node) {
        m_head = tree_node;
        ++m_size;
    }

    public boolean empty() {
        return m_head == null;
    }

    public int getSize() {
        return m_size;
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

    private Node<T> deleteItem(Node<T> curr_item, T value) {
        if (curr_item == null)
            return null;

        if (value == curr_item.getData()) {
            if (curr_item.getLeftChild() == null && curr_item.getRightChild() == null)
                return null;

            if (curr_item.getRightChild() == null)
                return curr_item.getLeftChild();
            else if (curr_item.getLeftChild() == null)
                return curr_item.getRightChild();

            T min_val = findMin( curr_item.getRightChild() );
            curr_item.setData(min_val);
            curr_item.setRightChild( deleteItem(curr_item.getRightChild(), min_val) );

            return curr_item;
        }
        if (value.compareTo(curr_item.getData()) < 0) {
            curr_item.setLeftChild( deleteItem(curr_item.getLeftChild(), value) );
            return curr_item;
        }

        curr_item.setRightChild( deleteItem(curr_item.getRightChild(), value) );
        return curr_item;
    }

    private T findMin(Node<T> curr_item) {
        return curr_item.getLeftChild() == null ? curr_item.getData()
                                                : findMin( curr_item.getLeftChild() );
    }

    public void deleteItem(T value) {
        if (m_head == null)
            return;

        m_head = deleteItem(m_head, value);
        --m_size;
    }

    public T searchItem(T value) {
        return searchItem(m_head, value);
    }

    private T searchItem(Node<T> curr_item, T value) {
        if (curr_item == null)
            return null;

        if (value == curr_item.getData())
            return curr_item.getData();

        return value.compareTo(curr_item.getData()) < 0 ? searchItem(curr_item.getLeftChild(), value)
                                                        : searchItem(curr_item.getRightChild(), value);
    }

    public void DFS_print() {
        DFS_print(m_head);
    }

    private void DFS_print(Node<T> node) {
        if (node == null)
            return;

        print(node.getData().toString());

        DFS_print( node.getLeftChild()  );
        DFS_print( node.getRightChild() );
    }

    private void print(String str) { System.out.println(str); }
}