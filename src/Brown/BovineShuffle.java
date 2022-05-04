package Brown;

//http://www.usaco.org/index.php?page=viewproblem2&cpid=760
import java.io.*;
public class BovineShuffle {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		File file = new File("shuffle.in");
		BufferedReader br = new BufferedReader(new FileReader(file));
		int n = Integer.parseInt(br.readLine());
		String[] line = br.readLine().split(" ");
		int[] o = new int[n+1];
		// pad array by 1 for easy comparison
		for(int i = 1; i <= n; i++) {
			o[i] = Integer.parseInt(line[i-1]);
		}
		String[] id = br.readLine().split(" ");
		File out = new File("shuffle.out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(out)); 
		for(int i = 1; i <= n; i++) {
			// index is value so after padding can be used for direct access. 
			int index = o[o[o[i]]];
			// need carriage return every run
			bw.write(id[index-1]+"\n");
		}
		br.close();
		bw.close();
		
	}

}
