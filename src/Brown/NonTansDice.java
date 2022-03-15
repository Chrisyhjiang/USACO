package Brown;

import java.io.*;

public class NonTansDice {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());  
		for(int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			int[] d1 = new int[4];
			int[] d2 = new int[4];
			int[] d3 = new int[4];
			for(int j = 0; j < 4; j++) {
				d1[j] = Integer.parseInt(line[j]);
			}
			for(int j = 4; j < 8; j++) {
				d2[j%4] = Integer.parseInt(line[j]);
			}
			String ans = "no";
			out: // break outerloop code
			for(int w = 1; w <= 10; w++) {
				for (int x = 1; x <= 10; x++) {
					for (int y = 1; y <= 10; y++) {
						for (int z = 1; z <= 10; z++) {
							d3[0] = w;
							d3[1] = x;
							d3[2] = y;
							d3[3] = z;
							
							boolean b1 = beat(d1,d2);
							boolean b2 = beat(d2,d3); 
							boolean b3 = beat(d3,d1); 
							// have to call both forward and backward
							boolean b4 = beat(d2,d1);
							boolean b5 = beat(d3,d2); 
							boolean b6 = beat(d1,d3);
							if((b1 && b2 && b3)||(b4 && b5 && b6)){
								ans = "yes";
								break out;
							}
						}
					}
				}
			}
			System.out.println(ans);
			
		}
	}
	
	public static boolean beat(int[] a, int[] b) {
		int win = 0;
		int loss = 0;
		for(int m: a) {
			for(int n: b) {
				if(m > n) {
					win++;
				}else if (m < n) {
					loss++;
				}
			}
		}
		return win > loss;
	}

}
