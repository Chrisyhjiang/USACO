package Brown;

import java.io.*;
import java.util.Arrays;
public class photoshoot {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
//		String path = "/Users/christopherjiang/Desktop/photo_bronze_jan20/";
		File file = new File("photo.in");
		BufferedReader br = new BufferedReader(new FileReader(file));
		int n = Integer.parseInt(br.readLine());
		String[] line = br.readLine().split(" ");
		int[] src= new int[line.length];
		
		for(int i = 0; i < src.length; i++) {
			src[i] = Integer.parseInt(line[i]);
		}
		
		int k = src[0];
		int[] result = new int[n];
		// occur has to be large enough for all the values. 
		boolean[] occur = new boolean[1000000];
		
		for(int i = 1; i < k; i++) {
			result[0] = i; 
			for(int j = 1; j < result.length; j++) {
				int m = src[j-1] - result[j-1];
				if(m > 0) {
					// uses the found element as the index of an array. 
					if (!occur[m-1]) {
						// value has occured
						occur[m-1] = true;
						result[j] = m;
					}else {
						// reset the arrays in case they dont work. 
						// only set the first value as -1 as that is the exist condition.
						result[0] = -1;
						break;
					}
				}else {
					result[0] = -1;
					break;
				}
			}
			// greedy algorithm ensures that the smallest i will be the optimal solution
			// thus once found there is not need to check anymore. 
			if(result[0] != -1) {
				break;
			}else {
				Arrays.fill(occur, false);
			}
		}
		File out = new File("photo.out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(out));
		String s = "";
		for(int a: result) {
			s += a + " ";
		}
		s = s.trim();
		bw.write(s);
		bw.close();
		br.close();
	}

}
