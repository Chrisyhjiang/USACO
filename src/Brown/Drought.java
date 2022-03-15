package Brown;

import java.io.*;
public class Drought {
	static int[] hungry; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			int m = Integer.parseInt(br.readLine());// # of cows
			String[] line = br.readLine().split(" ");
			hungry = new int[m];
			int min = Integer.MAX_VALUE;
			long total = 0;
			for(int j = 0; j < line.length; j++) {
				int num = Integer.parseInt(line[j]);
				hungry[j] = num;
				total += num;
				min = Math.min(min, num);
			}
			long ans = -1;
			int k = binSearch(0, min);
			if(k != -1) {
				ans = total - ((long)(m)) * k;
			}
			System.out.println(ans);
		}
	}
	public static int isValid(int target) {
		// use caching to prevent arrays.copy
		int result = 0; // 0 means true
		int c = hungry[0];
		for(int i = 0; i < hungry.length - 1; i++) {
			int dif = c - target;
			if (i == hungry.length - 2) {
				int delta = hungry[i+1] - dif; 
				if (delta > target) {
					result = 1; // target is too small
					break;
				}else if (delta < target) {
					result = 2; // target is too big. 
					break;
				}
			}else {
				c = hungry[i+1] - dif;
				if(c < target) {
					result = 2;
					break;
				}
			}
		}
		return result;		
	}
	
	public static int binSearch(int start, int end) {
//		modified binary search to find optimal solution
		int result = -1;
		if(start <= end) {
			int mid = (start + end)/2;
			if(isValid(mid) == 0) {
				result = mid;
				int n = binSearch(mid + 1, end);
				result = Math.max(result, n);
			}else if (isValid(mid) == 1) {
				result = binSearch(mid + 1, end);
			}else {
				result = binSearch(start, mid - 1);
			}
		}
		return result;
	}
}
