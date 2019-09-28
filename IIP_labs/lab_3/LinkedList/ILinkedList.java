package LinkedList;

public interface ILinkedList<T> {

    int     size();
    void    clear();
    boolean empty();

    void    insert(int position, T value);
    void    insert(int position, ILinkedList init_list);

    void    erase(T value);
    void    erase(int position);

    void    push_back(T value);
    void    push_front(T value);

    T       pop_back();
    T       pop_front();

}
