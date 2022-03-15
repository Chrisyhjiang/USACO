package Brown;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=1011#
import java.io.*;
public class Triangle2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
//		String path = "/Users/christopherjiang/Desktop/triangles_bronze_feb20/";
		File file = new File("triangles.in");
		BufferedReader br = new BufferedReader(new FileReader(file));
		int n = Integer.parseInt(br.readLine());
		int[] x = new int[n];
		int[] y = new int[n];
		
		for(int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			x[i] = Integer.parseInt(line[0]);
			y[i] = Integer.parseInt(line[1]);
		}
		// i will be the point, 
		// j will be on the same x as i
		// k will be on the same y as i
		int ma = -1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k< n; k++) {
					if((x[j] == x[i])&&(y[k]==y[i])) {
						int area = Math.abs(y[i]-y[j]) * Math.abs(x[i]-x[k]);
						ma = Math.max(ma, area);
					}
				}
			}
		}
		File out = new File("triangles.out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(out)); 
		bw.write(Integer.toString(ma));
		br.close();
		bw.close();

	}

}
