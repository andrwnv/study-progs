package LinkedList.ListExceptions;

public class List_TryingDeleteFromEmptyContain extends Exception {
    public List_TryingDeleteFromEmptyContain() {
        super("Trying delete element from empty data structure.");
    }
}
