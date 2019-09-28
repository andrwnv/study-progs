package LinkedList;

public class DoubleListNode<T> implements INode<T> {

    private T       m_data;
    private DoubleListNode<T> m_next;
    private DoubleListNode<T> m_prev;

    public DoubleListNode() {
        m_data = null;

        m_next = null;
        m_prev = null;
    }

    public DoubleListNode(T value) {
        m_data = value;

        m_next = null;
        m_prev = null;
    }

    public DoubleListNode(T value, DoubleListNode<T> next_node, DoubleListNode<T> prev_node) {
        m_data = value;

        m_next = next_node;
        m_prev = prev_node;
    }

    @Override
    public T getValue() {
        return m_data;
    }

}
