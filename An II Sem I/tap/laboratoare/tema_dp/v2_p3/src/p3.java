import java.io.*;
import java.util.*;

public class p3 {
	private static int n;
	private static final int INF = 0x3f3f3f3f;
	private static ArrayList<String> v; 
	private static String s;
	//dp[i] - numarul minim de siruri cu care poate fi descompus subsirul s(0, i)
	//dp[i] = 1, daca subsirl s(0, i) exista in dictionar
	//        1 + dp[j], 1 <= j <= i, in caz ca subsirul s(j, i) exista in dictionar
	private static int[] dp;
	private static pair[] pre;
	
	private static class pair {
		int pos, posv;
		
		pair() {
			pos = 0;
			posv = 0;
		}
	}
	
	private static void read() throws Exception {
		File fin = new File("date.in");
		Scanner sc = null;
		
		sc = new Scanner(fin);
		n = sc.nextInt();
		sc.nextLine();
		v = new ArrayList<String>(n + 1);
		v.add("0");
		v.add("1");
		for (int i = 1; i <= n; ++i) {
			String str = sc.nextLine();
			v.add(str);
		}
		
		if (sc != null) {
			sc.close();
		}
		
		fin = new File("cod.in");
		sc = null;
		sc = new Scanner(fin);
		
		s = sc.nextLine();
		
		dp = new int[s.length()];
		pre = new pair[s.length()];
		for (int i = 0; i < s.length(); ++i) {
			pre[i] = new pair();
		}
		
		if (sc != null) {
			sc.close();
		}
	}
	
	private static int check_string(String word) {
		for (int i = 0; i < n + 2; ++i) {
			if (word.compareTo(v.get(i)) == 0) {
				return i + 1;
			}
		}
		
		return 0;
	}
	
	private static void solve() {
		for (int i = 0; i < s.length(); ++i) {
			dp[i] = INF;
		}
		
		for (int i = 0; i < s.length(); ++i) {
			int p = check_string(s.substring(0, i + 1));
			if (p != 0) {
				dp[i] = 1;
				pre[i].pos = 0;
				pre[i].posv = p - 1;
			}
			
			for (int j = i + 1; j < s.length(); ++j) {
				p = check_string(s.substring(i + 1, j + 1));
				if (p != 0 && dp[i] + 1 < dp[j]) {
					dp[j] = dp[i] + 1;
					pre[j].pos = i;
					pre[j].posv = p - 1;
				}
			}
		}
	}
	
	private static void write_path(int pos, Writer wr) throws Exception {
		if (pos == 0) {
			return;
		}
		
		write_path(pre[pos].pos, wr);
		if (pos == s.length() - 1)
			wr.write(v.get(pre[pos].posv));
		else
			wr.write(v.get(pre[pos].posv) + " + ");
	}
	
	private static void write() throws Exception {
		Writer wr = new FileWriter("date.out");
		
		wr.write(dp[s.length() - 1] + "\n");
		write_path(s.length() - 1, wr);
		
		wr.close();
	}
	
	public static void main(String[] args) throws Exception {
		read();
		solve();
		write();
	}
}
