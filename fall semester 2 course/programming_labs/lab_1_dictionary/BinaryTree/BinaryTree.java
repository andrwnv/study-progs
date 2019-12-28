package BinaryTree;

import Debugger.Debugger;

public class BinaryTree< T extends Comparable<T> > {

    private Node<T> _head;
    private int _size;

    public BinaryTree() {
        Debugger.enableDebugger();

        _head = null;
        _size = 0;
    }

    public BinaryTree(Node<T> treeNode) {
        _head = treeNode;
        ++_size;
    }

    public boolean empty() {
        return _head == null;
    }

    public int getSize() {
        _size = 0;
        getSize(_head);

        return _size;
    }

    private void getSize(Node<T> node) {
        if (node == null)
                return;

        _size++;

        getSize( node.getLeftChild()  );
        getSize( node.getRightChild() );
    }

    public void clear() {
        _head = null;
    }

    private Node<T> addItem(Node<T> currNode, T value) {
        if (currNode == null)
            return new Node<T>(value);

        if (value.compareTo(currNode.getData()) < 0)
            currNode.setLeftChild(addItem(currNode.getLeftChild(), value));
        else
            currNode.setRightChild(addItem(currNode.getRightChild(), value));

        return currNode;
    }

    public void addItem(T value) {
        _head = addItem(_head, value);
        ++_size;
    }

    private Node<T> deleteItem(Node<T> currItem, T value) {
        if (currItem == null)
            return null;

        if (value == currItem.getData()) {
            if (currItem.getLeftChild() == null && currItem.getRightChild() == null)
                return null;

            if (currItem.getRightChild() == null)
                return currItem.getLeftChild();
            else if (currItem.getLeftChild() == null)
                return currItem.getRightChild();

            T minVal = findMin( currItem.getRightChild() );
            currItem.setData(minVal);
            currItem.setRightChild( deleteItem(currItem.getRightChild(), minVal) );

            return currItem;
        }
        if (value.compareTo(currItem.getData()) < 0) {
            currItem.setLeftChild( deleteItem(currItem.getLeftChild(), value) );
            return currItem;
        }

        currItem.setRightChild( deleteItem(currItem.getRightChild(), value) );
        return currItem;
    }

    private T findMin(Node<T> curr_item) {
        return curr_item.getLeftChild() == null ? curr_item.getData()
                                                : findMin( curr_item.getLeftChild() );
    }

    public void deleteItem(T value) {
        if (_head == null)
            return;

        _head = deleteItem(_head, value);

        --_size;
    }

    public T searchItem(T value) {
        return searchItem(_head, value);
    }

    private T searchItem(Node<T> currItem, T value) {
        if (currItem == null)
            return null;

        if (value.compareTo(currItem.getData()) == 0)
            return currItem.getData();

        return value.compareTo(currItem.getData()) < 0 ? searchItem(currItem.getLeftChild(),  value)
                                                       : searchItem(currItem.getRightChild(), value);
    }
}