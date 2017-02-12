import java.io.*;
import java.util.*;

public class graph {
	private static int N, M, start;
	private static ArrayList<ArrayDeque<Integer>> V = new ArrayList<ArrayDeque<Integer>>();
	private static ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
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
				ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
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
	
	public static void main(String[] args) throws Exception {
		read();
		write();
		bfs(start);
	}
}
