package Brown;

import java.io.*;
public class MadScientist {

	public static void main(String[] args) throws NumberFormatException, IOException {
		File file = new File("breedflip.in");
		BufferedReader br = new BufferedReader(new FileReader(file));
		int n = Integer.parseInt(br.readLine());
		char[] ogCows = br.readLine().toCharArray();
		char[] newCows = br.readLine().toCharArray();
		
		int turns = 0;
		
		for(int i = 0; i < n-1; i++) {
			if ((ogCows[i]!=newCows[i]) && (ogCows[i+1]==newCows[i+1])) {
				turns ++;
			}
		}
		
		if(ogCows[n-1]!=newCows[n-1]) {
			turns++;
		}
		
		System.out.println(turns);
		File out = new File("breedflip.out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(out)); 
		bw.write(Integer.toString(turns));
		br.close();
		bw.close();
	}

}
