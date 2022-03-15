package Silver;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=762
import java.io.*;
import java.util.ArrayList;
public class Homework {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		File file = new File("homework.in");
		BufferedReader br = new BufferedReader(new FileReader(file));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] line = br.readLine().split(" ");
		
		int[] min = new int[n];
		int[] sum = new int[n];
		// prefix sum array but backwards. since we are tracking everything after index
		sum[n-1] = Integer.parseInt(line[n-1]);
		min[n-1] = Integer.parseInt(line[n-1]);
		
		for(int i = n-2; i >= 0; i--) {
			int k = Integer.parseInt(line[i]);
			sum[i] = sum[i+1] + k;
			min[i] = Math.min(k, min[i+1]);
		}
		double maxAvg = 0.0;
		// use arraylist to keep track of maximum position
		ArrayList<Integer> pos = new ArrayList<Integer>();
		for(int i = 1; i < n-1; i++) {
			int length = n-i-1;
			double cur = (double)(sum[i] - min[i]) / length;
			if(cur > maxAvg) {
				// clear removes all elements
				pos.clear();
				pos.add(i);
				maxAvg = cur;
			}else if (cur == maxAvg) {
				pos.add(i);
			}
		}
		
		File out = new File("homework.out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(out)); 
		for(int a: pos) {
			bw.write(Integer.toString(a) + "\n");
		}
		br.close();
		bw.close();
	}

}
