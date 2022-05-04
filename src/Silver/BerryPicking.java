package Silver;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
public class BerryPicking {
	static int n;
	static int k;
	static int[] cr;
	public static void main(String[] args) throws IOException {
			File file = new File("berries.in");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String[] line = br.readLine().split(" ");
			n = Integer.parseInt(line[0]);
			k = Integer.parseInt(line[1]);
			String[] line2 = br.readLine().split(" ");
			br.close();
			cr = new int[n];
			int maxCap = -1;
			int result = 0;
			
			for(int j = 0; j < cr.length; j++) {
				int num = Integer.parseInt(line2[j]);
				cr[j] = num;
				// max capacity as big as the largest tree. 
				maxCap = Math.max(maxCap, num);
			}
			for (int j = 1; j <= maxCap; j++) {
				int h = getMaxCherry2(j);
				result = Math.max(result, h);
			}
			File out = new File("berries.out");
			BufferedWriter bw = new BufferedWriter(new FileWriter(out)); 
			bw.write(Integer.toString(result));
			bw.close();
	}
	public static int getMaxCherry(int c) {
		int result = 0;
		int full = 0;
		ArrayList<Integer> notFull = new ArrayList<Integer>();
		for(int i = 0; i < cr.length; i++) {
			int cherry = cr[i];
			int buckets = cherry / c; // # of buckets with highest capcity.
			int leftover = cherry % c; 
			full += buckets;
			if(full >= k) {
				break;
			}
			
			if (leftover > 0){
				notFull.add(leftover);
			}
		}
		
		if (full >= k) {
			result = k / 2 * c; 
		}else {
			Collections.sort(notFull);
			int diff = k - full;
			if (full >= k/2) {
				
				result += (full - k/2) * c;
				int start = Math.max(0,notFull.size() - diff);
				for(int i = start; i < notFull.size(); i++) {
					result += notFull.get(i); 
				}
				
			}else{
				int start = Math.max(0, notFull.size() - k + full);
				int end = Math.min(start + k/2, notFull.size());
				for(int i = start; i < end; i++) {
					result += notFull.get(i);
				}
			}
		}
		return result;
	}
	
	public static int getMaxCherry2(int c) {
		int result = 0;
		ArrayList<Integer> full = new ArrayList<Integer>();
		ArrayList<Integer> notFull = new ArrayList<Integer>();
		for(int i = 0; i < cr.length; i++) {
			int cherry = cr[i];
			int buckets = cherry / c; // # of buckets with highest capcity.
			int leftover = cherry % c;
			for(int j = 0; j < buckets; j++) {
				full.add(c);
			}
			if(full.size() >= k) {
				break;
			}
			if (leftover > 0){
				// only add when there is no 0. 
				notFull.add(leftover);
			}
		}
		if (full.size() >= k) {
			result = k / 2 * c; 
		}else {
			Collections.sort(notFull);
			// combines all the lists
			notFull.addAll(full);
			int start = 0;
			int end = notFull.size()/2;
			if (notFull.size() >= k) {
				start = notFull.size() - k;
				end = notFull.size() - k/2;
			}
			for(int i = start; i < end; i++) {
				result += notFull.get(i);
			}
		}
		return result;
	}
}
