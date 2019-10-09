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

    public Node(T data) {
        m_data = data;

        m_right_child = null;
        m_left_child  = null;
    }

    public Node<T> getLeftChild() {
        return m_left_child;
    }

    public Node<T> getRightChild() {
        return m_right_child;
    }

    public T getData() {
        return m_data;
    }

    public void setLeftChild(Node<T> left_child) {
        m_left_child = left_child;
    }

    public void setRightChild(Node<T> right_child) {
        m_right_child = right_child;
    }

    public void setData(T data) {
        m_data = data;
    }

}