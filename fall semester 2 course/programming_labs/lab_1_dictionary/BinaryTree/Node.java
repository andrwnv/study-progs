package BinaryTree;

class Node<T extends Comparable<T>> {

    private T       _data;
    private Node<T> _leftChild;
    private Node<T> _rightChild;

    public Node(Node<T> right_child, Node<T> left_child, T data) {
        _data = data;

        _leftChild  = left_child;
        _rightChild = right_child;
    }

    Node(T data) {
        _data = data;

        _leftChild  = null;
        _rightChild = null;
    }

    Node<T> getLeftChild() {
        return _leftChild;
    }

    Node<T> getRightChild() {
        return _rightChild;
    }

    T getData() {
        return _data;
    }

    void setLeftChild(Node<T> left_child) {
        _leftChild = left_child;
    }

    void setRightChild(Node<T> right_child) {
        _rightChild = right_child;
    }

    void setData(T data) {
        _data = data;
    }

}