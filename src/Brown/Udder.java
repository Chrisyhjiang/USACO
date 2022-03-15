package Brown;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=1083
import java.io.*;
import java.util.*;
public class Udder {
	static char[] line = null;
	static char[] cowphabet = null;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cowphabet = (br.readLine()).toCharArray();
		// hastable to cache indices of the cowphabet. 
		Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();
		for(int i = 0; i < cowphabet.length; i++) {
			ht.put(cowphabet[i], i);
		}
		// addition of @ to pad for the last case
		ht.put('@', -1);
		line = (br.readLine()+"@").toCharArray();
		int count = 0;
		// compares the adjacent characters
		//only when the current letter is greater than the next letter should coutn increase by 1 since it cannot fit
		for(int i = 0; i < line.length-1; i++) {
			if(ht.get(line[i]) >= ht.get(line[i+1])) {
				count++;
			}
		}

		System.out.println(count);		
		
	}

}
