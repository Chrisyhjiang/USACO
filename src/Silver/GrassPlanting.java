package Silver;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=894
import java.io.*;
public class GrassPlanting {

	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String path = "/Users/christopherjiang/Library/Mobile Documents/com~apple~CloudDocs/Documents/Programming/USACO/";
		File file = new File("planting.in");
		BufferedReader br = new BufferedReader(new FileReader(file));
		int n = Integer.parseInt(br.readLine());
		int[] pastures = new int[n+1];
		// number of unique grass pastures must be the greatest path + 1 as all other nodes can be written by repeating other nodes
		int ans = -1;
		for(int i = 0; i < n-1; i++) {
			String[] line = br.readLine().split(" ");
			int from = Integer.parseInt(line[0]);
			int to = Integer.parseInt(line[1]);
			pastures[from]++;
			pastures[to]++;	
		}
		
		for(int i = 0; i < pastures.length; i++) {
			ans = Math.max(ans, pastures[i]);
		}
			
		File out = new File("planting.out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(out)); 
		String s = Integer.toString(ans + 1);
		bw.write(s);
		// have to close or flush the Bufferwriter for bw to write. 
		bw.close();
		br.close();
	}

}

