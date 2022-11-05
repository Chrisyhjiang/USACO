package Brown;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=1156
import java.io.*;

public class AirCowndition {
	static int[] diff = null;
	public static void main4(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// TODO Auto-generated method stub
		int n = Integer.parseInt(br.readLine());
		String[] first = br.readLine().split(" ");
		String[] second = br.readLine().split(" ");
		diff = new int[n];
		
		for (int k = 0; k < n; k++) {
			diff[k] = Integer.parseInt(second[k]) - Integer.parseInt(first[k]);
		}
		
		int i = 0;
		int j = 0;
		int result = 0;
		while (i < n) {
			// advance i as long as diff[i] is 0
			while(i < n && diff[i]==0) {
				i++;
			}
			j = i;
			// all leading zeros are eliminated. j and i are in the same position
			if (i <n) {
				int num = diff[i];
				int maxOrMin = 0;
				
				if (num > 0) {
					// advance j as long as it is positive
					while(j < n && diff[j] > 0) {
						j++;
					}
					// search for minimum between diff[i] ... diff[j]
					maxOrMin = Integer.MAX_VALUE;
					for(int a = i; a < j; a++) {
						maxOrMin = Math.min(maxOrMin, diff[a]);
					}
				}else {
					// advance j as long as it is negative
					
					while(j < n && diff[j] < 0) {
						j++;
					}
					// search for maximum between diff[i] ... diff[j]
					maxOrMin = Integer.MIN_VALUE;
					for(int a = i; a < j; a++) {
						maxOrMin = Math.max(maxOrMin, diff[a]);
					}
				}
				
				for (int b = i; b < j; b++) {
					diff[b] -= maxOrMin;
				}
				result += Math.abs(maxOrMin);
			}
			
 		}
		System.out.println(result);
	}

}
