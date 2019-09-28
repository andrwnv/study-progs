package LinkedList;

public final class DoubleLinkedList<T> implements ILinkedList<T> {

    private DoubleListNode<T> m_head_node;
    private DoubleListNode<T> m_tail_node;
    private int     m_size;

    public DoubleLinkedList() {
        m_head_node = new DoubleListNode<>();
        m_tail_node = new DoubleListNode<>();

        m_size      = 0;
    }

    public DoubleLinkedList(DoubleListNode<T> head_node) {
        m_head_node = head_node;
        m_tail_node = head_node;

        m_size      = 1;
    }

    @Override
    public int size() {
        return m_size;
    }

    @Override
    public void clear() {
        m_head_node = null;
        m_tail_node = null;
    }

    @Override
    public boolean empty() {
        return m_head_node == null && m_tail_node == null;
    }

    @Override
    public void insert(int position, T value) {

    }

    @Override
    public void insert(int position, ILinkedList init_list) {

    }

    @Override
    public void erase(T value) {

    }

    @Override
    public void erase(int position) {

    }

    @Override
    public void push_back(T value) {

    }

    @Override
    public void push_front(T value) {

    }

    @Override
    public T pop_back() {
         T deleted_value = m_tail_node.getValue();

        // TODO: delete tail

         return deleted_value;
    }

    @Override
    public T pop_front() {
        T deleted_value = m_head_node.getValue();

        // TODO: delete head

        return deleted_value;
    }

}
