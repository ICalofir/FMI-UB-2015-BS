import java.io.*;
import java.util.*;

public class p4 {
	private static int n;
	private static final long INF = 4000000000000000000L;
	
	private static class pair implements Comparable<pair> {
		long first, second;
		
		pair(long first, long second) {
			this.first = first;
			this.second = second;
		}
		
		public int compareTo(pair obj) {
			if (first == obj.first) {
				if (second > obj.second) {
					return -1;
				} else if (second < obj.second) {
					return 11;
				}
				return 0;
			}
			
			if (first > obj.first) {
				return -1;
			} else if (first < obj.first) {
				return 1;
			}
			return 0;
		}
	}
	
	static ArrayList<pair> v = new ArrayList<pair>();
	static ArrayList<pair> w = new ArrayList<pair>();
	static ArrayList<pair> m = new ArrayList<pair>();
	static ArrayList<pair> aux = new ArrayList<pair>();
	
	private static void read() throws Exception {
		File fin = new File("date.in");
		Scanner sc = null;
		
		try {
			sc = new Scanner(fin);
			n = sc.nextInt();
			
			for (int i = 1; i <= n; ++i) {
				long x, y;
				x = sc.nextLong();
				y = sc.nextLong();
				pair p1 = new pair(x, y);
				pair p2 = new pair(y, x);
				v.add(p1);
				w.add(p2);
			}
			
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
	
	private static long get_dist(pair a, pair b) {
		return (a.first - b.first) * (a.first - b.first) + (a.second - b.second) * (a.second - b.second);
	}
	
	private static long minim (long a, long b) {
		if (a < b)
			return a;
		return b;
	}
	
	private static void merge(int lt, int mid, int rt) {
		int i = lt;
		int j = mid;
		
		while (i < mid || j <= rt) {
			if (i < mid && j <= rt && w.get(i).first < w.get(j).first) {
				m.add(w.get(i));
				++i;
				continue;
			}
			if (i == mid) {
				while (j <= rt) {
					m.add(w.get(j));
					++j;
				}
				break;
			}
			
			if (j <= rt && i < mid && w.get(j).first < w.get(i).first) {
				m.add(w.get(j));
				++j;
				continue;
			}
			if (j == rt + 1) {
				while (i < mid) {
					m.add(w.get(i));
					++i;
				}
				break;
			}
		}
	}
	
	private static long dei(int lt, int rt) {
		if (lt == rt) {
			return INF;
		} else if (rt - lt == 1) {
			return get_dist(v.get(lt), v.get(rt));
		} else if (rt - lt == 2) {
			return minim(get_dist(v.get(lt), v.get(rt)), minim(get_dist(v.get(lt), v.get(lt + 1)), get_dist(v.get(lt + 1), v.get(rt))));
		}
		
		int mid = (lt + rt) / 2;
		long sol = minim(dei(lt, mid), dei(mid, rt));
		m.clear();
		merge(lt, mid, rt);
		aux.clear();
		for (pair p : m) {
			if (Math.abs(p.second - v.get(mid).first) <= sol) {
				pair pp = new pair(p.second, p.first);
				aux.add(pp);
			}
		}
		
		for (int i = 0; i < aux.size() - 1; ++i) {
			for (int j = i + 1; j < aux.size() && j - i > 8; ++j) {
				sol = minim(sol, get_dist(aux.get(i), aux.get(j)));
			}
		}
		
		return sol;
	}
	
	private static void solve() throws Exception {
		Writer wr = new FileWriter("date.out");
		
		Collections.sort(v);
		Collections.sort(w);
		
		long sol = dei(0, v.size() - 1);
		double dsol = Math.sqrt(sol);
		wr.write(dsol + "\n");
		
		wr.close();
	}
	
	public static void main(String[] args) throws Exception {
		read();
		solve();
	}
}