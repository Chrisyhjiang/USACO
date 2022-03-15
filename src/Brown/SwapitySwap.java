package Brown;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=1013
import java.io.*;
public class SwapitySwap {
	// repeatedly swap the array and find out when the array becomes the original once more. 
	// use modulus to reduce runtime. 
	static int[] original;
	public static void main(String[] args) throws NumberFormatException, IOException {
//		String path = "/Users/christopherjiang/Desktop/swap_bronze_feb20/";
		File file = new File("swap.in");
		BufferedReader br = new BufferedReader(new FileReader(file));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int k = Integer.parseInt(line[1]);
		String[] line1 = br.readLine().split(" ");
		String[] line2 = br.readLine().split(" ");
		
		int fs = Integer.parseInt(line1[0])-1;
		int fe = Integer.parseInt(line1[1])-1;
		int ss = Integer.parseInt(line2[0])-1;
		int se = Integer.parseInt(line2[1])-1;
		
		original = new int[n];
		for(int i = 0; i < n; i++) {
			original[i] = i+1;
		}
		int repeat = -1;
		// fin a pattern for the array to repeat and become the same. Once that is found, modulus to cut run time. 
		for (int i = 1; i <= k; i++) {
			reverse(fs,fe);
			reverse(ss,se);
			if (i != k) {
				boolean isSame = same(original);
				if(isSame) {
					repeat = i;
					break;
				}
			}
		}
		if (repeat != -1) {
			for(int i = 0; i < k%repeat; i++) {
				reverse(fs,fe);
				reverse(ss,se);
			}
		}	
		File out = new File("swap.out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(out)); 
		String s = "";
		for(int a: original) {
			s += a + "\n";
		}
		bw.write(s);
		br.close();
		bw.close();
	}
	
	public static boolean same(int[] arr) {
		boolean b = true;
		for(int i = 0; i < arr.length; i++) {
			if (original[i]!=i+1) {
				b = false;
				break;
			}
		}
		return b;
	}
	
	public static void reverse(int start, int end) {
		// reverse function can using two pointers
		while (start < end) {
			int temp = original[start];
			original[start] = original[end];
			original[end] = temp;
			start++;
			end--;
		}
	}
}
