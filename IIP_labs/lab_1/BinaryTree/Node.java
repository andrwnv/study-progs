package BinaryTree;

class Node<T> {

    private T    m_data;
    private Node m_left_child;
    private Node m_right_child;

    public Node(Node right_child, Node left_child, T data) {
        m_data = data;

        m_left_child  = left_child;
        m_right_child = right_child;
    }

    public Node(T data) {
        m_data = data;

        m_right_child = null;
        m_left_child  = null;
    }

    public Node getLeftChild() {
        return m_left_child;
    }

    public Node getRightChild() {
        return m_right_child;
    }

    public T getData() {
        return m_data;
    }

    public void setLeftChild(Node left_child) {
        m_left_child = left_child;
    }

    public void setRightChild(Node right_child) {
        m_right_child = right_child;
    }

    public void setData(T data) {
        m_data = data;
    }

}