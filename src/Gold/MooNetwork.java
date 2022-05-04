// USACO 2022 February Contest, Gold - Problem 3. Moo Network
// http://www.usaco.org/index.php?page=viewproblem2&cpid=1211

package Gold;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class MooNetwork {
	static int[]parent; // an array to store the parent value of the minimum spanning tree 
	static int n;
	static TreeMap<Integer, Integer>[] elements; // an array to store the 11 rows
	static ArrayList<Edge> edges;
	static int[] xs; // an array for x-axis coordinates
	static int[] ys; // an array for y-axis coordinates

	public static void main(String[] args) throws NumberFormatException, IOException {
		// path for test data on my local machine
		String path = "/Users/chris/Downloads/prob3_gold_feb22/";
		for (int t = 1; t <= 15; t++) {
			String fn = path + t + ".in";
			File file = new File(fn);
			BufferedReader br = new BufferedReader(new FileReader(file));
			n = Integer.parseInt(br.readLine());
			parent = new int[n];
			xs = new int[n];
			ys = new int[n];
			elements = new TreeMap[11];
			// populate entries...
			for(int i = 0; i < n; i++) {
				String[] data = br.readLine().split(" ");
				xs[i] = Integer.parseInt(data[0]);
				ys[i] = Integer.parseInt(data[1]);
				if(elements[ys[i]] == null){
					elements[ys[i]] = new TreeMap<Integer, Integer>();
				}
				elements[ys[i]].put(xs[i], i);
				parent[i] = i;
			}
			// populate edges...
			edges = new ArrayList<Edge>();
			for(int i = 0; i < n; i++) {
				for(int j = 0; j <= 10; j++) {
					if(elements[j] != null) {
						/* for a given position p(x,y) we only need consider the left side of p and the max number of edges is 11
						 * for the same y-axis coordinate, take the the greatest key strictly less than the given key
						 * for the different y-axis coordinates, take the largest key which is less than or equal to the key
						 */
						Map.Entry<Integer, Integer> p = (ys[i] == j) ?  elements[j].lowerEntry(xs[i]) : elements[j].floorEntry(xs[i]);
						if(p != null) {
							long dx = p.getKey() - xs[i];
							long dy = j - ys[i];
							Edge e = new Edge(i, p.getValue(), dx*dx+dy*dy);
							edges.add(e);
						}
					}
				}
			}
			
			// Kruskal’s Minimum Spanning Tree Algorithm
			Collections.sort(edges);
			long sum = 0;
			for(Edge edge: edges) {
				int a = edge.getStart();
				int b = edge.getEnd();
				if(find(a) != find(b)){
					// merge
					sum += edge.getWeight();
					union(a, b);
					if(--n == 1) {
						break;
					}
				}
			}
			
			// check the output
			String ofn = path + t + ".out";
			File out = new File(ofn);
			BufferedReader bw = new BufferedReader(new FileReader(out));
			long expected = Long.parseLong(bw.readLine());
			System.out.println("expected: " + expected + " actual: " + sum);
			String r = (expected == sum ) ? "passed" : "failed";
			String msg = String.format("test case %d %s", t, r);
			System.out.println(msg);
			br.close();
			bw.close();
		}
	}
	
	public static int find(int curr) {
		if(parent[curr] == curr) {
			return curr;
		}else {
			return find(parent[curr]);
		}
	}
	
	public static void union(int a, int b) {
		parent[find(a)] = find(b);
	}
}

class Edge implements Comparable<Edge>{
	private long weight;
	private int start;
	private int end;
	
	public Edge(int start, int end, long wgh) {
		this.start = start;
		this.end = end;
		this.weight = wgh;
	}
	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	@Override
	public int compareTo(Edge o) {
		long diff = weight - o.getWeight();
		if (diff > 0) {;
			return 1;
		}else if (diff == 0) {
			return 0;
		}else {
			return -1;
		}
	}
}
