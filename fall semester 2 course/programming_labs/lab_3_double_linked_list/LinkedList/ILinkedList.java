package LinkedList;

public interface ILinkedList<T> {

    int     size();
    void    clear();
    boolean empty();

    boolean insert(int position, T value) throws Exception;
    boolean erase(int position) throws Exception;

    void    push_back(T value);
    void    push_front(T value);

    T       pop_back() throws Exception;
    T       pop_front() throws Exception;

    INode   getHead();
}
