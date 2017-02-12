import java.io.*;
import java.util.*;

public class p1 {
	static private class Node {
		int val;
		Node left, right;
		
		Node (int val) {
			this.val = val;
			left = null;
			right = null;
		}
	}
	
	private static Node root;
	private static String[] v;
	private static ArrayList<Integer> w = new ArrayList<Integer>();
	private static int pos;
	
	private static void read() throws Exception {
		File fin = new File("date.in");
		Scanner sc = null;
		
		try {
			sc = new Scanner(fin);
			v = sc.nextLine().split("[^a-zA-Z0-9]+");
			
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
	
	static void add(Node nod) {
		if (pos <= v.length) {
			if (v[pos].compareTo("null") != 0) {
				Node aux = new Node(Integer.valueOf(v[pos]));
				++pos;
				nod.left = aux;
				add(aux);
			} else {
				++pos;
			}
			
			if (v[pos].compareTo("null") != 0) {
				Node aux = new Node(Integer.valueOf(v[pos]));
				++pos;
				nod.right = aux;
				add(aux);
			} else {
				++pos;
			}
		}
	}
	
	static void inordine(Node nod) {
		if (nod == null) {
			return;
		}
		
		inordine(nod.left);
		w.add(nod.val);
		inordine(nod.right);
	}
	
	private static void solve() throws Exception {
		Writer wr = new FileWriter("date.out");
		
		if (v.length >= 1) {
			Node aux = new Node(Integer.valueOf(v[0]));
			root = aux;
		} else {
			root = null;
		}
		
		pos = 1;
		add(root);
		
		inordine(root);
		
		
		boolean ok = true;
		for (int i = 1; i < w.size() && ok; ++i) {
			if (w.get(i).compareTo(w.get(i - 1)) < 0) {
				ok = false;
			}
		}
		
		if (ok == true) {
			wr.write("da");
		} else {
			wr.write("nu");
		}
		
		wr.close();
	}
	
	public static void main(String[] args) throws Exception {
		read();
		solve();
	}
}