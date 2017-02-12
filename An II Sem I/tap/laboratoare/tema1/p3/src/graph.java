import myQueue.*;
import java.io.*;
import java.util.*;

public class graph {
	private static int N, M, start;
	private static ArrayList<myQueue<Integer>> V = new ArrayList<myQueue<Integer>>();
	private static myQueue<Integer> Q = new myQueue<Integer>();
	private static ArrayList<Boolean> used = new ArrayList<Boolean>();
	
	public static void read() throws Exception {
		File fin = new File("date.in");
		Scanner sc = null;
		
		try {
			sc = new Scanner(fin);
			
			String str = sc.nextLine();
			String[] numbers = str.split(" ");
			N = Integer.parseInt(numbers[0]);
			M = Integer.parseInt(numbers[1]);
			
			for (int i = 0; i <= N; ++i) {
				myQueue<Integer> deque = new myQueue<Integer>();
				V.add(deque);
				used.add(false);
			}
			
			for (int i = 1; i <= M; ++i) {
				str = sc.nextLine();
				numbers = str.split(" ");
				int nod1 = Integer.parseInt(numbers[0]);
				int nod2 = Integer.parseInt(numbers[1]);
				V.get(nod1).add(nod2);
				V.get(nod2).add(nod1);
			}
			
			str = sc.nextLine();
			numbers = str.split(" ");
			start = Integer.parseInt(numbers[0]);
			
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
	
	private static void bfs(int nod) throws Exception {
		Writer wr = new FileWriter("date.out", true);
		used.set(nod, true);
		Q.add(nod);
		
		while (!Q.isEmpty()) {
			int now = Q.remove();
			wr.write(String.valueOf(now) + " ");
			
			for (int i : V.get(now)) {
				if (!used.get(i)) {
					used.set(i, true);
					Q.add(i);
				}
			}
		}
		
		wr.close();
	}
	
	private static void write() throws Exception {
		Writer wr = new FileWriter("date.out");
		for (int i = 1; i <= N; ++i) {
			wr.write(String.valueOf(i) + ": ");
			
			for (int j : V.get(i)) {
				wr.write(String.valueOf(j) + " ");
			}
			
			wr.write("\n");
		}
		wr.close();
	}
	
	public static void main(String[] args) throws Exception{
		read();
		write();
		bfs(start);
		
		myQueue<Integer> qInt = new myQueue<Integer>();
		qInt.add(5);
		qInt.add(6);
		qInt.add(7);
		qInt.remove();
		for (int it : qInt) {
			System.out.println(it);
		}
		
		myQueue<String> qStr = new myQueue<String>();
		qStr.add("Ana");
		qStr.add("Ionut");
		qStr.add("mere");
		qStr.remove();
		qStr.remove();
		for (String it : qStr) {
			System.out.println(it);
		}
		
		myQueue<myQueue<Integer>> q = new myQueue<myQueue<Integer>>();
		myQueue<Integer> q1 = new myQueue<Integer>();
		q1.add(1);
		q1.add(2);
		myQueue<Integer> q2 = new myQueue<Integer>();
		q2.add(3);
		q2.add(4);
		myQueue<Integer> q3 = new myQueue<Integer>();
		q2.add(5);
		q2.add(6);
		q.add(q1);
		q.add(q2);
		q.add(q3);
		q.remove();
		for (myQueue<Integer> it : q) {
			for (int itt : it) {
				System.out.println(itt);	
			}
		}
	}
}
