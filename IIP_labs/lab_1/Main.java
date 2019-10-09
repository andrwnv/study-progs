import BinaryTree.BinaryTree;
import org.junit.platform.engine.support.hierarchical.Node;

public class Main {

    public static void main(String[] args) {
        BinaryTree<Integer> test = new BinaryTree<Integer>();
        test.addItem(10);
        test.addItem(55);
        test.addItem(2);
        test.addItem(0);

        test.deleteItem(0);
        test.DFS_print(test.getHead());

        System.out.println( test.searchItem(2) );
    }
}
