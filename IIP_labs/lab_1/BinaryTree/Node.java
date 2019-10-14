package BinaryTree;

class Node<T extends Comparable<T>> {

    private T    m_data;
    private Node<T> m_left_child;
    private Node<T> m_right_child;

    public Node(Node<T> right_child, Node<T> left_child, T data) {
        m_data = data;

        m_left_child  = left_child;
        m_right_child = right_child;
    }

    Node(T data) {
        m_data = data;

        m_right_child = null;
        m_left_child  = null;
    }

    Node<T> getLeftChild() {
        return m_left_child;
    }

    Node<T> getRightChild() {
        return m_right_child;
    }

    T getData() {
        return m_data;
    }

    void setLeftChild(Node<T> left_child) {
        m_left_child = left_child;
    }

    void setRightChild(Node<T> right_child) {
        m_right_child = right_child;
    }

    void setData(T data) {
        m_data = data;
    }

}