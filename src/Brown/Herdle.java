package Brown;

import java.io.*;
   
public class Herdle {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[][] answer = new String[3][3];
		String[][] guess = new String[3][3];
		int[] correctA = new int[26];
		int[] guessA = new int[26];
		for(int i = 0; i < 3; i++) {
			String s = br.readLine();
			for(int j = 0; j < s.length(); j++) {
				answer[i][j] = s.substring(j,j+1);
			}
		}
		for(int i = 0; i < 3; i++) {
			String s = br.readLine();
			for(int j = 0; j < s.length(); j++) {
				guess[i][j] = s.substring(j,j+1);
			}
		}	
		int g = 0;
		int y = 0;
		
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				String target = answer[i][j];
				String gue = guess[i][j];
				if(gue.equals(target)) {
					g++;
				}else {
					correctA[target.charAt(0) - 65]++;
					guessA[gue.charAt(0) - 65]++;
				}
			}
		}
		
		for(int i = 0; i < 26; i++) {
			int m = correctA[i];
			int n = guessA[i];
			if ((m > 0) && (n > 0)) {
				y += Math.min(m, n);
			}
		}
		System.out.println(g);
		System.out.println(y);
	}
}
