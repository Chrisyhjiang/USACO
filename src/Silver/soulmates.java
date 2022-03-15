package Silver;

import java.io.*;
public class soulmates {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			long a = Long.parseLong(line[0]);
			long b = Long.parseLong(line[1]);
			int total = Integer.MAX_VALUE;
			int lStep = 0;
			while (a > b) {
				// logic used to lower a number
				if(a % 2 == 1) {
					lStep++;
					a++;
				}
				a /= 2;
				lStep++;
			}
			while (a > 0) {
				int rStep = rightStep(a, b);
				int curTotal = lStep + rStep;
				if(curTotal >= total) {
					break;
				}else {
					total = curTotal;
				} // run for each multiple of 2 log n time. 
				if(a % 2 == 1) {
					lStep++;
					a++;
				}
				a /= 2;
				lStep++;
			}
			System.out.println(total);
		}

	}

	public static int rightStep(long a, long b) {
		int ans = 0;
		while (b > a) {
			if (b / 2 < a) {
				// once the next number is smaller, the greatest number has ben found
				break;
			}else {
				if (b %2 == 1) {
					ans++;
					// subtract since the inverse operation is used
					b--;
				}
				ans++;
				b /= 2;
			}
		}
		// difference is steps
		ans += (b - a);		
		return ans;
	}
}
