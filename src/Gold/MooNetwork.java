// USACO 2022 February Contest, Gold - Problem 3. Moo Network
// http://www.usaco.org/index.php?page=viewproblem2&cpid=1211

package Gold;

import java.io.*;
import java.util.Arrays;


public class MooNetwork {
	
	static long[][] matrix; // an matrix to represent the graph
	static int[]mst; // an array to store the parent value of the minimum spanning tree
	static boolean[] isInMST; // an array to indicate whether a node is in the minimum spanning tree
	static long[] keys; // an array to store the key value  
	static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		long[] x = new long[n];
		long[] y = new long[n];
		for(int i = 0; i < n; i++) {
			String[] data = br.readLine().split(" ");
			x[i] = Long.parseLong(data[0]);
			y[i] = Long.parseLong(data[1]);
		}
		
		matrix = new long[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0 ; j < n; j++) {
				long dist = (x[i]-x[j])*(x[i]-x[j]) + (y[i]-y[j]) * (y[i]-y[j]);
				matrix[i][j] = dist;
				matrix[j][i] = matrix[i][j];
			}
		}
		
		mst = new int[n];
		keys = new long[n];
		isInMST = new boolean [n];
		getMST();
		long result = 0;
		for(int i = 0 ; i < n; i++) {
			if(mst[i] >= 0) {
				result += matrix[mst[i]][i];
			}
		}
		System.out.println(result);

	}
	
	public static void getMST() {
		
		Arrays.fill(keys, Long.MAX_VALUE);
		keys[0] = 0;
		mst[0] = -1;
		for(int i = 0; i < n-1; i++) {
			int key = getMinKey();
			isInMST[key] = true;
			// update the key value of the neighbors
			for(int j = 0; j < n; j++) {
				if(matrix[key][j] != 0 && !isInMST[j] && matrix[key][j] < keys[j]) {
					mst[j] = key;
					keys[j] = matrix[key][j];
				}	
			}
		}
	}
	
	public static int getMinKey() {
		int result = -1;
		long minKey = Long.MAX_VALUE;
		for(int i = 0 ; i < n; i++) {
			if(!isInMST[i] && keys[i] < minKey) {
				minKey = keys[i];
				result = i;
			}
		}
		return result;
	}
}
