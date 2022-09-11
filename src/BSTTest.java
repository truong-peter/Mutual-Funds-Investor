
/**
* BSTTest.java
* @author Peter Truong
* @author Tammy Dinh
* CIS 22C Lab 5
*/
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BSTTest {

	public static void main(String[] args) {
		BST<Integer> L = new BST<>();
		Comparator<Integer> c = Comparator.comparingInt(a -> a);

		System.out.println("**Testing constructor**");
		System.out.print("should be blank(empty tree): ");
		L.preOrderPrint();

		System.out.println("**Testing insert**");
		L.insert(12, c);
		L.insert(5, c);
		L.insert(3, c);
		L.insert(1, c);
		System.out.println("Original tree: ");
		L.preOrderPrint();
		L.insert(7, c);
		L.insert(9, c);
		L.insert(8, c);
		L.insert(11, c);
		L.insert(15, c);
		L.insert(13, c);
		L.insert(14, c);
		L.insert(17, c);
		L.insert(20, c);
		L.insert(18, c);
		System.out.println("After insert: ");
		L.preOrderPrint();

		System.out.println("**Testing copy constructor**");
		BST<Integer> copy = new BST<>(L, c);
		System.out.println("Original tree: ");
		L.preOrderPrint();
		System.out.println("copy tree: ");
		copy.preOrderPrint();
		BST<Integer> copy2 = new BST<>(copy, c);
		System.out.println("copy2 tree: ");
		copy2.preOrderPrint();

		System.out.println("**Testing remove***");
		System.out.println("tree after removing 20 from copy2: ");
		copy2.remove(20, c);
		copy2.preOrderPrint();
		copy2.remove(1, c);
		System.out.println("tree after removing 1 from copy2: ");
		copy2.preOrderPrint();

		System.out.println("**Testing findMin***");
		System.out.println("finding min of original tree (1): " + L.findMin());
		System.out.println("finding min of copy2 (3): " + copy2.findMin());

		System.out.println();
		System.out.println("**Testing findMax***");
		System.out.println("finding max of original tree (20): " + L.findMax());
		System.out.println("finding max of copy2 (18): " + copy2.findMax());

		System.out.println();
		System.out.println("**Testing getHeight***");
		System.out.println("height of original tree (4): " + L.getHeight());
		System.out.println("height of copy2 tree (4): " + copy2.getHeight());

		System.out.println();
		System.out.println("**Testing getSize***");
		System.out.println("size of original tree (14): " + L.getSize());
		System.out.println("size of copy2 tree (12): " + copy2.getSize());

		System.out.println();
		System.out.println("**Testing isEmpty***");
		System.out.println("Should print false (original tree): " + L.isEmpty());
		BST<Integer> L2 = new BST<>();
		System.out.println("Should print true (L2): " + L2.isEmpty());

		System.out.println();
		System.out.println("**Testing getRoot***");
		System.out.println("Root of original tree (12): " + L.getRoot());
		System.out.println("Root of L2 (empty tree): ");
		try { // testing precondition
			L2.getRoot();
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}

		System.out.println();
		System.out.println("**Testing search***");
		System.out.println("Searching 20 in original tree (20): " + L.search(20, c));
		System.out.println("Searching 59 in original tree (null): " + L.search(59, c));

		System.out.println();
		System.out.println("**Testing preOrderPrint***");
		System.out.println("preOrderPrint of L: ");
		L.preOrderPrint();
		System.out.println("preOrderPrint of copy2: ");
		copy2.preOrderPrint();

		System.out.println();
		System.out.println("**Testing inOrderPrint***");
		System.out.println("inOrderPrint of L: ");
		L.inOrderPrint();
		System.out.println("inOrderPrint of copy2: ");
		copy2.inOrderPrint();

		System.out.println();
		System.out.println("**Testing postOrderPrint***");
		System.out.println("postOrderPrint of L: ");
		L.postOrderPrint();
		System.out.println("postOrderPrint of copy2: ");
		copy2.postOrderPrint();
	}

}
