import binaryTree.*;
import java.util.*;

public class p5 {
	public static void main(String[] args) {
		binaryTree<Integer, Integer> bt1 = new binaryTree<Integer, Integer>();
		binaryTree<Integer, Integer> bt2 = new binaryTree<Integer, Integer>();
		bt1.insert(5, 5);
		bt1.insert(3, 3);
		bt1.insert(1, 1);
		bt1.insert(7, 7);
		bt1.insert(6, 6);
		bt1.insert(8, 8);
		bt2.insert(7, 7);
		bt2.insert(8, 8);
		bt2.insert(5, 5);
		bt2.insert(6, 6);
		bt2.insert(3, 3);
		bt2.insert(1, 1);
		boolean eq = bt1.equals(bt2);
		System.out.println(eq);
		
		binaryTree<Integer, String> bt3 = new binaryTree<Integer, String>();
		binaryTree<Integer, String> bt4 = new binaryTree<Integer, String>();
		bt3.insert(5, "a");
		bt3.insert(3, "b");
		bt3.insert(1, "c");
		bt3.insert(7, "d");
		bt3.insert(6, "e");
		bt3.insert(8, "f");
		bt4.insert(5, "a");
		bt4.insert(3, "b");
		bt4.insert(1, "c");
		bt4.insert(7, "d");
		bt4.insert(6, "e");
		bt4.insert(8, "f");
		eq = bt3.equals(bt4);
		System.out.println(eq);
	}
}
