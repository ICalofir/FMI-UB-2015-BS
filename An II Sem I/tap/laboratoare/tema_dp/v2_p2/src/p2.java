import java.io.*;
import java.util.*;

public class p2 {
	private static int n, m;
	//dp[i][j] - suma maxima obtinuta pe linia i si coloana j
	//dp[i][j] = v[i][j] + max(dp[i + 1][j], dp[i][j - 1])
	private static int[][] v, dp;
	private static pair[][] pre;
	
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
		
		n = sc.nextInt();
		m = sc.nextInt();
		v = new int[n + 1][m + 1];
		dp = new int[2][m + 1];
		pre = new pair[n + 1][m + 1];
		
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				v[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				pre[i][j] = new pair();
			}
		}
		
		if (sc != null) {
			sc.close();
		}
	}
	
	private static void solve() {
		dp[0][1] = v[n][1];
		for (int j = 2; j <= m; ++j) {
			dp[0][j] = dp[0][j - 1] + v[n][j];
			pre[n][j].i = n;
			pre[n][j].j = j - 1;
		}
		
		for (int i = n - 1, l = 1; i >= 1; --i, l = 1 - l) {
			dp[l][1] = dp[1 - l][1] + v[i][1];
			pre[i][1].i = i + 1;
			pre[i][1].j = 1;
			for (int j = 2; j <= m; ++j) {
				if (dp[l][j - 1] > dp[1 - l][j]) {
					dp[l][j] = dp[l][j - 1] + v[i][j];
					pre[i][j].i = i;
					pre[i][j].j = j - 1;
				} else {
					dp[l][j] = dp[1 - l][j] + v[i][j];
					pre[i][j].i = i + 1;
					pre[i][j].j = j;
				}
			}
		}
	}
	
	private static void write_path(int i, int j, Writer wr) throws Exception {
		if (i == 0 && j == 0) {
			return ;
		}
		
		write_path(pre[i][j].i, pre[i][j].j, wr);
		wr.write(i + " " + j + "\n");
	}
	
	private static void write() throws Exception {
		Writer wr = new FileWriter("date.out");
		
		wr.write(dp[0][m] + "\n");
		write_path(1, m, wr);
		
		wr.close();
	}
	
	public static void main(String[] args) throws Exception {
		read();
		solve();
		write();
	}
}
