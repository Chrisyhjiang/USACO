package Brown;

//http://usaco.org/index.php?page=viewproblem2&cpid=1109
import java.io.*;
import java.util.*;
public class ClockwiseFence {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Hashtable<String, Integer> values = new Hashtable<String, Integer>();
		values.put("NE", 1);
		values.put("ES", 1);
		values.put("SW", 1);
		values.put("WN", 1);
		values.put("EN", -1);
		values.put("SE", -1);
		values.put("WS", -1);
		values.put("NW", -1);
		
		for (int k = 0; k < n; k++) {
			char[] line = br.readLine().toCharArray();
			char prev = line[0];
			int total = 0;
			for(int i = 1; i < line.length-1; i++) {
				if (line[i]!=prev) {
					String s = Character.toString(prev) + Character.toString(line[i]);
					total += values.get(s);
					prev = line[i];
				}
			}
			if (total>0) {
				System.out.println("CW");
				
			}else {
				System.out.println("CCW");	
			}
			
		}
	}

}
