package Brown;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=1084
import java.io.*;
public class EvenMoreOdd {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] arr = br.readLine().split(" ");
		int odd = 0;
		int even = 0;
		for(String a: arr) {
			if(Integer.parseInt(a)%2==0) {
				even++;
			}else {
				odd++;
			}
		}
		
		int result = 0;
		if(even == odd) {
			result = n;
		}else if (even > odd) {
			result = 2 * odd + 1;
		}else {
			int dif = odd - even;
			result = 2 * even;
			if (dif % 3 == 0) {
				result += 2 * (dif / 3);
			}else if (dif % 3 == 1) {
				result += 2 * (dif / 3) - 1;
			}else {
				result += 2 * (dif / 3) + 1;
			}
			
		}
		System.out.println(result);
	}

}
