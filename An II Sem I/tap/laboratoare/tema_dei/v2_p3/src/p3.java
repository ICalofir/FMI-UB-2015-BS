import java.io.*;
import java.util.*;

public class p3 {
	private static int n, sol;
	private static double WL, WR;
	private static class pair {
		int x;
		double w;
	}
	
	private static ArrayList<pair> v = new ArrayList<pair>();
	
	private static void swap(int i, int j) {
		pair aux = v.get(i);
		v.set(i, v.get(j));
		v.set(j, aux);
	}
	
	private static int partition(int lt, int rt) {
		int pivot = v.get(rt).x;
		int i = lt;
		for (int j = lt; j <= rt - 1; ++j) {
			if (v.get(j).x <= pivot) {
				swap(i, j);
				i += 1;
			}
		}
		swap(i, rt);
		
		return i;
	}
	
	private static double sum(int lt, int rt) {
		double s = 0.0;
		for (int i = lt; i <= rt; ++i) {
			s += v.get(i).w;
		}
		
		return s;
	}
	
	private static void WeightedMedian(int lt, int rt) {
		if (lt == rt)
			return;
		
		int q = partition(lt, rt);
		double wl = sum(lt, q - 1) + WL;
		double wr = sum(q + 1, rt) + WR;
		
		if (wl < 0.5 && wr <= 0.5) {
			sol = v.get(q).x;
			return;
		} else {
			if (wl > wr) {
				WR += wr;
				WeightedMedian(lt, q - 1);
			} else {
				WL += wl;
				WeightedMedian(q + 1, rt);
			}
		}
	}
	
	private static void read() throws Exception {
		File fin = new File("date.in");
		Scanner sc = null;
		
		try {
			sc = new Scanner(fin);
			n = sc.nextInt();
			
			for (int i = 1; i <= n; ++i) {
				int x = sc.nextInt();
				pair aux = new pair();
				
				aux.x = x;
				v.add(aux);
			}
			
			for (int i = 1; i <= n; ++i) {
				double w = sc.nextDouble();
				pair aux = new pair();
				
				aux.x = v.get(i - 1).x;
				aux.w = w;
				v.set(i - 1, aux);
			}
			
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
	
	private static void solve() throws Exception {
		Writer wr = new FileWriter("date.out");
		
		WeightedMedian(0, n - 1);
		wr.write(sol + "\n");
		
		wr.close();
	}
	
	public static void main(String[] args) throws Exception {
		read();
		solve();
	}
}