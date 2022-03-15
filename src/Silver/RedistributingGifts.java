package Silver;
// http://www.usaco.org/index.php?page=viewproblem2&cpid=1206
import java.io.*;
import java.util.*;
public class RedistributingGifts {
	static boolean[][] reachable;
	static ArrayList<ArrayList<Integer>> graph;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		graph = new ArrayList<ArrayList<Integer>>();
		reachable = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			ArrayList<Integer> ls = new ArrayList<Integer>();
			String[] line = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				int num = Integer.parseInt(line[j]);
				if (num != i+1) {
					ls.add(num);
				}else {
					ls.add(num);
					break;
				}
			}
			//only add needed numbers
			graph.add(ls);
		}
		
		for(int i = 0; i < n; i++) {
			dfs(i,i);
		}
		
		for(int i = 0; i < n; i++) {
			ArrayList<Integer> ls = graph.get(i);
			for (int g: ls) {
				// greedy checking always ensures that it is the best path.
				// invert the index to verify that it is cyclic. 
				if (reachable[g-1][i]) {
					System.out.println(g);
					break;
				}
			}
		}
	}
	
	public static void dfs (int src, int cur) {
		// reachable is visited as it wouldnt call in if it was reachable. src also changes
		if(!reachable[src][cur]) {
			reachable[src][cur] = true;
			ArrayList<Integer> ls = graph.get(cur);
			for(int neighbor: ls) {
				dfs(src, neighbor - 1);
			}
		}
		
	}

}

//class Node{
//	private int id;
//	private ArrayList<Integer> neighbors;
//	
//	public Node(int i) {
//		id = i;
//		neighbors = new ArrayList<Integer>();
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public ArrayList<Integer> getNeighbors() {
//		return neighbors;
//	}
//
//	public void setNeighbors(ArrayList<Integer> neighbors) {
//		this.neighbors = neighbors;
//	}
//	
//}
