import BinaryTree.BinaryTree;

public class Main {

    public static void main(String[] args) {
        BinaryTree<Integer> test = new BinaryTree<Integer>();
        test.addItem(10);
        test.addItem(55);
        test.addItem(2);
        test.addItem(3);

        test.deleteItem(10);

        test.DFS_print();
    }
}
