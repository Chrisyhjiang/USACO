package Brown;

//http://www.usaco.org/index.php?page=viewproblem2&cpid=916
import java.io.*;
public class GreatRevegetation {
	
	public static void main(String[] args) throws IOException {
		File file = new File("revegetate.in");
		BufferedReader br = new BufferedReader(new FileReader(file));		
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]); // number of cows
		// padding
		int[][] pastures = new int[n+1][n+1];
		for(int i = 0; i < m; i++) {
			String[] l = br.readLine().split(" ");
			int from = Integer.parseInt(l[0]);
			int to = Integer.parseInt(l[1]);
			// use int array to denote a path instead of inserting values into arraylist. 
			pastures[from][to] = 1;
			pastures[to][from] = 1;
		}
		int[] answer = new int[n+1];
		answer[1] = 1;
		
		for(int i = 2; i <= n; i++) {
			for(int seed = 1; seed <= 4; seed++) {
				// set flag for break
				boolean flag = true;
				for(int k = 1; k <= n; k++) {
					if (pastures[i][k] == 1) { // is an edge and neighbor
						if(answer[k] == seed) { // seed already used
							flag = false;
							break;
						}
					}
				}
				if(flag) {// least seed
					answer[i] = seed;
					break;
				}
			}
		}
		File out = new File("revegetate.out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(out)); 
		for(int i = 1; i <= n; i++) {
			bw.write(Integer.toString(answer[i]));
		}
		br.close();
		bw.close();
	}
}
