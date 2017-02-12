import java.util.*;
import java.io.*;

public class p4 {
	private static String A, B;
	private static int space, other;
	private static ArrayList<String> up, down;
	// dp[i][j] - penalizarea alinierii prefixului format din primele i litere din A cu primele j litere din B
	// dp[i][j] = minim(space + dp[i - 1][j], space + dp[i][j - 1], (0 sau penalizare_pereche) + dp[i - 1][j - 1]) i, j >= 1
	// dp[i][0] = i * spatiu;
	// dp[0][j] = j * spatiu;
	private static int[][] dp;
	private static pair[][] pre;
	private static HashMap<String, Integer> h = new HashMap<String, Integer>();
	
	private static class pair {
		int i, j;
		
		pair() {
			i = 0;
			j = 0;
		}
	}
	
	private static void read() throws Exception {
		File fin = new File("date.in");
		Scanner sc = null;
		
		sc = new Scanner(fin);
		
		A = sc.nextLine();
		B = sc.nextLine();
		
		space = sc.nextInt();
		other = sc.nextInt();
		sc.nextLine();
		
		while (sc.hasNextLine()) {
			String str = sc.nextLine();
			int nr = sc.nextInt();
			if (sc.hasNextLine()) {
				sc.nextLine();
			}
			h.put(str, nr);
		}
		
		up = new ArrayList<String>();
		down = new ArrayList<String>();
		
		dp = new int[2][B.length() + 1];
		pre = new pair[A.length() + 1][B.length() + 1];
		for (int i = 0; i <= A.length(); ++i) {
			for (int j = 0; j <= B.length(); ++j) {
				pre[i][j] = new pair();
			}
		}
		
		if (sc != null) {
			sc.close();
		}
	}
	
	private static void solve() {
		System.out.print("0 ");
		for (int j = 1; j <= B.length(); ++j) {
			dp[0][j] = j * space;
			pre[0][j].i = 0;
			pre[0][j].j = j - 1;
			System.out.print(dp[0][j] + " ");
		}
		System.out.println();
		
		for (int i = 1, l = 1; i <= A.length(); ++i, l = 1 - l) {
			dp[l][0] = i * space;
			System.out.print(dp[l][0] + " ");
			for (int j = 1; j <= B.length(); ++j) {
				int p;
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					p = 0;
				} else {
					p = other;
				}
				if (h.get(A.charAt(i - 1) + "" + B.charAt(j - 1)) != null) {
					p = h.get(A.charAt(i - 1) + "" + B.charAt(j - 1));
				}
				if (h.get(B.charAt(j - 1) + "" + A.charAt(i - 1)) != null) {
					p = h.get(B.charAt(j - 1) + "" + A.charAt(i - 1));
				}
				
				int minim = p + dp[1 - l][j - 1];
				pre[i][j].i = i - 1;
				pre[i][j].j = j - 1;
				
				if (space + dp[1 - l][j] < minim) {
					minim = space + dp[1 - l][j];
					pre[i][j].i = i - 1;
					pre[i][j].j = j;
				}
				
				if (space + dp[l][j - 1] < minim) {
					minim = space + dp[l][j - 1];
					pre[i][j].i = i;
					pre[i][j].j = j - 1;
				}
				
				dp[l][j] = minim;
				
				System.out.print(minim + " ");
			}
			System.out.println();
		}
	}
	
	private static void write_path(int i, int j, Writer wr) throws Exception {
		if (i == 0 && j == 0) {
			return;
		}
		
		if (pre[i][j].i == i - 1 && pre[i][j].j == j - 1) {
			up.add(A.charAt(i - 1) + "");
			down.add(B.charAt(j - 1) + "");
			write_path(i - 1, j - 1, wr);
		} else if (pre[i][j].i == i - 1) {
			up.add(A.charAt(i - 1) + "");
			down.add("-");
			write_path(i - 1, j, wr);
		} else {
			up.add("-");
			down.add(B.charAt(j - 1) + "");
			write_path(i, j - 1, wr);
		}
	}
	
	private static void write() throws Exception {
		Writer wr = new FileWriter("date.out");
		
		if (A.length() % 2 == 0) {
			wr.write(dp[0][B.length()] + "\n");
		} else {
			wr.write(dp[1][B.length()] + "\n");
		}
		
		write_path(A.length(), B.length(), wr);
		Collections.reverse(up);
		Collections.reverse(down);
		for (String w : up) {
			wr.write(w);
		}
		wr.write("\n");
		for (String w : down) {
			wr.write(w);
		}
		
		wr.close();
	}
	
	public static void main(String[] args) throws Exception {
		read();
		solve();
		write();
	}
}
