import java.io.*;
import java.util.*;

public class p2 {
	static private int nr, n, m;
	static private int x, y;
	
	private static void read() throws Exception {
		File fin = new File("date.in");
		Scanner sc = null;
		
		try {
			sc = new Scanner(fin);
			n = sc.nextInt();
			x = sc.nextInt();
			y = sc.nextInt();
			
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
	
	static private int[][] v;
	
	static private void dei(int xu, int xd, int yl, int yr, int gx, int gy) {
		int mx = (xu + xd) / 2;
		int my = (yl + yr) / 2;
		++m;
		
		if (yr - yl == 1) {
			if (gx == xu && gy == yl) {
				v[xu][yr] = m;
				v[xd][yl] = m;
				v[xd][yr] = m;
			}
			
			if (gx == xu && gy == yr) {
				v[xu][yl] = m;
				v[xd][yl] = m;
				v[xd][yr] = m;
			}
			
			if (gx == xd && gy == yl) {
				v[xu][yl] = m;
				v[xu][yr] = m;
				v[xd][yr] = m;
			}
			
			if (gx == xd && gy == yr) {
				v[xu][yl] = m;
				v[xu][yr] = m;
				v[xd][yl] = m;
			}
			
			return;
		}
		
		if (gx <= mx && gy <= my) {
			v[mx][my + 1] = m;
			v[mx + 1][my] = m;
			v[mx + 1][my + 1] = m;
			dei(xu, mx, yl, my, gx, gy);
			dei(xu, mx, my + 1, yr, mx, my + 1);
			dei(mx + 1, xd, yl, my, mx + 1, my);
			dei(mx + 1, xd, my + 1, yr, mx + 1, my + 1);
		}
		
		if (gx <= mx && gy >= my + 1) {
			v[mx][my] = m;
			v[mx + 1][my] = m;
			v[mx + 1][my + 1] = m;
			dei(xu, mx, yl, my, mx, my);
			dei(xu, mx, my + 1, yr, gx, gy);
			dei(mx + 1, xd, yl, my, mx + 1, my);
			dei(mx + 1, xd, my + 1, yr, mx + 1, my + 1);
		}
		
		if (gx >= mx + 1 && gy <= my) {
			v[mx][my] = m;
			v[mx][my + 1] = m;
			v[mx + 1][my + 1] = m;
			dei(xu, mx, yl, my, mx, my);
			dei(xu, mx, my + 1, yr, mx, my + 1);
			dei(mx + 1, xd, yl, my, gx, gy);
			dei(mx + 1, xd, my + 1, yr, mx + 1, my + 1);
		}
		
		if (gx >= mx + 1 && gy >= my + 1) {
			v[mx][my] = m;
			v[mx][my + 1] = m;
			v[mx + 1][my] = m;
			dei(xu, mx, yl, my, mx, my);
			dei(xu, mx, my + 1, yr, mx, my + 1);
			dei(mx + 1, xd, yl, my, mx + 1, my);
			dei(mx + 1, xd, my + 1, yr, gx, gy);
		}
	}
	
	private static void solve() throws Exception {
		Writer wr = new FileWriter("date.out");
	
		nr = 1;
		for (int i = 1; i <= n; ++i) {
			nr = nr * 2;
		}
		v = new int[nr + 1][nr + 1];
		
		m = 0;
		dei(1, nr, 1, nr, x, y);
		
		for (int i = 1; i <= nr; ++i) {
			for (int j = 1; j <= nr; ++j) {
				wr.write(v[i][j] + " ");
			}
			wr.write("\n");
		}
		
		wr.close();
	}
	
	public static void main(String[] args) throws Exception {
		read();
		solve();
	}
}